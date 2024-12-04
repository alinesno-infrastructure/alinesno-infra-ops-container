<template>
   <div class="app-container">
      <el-row :gutter="20">
         <!--应用数据-->
         <el-col :span="24" :xs="24">
            <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="100px">
               <el-form-item label="部署名称" prop="name">
                  <el-input v-model="queryParams.name" placeholder="请输入部署名称" clearable style="width: 240px" @keyup.enter="handleQuery" />
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

            <el-table v-loading="loading" :data="deploymentList" @selection-change="handleSelectionChange">
               <el-table-column type="selection" width="50" align="center" />
               <el-table-column label="部署名称" align="left" width="300" key="name" prop="name" v-if="columns[0].visible" :show-overflow-tooltip="true" />
               <el-table-column label="已就绪" align="center" key="ready" prop="ready" width="70" v-if="columns[1].visible" />
               <el-table-column label="最新版本" align="center" key="upToDate" prop="upToDate" width="100" v-if="columns[2].visible" />
               <!-- <el-table-column label="可用状态" align="center" key="available" prop="available" width="80" v-if="columns[3].visible" /> -->
               <el-table-column label="创建时间" align="center" prop="age" v-if="columns[4].visible" width="100">
                  <template #default="scope">
                     <span>{{ scope.row.age }}</span>
                  </template>
               </el-table-column>
               <el-table-column label="使用的镜像" align="left" key="images" prop="images" v-if="columns[5].visible" />

               <!-- 操作字段 -->
               <el-table-column label="操作" align="center" width="250" class-name="small-padding fixed-width">
                  <template #default="scope">
                     <el-tooltip content="修改" placement="top" v-if="scope.row.deploymentId !== 1">
                        <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
                           v-hasPermi="['system:deployment:edit']">查看</el-button>
                     </el-tooltip>
                     <el-tooltip content="修改" placement="top" v-if="scope.row.deploymentId !== 1">
                        <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
                           v-hasPermi="['system:deployment:edit']">日志/终端</el-button>
                     </el-tooltip>
                     <el-tooltip content="删除" placement="top" v-if="scope.row.deploymentId !== 1">
                        <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
                           v-hasPermi="['system:deployment:remove']">删除</el-button>
                     </el-tooltip>
                  </template>
               </el-table-column>
            </el-table>
            <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
         </el-col>
      </el-row>

      <!-- 添加或修改部署配置对话框 -->
      <el-dialog :title="title" v-model="open" width="900px" append-to-body>
         <el-form :model="form" :rules="rules" ref="deploymentRef" label-width="100px">
            <el-row>
               <el-col :span="24">
                  <el-form-item label="名称" prop="name">
                     <el-input v-model="form.name" placeholder="请输入部署名称" maxlength="50" />
                  </el-form-item>
               </el-col>
            </el-row>
            <el-row>
               <el-col :span="24">
                  <el-form-item label="已就绪Pod数量/总Pod数量" prop="ready">
                     <el-input v-model="form.ready" placeholder="请输入已就绪Pod数量/总Pod数量" maxlength="50" />
                  </el-form-item>
               </el-col>
               <el-col :span="24">
                  <el-form-item label="是否是最新的版本" prop="upToDate">
                     <el-input v-model="form.upToDate" placeholder="请输入是否是最新的版本" maxlength="50" />
                  </el-form-item>
               </el-col>
            </el-row>
            <el-row>
               <el-col :span="24">
                  <el-form-item label="可用状态" prop="available">
                     <el-input v-model="form.available" placeholder="请输入可用状态" maxlength="50" />
                  </el-form-item>
               </el-col>
               <el-col :span="24">
                  <el-form-item label="创建时间" prop="age">
                     <el-date-picker v-model="form.age" type="datetime" placeholder="选择日期时间" />
                  </el-form-item>
               </el-col>
            </el-row>
            <el-row>
               <el-col :span="24">
                  <el-form-item label="使用的镜像列表" prop="images">
                     <el-input v-model="form.images" type="textarea" placeholder="请输入使用的镜像列表, 分行输入" />
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

<script setup name="Deployment">

import {
   listDeployment,
   delDeployment,
   getDeployment,
   updateDeployment,
   addDeployment
} from "@/api/ops/container/deployment";

const router = useRouter();
const { proxy } = getCurrentInstance();

// 定义变量
const deploymentList = ref([]);
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
      pageSize: 500,
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
   listDeployment(proxy.addDateRange(queryParams.value, dateRange.value)).then(res => {
      loading.value = false;
      deploymentList.value = res.rows;
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
   const DeploymentIds = row.id || ids.value;
   proxy.$modal.confirm('是否确认删除应用编号为"' + DeploymentIds + '"的数据项？').then(function () {
      return delDeployment(DeploymentIds);
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
      DeploymentName: undefined,
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
   const DeploymentId = row.id || ids.value;
   getDeployment(DeploymentId).then(response => {
      form.value = response.data;
      open.value = true;
      title.value = "修改应用";
   });
};

/** 提交按钮 */
function submitForm() {
   proxy.$refs["databaseRef"].validate(valid => {
      if (valid) {
         if (form.value.DeploymentId != undefined) {
            updateDeployment(form.value).then(response => {
               proxy.$modal.msgSuccess("修改成功");
               open.value = false;
               getList();
            });
         } else {
            addDeployment(form.value).then(response => {
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
