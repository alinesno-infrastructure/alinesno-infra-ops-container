<template>
   <div class="app-container">
      <el-row :gutter="20">
         <!-- 事件数据 -->
         <el-col :span="24" :xs="24">
            <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="100px">
               <el-form-item label="事件类型" prop="type">
                  <el-select v-model="queryParams.type" placeholder="请选择事件类型" clearable style="width: 240px">
                     <el-option label="Normal" value="Normal"></el-option>
                     <el-option label="Warning" value="Warning"></el-option>
                  </el-select>
               </el-form-item>
               <el-form-item label="涉及对象" prop="obj">
                  <el-input v-model="queryParams.obj" placeholder="请输入涉及对象名称" clearable style="width: 240px" @keyup.enter="handleQuery" />
               </el-form-item>
               <el-form-item>
                  <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
                  <el-button icon="Refresh" @click="resetQuery">重置</el-button>
               </el-form-item>
            </el-form>

            <el-row :gutter="10" class="mb8">
               <right-toolbar v-model:showSearch="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
            </el-row>

            <el-table v-loading="loading" :data="eventList" @selection-change="handleSelectionChange">
               <el-table-column type="index" width="50" label="序号" align="center" />
               <el-table-column label="消息" align="left" key="message" prop="message" v-if="columns[6].visible" :show-overflow-tooltip="false">
                  <template #default="scope">
                     <div class="table-cell">
                        <div class="text-container">
                           {{ scope.row.message}}
                           <div style="font-size:13px;color:#555">
                              涉及对象: {{ scope.row.obj }} 
                           </div>
                        </div>
                     </div>
                  </template>
               </el-table-column>
               <el-table-column label="原因" align="center" key="reason" prop="reason" v-if="columns[3].visible" width="200" />
               <el-table-column label="来源" align="center" key="source" prop="source" v-if="columns[5].visible" width="160" />
               <el-table-column label="重复次数" align="center" key="count" prop="count" v-if="columns[1].visible" width="100" />

               <el-table-column label="事件类型" align="left" key="type" prop="type" v-if="columns[2].visible" width="200" >
                  <template #default="scope">
                     <div class="table-cell">
                        <div class="text-container">
                           {{ scope.row.type}}
                           <div style="font-size:13px;color:#555">
                              时间:<span>{{ parseTime(scope.row.time) }}</span>
                           </div>
                        </div>
                     </div>
                  </template>
               </el-table-column>

            </el-table>
            <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
         </el-col>
      </el-row>
   </div>
</template>

<script setup name="Event">

import {
   listEvent,
   delEvent,
   getEvent,
   updateEvent,
   addEvent
} from "@/api/ops/container/event";

const router = useRouter();
const { proxy } = getCurrentInstance();

// 定义变量
const eventList = ref([]);
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
   listEvent(proxy.addDateRange(queryParams.value, dateRange.value)).then(res => {
      loading.value = false;
      eventList.value = res.rows;
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
   const EventIds = row.id || ids.value;
   proxy.$modal.confirm('是否确认删除应用编号为"' + EventIds + '"的数据项？').then(function () {
      return delEvent(EventIds);
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
      EventName: undefined,
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
   const EventId = row.id || ids.value;
   getEvent(EventId).then(response => {
      form.value = response.data;
      open.value = true;
      title.value = "修改应用";
   });
};

/** 提交按钮 */
function submitForm() {
   proxy.$refs["databaseRef"].validate(valid => {
      if (valid) {
         if (form.value.EventId != undefined) {
            updateEvent(form.value).then(response => {
               proxy.$modal.msgSuccess("修改成功");
               open.value = false;
               getList();
            });
         } else {
            addEvent(form.value).then(response => {
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
