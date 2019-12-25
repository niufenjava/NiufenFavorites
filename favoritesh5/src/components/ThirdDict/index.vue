<template>
  <div>
    <el-tabs>
      <el-tab-pane label="百度翻译">
        <iframe
          :src="baiduDictSrc"
          :style="getDictMainStyle()"
        />
      </el-tab-pane>
      <el-tab-pane label="词源">
        <iframe
          :src="etymonlineSrc"
          :style="getDictMainStyle()"
        />
      </el-tab-pane>
      <el-tab-pane label="剑桥词典">
        <iframe
          :src="cambridgeSrc"
          :style="getDictMainStyle()"
        />
      </el-tab-pane>
      <!-- <el-tab-pane label="柯林斯">
        <iframe
          ref="collins"
          :src="collinsSrc"
          style="width:100%;height:600px"
        />
      </el-tab-pane> -->
    </el-tabs>

  </div>
</template>
<script>
export default {
  data () {
    return {
      word: 'test',
      flush: true,
            dictTopHeight: 85,

      baiduDictBaseSrc: 'https://fanyi.baidu.com/?aldtype=16047#en/zh/',
      baiduDictSrc: '',
      etymonlineBaseSrc: 'https://www.etymonline.com/search?q=',
      etymonlineSrc: '',
      cambridgeBaseSrc: 'https://dictionary.cambridge.org/zhs/%E8%AF%8D%E5%85%B8/%E8%8B%B1%E8%AF%AD-%E6%B1%89%E8%AF%AD-%E7%AE%80%E4%BD%93/',
      cambridgeSrc: '',
      collinsBaseSrc: 'https://www.collinsdictionary.com/zh/dictionary/english/',
      collinsSrc: ''

    }
  },
  computed: {
    mainBodyHeight() {
      return this.$store.state.mainBodyHeight
    }
  },
  methods: {
   getDictMainStyle() {
      return 'width:100%;height:' + (this.mainBodyHeight - this.dictTopHeight) + 'px'
    },
  // 打开抽屉方法
    open (wordName) {
      console.info(wordName)
      this.word = wordName
      console.info(this.baiduDictSrc)
      this.baiduDictSrc = this.baiduDictBaseSrc + this.word
      this.etymonlineSrc = this.etymonlineBaseSrc + this.word
      this.cambridgeSrc = this.cambridgeBaseSrc + this.word
    }
  }
}
</script>
