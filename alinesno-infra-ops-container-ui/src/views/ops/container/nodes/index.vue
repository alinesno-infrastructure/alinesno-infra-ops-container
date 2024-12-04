<template>
   <div class="app-container">
      <el-row :gutter="20">
         <!-- 节点数据 -->
         <el-col :span="24" :xs="24">
            <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="100px">
               <el-form-item label="节点名称" prop="name">
                  <el-input v-model="queryParams.name" placeholder="请输入节点名称" clearable style="width: 240px" @keyup.enter="handleQuery" />
               </el-form-item>
               <el-form-item label="主机名" prop="hostName">
                  <el-input v-model="queryParams['condition[hostName|like]']" placeholder="请输入主机名" clearable style="width: 240px" @keyup.enter="handleQuery" />
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

            <el-table v-loading="loading" :data="NodeList" @selection-change="handleSelectionChange">
               <el-table-column type="selection" width="50" align="center" />

               <!-- 业务字段-->
               <el-table-column label="名称" align="left" key="name" prop="name" v-if="columns[0].visible" class="name-column">
                  <template #default="scope">
                     <div class="table-cell">
                        <span class="icon-container">
                           <i class="fa-solid fa-server"></i>
                        </span>
                        <div class="text-container">
                           {{ scope.row.name }}
                           <div v-copyText="scope.row.promptId">
                              运行时: {{ scope.row.containerRuntimeVersion }} 
                           </div>
                        </div>
                     </div>
                  </template>
               </el-table-column>
               <el-table-column label="角色" align="center" key="roles" prop="roles" v-if="columns[1].visible" :show-overflow-tooltip="true" />
               <el-table-column label="主机名" align="left" key="hostName" prop="hostName" v-if="columns[2].visible">
                  <template #default="scope">
                     <div>
                        {{ scope.row.hostName}}
                     </div>
                     <div style="font-size: 13px;color: #a5a5a5;cursor: pointer;" v-copyText="scope.row.promptId">
                        内部IP {{ scope.row.internalIp}} <el-icon><CopyDocument /></el-icon>
                     </div>
                  </template>
               </el-table-column>
               <el-table-column label="Kubelet版本" align="left" key="kubeletVersion" prop="kubeletVersion" v-if="columns[2].visible">
                  <template #default="scope">
                     <div>
                        {{ scope.row.kubeletVersion }}
                     </div>
                     <div style="font-size: 13px;color: #a5a5a5;cursor: pointer;" v-copyText="scope.row.promptId">
                        运行时长:{{ scope.row.age}}
                     </div>
                  </template>
               </el-table-column>
               <el-table-column label="操作系统镜像" align="left" key="osImage" prop="osImage" v-if="columns[1].visible">
                  <template #default="scope">
                     <div>
                        {{ scope.row.osImage}}
                     </div>
                     <div style="font-size: 13px;color: #a5a5a5;cursor: pointer;" v-copyText="scope.row.promptId">
                        内核: {{ scope.row.oskernelVersion}} 
                     </div>
                  </template>
               </el-table-column>

               <!-- 操作字段  -->
               <el-table-column label="操作" align="center" width="200" class-name="small-padding fixed-width">
                  <template #default="scope">
                     <el-tooltip content="修改" placement="top" v-if="scope.row.id !== 1">
                        <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
                           v-hasPermi="['system:Node:edit']">修改</el-button>
                     </el-tooltip>
                     <el-tooltip content="删除" placement="top" v-if="scope.row.id !== 1">
                        <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
                           v-hasPermi="['system:Node:remove']">删除</el-button>
                     </el-tooltip>
                  </template>
               </el-table-column>
            </el-table>
            <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
         </el-col>
      </el-row>

      <!-- 添加或修改节点信息对话框 -->
      <el-dialog :title="title" v-model="open" width="900px" append-to-body>
         <el-form :model="form" :rules="rules" ref="nodeRef" label-width="100px">
            <el-row>
               <el-col :span="24">
                  <el-form-item label="名称" prop="name">
                     <el-input v-model="form.name" placeholder="请输入节点名称" maxlength="50" />
                  </el-form-item>
               </el-col>
               <el-col :span="24">
                  <el-form-item label="角色" prop="roles">
                     <el-input v-model="form.roles" placeholder="请输入节点角色" maxlength="50" />
                  </el-form-item>
               </el-col>
               <el-col :span="24">
                  <el-form-item label="主机名" prop="hostName">
                     <el-input v-model="form.hostName" placeholder="请输入主机名" maxlength="50" />
                  </el-form-item>
               </el-col>
               <el-col :span="24">
                  <el-form-item label="内部IP" prop="internalIp">
                     <el-input v-model="form.internalIp" placeholder="请输入内部IP地址" maxlength="50" />
                  </el-form-item>
               </el-col>
               <el-col :span="24">
                  <el-form-item label="容器运行时版本" prop="containerRuntimeVersion">
                     <el-input v-model="form.containerRuntimeVersion" placeholder="请输入容器运行时版本" maxlength="50" />
                  </el-form-item>
               </el-col>
               <el-col :span="24">
                  <el-form-item label="年龄/存在时间" prop="age">
                     <el-input v-model="form.age" placeholder="请输入节点年龄/存在时间" maxlength="50" />
                  </el-form-item>
               </el-col>
               <el-col :span="24">
                  <el-form-item label="Kubelet版本" prop="kubeletVersion">
                     <el-input v-model="form.kubeletVersion" placeholder="请输入Kubelet版本" maxlength="50" />
                  </el-form-item>
               </el-col>
               <el-col :span="24">
                  <el-form-item label="操作系统镜像" prop="osImage">
                     <el-input v-model="form.osImage" placeholder="请输入操作系统镜像" maxlength="100" />
                  </el-form-item>
               </el-col>
               <el-col :span="24">
                  <el-form-item label="内核版本" prop="oskernelVersion">
                     <el-input v-model="form.oskernelVersion" placeholder="请输入内核版本" maxlength="50" />
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

