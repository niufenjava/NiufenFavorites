<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
// 在这里监听浏览器可显示区域的高度并存储到vuex中
export default {
  name: 'App',
  data () {
    return {
      // 用于计算主体高度
      clientHeight: ''
    }
  },
  watch: {
    // 如果 `clientHeight` 发生改变，这个函数就会运行
    clientHeight: function (newVlaue, oldValue) {
      this.changeFixed(newVlaue)
    }
  },
  mounted () {
    // 获取浏览器可视区域高度
    this.clientHeight = document.documentElement.clientHeight
    const _ = this
    window.onresize = function temp () {
      _.clientHeight = `${document.documentElement.clientHeight}`
    }
  },

  methods: {
     // 动态修改样式
    changeFixed (clientHeight) {
      // 存储到vuex，使得各个组件都可以获取改高度

      this.$store.state.clientHeight = clientHeight
      this.$store.state.mainBodyHeight = (clientHeight - this.$store.state.mainHeaderHeight - this.$store.state.mainFooterHeight)
      console.info(this.$store.state)
    }
  }
}
</script>
<style>
</style>
