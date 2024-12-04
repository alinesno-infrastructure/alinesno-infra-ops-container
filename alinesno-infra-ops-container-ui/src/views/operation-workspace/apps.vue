<template>
  <div>
    <el-row class="acp-dashboard-panel" :gutter="20">
      <el-col class="panel-col" :span="19">
        <div class="grid-content">
          <div class="panel-header">
            <div class="header-title"><i class="fa-solid fa-link"></i> 编排计算使用教程</div>
          </div>
          <div class="panel-body" style="height: auto;position: relative;">
              <div class="direct-box">
                <ul>
                  <li class="box-item" v-for="item in directContent" :key="item">
                    <div class="dire-panel">
                      <div class="panel-title">
                        <img class="dire-panel-icon" :src="item.icon" :alt="item.name" />
                        {{ item.name }} 
                      </div>
                      <div class="panel-describe">{{ item.desc }}</div>
                      <!-- <div class="panel-tip">访问链接</div> -->
                    </div>
                  </li>
                </ul>
              </div>
          </div>
        </div>
      </el-col>
      <el-col :span="5">
        <div class="grid-content">
          <div class="panel-header">
            <div class="header-title"><i class="fa-solid fa-link"></i> 构建解决方案</div>
          </div>
          <div class="panel-body" style="height: auto;position: relative;">
            <div class="acp-app-list">
              <ul>
                <li class="app-items" style="width:100%" v-for="item in apps" :key="item">
                  <div class="app-icon">
                    <img :src="item.icon" :alt="item.name" />
                  </div>
                  <div class="app-info">
                    <div class="app-item-title">{{ item.name }} <i class="fa-solid fa-arrow-up-right-from-square"></i>
                    </div>
                    <div class="app-item desc">{{ item.desc }}</div>
                  </div>
                </li>
              </ul>
            </div>
          </div>

        </div>
      </el-col>
    </el-row>

  </div>
</template>

<script>

export default {
  data() {
    return {
      directContent: [
        {
            "icon": "http://data.linesno.com/icons/dashboard/1.svg",
            "name": "快速容器部署与迁移",
            "desc": "提供快速且无缝的容器部署与迁移服务"
        },
        {
            "icon": "http://data.linesno.com/icons/dashboard/3.svg",
            "name": "容器分层关系规范",
            "desc": "定义和规范容器的分层关系，确保容器在不同层级之间"
        },
        {
            "icon": "http://data.linesno.com/icons/dashboard/4.svg",
            "name": "容器生命周期管理",
            "desc": "管理容器的完整生命周期，创建、部署、运行和销毁等"
        },
        {
            "icon": "http://data.linesno.com/icons/dashboard/5.svg",
            "name": "轻量级容器管理与存储",
            "desc": "将容器管理和存储相结合，具有轻量级和高效的特点。"
        },
        {
            "icon": "http://data.linesno.com/icons/dashboard/6.svg",
            "name": "容器资源调度优化",
            "desc": "容器资源调度算法，以提高系统性能和资源利用效率。"
        } 
      ],
      apps: [
        {
          "icon": "http://data.linesno.com/icons/dashboard/7.svg",
          "name": "数据安全备份",
          "desc": "数据安全备份"
        },
        {
          "icon": "http://data.linesno.com/icons/dashboard/6.svg",
          "name": "企业数据迁移",
          "desc": "企业数据迁移"
        },
        {
          "icon": "http://data.linesno.com/icons/dashboard/8.svg",
          "name": "数据智能治理方案",
          "desc": "数据智能治理方案"
        },
        {
          "icon": "http://data.linesno.com/icons/dashboard/3.svg",
          "name": "数据离线分析",
          "desc": "数据离线分析"
        }
      ]
    };
  }
}
</script>
