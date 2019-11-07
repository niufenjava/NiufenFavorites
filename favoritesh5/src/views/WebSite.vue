<template>

  <el-container>
    <el-aside width="200px">
      <el-menu
        default-active="1"
        class="el-menu-vertical-demo"
        :collapse="isCollapse"
      >
        <el-menu-item
          v-for="item in typeList"
          :key="item.id"
          @click="clickMenu(item.id)"
        >
          <i :class="item.icon" />
          <span slot="title">{{ item.name }}</span>
        </el-menu-item>
      </el-menu>
    </el-aside>
    <el-main>

      <div
        v-for="item in curItemList"
        :key="item.id"
        style="width:200px;height:80px;float: left;margin-right:10px;margin-bottom:10px;"
      >
        <el-badge :value="item.count" class="item">
          <el-card shadow="hover">
            <el-row>
              <el-col :span="2">
                <el-image
                  style="width: 16px; height: 16px"
                  :src="item.icoUrl"
                />
              </el-col>

              <el-col :span="22">
                <a
                  style="margin-left:6px;color:green"
                  target="_blank"
                  :href="item.url"
                >{{ item.name }}</a>
              </el-col>
            </el-row>
            <el-row>
              <span style="font-size:10px;color:#B2B2B2">瑞幸咖啡-便民导航</span>
            </el-row>
            <!--<el-row>-->
            <!--<div class="block">-->
            <!--<el-rate size="mini"-->
            <!--v-model="value1"-->
            <!--:colors="iconClasses">-->
            <!--</el-rate>-->
            <!--</div>-->
            <!--</el-row>-->
          </el-card>
        </el-badge>
      </div>
    </el-main>
  </el-container>
</template>
<script>
export default {
  data () {
    return {
      value1: null,
      isCollapse: false,
      activeIndex: '1',
      activeIndex2: '1',
      type: 0,
      itemMap: new Map(),
      value: '',
      iconClasses: ['icon-rate-face-1', 'icon-rate-face-2', 'icon-rate-face-3'],
      typeList: [
        {
          id: 1,
          icon: 'el-icon-apple',
          name: 'COMMON'
        },
        {
          id: 2,
          icon: 'el-icon-coffee',
          name: 'LUCKIN COFFEE'
        },
        {
          id: 3,
          icon: 'el-icon-suitcase',
          name: 'TOOLS'
        },
        {
          id: 4,
          icon: 'el-icon-coin',
          name: 'RESOURCES'
        },
        {
          id: 5,
          icon: 'el-icon-eleme',
          name: 'FRONT-END'
        },
        {
          id: 6,
          icon: 'el-icon-receiving',
          name: 'BACK-END'
        },
        {
          id: 7,
          icon: 'el-icon-s-home',
          name: 'LOCAL'
        },
        {
          id: 8,
          icon: 'el-icon-files',
          name: 'OTHERS'
        }
      ],
      curItemList: [],
      itemList: [
        {
          type: 1,
          list: [
            {
              id: 1,
              name: '咖啡导航',
              icoUrl: 'http://doctest.luckincoffee.com/images/favicon.ico',
              url: 'http://doctest.luckincoffee.com/',
              count: 90,
              score: 5
            },
            {
              id: 2,
              name: '咖啡导航',
              icoUrl: 'http://doctest.luckincoffee.com/images/favicon.ico',
              url: 'http://doctest.luckincoffee.com/',
              count: 90,
              score: 5
            },
            {
              id: 3,
              name: '咖啡导航',
              icoUrl: 'http://doctest.luckincoffee.com/images/favicon.ico',
              url: 'http://doctest.luckincoffee.com/',
              count: 90,
              score: 5
            },
            {
              id: 4,
              name: '咖啡导航',
              icoUrl: 'http://doctest.luckincoffee.com/images/favicon.ico',
              url: 'http://doctest.luckincoffee.com/',
              count: 90,
              score: 5
            },
            {
              id: 5,
              name: '咖啡导航',
              icoUrl: 'http://doctest.luckincoffee.com/images/favicon.ico',
              url: 'http://doctest.luckincoffee.com/',
              count: 90,
              score: 5
            },
            {
              id: 6,
              name: '咖啡导航',
              icoUrl: 'http://doctest.luckincoffee.com/images/favicon.ico',
              url: 'http://doctest.luckincoffee.com/',
              count: 90,
              score: 5
            },
            {
              id: 7,
              name: '咖啡导航',
              icoUrl: 'http://doctest.luckincoffee.com/images/favicon.ico',
              url: 'http://doctest.luckincoffee.com/',
              count: 90,
              score: 5
            }
          ]
        },
        {
          type: 2,
          list: [
            {
              id: 8,
              name: '咖啡导航1',
              icoUrl: 'http://doctest.luckincoffee.com/images/favicon.ico',
              url: 'http://doctest.luckincoffee.com/',
              count: 90,
              score: 5
            }
          ]
        }
      ]
    }
  },
  watch: {
    type: function (newValue, oldValue) {
      this.changeType(newValue)
    }
  },
  created: function () {
    this.itemList.forEach((item, index, array) => {
      this.itemMap.set(item.type, item.list)
    })
    this.type = 1
  },
  methods: {
    handleSelect (key, keyPath) {
      console.log(key, keyPath)
    },
    openUrl () {
      alert(1)
    },
    clickMenu (type) {
      this.type = type
    },
    changeType (type) {
      this.curItemList = this.itemMap.get(type)
    }
  }
}
</script>
<style>
.el-header{
  padding:0px !important;
}
.el-card__body {
    padding: 15px !important;
}
.item {
  margin-top: 10px;
  margin-right: 40px;
}
</style>
