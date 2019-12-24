<template>
  <!-- 布局-主体 -->
  <el-main :style="getMainStyle()">
    <keep-alive>
      <!-- 路由显示区 -->
      <router-view />
    </keep-alive>
  </el-main>
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
    mainBodyHeight() {
      console.info(this.$store.state.mainBodyHeight)
      return this.$store.state.mainBodyHeight
    }
  },

  methods: {
    getMainStyle() {
      console.info(this.mainBodyHeight)
      return 'height:' + this.mainBodyHeight + 'px'
    },

    route (path) {
      this.$router.push(path)
    }

  }
}
</script>
<style>
</style>
