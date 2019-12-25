<template>
  <div>
    <el-button
      type="success"
      @click="increment"
    >increment</el-button>
    <el-button
      type="success"
      @click="changeClientHeight"
    >changeClientHeight</el-button>
    <div id="divId">
      <el-alert type="success">count</el-alert>
      <el-alert type="success">clientHeight:{{ clientHeight }}</el-alert>
      <el-alert type="success">styleVarTopHeight:{{ styleVarTopHeight }}</el-alert>
      <el-alert type="success">mainHeaderHeight:{{ mainHeaderHeight }}px</el-alert>
      <el-alert type="success">mainBodyHeight:{{ mainBodyHeight }}px</el-alert>
      <el-alert type="success">mainFooterHeight:{{ mainFooterHeight }}px</el-alert>
      <el-alert type="success">divHeight:{{ divHeight }}px</el-alert>

      <div class="test-class">test-class</div>
    </div>
  </div>
</template>
<script>
import { toggleClass } from '@/utils'
import styleVar from '@/styles/var.scss'

export default {
  data () {
    return {
       theme: false,
      styleVarTopHeight: 'mainHeaderHeight:' + styleVar.mainHeaderHeight,
      value: null,
      divHeight: null
    }
  },
  computed: {
    count() {
      return this.$store.state.count + 'a'
    },
    clientHeight() {
      return this.$store.state.clientHeight
    },
    mainHeaderHeight() {
      return this.$store.state.mainHeaderHeight
    },
    mainBodyHeight() {
      return this.$store.state.mainBodyHeight
    },
    mainFooterHeight() {
      return this.$store.state.mainFooterHeight
    }
  },
  watch: {
    theme() {
      toggleClass(document.body, 'custom-theme')
    },
    value: function (newValue, oldValue) {
    },
    count(newValue, oldValue) {
    },
    clientHeight(newValue, oldValue) {
    }
  },
  created: function () {
  },

  mounted () {
    // 获取浏览器可视区域高度
    this.divHeight = document.getElementById('divId').offsetHeight
    console.info('this.$refs.divRef.clientHeight:' + document.getElementById('divId').clientHeight)
    console.info('this.$refs.divRef.offsetHeight:' + document.getElementById('divId').offsetHeight)
        console.info('this.$refs.divRef.scrollHeight:' + document.getElementById('divId').scrollHeight)
  },
  methods: {

    increment() {
      this.$store.commit('increment')
    },
    changeClientHeight() {
    },
    openUrl () {
      alert(1)
    }
  }
}
</script>
<style lang="scss" scoped>
// scss 变量使用方式
@import "@/styles/var.scss";
.test-class {
  color: $blue;
}
</style>
