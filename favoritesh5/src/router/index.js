import Vue from 'vue'
import VueRouter from 'vue-router'
// import Home from '../views/Home.vue'
import Todo from '../views/Todo.vue'
import Layout from '@/layout'
Vue.use(VueRouter)

const routes = [{
    path: '/',
    component: Layout,
    redirect: '/home',
    children: [{
      path: '/home',
      // component: Home,
      component: () => import('@/views/Home.vue'),
      name: 'Home'
    }]
  },
  {
    path: '/',
    component: Layout,
    children: [{
      path: '/message',
      component: () => import('@/views/Message.vue'),
      name: 'Message'
    }]
  }, {
    path: '/',
    component: Layout,
    children: [{
      path: '/favorite',
      component: () => import('@/views/favorite'),
      name: 'favorite'
    }]
  },
  {
    path: '/',
    component: Layout,
    children: [{
      path: '/todo',
      component: Todo,
      name: 'Todo'
    }]
  },
  {
    path: '/',
    component: Layout,
    children: [{
      path: '/dict',
      component: () => import('@/views/Dict'),
      name: 'dict'
    }]
  },
  {
    path: '/',
    component: Layout,
    children: [{
      path: '/notebook',
      component: () => import('@/views/notebook'),
      name: 'Notebook'
    }]
  }
]

const router = new VueRouter({
  routes
})

export default router