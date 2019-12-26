<template>
  <div>
    <el-container>
      <!-- 侧边栏 -->
      <el-aside
        width="220px"
        style="margin:10px;"
      >
        <!-- 按钮、排序、搜索框 -->
        <div>
          <!-- 新增单词按钮 -->
          <el-row>
            <el-col :span="12">
              <el-button
                size="small"
                type="primary"
                @click="addWordClick"
              >新增</el-button>
            </el-col>
            <el-col :span="12">
              <el-dropdown
                size="small"
                style="float:right;margin-top:5px;"
                @command="handleWordListOrderDropDown"
              >
                <span class="el-dropdown-link">
                  {{ dictWordListOrderTypeName }}<i class="el-icon-arrow-down el-icon--right" />
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item
                    v-for="item in wordListOrderTypes"
                    :key="item.id"
                    :command="item.id"
                  >{{ item.name }}</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </el-col>
          </el-row>

          <!-- 单词搜索输入框 -->
          <el-row style="margin:10px;">
            <el-input
              v-model="searchInput"
              placeholder="Search"
              autocomplete="on"
              clearable
              @input="search"
              @keyup.enter.native="keyupEnter"
            />
          </el-row>
        </div>

        <el-scrollbar>
          <div
            ref="wordListRef"
            :style="getAsiderWordListStyle()"
          >
            <div
              v-for="(item, index) in wordList"
              :key="item.id"
              :index="index"
              style="height: 34px;margin-left:5px;"
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
      <el-main style="margin:10px !important">
        <WordView ref="wordViewRef" />
      </el-main>
    </el-container>

    <!-- 单词编辑-组件 -->
    <editWord
      ref="addWordComp"
      @parentCallback="addWordCallback"
    />
  </div>
</template>
<script>
import WordView from './WordView'
import editWord from './EditWord.vue'
import { dictApi } from '@/api/dict'
export default {
  components: {
    WordView,
    editWord
  },
  data () {
    return {
      asiderTopHeight: 120,
      searchInput: null,
      wordList: [
      ],
      curWordName: null,
      dictWordListOrderTypeName: '',
      wordListOrderTypes: [
        {
          'id': 1,
          'name': '默认排序'
        },
        {
          'id': 2,
          'name': '陌生程度'
        },
        {
          'id': 3,
          'name': '查询次数'
        },
        {
          'id': 4,
          'name': '修改时间'
        }
      ]
    }
  },

  computed: {
    mainBodyHeight() {
      return this.$store.state.mainBodyHeight
    },
    dictWordListOrderType() {
      console.info('this.$store.state.dictWordListOrderType', this.$store.state.dictWordListOrderType)
      return this.$store.state.dictWordListOrderType
    }
  },
  watch: {
    curWordName: function (newValue, oldValue) {
      this.openWordView(newValue)
      this.initWordList()
    },
    dictWordListOrderType: function (newValue, oldValue) {
      this.setDictWordListOrderTypeName()
    }
  },
  created() {
    this.setDictWordListOrderTypeName()
    this.wordListQuery()
  },
  methods: {
    setDictWordListOrderTypeName() {
      this.wordListOrderTypes.forEach(element => {
        if (this.dictWordListOrderType === element.id) {
          console.info('element.name', element.name)
          this.dictWordListOrderTypeName = element.name
        }
      })
    },
    handleWordListOrderDropDown(command) {
        console.info(command)
        this.$store.state.dictWordListOrderType = command
        this.wordListQuery()
    },
    addWordCallback(wordId, wordName) {
      if (wordName && wordName !== null && wordName !== '') {
        this.curWordName = wordName
        this.wordListQuery()
        }
    },
    getAsiderWordListStyle() {
      return 'height:' + (this.mainBodyHeight - this.asiderTopHeight) + 'px'
    },
    rateChange() {
      dictApi.wordDegree({ 'id': this.word.id, 'degree': this.word.degree }).then(response => {
        console.info(response)
      }).catch(err => {
        console.log(err)
      })
    },
    wordListQuery() {
      const queryParams = {
        'sortType': this.dictWordListOrderType
      }
      dictApi.wordList(queryParams).then(response => {
        this.wordList = response.wordList
        if (this.curWordName === null) {
          this.curWordName = this.wordList[0].name
        }
        this.initWordList()
      }).catch(err => {
        console.log(err)
      })
    },
    clickWord(word) {
      if (word === null || word === undefined) {
          this.curWordName = this.searchInput
      } else {
        this.curWordName = word
      }
    },

    keyupEnter() {
      this.clickWord(this.searchInput)
    },
    addWordClick () {
      this.$refs.addWordComp.open(null, this.searchInput)
    },

    openWordView (word) {
      this.$refs.wordViewRef.open(word)
    },

    // 搜索单词方法
    search () {
      this.initWordList()
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
      for (let j = 0; j < this.$refs.wordListRef.children.length; j++) {
        const element = this.$refs.wordListRef.children[j]
        const attriIndex = element.getAttribute('index')
        if (attriIndex + '' === scrollIndex + '') {
          element.scrollIntoView()
        }
      }
    },
    // 初始化单词列表
    initWordList () {
      for (let i = 0; i < this.wordList.length; i++) {
        const item = this.wordList[i]
        if (item.name === this.curWordName) {
          item.type = 'primary'
        } else {
          item.type = 'default'
        }
      }
      // 强行渲染
      this.$forceUpdate()
    }
  }
}
</script>
<style>
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
