<template>
    <div>
        <div class="deployment-workload" v-for="item in podList" style="width: 16%;float:left; margin:5px; background: #f5f5f5;padding: 10px;border-radius: 3px;">
            <div class="title"> 
                    {{ item.name }} 
            </div>
            <div style="padding-top: 5px;">
                <div class="el-row" style="font-size:13px;margin: 5px 0px;">
                    <div class="label el-col el-col-10">副本: <span style="font-weight: 800; color: green;">1</span>
                    </div>
                    <div class="label el-col el-col-14">IP: {{ item.ipAddress }}</div>
                </div>
                <div class="text_time" style="font-size:13px;margin-top:10px;margin-bottom:10px;">
                    <span> {{ item.phase }}:{{ item.age }}</span>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>

import {
   listPod
} from "@/api/ops/container/pod";

const { proxy } = getCurrentInstance();

const dateRange = ref([]);
const podList = ref([]);
const loading = ref(false);

const data = reactive({
   form: {},
   queryParams: {
      pageNum: 1,
      pageSize: 500,
      name: undefined,
      dbDesc: undefined
   }
});

const { queryParams } = toRefs(data);

/** 查询应用列表 */
function getList() {
   loading.value = true;
   listPod(proxy.addDateRange(queryParams.value, dateRange.value)).then(res => {
      loading.value = false;
      podList.value = res.rows;
   });
};

getList();

</script>

<style scoped lang="scss">
  .title {
    font-size: 16px;
    font-weight: bold;
    line-height: 20px;
    margin-top: 5px;
    color: #3b5998 ;
    margin-bottom: 5px;
    overflow: hidden;
  }
</style>
