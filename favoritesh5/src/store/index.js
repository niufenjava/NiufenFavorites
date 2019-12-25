import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    count: 0,
    // 浏览器可视区域高度
    clientHeight: 500,
    // 浏览器Header高度
    mainHeaderHeight: 60,
    // 浏览器底部栏高度
    mainFooterHeight: 30,
    // 浏览器主体高度
    mainBodyHeight: 440,
    dictWordListOrderType: 1
  },
  mutations: {
    increment(state) {
      state.count++
    }
  },
  actions: {},
  modules: {}
})