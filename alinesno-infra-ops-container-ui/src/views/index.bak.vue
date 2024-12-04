<template>
  <div class="app-container" v-loading="loading">
    <el-row>
      <el-col :span="24">
        <div class="top-header">
          <div class="header-icon-banner">
            <i class="fa-solid fa-ferry"></i>
          </div>
          <div class="icon">
            <div class="title">
              集群状态
              <div class="cluster-info">
                <span><i class="el-icon-monitor"></i>
                  {{ currentEnvClusterObj.clusterName }}</span>
                <span><i class="el-icon-link"></i> ApiServer:
                  {{ currentEnvClusterObj.apiServerUrl }}</span>
              </div>
            </div>
            <div class="title-desc">
              集群状态展示集群资源的概览和详情，您可以查看集群资源的监控数据和用量排行情况。
            </div>
          </div>
        </div>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="24">
        <div class="box-header">
          <div class="title">集群资源用量</div>
          <el-row>
            <el-col :span="24">
              <GridPod />
            </el-col>
          </el-row>
        </div>
      </el-col>
    </el-row>

  </div>
</template>

<script setup name="Index">

import GridPod from "@/views/ops/container/pod/gridPod"

import { 
  getCurrentCluster
} from "@/api/ops/container/cluster";

const currentEnvClusterObj = ref({
  clusterName: "测试集群",
  apiServerUrl: "http://localhost:8001"
})

const loading = ref(false)

/** 获取当前项目信息 */
function handleCurrentCluster(){
  getCurrentCluster().then(res => {
    if(res.data){
      currentEnvClusterObj.value = res.data
    }
  })
}

handleCurrentCluster() ; 

</script>

<style lang="scss" scoped>
.top-header {
  position: relative;
  padding-bottom: 24px;
  overflow: hidden;

  .header-icon-banner {
    float: left;
    font-size: 2.4rem;
    margin-right: 10px;
    color: #3b5998;
  }

  .icon {
    float: left;
  }

  .title {
    display: block;
    font-weight: 600;
    font-style: normal;
    font-size: 24px;
    color: #242e42;
    text-shadow: 0 4px 8px rgba(36, 46, 66, 0.1);
    margin-bottom: 10px;
  }

  .title-desc {
    color: #79879c !important;
    font-size: 12px;
  }
}

.cluster-info {
  float: right;
  position: relative;
  font-size: 14px;
  margin-left: 10px;
  font-weight: bold;
  margin-top: 10px;
  color: #3b5998;

  span {
    margin-left: 20px;
  }
}

.node-item {
  font-size: 12px;
  margin-bottom: 5px;
}

.no-install-plugin {
  opacity: 0.5;
}

.item-box {
  ul {
    list-style: none;
    padding: 0px;
    margin: 0px;

    .server-desc {
      font-size: 1.6rem;
      color: #3b5998;
    }

    .active {
      background: #3b5998 !important;
      color: #fff !important;

      .server-desc {
        color: #fff !important;
      }
    }

    li.item-box-info {
      padding: 8px;
      float: left;
      width: 100%;
      background: #f9fbfd;
      border-radius: 5px;
      color: #222;
      margin-bottom: 5px;

      .item-status {
        float: left;
      }

      .status-info {
        float: left;
        margin-left: 20px;

        .item-text {
          margin-bottom: 5px;
          font-size: 12px;
          font-weight: 600;
          line-height: 1.67;
        }

        .item-num {
          height: 22px;
          line-height: 22px;
          font-size: 18px;
          font-weight: 600;

          .total-num {
            font-size: 14px;
          }
        }
      }
    }
  }
}

.sidecard-bar {
  padding: 20px;
}

.item-list {
  .item-icon {
    img {
      width: 94px;
      height: 20px;
    }
  }
}

.box-header {
  .title {
    position: relative;
    height: 20px;
    margin-bottom: 20px;
    font-size: 14px;
    font-weight: 600;
    line-height: 1.43;
    zoom: 1;
  }

  .box-body {
    // padding: 20px;
    float: left;
    width: 100%;
    border-radius: 5px;
    background: #f9fbfd;

    ul {
      list-style: none;
      padding: 0px;
      margin: 0px;

      li.item-list {
        float: left;
        width: calc(33% - 10px);
        margin-right: 10px;
        background: #f9fbfd;
        padding: 10px;
        border-radius: 5px;
        margin-bottom: 10px;

        .item-icon {
          float: left;
        }

        .item-label {
          float: right;
        }
      }
    }

    .sidecard-pie {
      float: left;
      margin-left: 30px;
    }

    .sidecard {
      float: left;
      display: flex;
      flex-direction: column;
      justify-content: center;
      margin-left: 45px;
      margin-top: 40px;

      .box-title {
        font-size: 16px;
        font-weight: 600;
        line-height: 1.43;
        margin-bottom: 5px;
      }
    }
  }
}
</style>
