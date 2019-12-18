<template>
  <div style="float:right;display: inline">
    <!-- 提供外部的编辑按钮 -->
    <!-- 抽屉 -->
    <el-drawer
      with-header="false"
      title="编辑单词"
      size="80%"
      :visible.sync="drawer"
      direction="rtl"
      :before-close="closeDrawerBefore"
    >
      <!-- 抽屉显示区域 -->
      <el-container>
        <el-main
          :style="{ height: mainHeight+'px' }"
          style="overflow: hidden;"
        >
          <el-row :gutter="20">

            <!-- 左边一半-我的词典详情显示区域 -->
            <el-col :span="12">

              <!-- 第一行显示单词；和按钮 -->
              <el-row style="margin-bottom:20px">
                <el-col :span="16">
                  <md-input
                    v-model="word.name"
                    placeholder="单词"
                  >Word</md-input>
                </el-col>
                <el-col
                  :span="8"
                  style="margin-top:20px"
                >
                  <el-button
                    style="float:right;margin-right:15px"
                    type="success"
                    round
                    @click="saveWord"
                  >保存</el-button>

                </el-col>
              </el-row>

              <!-- 我是一个分割线 -->
              <!-- 折叠面板。循环，单词详细解释 -->
              <el-scrollbar>
                <el-container>
                  <div
                    :style="{ height: mainHeight-120+'px' }"
                    style="height:500px;width:100%"
                  >
                    <!-- 我是一个分割线 -->
                    <el-row style="margin-top:20px;margin-bottom:20px;">
                      <el-divider content-position="left">Explain</el-divider>
                    </el-row>
                    <!-- 我是一个分割线 -->
                    <el-card
                      v-for="(explain,index) in word.explainList"
                      :key="explain.id"
                      :name="explain.id"
                      class="box-card"
                      shadow="hover"
                      style="margin-bottom:10px;"
                    >
                      <!-- 单词详解-头部 -->
                      <div
                        slot="header"
                        class="clearfix"
                      >
                        <div style="display:inline;width:80px">
                          <span>Explain {{ index+1 }}</span>
                          <el-link
                            style="float: right; padding: 3px 0;display:inline;"
                            type="danger"
                            @click="delExplain(index)"
                          >delete
                          </el-link>
                        </div>

                      </div>
                      <el-row style="margin-bottom:10px;">
                        <el-radio-group v-model="explain.type">
                          <el-radio :label="1">n.</el-radio>
                          <el-radio :label="2">v.</el-radio>
                          <el-radio :label="3">adj.</el-radio>
                          <el-radio :label="4">adv.</el-radio>
                          <el-radio :label="5">prep.</el-radio>
                          <el-radio :label="6">conj.</el-radio>
                        </el-radio-group>
                      </el-row>
                      <!-- 单词详解 -->
                      <el-row>
                        <md-input
                          v-model="explain.name"
                          placeholder="解释"
                        >解释</md-input>
                      </el-row>
                      <div>
                        <div>
                          <md-input
                            v-model="explain.explainEn"
                            style="margin-top:10px;"
                            placeholder="explainEn"
                          />
                        </div>
                        <div>
                          <md-input
                            v-model="explain.explainCn"
                            style="margin-top:10px;"
                            placeholder="explainCn"
                          />
                        </div>
                      </div>
                      <!-- 单词例子 -->
                      <ol>
                        <li
                          v-for="(example, idx) in explain.exampleList"
                          :key="example.id"
                        >
                          <md-input
                            v-model="example.exampleEn"
                            style="margin-top:10px;"
                            placeholder="exampleEn"
                          />
                          <el-row>
                            <el-col :span="20">
                              <md-input
                                v-model="example.exampleCn"
                                style="margin-top:10px;"
                                placeholder="exampleCn"
                              />
                            </el-col>
                            <el-col
                              :span="4"
                              style="margin-top:10px;"
                            >
                              <!-- 删除例子按钮 -->
                              <el-link
                                style="float:right"
                                size="mini"
                                type="danger"
                                @click="delExample(idx,explain.exampleList)"
                              >delete</el-link>
                            </el-col>
                          </el-row>
                        </li>
                      </ol>

                      <!-- 添加例子按钮 -->
                      <div style="float: right;margin-right:20px;margin-bottom:20px;">
                        <el-button
                          style="float:middle"
                          size="mini"
                          type="primary"
                          @click="addExample(explain.exampleList,explain)"
                        >+example</el-button>
                      </div>
                    </el-card>

                    <el-row style="margin-bottom:20px;margin-top:20px;">
                      <el-col
                        :span="6"
                        :offset="10"
                      >
                        <el-button
                          size="mini"
                          type="primary"
                          round
                          @click="addExplain"
                        >+Explain</el-button>
                      </el-col>
                    </el-row>
                    <!-- 我是一个分割线 -->
                    <el-row style="margin-top:20px;margin-bottom:20px;">
                      <el-divider content-position="left">词根词缀</el-divider>
                    </el-row>
                    <!-- 词根词缀按OL展示 -->
                    <div>
                      <el-select
                        v-model="selectValue"
                        style="width:100%"
                        multiple
                        filterable
                        remote
                        reserve-keyword
                        placeholder="词根词缀"
                        :remote-method="remoteMethod"
                        :loading="loading"
                      >
                        <el-option
                          v-for="item in options"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value"
                        />
                      </el-select>
                    </div>
                    <!-- 记忆技巧 -->
                    <md-input
                      v-model="word.memonic.skill"
                      style="margin-bottom:50px;margin-top:50px"
                      placeholder="记忆技巧"
                    >记忆技巧</md-input>

                  </div>
                </el-container>
              </el-scrollbar>
            </el-col>

            <!-- 百度翻译区域 -->
            <el-col
              :style="{ height: mainHeight-40+'px' }"
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
    </el-drawer>
  </div>
