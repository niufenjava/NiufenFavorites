<template>
  <el-container>
    <!-- 布局-头部导航 -->
    <el-header
      height="60px"
      style="background-color:#24292e;opacity: 1;"
    >
      <el-row>

        <!-- 导航菜单 -->
        <el-col :span="16">
          <el-menu
            default-active="/webSite"
            router
            class="el-menu-demo"
            mode="horizontal"
            background-color="#24292e"
            text-color="#fff"
            active-text-color="#feac5e"
            style="border: 0px;"
          >
            <el-menu-item index="/home">
              <el-image
                style="width: 32px; height: 32px;"
                :src="logoUrl"
              />
            </el-menu-item>
            <el-menu-item index="/word">Word</el-menu-item>
            <el-menu-item index="/notebook">Notebook</el-menu-item>
            <el-menu-item index="/webSite">WebSite</el-menu-item>
            <el-menu-item index="/todo">Todo</el-menu-item>
            <el-menu-item index="/message">Message</el-menu-item>
          </el-menu>
        </el-col>

        <!-- 百度搜索 -->
        <el-col :span="8">
          <search-engine style="margin-top:10px;margin-right:20px;" />
        </el-col>
      </el-row>
    </el-header>

    <!-- 布局-主体 -->
    <el-main
      ref="homePage"
      style="padding: 0px"
    >
      <keep-alive>
        <!-- 路由显示区 -->
        <router-view style="height:100%;" />
      </keep-alive>
    </el-main>
  </el-container>
</template>
<script>
import logoUrl from '@/assets/logo.png'
import SearchEngine from '@/components/searchEngine'
export default {
  components: {
    SearchEngine
  },
  data () {
    return {
      // 用于计算主体高度
      clientHeight: '',
      logoUrl: logoUrl
    }
  },
  watch: {
    // 如果 `clientHeight` 发生改变，这个函数就会运行
    clientHeight: function () {
      this.changeFixed(this.clientHeight)
    }
  },
  mounted () {
    // 获取浏览器可视区域高度
    this.clientHeight = `${document.documentElement.clientHeight}`
    window.onresize = function temp () {
      this.clientHeight = `${document.documentElement.clientHeight}`
    }
  },

  methods: {
     // 动态修改样式
    changeFixed (clientHeight) {
      this.$refs.homePage.$el.style.height = clientHeight - 60 + 'px'
    },
    route (path) {
      this.$router.push(path)
    }
  }
}
</script>
<style>
</style>
