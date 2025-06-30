<script setup lang="ts">
import { computed, reactive } from 'vue'
import { message } from 'ant-design-vue';
import { userLoginUsingPost } from '@/api/userController.ts'
import router from '@/router'
import { userStore } from '@/stores/userStore.ts'

interface FormState {
  userAccount: string;
  password: string;
  remember: boolean;
}

const formState = reactive<FormState>({
  userAccount: '',
  password: '',
  remember: true,
});
const onFinish = async (values: any) => {
  const res = await userLoginUsingPost(values);
  if (res.code === 0 && res.data) {
    await userStore().fetchLoginUser()
    message.success('登录成功')
    router.push({
      path: '/',
      replace: true,
    })
  } else {
    message.error('登录失败，' + res.msg)
  }
};

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};
const disabled = computed(() => {
  return !(formState.userAccount && formState.password);
});
</script>

<template>
<div id="user-login">
  <div class="background"></div>
  <a-card title="用户登录" :bordered="false" class="login-card"
          headStyle='text-align: center;margin-bottom: 16px;'
          :label-col="{ span: 8 }"
          :wrapper-col="{ span: 8 }">
    <a-form
      :model="formState"
      name="basic"

      autocomplete="off"
      @finish="onFinish"
      @finishFailed="onFinishFailed"
    >
      <a-form-item
        label="账号"
        name="userAccount"
        :rules="[
        { required: true, message: '请输入账号' },
        { min: 3, max: 20, message: '长度需在3到20个字符之间' }
      ]"
      >
        <a-input v-model:value="formState.userAccount" />
      </a-form-item>

      <a-form-item
        label="密码"
        name="password"
        :rules="[
        { required: true, message: '请输入密码' },
        { min: 8, max: 20, message: '密码长度不足' }
      ]"
      >
        <a-input-password v-model:value="formState.password" />
      </a-form-item>

      <a-form-item name="remember" :wrapper-col="{ offset: 3, span: 16 }">
        <a-checkbox v-model:checked="formState.remember">记住密码</a-checkbox>
      </a-form-item>

      <a-form-item :wrapper-col="{ offset: 8, span: 16 }">
        <a-button :disabled="disabled" type="primary" html-type="submit" class="login-form-button">
          登录
        </a-button>
        Or
        <a href="/user/register">去注册</a>
      </a-form-item>



    </a-form>
  </a-card>

</div>
</template>

<style scoped>
#user-login {
  .login-card{
    width: 30%;
    margin:10% auto;
  }
}
</style>
