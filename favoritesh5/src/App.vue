<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>
<style>
  body {
    display: block;
    margin: 0px;
    padding: 0;
    height: 100%;
    overflow: hidden;
  }
  .el-row {
    margin-left: 0px !important;
    margin-right: 0px !important;
  }
</style>
