<template>
  <div>
    <el-container>
      <!-- 侧边栏 -->
      <el-aside
        v-show="showTreeFlag"
        width="auto"
        style="margin:10px;margin-right:0px"
      >
        <!-- 新增单词按钮 -->
        <el-row>
          <el-button
            size="mini"
            type="primary"
            @click="append"
          >新增</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="deleteNode"
          >删除</el-button>
          <!-- 隐藏/显示三方词典 图标按钮 -->
          <el-link
            style="float:right;font-size:20px;margin-left:10px"
            @click="showTreeFlag=!showTreeFlag"
          >
            <i class="el-icon-s-fold" />
          </el-link>
        </el-row>

        <!-- 单词搜索输入框 -->
        <el-row style="margin-top:7px">
          <el-input
            v-model="filterText"
            placeholder="输入关键字进行过滤"
          />
        </el-row>

        <el-row style="margin-top:10px;">
          <!-- 笔记本书 -->
          <el-scrollbar>
            <!-- 这里应该是个树 default-expand-all 是否默认展开所有节点
             -->
            <el-tree
              ref="tree"
              empty-text="未命名"
              :style="getTreeDivStyle()"
              class="filter-tree"
              :data="treeData"
              :props="defaultProps"
              :filter-node-method="filterNode"
              node-key="id"
              :check-on-click-node="true"
              :default-expanded-keys="[1]"
              :default-checked-keys="[1]"
              auto-expand-parent
              highlight-current
              :expand-on-click-node="true"
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
          </el-scrollbar>
        </el-row>
      </el-aside>

      <!-- 隐藏笔记树时显示 -->
      <el-aside
        v-show="!showTreeFlag"
        width="20px"
        style="margin:10px;margin-right:0px"
      >
        <el-link
          style="float:right;font-size:20px;margin-left:10px"
          @click="showTreeFlag=!showTreeFlag"
        >
          <i class="el-icon-s-unfold" />
        </el-link>
      </el-aside>

      <!-- 笔记内容显示区域 -->
      <el-main>
        <noteComp
          ref="noteCompRef"
          @parentCallback="noteCompRefCallback"
        />
      </el-main>

    </el-container>

  </div>
</template>
<script>
import noteComp from '@/views/notebook/note.vue'
import { notebookApi } from '@/api/notebook'

export default {
  components: {
    noteComp
  },
  data () {
    return {
      showTreeFlag: true,
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
      treeTopHeight: 150
    }
  },

  computed: {
    // 监听显示区域高度
    mainBodyHeight() {
      return this.$store.state.mainBodyHeight
    }
  },

  watch: {
    // 笔记监听搜索框
    filterText(val) {
        this.$refs.tree.filter(val)
      }
  },

  created() {
    this.getTree()
  },

  methods: {

    // 笔记标题修改回调
    noteCompRefCallback(title) {
      this.note.label = title
    },

    // 获取树的宽-高样式
    getTreeDivStyle() {
      return 'width:100%;height:' + (this.mainBodyHeight - this.treeTopHeight) + 'px'
    },

    // 笔记树点击操作
    handleNodeClick() {
      this.note = this.getCurrentNode()
      this.$refs.noteCompRef.init(this.note.id)
    },

    // 远程获取树节点信息
    getTree() {
      const _ = this
      notebookApi.noteTree().then(response => {
        _.treeData = response.tree
      })
    },

    handleDragStart(node, ev) {
      // console.log('drag start', node)
    },
    handleDragEnter(draggingNode, dropNode, ev) {
      // console.log('tree drag enter: ', dropNode.label)
    },
    handleDragLeave(draggingNode, dropNode, ev) {
      // console.log('tree drag leave: ', dropNode.label)
    },
    handleDragOver(draggingNode, dropNode, ev) {
      // console.log('tree drag over: ', dropNode.label)
    },
    handleDragEnd(draggingNode, dropNode, dropType, ev) {
    let parentNode
    if (dropType === 'inner') {
      console.info('放到' + dropNode.label + '节点下面了')
      parentNode = dropNode
    } else {
      console.info('放到' + dropNode.parent.label + '节点下面了')
            parentNode = dropNode.parent
    }

          console.info(parentNode)

      const childredIds = []
      parentNode.childNodes.forEach(child => {
        childredIds.push(child.key)
      })
      const data = { 'parentId': parentNode.key, 'childrenIds': childredIds }
      notebookApi.noteSort(data).then(response => {

      })
      console.info(data)
    },
    handleDrop(draggingNode, dropNode, dropType, ev) {
      // console.log('tree drop: ', dropNode.label, dropType)
    },
    allowDrop(draggingNode, dropNode, type) {
      // ROOT笔记本不能拖拽
      if (draggingNode.key === 1) {
        return false
      }
      // 不能放置到 ROOT笔记本同一级别

      if (dropNode.key === 1) {
        return false
      }
      return true
    },
    allowDrag(draggingNode) {
      return draggingNode.data.label.indexOf('三级 3-2-2') === -1
    },

    // 过滤笔记
    filterNode(value, data) {
        if (!value) return true
        return data.label.indexOf(value) !== -1
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
            this.$refs.tree.remove(node)
              this.$notify.success({
                title: '成功',
                message: '删除笔记成功'
              })
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
    },
    // 添加一个笔记
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
        this.curTreeKey = response.id
        this.$refs.tree.append({ 'label': '未命名', 'id': this.curTreeKey }, node)
        this.$refs.tree.setCurrentKey(this.curTreeKey)
        this.$refs.tree.store.nodesMap[node.id].expanded = true
        this.handleNodeClick()
      })
    },

      // 获取当前选中节点
    getCurrentNode() {
      console.info(this.$refs.tree.getCurrentNode())
      return this.$refs.tree.getCurrentNode()
    }
  }
}
</script>
<style>
.el-tree-node__expand-icon {
  cursor: pointer;
  color: blueviolet;
  font-size: 22px;
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
  height: 30px;
  cursor: pointer;
}
.el-tree {
  position: relative;
  cursor: default;
  background: none;
  font-size: 16px;
  color: #606266;
}
</style>
