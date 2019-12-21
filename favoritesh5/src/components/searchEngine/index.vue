<template>
  <div>
    <div style="margin-top:0px;">
      <el-input
        v-model="keyword"
        placeholder="搜索内容"
        @keyup="get($event)"
        @keydown.enter="search()"
        @keydown.down="selectDown()"
        @keydown.up.prevent="selectUp()"
      >
        <!--
        <el-button
          slot="prepend"
          size="medium"
          @click="switchEngine()"
        >Baidu</el-button> -->
        <el-button
          slot="append"
          type="primary"
          icon="el-icon-search"
          @click="search()"
        >Baidu</el-button>
      </el-input>
    </div>
    <!-- <input v-model="keyword" type="text" @keyup="get($event)" @keydown.enter="search()" @keydown.down="selectDown()" @keydown.up.prevent="selectUp()"> -->
    <!-- 这是一个小叉叉，点击它可清除输入框内容 -->
    <!-- <span class="search-reset" @click="clearInput()">&times;</span> -->
    <!-- <button class="search-btn" @click="search()">搜一下</button> -->
    <div class="search-select">
      <!-- transition-group也是vue2.0中的新特性,tag='ul'表示用ul包裹v-for出来的li -->
      <transition-group
        v-cloak
        name="itemfade"
        tag="ul"
        mode="out-in"
      >
        <li
          v-for="(value,index) in myData"
          :key="value"
          :class="{selectback:index==now}"
          class="search-select-option search-select-list"
          @mouseover="selectHover(index)"
          @click="selectClick(index)"
        >
          {{ value }}
        </li>
      </transition-group>
    </div>
  </div>
</template>

<script type="text/javascript">
import baiduPng from '@/static/img/Baidu_256x84.png'
import googlePng from '@/static/img/Google_92x30.png'
export default {
  // 注册组件
  components: {
  },
  data: function () {
    return {
      baiduPng: baiduPng,
      googlePng: googlePng,
      myData: [], // 用来接收ajax得到的数据
      keyword: '', // v-model绑定的输入框的value
      now: -1,
      cities: [{
        value: 'baidu',
        label: 'Baidu'
      }, {
        value: 'google',
        label: 'Google'
      }],
      value2: '',
      value3: true,
      searchIndex: 0,
      logoData: [{
        name: '百度搜索',
        searchSrc: 'https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=0&rsv_idx=1&tn=baidu&wd='
      }, {
        name: '搜狗搜索',
        searchSrc: 'https://www.sogou.com/web?query='
      }]
    }
  },
  methods: {
    // &event是实参，表示event对象
    get: function (ev) {
      // 如果按得键是上或者下，就不进行ajax
      if (ev.keyCode === 38 || ev.keyCode === 40) {
        return
      }

      this.$http.jsonp('https://sug.so.360.cn/suggest?word=' + this.keyword + '&encodein=utf-8&encodeout=utf-8').then(function (res) {
        this.myData = res.data.s
      })
    },
    selectDown: function () {
      this.now++
      // 到达最后一个时，再按下就回到第一个
      if (this.now === this.myData.length) {
        this.now = 0
      }
      this.keyword = this.myData[this.now]
    },
    selectUp: function () {
      this.now--
      // 同上
      if (this.now === -1) {
        this.now = this.myData.length - 1
      }
      this.keyword = this.myData[this.now]
    },
    search: function () {
      // 打开对应的搜索界面
      window.open(this.logoData[this.searchIndex].searchSrc + this.keyword)
    },
    switchEngine: function () {
    },
    selectHover: function (index) {
      this.now = index
    },
    selectClick: function (index) {
      this.keyword = this.myData[index]
      this.search()
    },
    clearInput: function () {
      this.keyword = ''
      this.$http.jsonp('https://sug.so.360.cn/suggest?word=' + this.keyword + '&encodein=utf-8&encodeout=utf-8').then(function (res) {
        this.myData = res.data.s
      })
    },
    getIndex: function (index) {
      this.searchIndex = index
    }
  }
}
</script>

<style type="text/css">
.search-select {
  position: absolute;
  top: 45px;
  width: 500px;
  box-sizing: border-box;
  z-index: 999;
}

.search-select li {
  border: 1px solid #d4d4d4;
  border-top: none;
  border-bottom: none;
  background-color: #fff;
  width: 100%;
}

.search-select-option {
  box-sizing: border-box;
  padding: 7px 10px;
}

.selectback {
  background-color: #eee !important;
  cursor: pointer;
}

input::-ms-clear {
  display: none;
}

.search-reset {
  width: 21px;
  height: 21px;
  position: absolute;
  display: block;
  line-height: 21px;
  text-align: center;
  cursor: pointer;
  font-size: 20px;
  right: 110px;
  top: 12px;
}

.search-select-list {
  transition: all 0.5s;
}

.itemfade-enter,
.itemfade-leave-active {
  opacity: 0;
}

.itemfade-leave-active {
  position: absolute;
}

.selectback {
  background-color: #eee !important;
  cursor: pointer;
}
.search-select ul {
  margin: 0;
  text-align: left;
}
</style>
