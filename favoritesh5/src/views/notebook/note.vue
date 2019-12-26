<template>
  <div>
    <el-container>
      <el-row
        style="height:100%"
        :gutter="20"
      >
        <!-- 我的词典详情显示区域 -->
        <el-col :span="myWordCol">
          <el-row>
            <el-switch
              v-model="noteEditMode"
              style="margin-bottom:5px;"
              active-text="编辑"
              inactive-text="阅读"
            />
            <el-rate
              v-model="note.degree"
              style="display:inline;margin-left:20px;float:right"
              :colors="rateColors"
              @change="rateChange"
            />

          </el-row>
          <!-- 第一行，主要显示区域 -->
          <el-row>
            <el-input
              v-model="note.title"
              placeholder="标题"
              @blur="blueTitleChange"
            />

            <!-- 我是一个分割线 -->
            <!-- 折叠面板。循环，单词详细解释 -->
            <el-row style="margin-top:10px;">
              <div
                ref="wordDetailMain"
                style="width:100%;height:100%"
              >
                <mavon-editor
                  v-show="noteEditMode"
                  v-model="note.content"
                  style="height:100%;"
                  fontSize="24px"
                  placeholder=""
                  codeStyle="vs"
                  previewBackground="transparent"
                  toolbarsBackground="transparent"
                  :navigation="false"
                  @save="contentChange"
                />
                <MDView
                  v-show="!noteEditMode"
                  ref="mdview"
                  @callback="mdViewSelectWordCallback"
                >
                  {{ note.content }}
                </MDView>
              </div>
            </el-row>
          </el-row>
        </el-col>

        <!-- 关联单词区域 -->
        <el-col
          ref="thirdDictHeight"
          :span="thirdWordCol"
        >
          <!-- 新增单词按钮 -->
          <el-row>
            <el-col :span="24">
              <el-button
                size="small"
                style="margin:10px"
                type="primary"
                @click="addWordRelClick"
              >关联单词</el-button>
            </el-col>
          </el-row>
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
          <el-container style="width:100%;height:100%;">
            <el-scrollbar style="width:100%;height:100%;">

              <div
                ref="wordListRef"
                style="width:100%;height:100%"
              >
                <el-card
                  v-for="(item, index) in wordList"
                  :key="item.id"
                  :index="index"
                  style="width:95%;margin-bottom:5px;"
                  shadow="hover"
                >
                  <div
                    slot="header"
                    class="clearfix"
                  >
                    <span
                      v-if="item.type === 'danger'"
                      style="color:#F56C6C"
                      @click="wordClick(item.name)"
                    >{{ item.name }}</span>
                    <span
                      v-if="item.type !== 'danger'"
                      style="color:#409EFF"
                      @click="wordClick(item.name)"
                    >{{ item.name }}</span>
                    <el-link
                      style="float: right; padding: 3px 0"
                      :underline="false"
                      @click="cancelRelWord(item.id)"
                    >
                      <i class="el-icon-close el-icon--right" />
                    </el-link>

                  </div>
                  {{ item.descp }}
                </el-card>
              </div>
            </el-scrollbar>
          </el-container>
        </el-col>

      </el-row>

      <!-- 单词编辑-组件 -->
      <editWord
        ref="editWordComp"
        @parentCallback="init"
      />
    </el-container>

  </div>
</template>
<script>
import editWord from '@/views/Dict/EditWord.vue'
import MDView from '@/components/MDView'
import { dictApi } from '@/api/dict'
import { notebookApi } from '@/api/notebook'

