<script setup lang="ts">
import { reactive, ref } from 'vue'
import type { Rule } from 'ant-design-vue/es/form'
import { type FormInstance, message } from 'ant-design-vue'
import { getUserUsingGet, userLoginUsingPost, userRegisterUsingPost } from '@/api/userController.ts'
import { userStore } from '@/stores/userStore.ts'
import router from '@/router'
interface FormState {
  userAccount: string
  userPassword: string
  passwordConfirm: string
}
const formRef = ref<FormInstance>()
const formState = reactive<FormState>({
  userAccount: '',
  userPassword: '',
  passwordConfirm: '',
})

const validatePass2 = async (_rule: Rule, value: string) => {
  if (value === '') {
    return Promise.reject('请确认密码')
  } else if (value !== formState.userPassword) {
    return Promise.reject('两次输入不一致！')
  } else {
    return Promise.resolve()
  }
}

const rules: Record<string, Rule[]> = {
  userAccount: [
    { required: true, message: '账号不能为空！', trigger: 'change' },
    { min: 4, max: 20, message: '账号过短', trigger: 'change' },
  ],
  userPassword: [
    { required: true, message: '密码不能为空！', trigger: 'change' },
    { min: 8, max: 20, message: '密码长度不足' },
  ],
  passwordConfirm: [{ validator: validatePass2, trigger: 'change' }],
}
const layout = {
  labelCol: { span: 4 },
  wrapperCol: { span: 14 },
}
const handleFinish = async (values: FormState) => {
  console.log(values)
  const res = await userRegisterUsingPost(values)
  if (res.code === 0) {
    await userLoginUsingPost({
      userAccount: values.userAccount,
      password: values.userPassword
    })
    await userStore().fetchLoginUser()
    message.success('注册成功')
    await router.push({
      path: '/',
      replace: true,
    })
  }else {
    message.error('注册失败，' + res.msg)
  }
}
const handleFinishFailed = (errors) => {
  console.log(errors)
}

const resetForm = () => {
  formRef.value.resetFields()
}
</script>

<template>
  <div id="user-register">
    <a-form
      ref="formRef"
      name="custom-validation"
      :model="formState"
      :rules="rules"
      v-bind="layout"
      @finish="handleFinish"
      @finishFailed="handleFinishFailed"
      class="register-form"
    >
      <a-form-item has-feedback label="账号" name="userAccount">
        <a-input v-model:value="formState.userAccount" type="username" autocomplete="off" />
      </a-form-item>

      <a-form-item has-feedback label="密码" name="userPassword">
        <a-input
          v-model:value="formState.userPassword"
          type="password"
          autocomplete="off"
          @change="formState.passwordConfirm=''"
        />
      </a-form-item>

      <a-form-item has-feedback label="确认密码" name="passwordConfirm">
        <a-input v-model:value="formState.passwordConfirm" type="password" autocomplete="off" />
      </a-form-item>
      <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
        <a-button type="primary" html-type="submit">注册</a-button>
        <a-button style="margin-left: 10px" @click="resetForm">重置</a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<style scoped>
.register-form {
  width: 50%;
  margin: 0 auto;
  margin-top: 60px;
}
</style>
