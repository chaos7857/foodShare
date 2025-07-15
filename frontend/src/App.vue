<script setup lang="ts">
import GlobalHeader from '@/components/common/GlobalHeader.vue'
import { userStore } from "@/stores/userStore.ts";
import { PlusCircleOutlined } from '@ant-design/icons-vue';
import router from '@/router'
import GlobalSider from '@/components/common/GlobalSider.vue'

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
        <GlobalSider/>
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
