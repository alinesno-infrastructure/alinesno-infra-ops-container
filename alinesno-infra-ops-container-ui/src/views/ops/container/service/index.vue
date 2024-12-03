<template>
   <div class="app-container">
      <el-row :gutter="20">
         <!--应用数据-->
         <el-col :span="24" :xs="24">
            <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="100px">
               <el-form-item label="应用名称" prop="name">
                  <el-input v-model="queryParams.name" placeholder="请输入应用名称" clearable style="width: 240px" @keyup.enter="handleQuery" />
               </el-form-item>
               <el-form-item label="应用名称" prop="name">
                  <el-input v-model="queryParams['condition[name|like]']" placeholder="请输入应用名称" clearable style="width: 240px" @keyup.enter="handleQuery" />
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

            <el-table v-loading="loading" :data="SvcList" @selection-change="handleSelectionChange">
               <el-table-column type="selection" width="50" align="center" />

               <!-- 业务字段-->
               <el-table-column label="名称" align="left" key="name" prop="name" v-if="columns[0].visible" />
               <el-table-column label="类型" align="center" key="type" prop="type" width="100" v-if="columns[0].visible" />
               <el-table-column label="ClusterIP" align="center" width="200" key="clusterIP" prop="clusterIP" v-if="columns[2].visible" :show-overflow-tooltip="true" />
               <el-table-column label="端口" align="center" key="dbType" width="300" prop="dbType" v-if="columns[3].visible" :show-overflow-tooltip="true">
                  <template #default="scope">
                     <div style="display: flex;flex-direction: column-reverse;gap: 5px;flex-wrap: wrap;align-content: space-around;">
                        <el-button-group class="ml-4" v-for="portItem in scope.row.ports">
                           <el-button bg size="small" type="primary" v-if="portItem.port" round>{{ portItem.port }}</el-button>
                           <el-button bg size="small" type="success" v-if="portItem.nodePort" round>{{ portItem.nodePort}}</el-button>
                           <el-button bg size="small" type="warning" round>{{ portItem.protocol }}</el-button>
                        </el-button-group>
                     </div>
                  </template>
               </el-table-column>
               <el-table-column label="创建时间" align="center" key="age" prop="age" v-if="columns[4].visible" width="120" />

               <!-- 操作字段  -->
               <el-table-column label="操作" align="center" width="300" class-name="small-padding fixed-width">
                  <template #default="scope">
                     <el-tooltip content="查看" placement="top" v-if="scope.row.SvcId !== 1">
                        <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['system:Svc:edit']">查看</el-button>
                     </el-tooltip>
                     <el-tooltip content="YAML" placement="top" v-if="scope.row.SvcId !== 1">
                        <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
                           v-hasPermi="['system:Svc:edit']">YAML</el-button>
                     </el-tooltip>
                     <el-tooltip content="修改" placement="top" v-if="scope.row.SvcId !== 1">
                        <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
                           v-hasPermi="['system:Svc:edit']">修改</el-button>
                     </el-tooltip>
                     <el-tooltip content="删除" placement="top" v-if="scope.row.SvcId !== 1">
                        <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
                           v-hasPermi="['system:Svc:remove']">删除</el-button>
                     </el-tooltip>
                  </template>

               </el-table-column>
            </el-table>
            <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
         </el-col>
      </el-row>

      <!-- 添加或修改应用配置对话框 -->
      <el-dialog :title="title" v-model="open" width="900px" append-to-body>
         <el-form :model="form" :rules="rules" ref="databaseRef" label-width="100px">
            <el-row>
               <el-col :span="24">
                  <el-form-item label="名称" prop="name">
                     <el-input v-model="form.name" placeholder="请输入应用名称" maxlength="50" />
                  </el-form-item>
               </el-col>
            </el-row>
            <el-row>
               <el-col :span="24">
                  <el-form-item label="连接" prop="age">
                     <el-input v-model="form.age" placeholder="请输入age连接地址" maxlength="128" />
                  </el-form-item>
               </el-col>
               <el-col :span="24">
                  <el-form-item label="类型" prop="dbType">
                     <el-input v-model="form.dbType" placeholder="请输入类型" maxlength="50" />
                  </el-form-item>
               </el-col>
            </el-row>
            <el-row>
               <el-col :span="24">
                  <el-form-item label="用户名" prop="dbUsername">
                     <el-input v-model="form.dbUsername" placeholder="请输入连接用户名" maxlength="30" />
                  </el-form-item>
               </el-col>
               <el-col :span="24">
                  <el-form-item label="密码" prop="dbPasswd">
                     <el-input v-model="form.dbPasswd" placeholder="请输入应用密码" type="password" maxlength="30" show-password />
                  </el-form-item>
               </el-col>
            </el-row>

            <el-row>
               <el-col :span="24">
                  <el-form-item label="备注" prop="dbDesc">
                     <el-input v-model="form.dbDesc" placeholder="请输入应用备注"></el-input>
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

<script setup name="Svc">

import {
   listSvc,
   delSvc,
   getSvc,
   updateSvc,
   addSvc
} from "@/api/ops/container/svc";

const router = useRouter();
const { proxy } = getCurrentInstance();

// 定义变量
const SvcList = ref([]);
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
   { key: 6, label: `更新时间`, visible: true }
]);

const data = reactive({
   form: {},
   queryParams: {
      pageNum: 1,
      pageSize: 10,
      name: undefined,
      dbDesc: undefined
   },
   rules: {
      name: [{ required: true, message: "名称不能为空", trigger: "blur" }] , 
      age: [{ required: true, message: "连接不能为空", trigger: "blur" }],
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
   listSvc(proxy.addDateRange(queryParams.value, dateRange.value)).then(res => {
      loading.value = false;
      SvcList.value = res.rows;
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
   const SvcIds = row.id || ids.value;
   proxy.$modal.confirm('是否确认删除应用编号为"' + SvcIds + '"的数据项？').then(function () {
      return delSvc(SvcIds);
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
      SvcName: undefined,
      clusterIP: undefined,
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
   const SvcId = row.id || ids.value;
   getSvc(SvcId).then(response => {
      form.value = response.data;
      open.value = true;
      title.value = "修改应用";
   });
};

/** 提交按钮 */
function submitForm() {
   proxy.$refs["databaseRef"].validate(valid => {
      if (valid) {
         if (form.value.SvcId != undefined) {
            updateSvc(form.value).then(response => {
               proxy.$modal.msgSuccess("修改成功");
               open.value = false;
               getList();
            });
         } else {
            addSvc(form.value).then(response => {
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
