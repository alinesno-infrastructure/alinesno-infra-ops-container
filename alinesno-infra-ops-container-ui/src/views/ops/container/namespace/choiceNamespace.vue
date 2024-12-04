<template>
   <div >
      <el-row :gutter="20">
         <!--应用数据-->
         <el-col :span="24" :xs="24">
            <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="100px">
               <el-form-item label="应用名称" prop="name">
                  <el-input v-model="queryParams.name" placeholder="请输入应用名称" clearable style="width: 240px" @keyup.enter="handleQuery" />
               </el-form-item>
               <el-form-item>
                  <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
                  <el-button icon="Refresh" @click="resetQuery">重置</el-button>
               </el-form-item>
            </el-form>

            <el-table v-loading="loading" :data="NamespaceList" @selection-change="handleSelectionChange">
               <el-table-column type="selection" width="50" align="center" />

               <el-table-column label="名称" align="left" key="name" prop="name" v-if="columns[0].visible" class="name-column">
                  <template #default="scope">
                     <i class="fa-solid fa-feather"></i>
                     {{ scope.row.name}}
                  </template>
               </el-table-column>
               <el-table-column label="是否进入" align="center" key="apiServerUrl" prop="apiServerUrl" v-if="columns[4].visible">
                  <template #default="scope">
                     <el-button type="primary" text icon="Paperclip" @click="configPrompt(scope.row)">可以访问</el-button>
                  </template>
               </el-table-column>
               <el-table-column label="状态" align="center" key="hasStatus" v-if="columns[5].visible">
                  <template #default="scope">
                     <el-button type="primary" text bg icon="Paperclip" @click="configPrompt(scope.row)">{{ scope.row.phase }}</el-button>
                  </template>
               </el-table-column>

               <!-- 操作字段  -->
               <el-table-column label="操作" align="center" width="100" class-name="small-padding fixed-width">
                  <template #default="scope">
                     <el-button
                        type="primary"
                        bg 
                        link
                        @click="handleChoiceNamespace(scope.row)"
                        v-hasPermi="['system:menu:edit']">
                        <i class="fa-solid fa-pen-nib"></i> 选择
                     </el-button>
                  </template>

               </el-table-column>
            </el-table>
            <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
         </el-col>
      </el-row>

   </div>
</template>

<script setup name="Namespace">

import {
   listNamespace,
   delNamespace,
   getNamespace,
   updateNamespace,
   addNamespace
} from "@/api/ops/container/namespace";

import {
   choiceNamespace
} from "@/api/ops/container/cluster";

const router = useRouter();
const { proxy } = getCurrentInstance();

// 定义变量
const NamespaceList = ref([]);
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

const k8sConfigType = ref([
  { key: 'token', name: '令牌' },
  { key: 'config', name: '配置' }
]);

const icons = ref([
  { id: 1, icon: 'fa-solid fa-charging-station'} ,
  { id: 1, icon: 'fa-solid fa-truck'} ,
  { id: 2, icon: 'fa-solid fa-paper-plane'} ,
  { id: 2, icon: 'fa-solid fa-ship'} ,
  { id: 3, icon: 'fa-solid fa-chart-column'},
  { id: 4, icon: 'fa-solid fa-server'}, 
  { id: 5, icon: 'fa-solid fa-box-open'}, 
  { id: 8, icon: 'fa-solid fa-file-invoice-dollar'}, 
  { id: 9, icon: 'fa-solid fa-user-tie'},
]);

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
      pageSize: 200,
      name: undefined,
      dbDesc: undefined
   },
   rules: {
      name: [{ required: true, message: "名称不能为空", trigger: "blur" }] , 
      configType: [{ required: true, message: "配置类型不能为空", trigger: "blur" }] , 
      apiServerUrl: [{ required: true, message: "连接不能为空", trigger: "blur" }],
      kubeConfig: [{ required: true, message: "集群配置不能为空", trigger: "blur" }] , 
   }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询应用列表 */
function getList() {
   loading.value = true;
   listNamespace(proxy.addDateRange(queryParams.value, dateRange.value)).then(res => {
      loading.value = false;
      NamespaceList.value = res.rows;
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
   const NamespaceIds = row.id || ids.value;
   proxy.$modal.confirm('是否确认删除应用编号为"' + NamespaceIds + '"的数据项？').then(function () {
      return delNamespace(NamespaceIds);
   }).then(() => {
      getList();
      proxy.$modal.msgSuccess("删除成功");
   }).catch(() => { });
};


/** 选择项目 */
function handleChoiceNamespace(row){
  choiceNamespace(row.name).then(res => {
    location.reload();
  })
}

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
      NamespaceName: undefined,
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
   const NamespaceId = row.id || ids.value;
   getNamespace(NamespaceId).then(response => {
      form.value = response.data;
      open.value = true;
      title.value = "修改应用";
   });
};

/** 提交按钮 */
function submitForm() {
   proxy.$refs["databaseRef"].validate(valid => {
      if (valid) {
         if (form.value.NamespaceId != undefined) {
            updateNamespace(form.value).then(response => {
               proxy.$modal.msgSuccess("修改成功");
               open.value = false;
               getList();
            });
         } else {
            addNamespace(form.value).then(response => {
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
