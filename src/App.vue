<script setup lang="ts">
import GlobalHeader from '@/components/common/GlobalHeader.vue'
import { userStore } from "@/stores/userStore.ts";
import { PlusCircleOutlined } from '@ant-design/icons-vue';
import router from '@/router'

userStore().fetchLoginUser()

// healthUsingGet().then((res) => {
//   console.log(res)
// })
</script>

<template>
  <a-layout style="min-height: 100vh">
    <a-layout-header class="header">
      <GlobalHeader />
    </a-layout-header>
    <a-layout>
      <a-layout-sider class="sider">
        <a-button type="primary"
                  shape="round"
                  danger
        class="publish-button"
        @click="router.push('/publish')">
          <template #icon>
            <PlusCircleOutlined />
          </template>
          发布
        </a-button>
      </a-layout-sider>

      <a-layout-content class="content">
        <router-view />
      </a-layout-content>
    </a-layout>

    <a-layout-footer class="footer">
      本系统最终解释权归hfut-2022211057所有
    </a-layout-footer>
  </a-layout>
</template>

<style scoped lang="less">

  .header {
    padding-inline: 20px;
    background: #fff;
    color: unset;
    margin-bottom: 16px;
  }

  .sider{
    text-align: center;
    lineHeight: 120px;
    color: black;
    background: white;
    padding-left: 16px;

    .publish-button{
      margin-top: 160px;
      width: 250px;
      height: 100px;
      font-size: 26px;
    }
  }
  .content {
    padding: 20px;
    background: linear-gradient(to right, #fefefe, #fff);
    margin-bottom: 28px;
  }

  .footer {
    background: #efefef;
    padding: 16px;
    position: fixed;
    bottom: 0;
    left: 0;
    right: 0;
    text-align: center;

    #beian {
      width: 16px;
      margin: 0 5px;
      display: inline;
    }
  }

</style>
