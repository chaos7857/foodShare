import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue'),
    },
    {
      path: '/user/login',
      name: '用户登录',
      component: () => import('@/views/user/UserLoginView.vue'),
    },
    {
      path: '/user/me',
      name: '用户信息',
      component: () => import('@/views/user/UserInfoView.vue'),
    },
    // {
    //   path: '/user/register',
    //   name: '用户注册',
    //   component: UserRegisterPage,
    // },
  ],
})

export default router
