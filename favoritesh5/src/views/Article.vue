<template>
  <el-container>
    <el-aside width="200px">
      <el-menu
        default-active="1"
        class="el-menu-vertical-demo"
        :collapse="isCollapse"
      >
        <el-menu-item
          v-for="item in typeList"
          :key="item.id"
          @click="clickMenu(item.id)"
        >
          <span slot="title">{{ item.name }}</span>
        </el-menu-item>
      </el-menu>
    </el-aside>
    <el-main>
      <el-row :gutter="20">
        <el-col :span="8">
          <video controls="" autoplay="" name="media"><source src="http://dict.youdao.com/dictvoice?audio=provide.&amp;le=eng" type="audio/mpeg"></video>
          <quill-editor ref="myQuillEditor" v-model="content" :options="editorOption" @blur="onEditorBlur($event)" @focus="onEditorFocus($event)" @change="onEditorChange($event)" />
          <button @click="saveHtml">保存</button>
        </el-col>
        <el-col :span="8">
          <quill-editor ref="myQuillEditor2" v-model="content2" :options="editorOption" @blur="onEditorBlur($event)" @focus="onEditorFocus($event)" @change="onEditorChange($event)" />
          <button @click="saveHtml">保存</button>
        </el-col>
        <el-col :span="8">
          <el-button type="primary" style="margin-left: 16px;" @click="drawer = true">
            点我打开
          </el-button>
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>introduce</span><span>   vt. 介绍；引进；提出；采用</span><span>   ˌɪntrəˈduːs</span>
              <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
            </div>
            <div v-for="o in 4" :key="o" class="text item">
              {{ '列表内容 ' + o }}
            </div>
          </el-card>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>
<script>
export default {
  components: {
  },
  data () {
    return {
      direction: 'rtl',
      content: '<h2>I am Example</h2>',
      content2: '<h2>I am Example</h2>',
      editorOption: {
        // some quill options
      },
      value1: null,
      isCollapse: false,
      activeIndex: '1',
      activeIndex2: '1',
      type: 0,
      itemMap: new Map(),
      value: '',
      typeList: [
        {
          id: 1,
          name: '0、Introduction to Objects 对象导论'
        },
        {
          id: 2,
          name: '1、The progress of abstraction  抽象过程'
        },
        {
          id: 3,
          name: '2、An object has an interface  每个对象都有一个接口'
        }
      ]
    }
  },

  computed: {
    editor () {
      return this.$refs.myQuillEditor.quill
    },

    editor2 () {
      return this.$refs.myQuillEditor2.quill
    }
  },
  methods: {
    onEditorReady (editor) { // 准备编辑器
    },
    onEditorBlur () { }, // 失去焦点事件
    onEditorFocus () { }, // 获得焦点事件
    onEditorChange () { }, // 内容改变事件
    saveHtml: function (event) {
      alert(this.content)
    },
    clickMenu (type) {
      this.type = type
    },
    handleClose (done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done()
        })
        .catch(_ => { })
    },
    changeType (type) { }
  }
}
</script>
<style>
.el-header {
  padding: 0px !important;
}
.el-card__body {
  padding: 15px !important;
}
.item {
  margin-top: 10px;
  margin-right: 40px;
}
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}

.box-card {
  width: 480px;
}
</style>
