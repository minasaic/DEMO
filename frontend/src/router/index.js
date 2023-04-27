import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import SearchView from '../views/SearchView.vue'
import MyPageView from '../views/MyPageView.vue'
import CreateView from '../views/CreateView.vue'
import UserPage from '../views/UserPageView.vue'

Vue.use(VueRouter)

const routes = [
 {
    path: '/',
    name: 'home',
    component: HomeView
 },
 {
    path: '/search',
    name: 'search',
    component: SearchView
 },
 {
    path: '/mypage',
    name: 'mypage',
    component: MyPageView
 },
 {
    path: '/create',
    name: 'create',
    component: CreateView
 },
 {
   path: '/userpage',
   name: 'userpage',
   component:UserPage
 }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router