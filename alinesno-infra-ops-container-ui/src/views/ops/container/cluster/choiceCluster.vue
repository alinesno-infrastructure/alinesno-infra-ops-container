<template>
   <div>
      <el-row :gutter="20">
         <!--应用数据-->
         <el-col :span="24" :xs="24">
            <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="100px">
               <el-form-item label="应用名称" prop="clusterName">
                  <el-input v-model="queryParams.clusterName" placeholder="请输入应用名称" clearable style="width: 240px" @keyup.enter="handleQuery" />
               </el-form-item>
               <el-form-item>
                  <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
               </el-form-item>
            </el-form>

            <el-table v-loading="loading" :data="ClusterList" @selection-change="handleSelectionChange">
               <el-table-column type="selection" width="50" align="center" />

               <!-- 业务字段-->
               <el-table-column label="应用名称" align="left" width="200" key="clusterName" prop="clusterName" v-if="columns[0].visible">
                  <template #default="scope">
                     <i :class="scope.row.icon" ></i> {{  scope.row.clusterName }}
                  </template>
               </el-table-column>
               <el-table-column label="类型" align="center" width="100" key="configType" prop="configType" v-if="columns[3].visible" :show-overflow-tooltip="true" />
               <el-table-column label="应用地址" align="left" key="apiServerUrl" prop="apiServerUrl" v-if="columns[4].visible" />

               <el-table-column label="添加时间" align="center" prop="addTime" v-if="columns[6].visible" width="160">
                  <template #default="scope">
                     <span>{{ parseTime(scope.row.addTime) }}</span>
                  </template>
               </el-table-column>

               <!-- 操作字段  -->
               <el-table-column label="操作" align="center" width="100" class-name="small-padding fixed-width">
                  <template #default="scope">
                     <el-button
                        type="primary"
                        bg 
                        link
                        @click="handleChoiceCluster(scope.row)"
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

<script setup name="Cluster">

import {
   listCluster,
   choiceCluster,
   updateCluster,
   addCluster
} from "@/api/ops/container/cluster";

const { proxy } = getCurrentInstance();

// 定义变量
const ClusterList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const dateRange = ref([]);

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
      pageSize: 10,
      clusterName: undefined,
      dbDesc: undefined
   },
   rules: {
      clusterName: [{ required: true, message: "名称不能为空", trigger: "blur" }] , 
      configType: [{ required: true, message: "配置类型不能为空", trigger: "blur" }] , 
      apiServerUrl: [{ required: true, message: "连接不能为空", trigger: "blur" }],
      kubeConfig: [{ required: true, message: "集群配置不能为空", trigger: "blur" }] , 
   }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询应用列表 */
function getList() {
   loading.value = true;
   listCluster(proxy.addDateRange(queryParams.value, dateRange.value)).then(res => {
      loading.value = false;
      ClusterList.value = res.rows;
      total.value = res.total;
   });
};

/** 选择项目 */
function handleChoiceCluster(row){
  choiceCluster(row.id).then(res => {
    location.reload();
  })
}

/** 搜索按钮操作 */
function handleQuery() {
   queryParams.value.pageNum = 1;
   getList();
};

/** 选择条数  */
function handleSelectionChange(selection) {
   ids.value = selection.map(item => item.id);
   single.value = selection.length != 1;
   multiple.value = !selection.length;
};

/** 提交按钮 */
function submitForm() {
   proxy.$refs["databaseRef"].validate(valid => {
      if (valid) {
         if (form.value.ClusterId != undefined) {
            updateCluster(form.value).then(response => {
               proxy.$modal.msgSuccess("修改成功");
               open.value = false;
               getList();
            });
         } else {
            addCluster(form.value).then(response => {
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
