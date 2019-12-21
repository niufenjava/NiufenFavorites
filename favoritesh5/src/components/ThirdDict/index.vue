<template>
  <div style="height:100%">
    <el-tabs>
      <el-tab-pane label="百度翻译">
        <iframe
          ref="baiduDict"
          :src="baiduDictSrc"
          style="width:100%;height:600px"
        />
      </el-tab-pane>
      <!-- <el-tab-pane label="谷歌翻译">
        <iframe
          ref="googleDict"
          :src="googleDictSrc"
          style="width:100%;height:600px"
        />
      </el-tab-pane> -->
      <el-tab-pane label="词源">
        <iframe
          ref="etymonline"
          :src="etymonlineSrc"
          style="width:100%;height:600px"
        />
      </el-tab-pane>
      <el-tab-pane label="维基百科">
        <iframe
          ref="wiki"
          :src="etymonwikipedia"
          style="width:100%;height:600px"
        />
      </el-tab-pane>
    </el-tabs>

  </div>
</template>
<script>
export default {
  data () {
    return {
      word: 'test',
      flush: true,
            clientHeight: '',

      baiduDictBaseSrc: 'https://fanyi.baidu.com/?aldtype=16047#en/zh/',
      googleDictBaseSrc: 'https://translate.google.cn/#view=home&op=translate&sl=en&tl=zh-CN&text=',
      etymonlineBaseSrc: 'https://www.etymonline.com/search?q=',
      baiduDictSrc: 'https://fanyi.baidu.com/?aldtype=16047#en/zh/',
      googleDictSrc: 'https://translate.google.cn/#view=home&op=translate&sl=en&tl=zh-CN&text=',
      etymonlineSrc: 'https://www.etymonline.com/search?q=',
      etymonwikipedia: 'https://zh.wikipedia.org/wiki/Wikipedia:%E9%A6%96%E9%A1%B5'
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
      this.$refs.baiduDict.style.height = clientHeight - 160 + 'px'
            this.$refs.etymonline.style.height = clientHeight - 160 + 'px'
            this.$refs.wiki.style.height = clientHeight - 160 + 'px'
    },
  // 打开抽屉方法
    open (wordName) {
      console.info(wordName)
      this.word = wordName
      console.info(this.baiduDictSrc)
      this.baiduDictSrc = this.baiduDictBaseSrc + wordName
      // this.googleDictSrc = this.googleDictBaseSrc + wordName
      this.etymonlineSrc = this.etymonlineBaseSrc + wordName

      // this.$refs.baiduDict.contentWindow.location.reload(true)
      // this.$refs.googleDict.contentWindow.location.reload(true)
      // this.$refs.etymonline.contentWindow.location.reload(true)
      // this.$refs.wiki.contentWindow.location.reload(true)
    }
  }
}
</script>
