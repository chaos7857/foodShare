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
      path: '/user/register',
      name: '用户注册',
      component: () => import('@/views/user/UserRegisterView.vue'),
    },
    {
      path: '/user/me',
      name: '用户信息',
      component: () => import('@/views/user/UserInfoView.vue'),
    },
    {
      path: '/share/publish',
      name: '发布分享',
      component: () => import('@/views/share/PublishShareView.vue'),
    },
    {
      path: '/share/info/:id',
      name: '发布详情',
      component: () => import('@/views/share/ShareInfoView.vue'),
      props: true,
    },
    {
      path: '/share/my',
      name: '我的分享',
      component: () => import('@/views/share/ListMyShareView.vue'),
      props: true,
    },
    // {
    //   path: '/share/storeup',
    //   name: '我的收藏',
    //   component: () => import('@/views/share/ListMyShareView.vue'),
    //   props: true,
    // },
    {
      path: '/admin/review',
      name: '审核',
      component: () => import('@/views/admin/ReviewShareView.vue'),
    },
    {
      path: '/admin/manager',
      name: '用户管理',
      component: () => import('@/views/admin/ManageUserView.vue'),
    },

  ],
})

export default router
