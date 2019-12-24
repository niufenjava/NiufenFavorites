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
  font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB",
    "Microsoft YaHei", "微软雅黑", Arial, sans-serif;

  display: block;
  margin: 0px;
  padding: 0;
  height: 100%;
  overflow: hidden;
  background: #feac5e; /* fallback for old browsers */
  background: -webkit-linear-gradient(
    to left,
    #4bc0c8,
    #c779d0,
    #feac5e
  ); /* Chrome 10-25, Safari 5.1-6 */
  background: linear-gradient(
    to left,
    #4bc0c8,
    #c779d0,
    #feac5e
  ); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}
.el-row {
  margin-left: 0px !important;
  margin-right: 0px !important;
}
</style>
