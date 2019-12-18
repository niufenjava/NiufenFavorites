import Vue from 'vue'
import VueRouter from 'vue-router'
import WebSite from '../views/WebSite.vue'
import Todo from '../views/Todo.vue'
import Main from '../views/Main.vue'
Vue.use(VueRouter)

const routes = [{
  path: '/',
  component: Main,
  redirect: '/webSite',
  children: [{
    path: 'webSite',
    component: WebSite,
    name: 'WebSite'
  }]
},
{
  path: '/',
  component: Main,
  children: [{
    path: '/message',
    component: () => import('@/views/Message.vue'),
    name: 'Message'
  }]
},
{
  path: '/',
  component: Main,
  children: [{
    path: '/home',
    component: () => import('@/views/Home.vue'),
    name: 'Home'
  }]
},
{
  path: '/',
  component: Main,
  children: [{
    path: '/todo',
    component: Todo,
    name: 'Todo'
  }]
},
{
  path: '/',
  component: Main,
  children: [{
    path: '/word',
    component: () => import('@/views/Word.vue'),
    name: 'Word'
  }]
},
{
  path: '/',
  component: Main,
  children: [{
    path: '/article',
    component: () => import('@/views/Article.vue'),
    name: 'Article'
  }]
}
]

const router = new VueRouter({
  routes
})

export default router
