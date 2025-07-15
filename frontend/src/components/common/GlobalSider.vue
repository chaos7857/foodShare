<script setup lang="ts">

import router from '@/router'
import { PlusCircleOutlined , StarOutlined, CompassOutlined, CoffeeOutlined } from '@ant-design/icons-vue'
import { userStore } from '@/stores/userStore.ts'
</script>

<template>
<div id="sider">
  <a-button type="text"
            shape="round"
            danger
            class="find-publish-button"
            @click="router.push('/')">
    <template #icon>
      <CompassOutlined />
    </template>
    发现
  </a-button>

  <a-button type="text"
            shape="round"
            danger
            class="store-publish-button"
            @click="router.push('/share/storeup')">
    <template #icon>
      <StarOutlined />
    </template>
    收藏
  </a-button>

  <a-button type="text"
            shape="round"
            danger
            class="my-publish-button"
            @click="router.push('/share/my')">
    <template #icon>
      <CoffeeOutlined />
    </template>
    我的
  </a-button>

  <a-button type="text"
            shape="round"
            danger
            class="review-button"
            @click="router.push('/admin/review')"
  v-if="userStore().loginUser.userRole === 'admin' ||userStore().loginUser.userRole === 'dev'">
    <template #icon>
      <CoffeeOutlined />
    </template>
    审核
  </a-button>

  <a-button type="text"
            shape="round"
            danger
            class="manager-button"
            @click="router.push('/admin/manager')"
            v-if="userStore().loginUser.userRole === 'admin' ||userStore().loginUser.userRole === 'dev'">
    <template #icon>
      <CoffeeOutlined />
    </template>
    管理
  </a-button>

  <a-button type="text"
            shape="round"
            danger
            class="publish-button"
            @click="router.push('/share/publish')">
    <template #icon>
      <PlusCircleOutlined />
    </template>
    发布
  </a-button>
</div>
</template>

<style scoped>
#sider{
  font-size: 18px;

  .find-publish-button{
    margin-top: 30px;
    width: 200px;
    height: 50px;
    font-size: 18px;
    font-weight: bold;
    color: black;
  }

  .store-publish-button{
    margin-top: 10px;
    width: 200px;
    height: 50px;
    font-size: 18px;
    font-weight: bold;
    color: black;
  }

  .my-publish-button{
    margin-top: 10px;
    width: 200px;
    height: 50px;
    font-size: 18px;
    font-weight: bold;
    color: black;
  }

  .review-button{
    margin-top: 10px;
    width: 200px;
    height: 50px;
    font-size: 18px;
    font-weight: bold;
    color: black;
  }

  .manager-button{
    margin-top: 10px;
    width: 200px;
    height: 50px;
    font-size: 18px;
    font-weight: bold;
    color: black;
  }

  .publish-button{
    margin-top: 10px;
    width: 200px;
    height: 100px;
    font-size: 18px;
    font-weight: bold;
  }
}
</style>
