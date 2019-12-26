<template>
  <div>
    <el-container style="height:100%">
      <!-- 侧边栏 -->
      <el-aside
        width="320px"
        style="hegiht:100%;"
      >

        <!-- 单词搜索输入框 -->
        <el-row>
          <el-input
            v-model="filterText"
            placeholder="输入关键字进行过滤"
          />
        </el-row>
        <!-- 新增单词按钮 -->
        <el-row>
          <el-button
            type="primary"
            @click="append"
          >新增</el-button>
          <el-button
            type="primary"
            @click="deleteNode"
          >删除</el-button>
        </el-row>
        <el-scrollbar style="height:100%">

          <div ref="listRef">
            <!-- 这里应该是个树
                 default-expand-all 是否默认展开所有节点

             -->
            <div style="heght:100%;margin-top:20px;">
              <el-tree
                ref="tree"
                class="filter-tree"
                :data="treeData"
                :props="defaultProps"
                :filter-node-method="filterNode"
                node-key="id"
                check-on-click-node
                :default-expanded-keys="[1]"
                :default-checked-keys="[1]"
                auto-expand-parent
                highlight-current
                :expand-on-click-node="false"
                draggable
                :allow-drop="allowDrop"
                :allow-drag="allowDrag"
                @node-click="handleNodeClick"
                @node-drag-start="handleDragStart"
                @node-drag-enter="handleDragEnter"
                @node-drag-leave="handleDragLeave"
                @node-drag-over="handleDragOver"
                @node-drag-end="handleDragEnd"
                @node-drop="handleDrop"
              >
                <span
                  slot-scope="{ node }"
                  class="custom-tree-node"
                >
                  <span>{{ node.label }}</span>
                </span>
              </el-tree>
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
              <el-switch
                v-model="noteSwitch"
                style="margin-bottom:5px;"
                active-text="编辑"
                inactive-text="阅读"
              />
              <el-rate
                v-model="word.degree"
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
                @blur="titleChange"
              />

              <!-- 我是一个分割线 -->
              <!-- 折叠面板。循环，单词详细解释 -->
              <el-row style="margin-top:10px;">
                <div
                  ref="wordDetailMain"
                  style="width:100%;height:100%"
                >
                  <mavon-editor
                    v-show="noteSwitch"
                    v-model="note.content"
                    style="height:100%;"
                    fontSize="24px"
                    placeholder=""
                    codeStyle="vs"
                    previewBackground="transparent"
                    toolbarsBackground="transparent"
                    :navigation="false"
                    @change="contentChange"
                    @save="contentChange"
                  />
                  <MDView
                    v-show="!noteSwitch"
                    ref="mdview"
                    @callback="mdViewSelectWord"
                  >
                    {{ note.content }}
                  </MDView>
                </div>
              </el-row>
            </el-row>
          </el-col>

          <!-- 百度翻译区域 -->

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
                  @click="addWordClick"
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
      noteSwitch: true,
      markDownProps: {
        value: 'test',
        language: 'en',
        fontSize: '120px'
      },
      wordList: [
      ],
      note: {
        title: '',
        content: ''
      },
      html: '',
      languageTypeList: {
        'en': 'en_US',
        'zh': 'zh_CN',
        'es': 'es_ES'
      },
      treeData: [{
        id: 1,
        label: '笔记本',
        children: [{}]
      }],
        defaultProps: {
          children: 'children',
          label: 'label'
        },
      filterText: '',
      curTreeKey: 1,
      myWordCol: 18,
      thirdWordCol: 6,
      searchInput: null,
      collapseActiveIds: [0, 1, 2, 3, 4],
      value: '',
      rateColors: ['#99A9BF', '#F7BA2A', '#FF9900'], // 等同于 { 2: '#99A9BF', 4: { value: '#F7BA2A', excluded: true }, 5: '#FF9900' }
      word: {
        name: '',
        symbol: '',
        descp: '',
        defList: [
        ],
        skillDesc: '',
        etymaIdList: []
      }
    }
  },
  computed: {
    language() {
      return this.languageTypeList[this.$store.getters.language]
    }
  },
  watch: {
    filterText(val) {
        this.$refs.tree.filter(val)
      },
    noteSwitch: function () {
      if (this.noteSwitch) {
        this.$refs.mdview.open(this.note.content)
      }
    }
  },
  created() {
    this.wordListInit()
    this.getTree()
  },

  methods: {
    noteSwitchChange() {
      if (this.noteSwitch) {
        this.$refs.mdview.open(this.note.content)
      }
    },
    handleNodeClick() {
      this.getNoteDetail()
    },
    titleChange() {
      const _ = this
      notebookApi.noteRename({ 'id': this.note.id, 'title': this.note.title }).then(response => {
        const node = _.getCurrentNode()
        console.info(node)
        node.label = this.note.title
      }).catch(err => {
        console.log(err)
      })
    },
    contentChange() {
      notebookApi.noteContent({ 'id': this.note.id, 'content': this.note.content }).then(response => {
      }).catch(err => {
        console.log(err)
      })
    },
    wordListInit() {
      notebookApi.noteRelWordList(this.note.id).then(response => {
        this.wordList = response.wordList
      }).catch(err => {
        console.log(err)
      })
    },
    getTree() {
      const _ = this
      notebookApi.noteTree().then(response => {
        console.info('response.tree', response)
        _.treeData = response.tree
        // _.setCheckedKeys()
      }).catch(err => {
        console.log(err)
      })
    },
    getHtml() {
      this.html = this.$refs.markdownEditor.getHtml()
      console.log(this.html)
    },
    handleDragStart(node, ev) {
        console.log('drag start', node)
      },
      handleDragEnter(draggingNode, dropNode, ev) {
        console.log('tree drag enter: ', dropNode.label)
      },
      handleDragLeave(draggingNode, dropNode, ev) {
        console.log('tree drag leave: ', dropNode.label)
      },
      handleDragOver(draggingNode, dropNode, ev) {
        console.log('tree drag over: ', dropNode.label)
      },
      handleDragEnd(draggingNode, dropNode, dropType, ev) {
        console.log('tree drag end: ', dropNode && dropNode.label, dropType)
      },
      handleDrop(draggingNode, dropNode, dropType, ev) {
        console.log('tree drop: ', dropNode.label, dropType)
      },
      allowDrop(draggingNode, dropNode, type) {
        if (dropNode.data.label === '二级 3-1') {
          return type !== 'inner'
        } else {
          return true
        }
      },
      allowDrag(draggingNode) {
        return draggingNode.data.label.indexOf('三级 3-2-2') === -1
      },
    filterNode(value, data) {
        if (!value) return true
        return data.label.indexOf(value) !== -1
      },
      setCheckedKeys() {
        // this.$refs.tree.setCheckedKeys([this.curTreeKey]);
      },
    // 树-添加子节点
    deleteNode() {
      const node = this.getCurrentNode()
      if (node === undefined || node === null || node.length === 0) {
        this.$notify.error({
          title: '错误',
          message: '请选择一个节点再添加'
        })
        return
      }
        this.$confirm('此操作将永久删除该笔记, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          notebookApi.noteDelete({ 'id': node.id }).then(response => {
            console.info(response)
            this.$refs.tree.remove(node)
            this.getNoteDetail()
              this.$notify.success({
                title: '成功',
                message: '删除笔记成功'
              })
            // this.getTree()
          }).catch(err => {
            console.log(err)
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
    },
    append() {
      const node = this.getCurrentNode()
      if (node === undefined || node === null || node.length === 0) {
        this.$notify.error({
          title: '错误',
          message: '请选择一个节点再添加'
        })
        return
      }
      notebookApi.noteCreate({ 'name': '未命名', 'patientId': node.id }).then(response => {
        console.info(response)
        this.curTreeKey = response.id
        this.$refs.tree.append({ 'label': '未命名', 'id': this.curTreeKey }, node)
        this.$refs.tree.setCurrentKey(this.curTreeKey)
        this.$refs.tree.store.nodesMap[node.id].expanded = true
        this.getNoteDetail()
      }).catch(err => {
        console.log(err)
      })
      },

      getCurrentNode() {
        console.info(this.$refs.tree.getCurrentNode())
        return this.$refs.tree.getCurrentNode()
      },

      remove(node, data) {
        const parent = node.parent
        const children = parent.data.children || parent.data
        const index = children.findIndex(d => d.id === data.id)
        children.splice(index, 1)
      },

      renderContent(h, { node, data, store }) {
        return (
          <span class='custom-tree-node'>
            <span>{node.label}</span>
            <span>
              <el-button size='mini' type='text' on-click={ () => this.append(data) }>Append</el-button>
              <el-button size='mini' type='text' on-click={ () => this.remove(node, data) }>Delete</el-button>
            </span>
          </span>)
      },
    rateChange() {
      dictApi.wordDegree({ 'id': this.word.id, 'degree': this.word.degree }).then(response => {
        console.info(response)
      }).catch(err => {
        console.log(err)
      })
    },
    mdViewSelectWord(txt) {
      this.searchInput = txt
      this.search()
    },
    init(wordId) {
      console.info(wordId)
      notebookApi.noteRelWord({ 'id': this.note.id, 'wordId': wordId })
        .then(response => {
          this.wordListInit()
        })
        .catch(err => {
          console.log(err)
        })
    },
    getNoteDetail() {
      const curNode = this.getCurrentNode()
      notebookApi.noteDetail(curNode.id).then(response => {
        this.note = response.note
        this.wordListInit()
      })
    },
    wordClick (word) {
      this.$refs.editWordComp.open(null, word)
    },
    addWordClick () {
      console.info('this.searchInput', this.searchInput)
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
      for (let j = 0; j < this.$refs.wordListRef.children.length; j++) {
        const element = this.$refs.wordListRef.children[j]
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
    cancelRelWord (wordId) {
      notebookApi.noteRelWordDelete({ 'id': this.note.id, 'wordId': wordId })
        .then(response => {
          this.wordListInit()
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
