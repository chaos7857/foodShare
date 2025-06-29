<script setup lang="ts">
import { HeartOutlined, ShareAltOutlined, EllipsisOutlined } from '@ant-design/icons-vue';
import router from '@/router'
</script>

<template>
<div id="home">
  <a-card
    hoverable
    class="share"
    v-for="index in 40"
    @click="router.push('/share/info/'+index)">
    <template #cover>
      <img
        alt="example"
        src="https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png"
      />
    </template>

    <template #actions>
      <heart-outlined key="favorite" />
      <ShareAltOutlined key="edit" />
      <ellipsis-outlined key="ellipsis" />
    </template>

    <a-card-meta title="Card title" description="This is the description">
      <template #avatar>
        <a-avatar src="https://joeschmoe.io/api/v1/random" />
      </template>
    </a-card-meta>
  </a-card>

  <a-back-top />
</div>
</template>

<style>
#home{
  display: flex;
  flex-wrap: wrap;
  padding: 20px;
  gap: 20px;
  .share{
    margin-left: 40px;
    width: 300px;
  }
}
</style>
