import {ref} from 'vue'
import {defineStore} from 'pinia'
import { getLoginUserUsingGet } from '@/api/userController.ts'

export const userStore = defineStore('loginUser', () => {
  const loginUser = ref<any>({
    userName: '未登录',
  })

  async function fetchLoginUser() {
    const res = await getLoginUserUsingGet();
    if (res.code === 0 && res.data) {
      loginUser.value = res.data;
    }
    // 测试用户登录，3 秒后自动登录
    // setTimeout(() => {
    //   loginUser.value = { userName: '测试用户', id: 1 }
    // }, 3000)
  }

  /**
   * 设置登录用户
   * @param newLoginUser
   */
  function setLoginUser(newLoginUser: any) {
    loginUser.value = newLoginUser
  }

  // 返回
  return { loginUser, fetchLoginUser, setLoginUser }
})
