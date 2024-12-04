<template>
   <div class="app-container">
      <el-row :gutter="20">
         <!-- 持久卷数据 -->
         <el-col :span="24" :xs="24">
            <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="100px">
               <el-form-item label="PV名称" prop="name">
                  <el-input v-model="queryParams.name" placeholder="请输入PV名称" clearable style="width: 240px" @keyup.enter="handleQuery" />
               </el-form-item>
               <el-form-item>
                  <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
                  <el-button icon="Refresh" @click="resetQuery">重置</el-button>
               </el-form-item>
            </el-form>

            <el-row :gutter="10" class="mb8">
               <el-col :span="1.5">
                  <el-button type="primary" plain icon="Plus" @click="handleAdd">新增</el-button>
               </el-col>
               <el-col :span="1.5">
                  <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate">修改</el-button>
               </el-col>
               <el-col :span="1.5">
                  <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete">删除</el-button>
               </el-col>
               <right-toolbar v-model:showSearch="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
            </el-row>

            <el-table v-loading="loading" :data="pvList" @selection-change="handleSelectionChange">
               <el-table-column type="selection" width="50" align="center" />
               
               <!-- 业务字段 -->
               <el-table-column label="名称" align="center" key="name" prop="name" v-if="columns[0].visible" />
               <el-table-column label="状态" align="center" key="status" prop="status" v-if="columns[1].visible" />
               <el-table-column label="容量" align="center" key="capacity" prop="capacity" v-if="columns[2].visible" />
               <el-table-column label="访问模式" align="center" key="accessModes" prop="accessModes" v-if="columns[3].visible" />
               <el-table-column label="回收策略" align="center" key="reclaimPolicy" prop="reclaimPolicy" v-if="columns[4].visible" />
               <el-table-column label="创建时间" align="center" prop="creationTimestamp" v-if="columns[5].visible" width="160">
                  <template #default="scope">
                     <span>{{ parseTime(scope.row.creationTimestamp) }}</span>
                  </template>
               </el-table-column>
               <el-table-column label="存活时间" align="center" prop="age" v-if="columns[6].visible" />
               <el-table-column label="标签" align="center" key="labels" prop="labels" v-if="columns[7].visible" :show-overflow-tooltip="true" />

               <!-- 操作字段 -->
               <el-table-column label="操作" align="center" width="150" class-name="small-padding fixed-width">
                  <template #default="scope">
                     <el-tooltip content="修改" placement="top" v-if="scope.row.PvId !== 1">
                        <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
                           v-hasPermi="['system:Pv:edit']"></el-button>
                     </el-tooltip>
                     <el-tooltip content="删除" placement="top" v-if="scope.row.PvId !== 1">
                        <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
                           v-hasPermi="['system:Pv:remove']"></el-button>
                     </el-tooltip>
                  </template>
               </el-table-column>
            </el-table>
            <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
         </el-col>
      </el-row>

      <!-- 添加或修改持久卷配置对话框 -->
      <el-dialog :title="title" v-model="open" width="900px" append-to-body>
         <el-form :model="form" :rules="rules" ref="databaseRef" label-width="100px">
            <el-row>
               <el-col :span="24">
                  <el-form-item label="名称" prop="name">
                     <el-input v-model="form.name" placeholder="请输入PV名称" maxlength="50" />
                  </el-form-item>
               </el-col>
            </el-row>
            <el-row>
               <el-col :span="24">
                  <el-form-item label="状态" prop="status">
                     <el-input v-model="form.status" placeholder="请输入状态" maxlength="50" />
                  </el-form-item>
               </el-col>
               <el-col :span="24">
                  <el-form-item label="容量" prop="capacity">
                     <el-input v-model="form.capacity" placeholder="请输入容量" maxlength="50" />
                  </el-form-item>
               </el-col>
            </el-row>
            <el-row>
               <el-col :span="24">
                  <el-form-item label="访问模式" prop="accessModes">
                     <el-input v-model="form.accessModes" placeholder="请输入访问模式" maxlength="50" />
                  </el-form-item>
               </el-col>
               <el-col :span="24">
                  <el-form-item label="回收策略" prop="reclaimPolicy">
                     <el-input v-model="form.reclaimPolicy" placeholder="请输入回收策略" maxlength="50" />
                  </el-form-item>
               </el-col>
            </el-row>
            <el-row>
               <el-col :span="24">
                  <el-form-item label="标签" prop="labels">
                     <el-input v-model="form.labels" placeholder="请输入标签"></el-input>
                  </el-form-item>
               </el-col>
            </el-row>
         </el-form>
         <template #footer>
            <div class="dialog-footer">
               <el-button type="primary" @click="submitForm">确 定</el-button>
               <el-button @click="cancel">取 消</el-button>
            </div>
         </template>
      </el-dialog>
   </div>
