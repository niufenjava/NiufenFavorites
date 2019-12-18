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
              >{{ item.name }}</el-link>
            </div>
          </div>
        </el-scrollbar>

      </el-aside>
      <el-container>
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
                <code style="margin-left:20px;">[{{ word.symbol }}]</code>
                <el-rate
                  v-model="value"
                  style="display:inline;margin-left:20px"
                  :colors="colors"
                />
                <el-link
                  type="primary"
                  icon="el-icon-edit"
                  @click="editWordClick"
                />
              </el-row>

              <!-- 单词简单解释 -->
              <el-row>
                <span style="font-size:14px;">{{ word.explainCn }}</span>
              </el-row>

              <!-- 我是一个分割线 -->
              <el-divider />
              <!-- 折叠面板。循环，单词详细解释 -->
              <el-scrollbar>
                <el-container>
                  <div
                    ref="wordDetailMain"
                    style="height:500px;width:100%"
                  >

                    <!-- 我是一个分割线 -->
                    <el-divider content-position="left">Word Explain</el-divider>
                    <el-collapse v-model="activeNames">
                      <el-collapse-item
                        v-for="explain in word.explainList"
                        :key="explain.id"
                        :name="explain.id"
                      >
                        <!-- 单词详解-头部 -->
                        <template slot="title">
                          <div style="color: #E6A23C;font-size:16px">
                            <span>{{ explain.type }}</span><span style="margin-left:10px">{{ explain.name }}</span>
                          </div>
                        </template>

                        <!-- 单词详解 -->
                        <div>
                          <div>
                            <span>{{ explain.explainEn }}</span>
                          </div>
                          <div>
                            <span style="color: #909399">{{ explain.explainCn }}</span>
                          </div>
                        </div>

                        <!-- 单词例子 -->
                        <ol>
                          <li
                            v-for="example in explain.exampleList"
                            :key="example.id"
                          >
                            <div>
                              <span>{{ example.exampleEn }}</span>
                            </div>
                            <div>
                              <span style="color: #909399">{{ example.exampleCn }}</span>
                            </div>
                          </li>
                        </ol>
                      </el-collapse-item>
                    </el-collapse>

                    <!-- 我是一个分割线 -->
                    <el-divider content-position="left">词根词缀</el-divider>
                    <!-- 词根词缀按OL展示 -->
                    <div>
                      <ol>
                        <li
                          v-for="item in word.memonic.affixes"
                          :key="item.id"
                        >
                          <el-tag type="success">前缀</el-tag><span>{{ item.name }}</span><span style="margin-left:20px;">{{ item.desc }}</span>
                        </li>
                      </ol>
                    </div>
                    <!-- 记忆技巧 -->
                    <div>
                      <span>{{ word.memonic.skill }}</span>
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
    <editWord ref="editWordComp" />

  </div>