<script setup name="Node">

import {
   listNode,
   delNode,
   getNode,
   updateNode,
   addNode
} from "@/api/ops/container/node";

const router = useRouter();
const { proxy } = getCurrentInstance();

// 定义变量
const NodeList = ref([]);
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
      pageSize: 100,
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
   listNode(proxy.addDateRange(queryParams.value, dateRange.value)).then(res => {
      loading.value = false;
      NodeList.value = res.rows;
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
   const NodeIds = row.id || ids.value;
   proxy.$modal.confirm('是否确认删除应用编号为"' + NodeIds + '"的数据项？').then(function () {
      return delNode(NodeIds);
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
      NodeName: undefined,
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
   const NodeId = row.id || ids.value;
   getNode(NodeId).then(response => {
      form.value = response.data;
      open.value = true;
      title.value = "修改应用";
   });
};

/** 提交按钮 */
function submitForm() {
   proxy.$refs["databaseRef"].validate(valid => {
      if (valid) {
         if (form.value.NodeId != undefined) {
            updateNode(form.value).then(response => {
               proxy.$modal.msgSuccess("修改成功");
               open.value = false;
               getList();
            });
         } else {
            addNode(form.value).then(response => {
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

<!-- <style lang="scss" scoped>

// 定义一些可复用的变量
$primary-color: #409EFF;
$text-secondary-color: #a5a5a5;
$icon-font-size: 40px;
$text-small-font-size: 13px;

// 定义基础样式
.table-cell {
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 10px; // 使用gap属性代替margin或padding来创建间距

  .icon-container {
    font-size: $icon-font-size;
    color: $primary-color;
    opacity: 0.7;
    i {
      // 如果需要针对图标有更多样式调整，可以在这里添加
    }
  }

  .text-container {
    & > div {
      font-size: $text-small-font-size;
      color: $text-secondary-color;
      cursor: pointer;
    }
  }
}

</style> -->