<template>
  <div>
    <el-container style="border: 0px solid #eee;margin: 0px">
      <el-header height="60px">
        <el-row style="background-color:#24292e">
          <el-col :span="1">
            <div style="height:60px;width:100%">
              <a href="#/home">
                <el-image
                  style="width: 32px; height: 32px;margin-top:12px;margin-left:12px;"
                  :src="logoUrl"
                />
              </a>
            </div>
          </el-col>
          <el-col :span="5">
            <el-menu
              default-active="/webSite"
              router
              class="el-menu-demo"
              mode="horizontal"
              background-color="#24292e"
              text-color="#fff"
              active-text-color="#ffd04b"
              style="border: 0px"
            >
              <el-menu-item index="/webSite">WebSite</el-menu-item>
              <el-menu-item index="/todo">Todo</el-menu-item>
              <el-menu-item index="/message">Message</el-menu-item>
            </el-menu>
          </el-col>

          <el-col :span="8">
            <search-engine />
          </el-col>
        </el-row>
      </el-header>
      <el-main ref="homePage" style="padding:0px !important;margin-top:4px;">
        <el-scrollbar style="height: 100%;">
          <router-view />
        </el-scrollbar>
      </el-main>
    </el-container>
  </div>
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
      select: 'Baidu',
      inputValue: '',
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
    // document.body.clientWidth;
    // console.log(self.clientHeight);
    window.onresize = function temp () {
      this.clientHeight = `${document.documentElement.clientHeight}`
    }
  },
  methods: {
    changeFixed (clientHeight) { // 动态修改样式
      // console.log(clientHeight);
      // console.log(this.$refs.homePage.$el.style.height);
      this.$refs.homePage.$el.style.height = clientHeight - 70 + 'px'
    },
    route (path) {
      this.$router.push(path)
    }
  }
}
</script>
<style>
.el-scrollbar__wrap {
   overflow-x: hidden !important;
}
  .el-select {
    width: 100px;
  }.el-input {
    width: 150px;
  }
  .input-with-select .el-input-group__prepend {
    background-color: #fff;
  }
</style>