</template>
<script>
import editWord from '@/views/EditWord.vue'
import baiduDist from '@/components/BaiduDist'
export default {
  components: {
    editWord,
    baiduDist
  },
  data () {
    return {
      searchInput: null,
      activeNames: [0, 1, 2, 3, 4],
      value: null,
      iconClasses: ['icon-rate-face-1', 'icon-rate-face-2', 'icon-rate-face-3'], // 等同于 { 2: 'icon-rate-face-1', 4: { value: 'icon-rate-face-2', excluded: true }, 5: 'icon-rate-face-3' }
      colors: ['#99A9BF', '#F7BA2A', '#FF9900'], // 等同于 { 2: '#99A9BF', 4: { value: '#F7BA2A', excluded: true }, 5: '#FF9900' }
      wordList: [
        {
          id: 1,
          name: 'provoidea'
        },
        {
          id: 2,
          name: 'provoideb'
        },
        {
          id: 3,
          name: 'provoidec'
        },
        {
          id: 4,
          name: 'provoided'
        },
        {
          id: 5,
          name: 'provoidee'
        },
        {
          id: 6,
          name: 'provoidef'
        },
        {
          id: 7,
          name: 'provoideg'
        },
        {
          id: 8,
          name: 'provoideh'
        },
        {
          id: 9,
          name: 'provoideg'
        },
        {
          id: 10,
          name: 'provoideer'
        },
        {
          id: 11,
          name: 'provoideaaa'
        },
        {
          id: 12,
          name: 'provoide'
        },
        {
          id: 13,
          name: 'provoide'
        },
        {
          id: 14,
          name: 'provoide'
        },
        {
          id: 15,
          name: 'provoide'
        },
        {
          id: 16,
          name: 'provoide'
        },
        {
          id: 17,
          name: 'provoide'
        },
        {
          id: 18,
          name: 'provoide'
        },
        {
          id: 19,
          name: 'provoide'
        },
        {
          id: 20,
          name: 'provoide'
        },
        {
          id: 21,
          name: 'provoide'
        },
        {
          id: 22,
          name: 'provoide'
        },
        {
          id: 23,
          name: 'provoide'
        },
        {
          id: 24,
          name: 'provoide'
        },
        {
          id: 25,
          name: 'provoide'
        },
        {
          id: 26,
          name: 'provoide'
        },
        {
          id: 27,
          name: 'provoide'
        },
        {
          id: 28,
          name: 'provoideaaa'
        },
        {
          id: 29,
          name: 'provoide'
        },
        {
          id: 30,
          name: 'provoide'
        },
        {
          id: 31,
          name: 'provoide'
        },
        {
          id: 32,
          name: 'provoide3'
        },
        {
          id: 33,
          name: 'provoide'
        }
      ],
      word: {
        name: 'provide',
        symbol: 'prəˈvaɪd',
        explainCn: 'v. 提供',
        explainList: [
          {
            id: 0,
            type: 'noun',
            name: '提供',
            explainEn: 'to give sth to sb or make it available for them to use',
            explainCn: '给某人提供某物，并使其可用',
            exampleList: [
              {
                id: 0,
                exampleEn: 'We are here to provide a service for the public.',
                exampleCn: '我们来这里是为公众服务。'
              },
              {
                id: 1,
                exampleEn:
                  'The report was not expected to provide any answers. ',
                exampleCn: '人们没有指望这个报告会提供什么答案。'
              }
            ]
          },
          {
            id: 1,
            type: 'adj',
            name: '提供de',
            explainEn: 'to give sth to sb or make it available for them to use',
            explainCn: '给某人提供某物，并使其可用',
            exampleList: [
              {
                id: 0,
                exampleEn: 'We are here to provide a service for the public.',
                exampleCn: '我们来这里是为公众服务。'
              },
              {
                id: 1,
                exampleEn:
                  'The report was not expected to provide any answers. ',
                exampleCn: '人们没有指望这个报告会提供什么答案。'
              }
            ]
          },
          {
            id: 2,
            type: 'adj',
            name: '提供de',
            explainEn: 'to give sth to sb or make it available for them to use',
            explainCn: '给某人提供某物，并使其可用',
            exampleList: [
              {
                id: 0,
                exampleEn: 'We are here to provide a service for the public.',
                exampleCn: '我们来这里是为公众服务。'
              },
              {
                id: 1,
                exampleEn:
                  'The report was not expected to provide any answers. ',
                exampleCn: '人们没有指望这个报告会提供什么答案。'
              }
            ]
          },
          {
            id: 3,
            type: 'adj',
            name: '提供de',
            explainEn: 'to give sth to sb or make it available for them to use',
            explainCn: '给某人提供某物，并使其可用',
            exampleList: [
              {
                id: 0,
                exampleEn: 'We are here to provide a service for the public.',
                exampleCn: '我们来这里是为公众服务。'
              },
              {
                id: 1,
                exampleEn:
                  'The report was not expected to provide any answers. ',
                exampleCn: '人们没有指望这个报告会提供什么答案。'
              }
            ]
          },
          {
            id: 4,
            type: 'adj',
            name: '提供de',
            explainEn: 'to give sth to sb or make it available for them to use',
            explainCn: '给某人提供某物，并使其可用',
            exampleList: [
              {
                id: 0,
                exampleEn: 'We are here to provide a service for the public.',
                exampleCn: '我们来这里是为公众服务。'
              },
              {
                id: 1,
                exampleEn:
                  'The report was not expected to provide any answers. ',
                exampleCn: '人们没有指望这个报告会提供什么答案。'
              }
            ]
          }
        ],
        memonic: {
          skill: 'pro 在前 + vid 看 + e → 提前看好 → 预备',
          affixes: [
            {
              id: 1,
              type: 1,
              name: 'vid',
              desc: '= to see 看'
            },
            {
              id: 2,
              type: 2,
              name: 'pro',
              desc: '向前'
            }
          ]
        }
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
    editWordClick () {
      this.$refs.editWordComp.open(1, null)
    },
    addWordClick () {
      this.$refs.editWordComp.open(null, 'complex')
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
ol {
  padding-inline-start: 20px;
}
</style>
