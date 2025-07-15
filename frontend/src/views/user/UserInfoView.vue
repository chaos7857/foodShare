<script setup lang="ts">
import { userStore } from '@/stores/userStore.ts'
import { userLogoutUsingGet } from '@/api/userController.ts'
import router from '@/router'
import { message } from 'ant-design-vue'

const loginUser = userStore().loginUser

const userLogout = async () => {
  const res = await userLogoutUsingGet()
  if (res.code === 0) {
    userStore().setLoginUser({
      userName: '未登录',
    })
    message.success(res.data)
    await router.push('/user/login')
  } else {
    message.error(res.msg)
  }
}

</script>

<template>
  <div id="user-info">
    <a-descriptions title="用户信息">
      <a-descriptions-item v-for="(value, key, index) in loginUser" :key="index" :label="key">
        {{ value }}
      </a-descriptions-item>
      <a-descriptions-item>
        <a-button type="text" @click="userLogout">退出</a-button>
      </a-descriptions-item>
    </a-descriptions>

  </div>
</template>

<style scoped>
#user-info {
  margin-left: 16%;


}
</style>
