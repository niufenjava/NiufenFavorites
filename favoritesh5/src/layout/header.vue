<template>
  <!-- 布局-头部导航 -->
  <el-header
    :height="getMainHeaderHeight()"
    class="layout-Header"
  >
    <el-row>
      <!-- 导航菜单 -->
      <el-col :span="16">
        <el-menu
          default-active="/Home"
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
          <el-menu-item index="/dict">Dict</el-menu-item>
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
      logoUrl: logoUrl
    }
  },
  computed: {
    mainHeaderHeight() {
      return this.$store.state.mainHeaderHeight
    }
  },

  methods: {
    getMainHeaderHeight() {
      return this.mainHeaderHeight + 'px'
    }
  }
}
</script>
<style>
</style>
