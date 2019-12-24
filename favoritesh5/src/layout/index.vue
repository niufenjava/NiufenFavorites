<template>
  <!-- direction 布局方向
      子元素中有 el-header 或 el-footer
      时为 vertical 垂直，否则为 horizontal 水平 -->
  <el-container direction="vertical">
    <Header />
    <Main />
    <Footer />
  </el-container>
</template>
<script>
import Main from '@/layout/main.vue'
import Header from '@/layout/header.vue'
import Footer from '@/layout/footer.vue'

export default {
  components: {
    Main, Header, Footer
  },
  data () {
    return {
    }
  },
  watch: {

  },
  mounted () {

  },

  methods: {
  }
}
</script>
<style>
</style>
