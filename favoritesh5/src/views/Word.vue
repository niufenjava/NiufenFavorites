<template>
  <div>
    <el-container style="height:100%">
      <!-- 侧边栏 -->
      <el-aside
        width="220px"
        style="hegiht:100%;"
      >
        <!-- 新增单词按钮 -->
        <el-row>
          <el-col :span="12">
            <el-button
              size="small"
              style="margin:10px"
              type="primary"
              @click="addWordClick"
            >新增单词</el-button>
          </el-col>
          <el-col :span="12">
            <el-dropdown
              size="small"
              style="float:right;margin-top:15px;"
            >
              <span class="el-dropdown-link">
                默认排序<i class="el-icon-arrow-down el-icon--right" />
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>默认排序</el-dropdown-item>
                <el-dropdown-item>熟悉程度</el-dropdown-item>
                <el-dropdown-item>查询次数</el-dropdown-item>
                <el-dropdown-item>修改时间</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </el-col>
        </el-row>

        <!-- 单词搜索输入框 -->
        <el-row>
          <el-input
            v-model="searchInput"
            style="margin:10px;width:200px;margin-top:0px;"
            placeholder="Search"
            autocomplete="on"
            clearable
            @input="search"
          />
        </el-row>
        <el-scrollbar>

          <div ref="listRef">
            <div
              v-for="(item, index) in wordList"
              :key="item.id"
              :index="index"
              style="height: 34px;margin-left:14px;"
            >
              <el-link
                :underline="true"
                :type="item.type"
                style="font-size:18px;font-weight:500px;"
                @click="clickWord(item.name)"
              >{{ item.name }}</el-link>
            </div>

          </div>
        </el-scrollbar>

      </el-aside>
      <!-- v-if="word.name" -->
      <el-main
        ref="wordMain"
        style="overflow: hidden;height:100%"
      >
        <el-row
          style="height:100%"
          :gutter="20"
        >

          <!-- 我的词典详情显示区域 -->
          <el-col :span="myWordCol">
            <el-row>
              <el-rate
                v-model="word.degree"
                style="display:inline;margin-left:20px;float:right"
                :texts="rateTexts"
                :colors="rateColors"
                @change="rateChange"
              />

            </el-row>
            <!-- 第一行，主要显示区域 -->
            <el-row>
              <span style="color:#409eff;font-size:30px;font-weight:800">{{ word.name }}</span>
              <code style="margin-left:20px;">[{{ word.soundmark }}]</code>

              <!-- 编辑按钮 -->
              <el-link
                style="margin-right:10px;margin-top:20px;float:right"
                type="default"
                icon="el-icon-edit"
                @click="editWordClick"
              >编辑</el-link>
            </el-row>

            <!-- 单词简单解释 -->
            <el-row>
              <span style="font-size:14px;">{{ word.descp }}</span>
            </el-row>

            <!-- 我是一个分割线 -->
            <!-- 折叠面板。循环，单词详细解释 -->
            <el-row style="background-color:white;margin-top:10px;">
              <el-scrollbar>
                <div
                  ref="wordDetailMain"
                  style="width:100%;"
                >
                  <div style="margin:20px;">
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
                    <!-- <ol>
                        <li
                          v-for="item in word.memonic.affixes"
                          :key="item.id"
                        >
                          <el-tag type="success">前缀</el-tag><span>{{ item.name }}</span><span style="margin-left:20px;">{{ item.desc }}</span>
                        </li>
                      </ol> -->
                    <!-- 记忆技巧 -->
                    <div style="background-color:white;margin-bottom:200px;">
                      <span>{{ word.skillDesc }}</span>
                    </div>
                  </div>
                </div>
              </el-scrollbar>
            </el-row>
          </el-col>

          <!-- 百度翻译区域 -->
          <el-col
            ref="thirdDictHeight"
            :span="thirdWordCol"
          >
            <el-container style="width:100%;height:100%">
              <div style="width:100%;height:100%">
                <thirdDict ref="thirdDictRef" />
              </div>
            </el-container>
          </el-col>
        </el-row>
      </el-main>

      <!-- 单词编辑-组件 -->
      <editWord
        ref="editWordComp"
        @parentCallback="init"
      />
    </el-container>

  </div>
</template>
<script>
import editWord from '@/views/EditWord.vue'
import thirdDict from '@/components/ThirdDict'
import { dictApi } from '@/api/dict'
export default {
  components: {
    editWord,
    thirdDict
  },
  data () {
    return {
      myWordCol: 12,
      thirdWordCol: 12,
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
    },
    word: function () {
      this.$refs.thirdDictRef.open(this.word.name)
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
        if (this.word.defList) {
        for (let index = 0; index < this.word.defList.length; index++) {
          const element = this.word.defList[index]
          this.collapseActiveIds.push(element.id)
        }
      }
      }).catch(err => {
        console.log(err)
      })
    },
    wordListInit() {
      dictApi.wordList().then(response => {
        this.wordList = response.wordList
        this.word = this.wordList[0]
      }).catch(err => {
        console.log(err)
      })
    },
    clickWord(word) {
      this.initSearch()
      for (let i = 0; i < this.wordList.length; i++) {
        const item = this.wordList[i]
        if (item.name === word) {
          item.type = 'primary'
        }
      }
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
        element.type = 'default'
      })
    },
    changeFixed (clientHeight) {
      // 动态修改样式
      // console.log(clientHeight);
      // console.log(this.$refs.baiduHeight.$el.style)
      // this.$refs.wordAside.$el.style.height = clientHeight - 165 + 'px'
      this.$refs.listRef.style.height = clientHeight - 190 + 'px'
      // this.$refs.wordMain.$el.style.height = clientHeight - 160 + 'px'
      this.$refs.thirdDictHeight.$el.style.height = clientHeight - 125 + 'px'
      this.$refs.wordDetailMain.style.height = clientHeight - 240 + 'px'
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
  margin-bottom: 20px;
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
  background-color: rgb(238, 241, 246);
  padding: 0 20px;
}
ol {
  padding-inline-start: 20px;
}
.el-link.el-link--default {
  color: black;
}
.el-link.el-link--primary:hover {
  color: #409eff;
}
.el-link.el-link--primary {
  color: #409eff;
}
.el-collapse-item__content {
  padding-bottom: 0;
  font-size: 16px;
  color: #303133;
  line-height: 1.769230769230769;
}
</style>