</template>
<script>
import baiduDist from '@/components/BaiduDist'
import mdInput from '@/components/MDinput'
export default {
  components: {
    baiduDist,
    mdInput
  },
  data () {
    return {
      options: [],
      selectValue: [],
      list: [],
      loading: false,
      states: ['Alabama', 'Alaska', 'Arizona',
        'Arkansas', 'California', 'Colorado',
        'Connecticut', 'Delaware', 'Florida',
        'Georgia', 'Hawaii', 'Idaho', 'Illinois',
        'Indiana', 'Iowa', 'Kansas', 'Kentucky',
        'Louisiana', 'Maine', 'Maryland',
        'Massachusetts', 'Michigan', 'Minnesota',
        'Mississippi', 'Missouri', 'Montana',
        'Nebraska', 'Nevada', 'New Hampshire',
        'New Jersey', 'New Mexico', 'New York',
        'North Carolina', 'North Dakota', 'Ohio',
        'Oklahoma', 'Oregon', 'Pennsylvania',
        'Rhode Island', 'South Carolina',
        'South Dakota', 'Tennessee', 'Texas',
        'Utah', 'Vermont', 'Virginia',
        'Washington', 'West Virginia', 'Wisconsin',
        'Wyoming'],
      mainHeight: 0,
      direction: 'rtl',
      drawer: false,
      activeNames: [0, 1, 2, 3, 4],
      value: null,
      iconClasses: ['icon-rate-face-1', 'icon-rate-face-2', 'icon-rate-face-3'], // 等同于 { 2: 'icon-rate-face-1', 4: { value: 'icon-rate-face-2', excluded: true }, 5: 'icon-rate-face-3' }
      colors: ['#99A9BF', '#F7BA2A', '#FF9900'], // 等同于 { 2: '#99A9BF', 4: { value: '#F7BA2A', excluded: true }, 5: '#FF9900' }
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
  created () {
    this.clientHeight = `${document.documentElement.clientHeight}`
    this.changeFixed(this.clientHeight)
  },
  mounted () {
    this.list = this.states.map(item => {
      return { value: `value:${item}`, label: `label:${item}` }
    })
  },

  methods: {
    addExplain () {
      this.word.explainList.push({ exampleList: [] })
    },
    delExplain (index) {
      this.word.explainList.splice(index, 1)
    },
    addExample (list, explain) {
      if (list === undefined) {
        list = []
        explain.exampleList = []
      }
      console.info(list)
      explain.exampleList.push({})
    },
    delExample (index, list) {
      list.splice(index, 1)
    },
    remoteMethod (query) {
      if (query !== '') {
        this.loading = true
        setTimeout(() => {
          this.loading = false
          this.options = this.list.filter(item => {
            return item.label.toLowerCase()
              .indexOf(query.toLowerCase()) > -1
          })
        }, 200)
      } else {
        this.options = []
      }
    },
    // 关闭抽屉前调用方法
    closeDrawerBefore (done) {
      done()
      // this.drawer = false
    },
    // 打开抽屉方法
    open (id, word) {
      if (id === null) {
        this.word.name = word
      }
      this.drawer = true
    },
    changeFixed (clientHeight) {
      // 动态修改样式
      console.log(clientHeight)
      this.mainHeight = (clientHeight - 50)
    },
    saveWord () {
      console.info('保存单词逻辑')
      console.info('this.word', this.word)
    }
  }
}
</script>
<style>
/* 卡片头部样式 */
.el-card__header {
  padding: 5px 20px;
  border-bottom: 1px solid #ebeef5;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
}
/* 抽屉头部样式 */
.el-drawer__header {
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  color: #72767b;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  margin-bottom: 0px;
  padding: 20px 20px 0;
}
/* 分割线样式 */
.el-divider--horizontal {
  display: block;
  height: 1px;
  width: 100%;
  margin: 12px 0;
}
/* 分割线字体样式 */
.el-divider__text {
  font-weight: 500;
  font-size: 20px;
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
