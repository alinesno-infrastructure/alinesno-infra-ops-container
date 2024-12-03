<template>
  <div>
    <div class="header-logo-bar" to="/index">
      <div class="header-logo" v-if="enableLogo" @click="enterDomain">
        <img :src="saasLogoUrl" alt="" />
      </div>
      <a :title="saasTitle" target="_self" class="header-logo-label">
        <span>{{ saasTitle }} </span>
      </a>
      <div v-if="saasUrl" class="dashboard-home" @click="choiceCurrentCluster()">
        <i class="fa-solid fa-house"></i> 工作台
      </div>
      <div class="dashboard-home" style="cursor: pointer;" @click="choiceCurrentCluster()">
        <i :class="clusterInfo.icon"></i> {{ clusterInfo.clusterName}}
      </div>
      <div class="dashboard-home" style="cursor: pointer;" @click="choiceCurrentNamespace()">
        <i class="fa-solid fa-feather"></i> {{ clusterNamespaceInfo.nsName }}
      </div>
    </div>

    <!-- 项目选择弹窗管理 -->
    <el-dialog :title="title" v-model="open" v-if="open" width="1200px" append-to-body>
      <choiceCluster />

      <template #footer>
        <div class="dialog-footer">
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>

    </el-dialog>

    <!-- 空间选择弹窗管理 -->
    <el-dialog :title="titleNs" v-model="openNs" v-if="openNs" width="1200px" append-to-body>
      <choiceNamespace />

      <template #footer>
        <div class="dialog-footer">
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>

    </el-dialog>

  </div>
</template>


<script setup>

const saasTitle = 'AIP智能设施'
const enableLogo = ref(true);
const saasUrl = ref('http://alinesno-infra-plat-console-ui.beta.plat.infra.linesno.com') ;
const saasLogoUrl = 'http://data.linesno.com/logo_2.png' ; 

const open = ref(false);
const title = ref('')

const openNs = ref(false);
const titleNs = ref('')

const clusterInfo = ref({
  id: 0 , 
  icon: 'fa-solid fa-lemon' ,
  clusterName: '请选择当前集群'
})

const clusterNamespaceInfo = ref({
  nsName: '请选择当前命名空间'
})

import { 
  getCurrentCluster
} from "@/api/ops/container/cluster";

import choiceCluster from '@/views/ops/container/cluster/choiceCluster'
import choiceNamespace from '@/views/ops/container/namespace/choiceNamespace'

// 进入企业官网
function enterDomain() {
  if (this.domainName) { // 跳转进入官网
    window.open(this.domainName)
  }
}

function choiceCurrentCluster(){
 console.log('选择项目.') 
  open.value = true;
  title.value = "请选择项目";
}

function choiceCurrentNamespace(){
  openNs.value = true;
  titleNs.value = "请选择命令空间";
}

/** 获取当前项目信息 */
function handleCurrentCluster(){
  getCurrentCluster().then(res => {
    if(res.data){
      clusterInfo.value = res.data

      if(res.data.namespace){
        clusterNamespaceInfo.value.nsName = res.data.namespace
      }

    }
  })
}

function cancel() {
  open.value = false;
  openNs.value = false;
}

handleCurrentCluster() ; 

</script>