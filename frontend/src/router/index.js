import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import SearchView from '../views/SearchView.vue'
import MyPageView from '../views/MyPageView.vue'
import CreateView from '../views/CreateView.vue'
import UserPage from '../views/UserPageView.vue'
import CreateAcount from '../views/CreateAccount.vue'
import LoginView from '../views/LoginView.vue'
import LikesPageView from '../views/LikesPageView.vue'

Vue.use(VueRouter)

const routes = [
 {
   path: '/createaccount',
   name: 'createaccount',
   component: CreateAcount
 },
 {
   path: '/login',
   name: 'login',
   component: LoginView
 },
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
 },
 {
   path: '/likespage',
   name: 'likespage',
   component:LikesPageView
 }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router