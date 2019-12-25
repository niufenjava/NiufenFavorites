<template>
  <div>
    <el-row>
      <el-col :span="myWordCol">
        <div style="margin-right:10px">
          <div style="margin-bottom:10px;margin-top:10px;">
            <el-row>

              <!-- 隐藏/显示三方词典 -->
              <el-link
                style="float:right;font-size:20px;margin-left:10px"
                @click="showThirdDictFlag=!showThirdDictFlag"
              >
                <i
                  v-if="showThirdDictFlag"
                  class="el-icon-s-unfold"
                />
                <i
                  v-if="!showThirdDictFlag"
                  class="el-icon-s-fold"
                />
              </el-link>

              <el-rate
                v-model="word.degree"
                style="display:inline;margin-left:20px;float:right"
                :colors="rateColors"
                @change="rateChange"
              />

              <el-switch
                v-model="showChFlag"
                active-text="中英"
                inactive-text="全英"
                @click="showChFlag =!showChFlag"
              />
            </el-row>
            <!-- 第一行，主要显示区域 -->
            <el-row>
              <span style="color:#409eff;font-size:30px;font-weight:800">{{ word.name }}</span>
              <code style="margin-left:20px;">[{{ word.soundmark }}]</code>

              <!-- 编辑按钮 -->
              <el-link
                style="margin-top:15px;float:right"
                type="default"
                icon="el-icon-edit"
                @click="editWordClick"
              >编辑</el-link>
            </el-row>

            <!-- 单词简单解释 -->
            <el-row v-show="showChFlag">
              <span style="font-size:14px;">{{ word.descp }}</span>
            </el-row>
          </div>

          <!-- 我是一个分割线 -->
          <!-- 折叠面板。循环，单词详细解释 -->
          <el-row style="background-color:white">
            <el-scrollbar>
              <div :style="getWordViewMainStyle()">
                <div style="margin:10px;">
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
                          <span>{{ def.typeDesc }}</span><span
                            v-show="showChFlag"
                            style="margin-left:10px"
                          >{{ def.name }}</span>
                        </div>
                      </template>

                      <!-- 单词详解 -->
                      <div>
                        <div>
                          <span>{{ def.explainEn }}</span>
                        </div>
                        <div v-show="showChFlag">
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
                          <div v-show="showChFlag">
                            <span style="color: #909399">{{ example.exampleCh }}</span>
                          </div>
                        </li>
                      </ol>
                    </el-collapse-item>
                  </el-collapse>

                  <h4>记忆技巧</h4>
                  <div style="background-color:white;margin-bottom:200px;">
                    <span>{{ word.skillDesc }}</span>
                  </div>
                </div>
              </div>
            </el-scrollbar>

          </el-row>
        </div>
      </el-col>

      <el-col
        v-show="showThirdDictFlag"
        ref="thirdDictHeight"
        :span="thirdWordCol"
      >
        <thirdDict ref="thirdDictRef" />
      </el-col>

    </el-row>
    <editWord
      ref="editWordComp"
      @parentCallback="init"
    />
  </div>
</template>
<script>
import editWord from './EditWord.vue'
import thirdDict from '@/components/ThirdDict'
import { dictApi } from '@/api/dict'
export default {
  name: 'WordView',
  components: {
    editWord, thirdDict
  },
  data () {
    return {
      showChFlag: true,
      showThirdDictFlag: true,
      myWordCol: 12,
      thirdWordCol: 12,
      collapseActiveIds: [0, 1, 2, 3, 4],
      rateColors: ['#99A9BF', '#F7BA2A', '#FF9900'],
      word: {
        name: 'concept',
        symbol: '',
        descp: '',
        defList: [
        ],
        skillDesc: '',
        etymaIdList: []
      },
      wordViewTopHeight: 150,
      wordViewExplainHeight: null
    }
  },
  computed: {
    mainBodyHeight() {
      return this.$store.state.mainBodyHeight
    }
  },
  watch: {
    showThirdDictFlag (newValue, oldValue) {
      console.info(newValue)
      if (!newValue) {
        this.myWordCol = 24
      } else {
        this.myWordCol = 12
        this.thirdWordCol = 12
      }
    },
      showChFlag(newValue) {
        if (newValue) {
        this.wordViewTopHeight = this.wordViewTopHeight + 20
        } else {
        this.wordViewTopHeight = this.wordViewTopHeight - 20
        }
      }

  },

  created() {
    this.init()
  },
  methods: {
    open(word) {
      this.word.name = word
      this.wordInfo(this.word.name)
      this.openThirdDict(this.word.name)
    },
    getWordViewMainStyle() {
      return 'height:' + (this.mainBodyHeight - this.wordViewTopHeight) + 'px'
    },
    rateChange() {
      dictApi.wordDegree({ 'id': this.word.id, 'degree': this.word.degree }).then(response => {
        console.info(response)
      }).catch(err => {
        console.log(err)
      })
    },
    init() {
      this.wordInfo(this.word.name)
    },

    openThirdDict (word) {
      this.$refs.thirdDictRef.open(word)
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
    // 打开单词编辑
    editWordClick () {
      this.$refs.editWordComp.open(null, this.word.name)
    }
  }
}
</script>
<style>
h4 {
  margin-bottom: 10px;
}
/* .el-collapse-item__header {
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  height: 48px;
  line-height: 48px;
  background-color: transparent;
}
.el-collapse-item__wrap {
  will-change: height;
  background-color: transparent;
} */
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