export default {
  components: {
    editWord, MDView
  },
  data () {
    return {
      noteEditMode: true,
      wordList: [],
      noteId: 43,
      note: {
        title: '',
        content: '',
        degree: 1
      },
      html: '',
      myWordCol: 18,
      thirdWordCol: 6,
      searchInput: null,
      collapseActiveIds: [0, 1, 2, 3, 4],
      rateColors: ['#99A9BF', '#F7BA2A', '#FF9900'],
      timer: '',
      curNoteContent: ''
    }
  },
  watch: {
    noteId: function (newValue) {
      this.getNoteDetail()
    },
    // 监听笔记模式 true:编辑；false:阅读
    noteEditMode: function (newValue) {
      if (!newValue) {
        this.initMdViewComp()
      }
    }
  },
  mounted() {
      this.timer = setInterval(this.contentChange, 5000)
    },
    beforeDestroy() {
      clearInterval(this.timer)
    },
    created() {
      this.getNoteDetail()
    },

  methods: {
    // 供父组件调用，用于初始化
    init(noteId) {
      this.noteId = noteId
    },
    // 阅读模式组件初始化
    initMdViewComp() {
      this.$refs.mdview.open(this.note.content)
    },
    // 编辑完标题时触发
    blueTitleChange() {
      notebookApi.noteRename({ 'id': this.note.id, 'title': this.note.title }).then(response => {
        // const node = _.getCurrentNode()
        // console.info(node)
        // node.label = this.note.title
        // 这里需要调用父节点，并且父节点改名
      }).catch(err => {
        console.log(err)
      })
    },
    // 内容变化时触发
    contentChange() {
      if (this.curNoteContent === this.note.content) {
        return
      }
      this.curNoteContent = this.note.content
      notebookApi.noteContent({ 'id': this.note.id, 'content': this.note.content }).then(response => {
      }).catch(err => {
        console.log(err)
      })
    },
    // 获取关联单词列表
    getRelWordListInit() {
      notebookApi.noteRelWordList(this.note.id).then(response => {
        this.wordList = response.wordList
      }).catch(err => {
        console.log(err)
      })
    },
    // 文章关注度变化
    rateChange() {
      notebookApi.noteDegree({ 'id': this.note.id, 'degree': this.note.degree }).then(response => {
        console.info(response)
      }).catch(err => {
        console.log(err)
      })
    },
    // 阅读模式选择单词回调
    mdViewSelectWordCallback(txt) {
      this.searchInput = txt
      this.search()
    },
    // 获取笔记详情
    getNoteDetail() {
      notebookApi.noteDetail(this.noteId).then(response => {
        this.note = response.note
        this.initMdViewComp()
        this.getRelWordListInit()
      })
    },
    // 点击打开编辑单词
    wordClick (word) {
      this.$refs.editWordComp.open(null, word)
    },
    // 关联单词按钮
    addWordRelClick () {
      console.info('this.searchInput', this.searchInput)
      this.$refs.editWordComp.open(null, this.searchInput)
    },
    // 关联单词搜索
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
      for (let j = 0; j < this.$refs.wordListRef.children.length; j++) {
        const element = this.$refs.wordListRef.children[j]
        const attriIndex = element.getAttribute('index')
        if (attriIndex + '' === scrollIndex + '') {
          element.scrollIntoView()
        }
      }
    },
    // 初始化搜索
    initSearch () {
      this.wordList.forEach(element => {
        element.type = 'default'
      })
    },
    // 取消关联
    cancelRelWord (wordId) {
      notebookApi.noteRelWordDelete({ 'id': this.note.id, 'wordId': wordId })
        .then(response => {
          this.getRelWordListInit()
        })
        .catch(err => {
          console.error(err)
        })
    }
  }
}
</script>
<style>
.markdown-body {
  -ms-text-size-adjust: 100%;
  -webkit-text-size-adjust: 100%;
  line-height: 1.5;
  color: #24292e;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, Arial,
    sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
  font-size: 30px;
  line-height: 1.5;
  word-wrap: break-word;
}
.el-tree-node__expand-icon {
  cursor: pointer;
  color: blueviolet;
  font-size: 26px;
  -webkit-transform: rotate(0);
  transform: rotate(0);
  -webkit-transition: -webkit-transform 0.3s ease-in-out;
  transition: -webkit-transform 0.3s ease-in-out;
  transition: transform 0.3s ease-in-out;
  transition: transform 0.3s ease-in-out, -webkit-transform 0.3s ease-in-out;
  transition: transform 0.3s ease-in-out, -webkit-transform 0.3s ease-in-out;
}
.el-tree-node__content:hover {
  background-color: transparent;
}
.el-scrollbar .el-scrollbar__wrap .el-scrollbar__view {
  white-space: nowrap;
}
.el-tree-node__content span button {
  display: none;
}
.el-tree-node__content:hover span button {
  display: inline;
}
.el-tree--highlight-current .el-tree-node.is-current > .el-tree-node__content {
  color: rgb(64, 158, 255);
  font-weight: 900;
  background-color: transparent;
}
.el-tree--highlight-current .el-tree-node.is-current > .el-tree-node__content {
  background-color: transparent;
}
.el-tree-node:focus > .el-tree-node__content {
  background-color: transparent;
}
.el-tree-node__content {
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  height: 36px;
  cursor: pointer;
}
.el-tree {
  position: relative;
  cursor: default;
  background: none;
  font-size: 20px;
  color: #606266;
}
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
