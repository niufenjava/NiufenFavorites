<template>
  <div>

    <el-container style="border: 1px solid #eee">
      <!-- 侧边栏 -->
      <el-aside
        ref="wordAside"
        width="200px"
        style="background-color: rgb(238, 241, 246)"
      >
        <el-row>
          <!-- prefix-icon="el-icon-search" -->
          <el-input
            v-model="searchInput"
            style="display:inline;float:left;width:78%"
            placeholder="请输入内容"
            autocomplete="on"
            clearable
            @input="search"
          />
          <el-button
            size="mini"
            style="display:inline;float:right;margin-top:5px"
            type="success"
            @click="addWordClick"
          >+</el-button>
        </el-row>

        <el-scrollbar ref="sideScroll">

          <div ref="listRef">
            <div
              v-for="(item, index) in wordList"
              :key="item.id"
              :index="index"
              style="height: 44px;margin-left:16px;"
            >
              <el-link
                :underline="false"
                :type="item.type"
                @click="clickWord(item.name)"
              >{{ item.name }}</el-link>
            </div>
          </div>
        </el-scrollbar>

      </el-aside>
      <el-container>
        <!-- v-if="word.name" -->
        <el-main
          ref="wordMain"
          style="overflow: hidden;"
        >
          <el-row :gutter="20">

            <!-- 我的词典详情显示区域 -->
            <el-col :span="12">
              <!-- 第一行，主要显示区域 -->
              <el-row>
                <span style="color:#409EFF;font-size:26px;font-weight:700">{{ word.name }}</span>
                <code style="margin-left:20px;">[{{ word.soundmark }}]</code>
                <el-rate
                  v-model="word.degree"
                  show-text
                  style="display:inline;margin-left:20px"
                  :texts="rateTexts"
                  :colors="rateColors"
                  @change="rateChange"
                />
                <!-- 编辑按钮 -->
                <el-link
                  style="margin-right:30px;margin-top:10px;float:right"
                  type="primary"
                  icon="el-icon-edit"
                  @click="editWordClick"
                />
              </el-row>

              <!-- 单词简单解释 -->
              <el-row>
                <span style="font-size:14px;">{{ word.descp }}</span>
              </el-row>

              <!-- 我是一个分割线 -->
              <!-- 折叠面板。循环，单词详细解释 -->
              <el-scrollbar>
                <el-container>
                  <div
                    ref="wordDetailMain"
                    style="height:500px;width:100%"
                  >

                    <!-- 我是一个分割线 -->
                    <!-- <div style="margin-top:30px;margin-bottom:30px">

                      <el-divider content-position="left">单词解释</el-divider>
                    </div> -->
                    <h4>单词解释</h4>
                    <el-collapse v-model="collapseActiveIds">
                      <el-collapse-item
                        v-for="def in word.defList"
                        :key="def.id"
                        :name="def.id"
                      >
                        <!-- 单词详解-头部 -->
                        <template slot="title">
                          <div style="color: #E6A23C;font-size:16px">
                            <span>{{ def.typeDesc }}</span><span style="margin-left:10px">{{ def.name }}</span>
                          </div>
                        </template>

                        <!-- 单词详解 -->
                        <div>
                          <div>
                            <span>{{ def.explainEn }}</span>
                          </div>
                          <div>
                            <span style="color: #909399">{{ def.explainCh }}</span>
                          </div>
                        </div>

                        <!-- 单词例子 -->
                        <ol>
                          <li
                            v-for="example in def.exampleList"
                            :key="example.id"
                          >
                            <div>
                              <span>{{ example.exampleEn }}</span>
                            </div>
                            <div>
                              <span style="color: #909399">{{ example.exampleCh }}</span>
                            </div>
                          </li>
                        </ol>
                      </el-collapse-item>
                    </el-collapse>

                    <!-- 我是一个分割线 -->
                    <!-- <div style="margin-top:30px;margin-bottom:30px">
                      <el-divider content-position="left">词根词缀</el-divider>
                    </div> -->
                    <h4>记忆技巧</h4>
                    <!-- 词根词缀按OL展示 -->
                    <div>
                      <!-- <ol>
                        <li
                          v-for="item in word.memonic.affixes"
                          :key="item.id"
                        >
                          <el-tag type="success">前缀</el-tag><span>{{ item.name }}</span><span style="margin-left:20px;">{{ item.desc }}</span>
                        </li>
                      </ol> -->
                    </div>
                    <!-- 记忆技巧 -->
                    <div>
                      <span>{{ word.skillDesc }}</span>
                    </div>
                  </div>
                </el-container>
              </el-scrollbar>
            </el-col>

            <!-- 百度翻译区域 -->
            <el-col
              ref="baiduHeight"
              :span="12"
            >
              <el-container style="width:100%;height:100%">
                <div style="width:100%;height:100%">
                  <baiduDist :word="word.name" />
                </div>
              </el-container>
            </el-col>
          </el-row>
        </el-main>
      </el-container>
    </el-container>
    <editWord
      ref="editWordComp"
      @parentCallback="init"
    />

  </div>