</template>

<script setup name="Pv">

import {
   listPv,
   delPv,
   getPv,
   updatePv,
   addPv
} from "@/api/ops/container/pv";

const router = useRouter();
const { proxy } = getCurrentInstance();

// 定义变量
const pvList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const dateRange = ref([]);
const postOptions = ref([]);
const roleOptions = ref([]);

// 列显隐信息
const columns = ref([
   { key: 0, label: `应用名称`, visible: true },
   { key: 1, label: `应用描述`, visible: true },
   { key: 2, label: `表数据量`, visible: true },
   { key: 3, label: `类型`, visible: true },
   { key: 4, label: `应用地址`, visible: true },
   { key: 5, label: `状态`, visible: true },
   { key: 6, label: `更新时间`, visible: true },
   { key: 7, label: `更新时间`, visible: true }
]);

const data = reactive({
   form: {},
   queryParams: {
      pageNum: 1,
      pageSize: 10,
      dbName: undefined,
      dbDesc: undefined
   },
   rules: {
      dbName: [{ required: true, message: "名称不能为空", trigger: "blur" }] , 
      jdbcUrl: [{ required: true, message: "连接不能为空", trigger: "blur" }],
      dbType: [{ required: true, message: "类型不能为空", trigger: "blur" }] , 
      dbUsername: [{ required: true , message: "用户名不能为空", trigger: "blur"}],
      dbPasswd: [{ required: true, message: "密码不能为空", trigger: "blur" }] , 
      dbDesc: [{ required: true, message: "备注不能为空", trigger: "blur" }] 
   }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询应用列表 */
function getList() {
   loading.value = true;
   listPv(proxy.addDateRange(queryParams.value, dateRange.value)).then(res => {
      loading.value = false;
      pvList.value = res.rows;
      total.value = res.total;
   });
};

/** 搜索按钮操作 */
function handleQuery() {
   queryParams.value.pageNum = 1;
   getList();
};

/** 重置按钮操作 */
function resetQuery() {
   dateRange.value = [];
   proxy.resetForm("queryRef");
   queryParams.value.deptId = undefined;
   proxy.$refs.deptTreeRef.setCurrentKey(null);
   handleQuery();
};
/** 删除按钮操作 */
function handleDelete(row) {
   const PvIds = row.id || ids.value;
   proxy.$modal.confirm('是否确认删除应用编号为"' + PvIds + '"的数据项？').then(function () {
      return delPv(PvIds);
   }).then(() => {
      getList();
      proxy.$modal.msgSuccess("删除成功");
   }).catch(() => { });
};

/** 选择条数  */
function handleSelectionChange(selection) {
   ids.value = selection.map(item => item.id);
   single.value = selection.length != 1;
   multiple.value = !selection.length;
};

/** 重置操作表单 */
function reset() {
   form.value = {
      id: undefined,
      deptId: undefined,
      PvName: undefined,
      nickName: undefined,
      password: undefined,
      phonenumber: undefined,
      status: "0",
      remark: undefined,
   };
   proxy.resetForm("databaseRef");
};
/** 取消按钮 */
function cancel() {
   open.value = false;
   reset();
};

/** 新增按钮操作 */
function handleAdd() {
   reset();
   open.value = true;
   title.value = "添加应用";
};

/** 修改按钮操作 */
function handleUpdate(row) {
   reset();
   const PvId = row.id || ids.value;
   getPv(PvId).then(response => {
      form.value = response.data;
      open.value = true;
      title.value = "修改应用";
   });
};

/** 提交按钮 */
function submitForm() {
   proxy.$refs["databaseRef"].validate(valid => {
      if (valid) {
         if (form.value.PvId != undefined) {
            updatePv(form.value).then(response => {
               proxy.$modal.msgSuccess("修改成功");
               open.value = false;
               getList();
            });
         } else {
            addPv(form.value).then(response => {
               proxy.$modal.msgSuccess("新增成功");
               open.value = false;
               getList();
            });
         }
      }
   });
};

getList();

</script>
