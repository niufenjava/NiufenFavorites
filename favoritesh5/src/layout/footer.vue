<template>
  <el-container>
    <el-footer :height="getMainFooterHeight()">haijun.zhang@luckincoffee.com</el-footer>
  </el-container>
</template>
<script>
export default {
  components: {
  },
  data () {
    return {
    }
  },
  computed: {
    mainFooterHeight() {
      return this.$store.state.mainFooterHeight
    }
  },

  methods: {
    getMainFooterHeight() {
      return this.mainFooterHeight + 'px'
    }
  }
}
</script>
<style>
.el-footer {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
}
</style>