</template>
<script>
import editWord from '@/views/EditWord.vue'
import baiduDist from '@/components/BaiduDist'
import { dictApi } from '@/api/dict'
export default {
  components: {
    editWord,
    baiduDist
  },
  data () {
    return {
      searchInput: null,
      collapseActiveIds: [0, 1, 2, 3, 4],
      value: null,
      iconClasses: ['icon-rate-face-1', 'icon-rate-face-2', 'icon-rate-face-3'], // 等同于 { 2: 'icon-rate-face-1', 4: { value: 'icon-rate-face-2', excluded: true }, 5: 'icon-rate-face-3' }
      rateColors: ['#99A9BF', '#F7BA2A', '#FF9900'], // 等同于 { 2: '#99A9BF', 4: { value: '#F7BA2A', excluded: true }, 5: '#FF9900' }
      rateTexts: ['未知', '失望', '一般', '良好', '掌握'],
      wordList: [
      ],
      word: {
        name: '',
        symbol: '',
        descp: '',
        defList: [
        ],
        skillDesc: '',
        etymaIdList: []
      },
      clientHeight: ''
    }
  },
  watch: {
    // 如果 `clientHeight` 发生改变，这个函数就会运行
    clientHeight: function () {
      this.changeFixed(this.clientHeight)
    }
  },
  created() {
    this.wordListInit()
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
    rateChange() {
      dictApi.wordDegree({ 'id': this.word.id, 'degree': this.word.degree }).then(response => {
        console.info(response)
      }).catch(err => {
        console.log(err)
      })
    },
    init() {
      this.wordListInit()
      this.wordInfo(this.word.name)
    },
    wordInfo(word) {
      dictApi.wordInfo(word).then(response => {
        console.info(response)
        this.word = response.word
        this.collapseActiveIds = []
        for (let index = 0; index < this.word.defList.length; index++) {
          const element = this.word.defList[index]
          this.collapseActiveIds.push(element.id)
        }
      }).catch(err => {
        console.log(err)
      })
    },
    wordListInit() {
      dictApi.wordList().then(response => {
        this.wordList = response.wordList
      }).catch(err => {
        console.log(err)
      })
    },
    clickWord(word) {
      this.wordInfo(word)
    },
    editWordClick () {
      this.$refs.editWordComp.open(null, this.word.name)
    },
    addWordClick () {
      this.$refs.editWordComp.open(null, this.searchInput)
    },
    search () {
      this.initSearch()
      if (this.searchInput === null || this.searchInput === '') {
        return
      }
      let scrollIndex = -1
      for (let i = 0; i < this.wordList.length; i++) {
        const word = this.wordList[i]
        if (word.name.includes(this.searchInput, 0)) {
          word.type = 'danger'
          if (scrollIndex === -1) {
            scrollIndex = i
          }
          if (word.name === this.searchInput) {
            scrollIndex = i
          }
        }
      }
      for (let j = 0; j < this.$refs.listRef.children.length; j++) {
        const element = this.$refs.listRef.children[j]
        const attriIndex = element.getAttribute('index')
        if (attriIndex + '' === scrollIndex + '') {
          element.scrollIntoView()
        }
      }
    },
    initSearch () {
      this.wordList.forEach(element => {
        element.type = ''
      })
    },
    changeFixed (clientHeight) {
      // 动态修改样式
      // console.log(clientHeight);
      // console.log(this.$refs.baiduHeight.$el.style)
      this.$refs.wordAside.$el.style.height = clientHeight - 165 + 'px'
      this.$refs.listRef.style.height = clientHeight - 215 + 'px'
      this.$refs.wordMain.$el.style.height = clientHeight - 165 + 'px'
      this.$refs.baiduHeight.$el.style.height = clientHeight - 185 + 'px'
      this.$refs.wordDetailMain.style.height = clientHeight - 285 + 'px'
    }
  }
}
</script>
<style>
/* 分割线样式 */
.el-divider--horizontal {
  display: block;
  height: 1px;
  width: 100%;
  margin: 12px 0;
}
/* 折叠面板样式 */
.el-collapse-item__content {
  padding-bottom: 0;
  font-size: 13px;
  color: #303133;
  line-height: 1.769230769230769;
}
.el-divider__text {
  font-weight: 500;
  font-size: 18px;
}
.el-divider__text {
  position: absolute;
  background-color: #fff;
  padding: 0 20px;
  color: green;
}
ol {
  padding-inline-start: 20px;
}
</style>
