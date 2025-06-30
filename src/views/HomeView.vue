<script setup lang="ts">
import { HeartOutlined, ShareAltOutlined, EllipsisOutlined } from '@ant-design/icons-vue'
import router from '@/router'

import { onMounted, ref, watch } from 'vue'
import { listShareByRsUsingPost } from '@/api/shareController.ts'
import { message } from 'ant-design-vue'
const current = ref(1)
const pageSize = ref(10)
const pageSizeOptions = ref([1, 2, 5, 10, 20])
let data = ref([])
let total = ref('0')
const refreshData = async () => {
  const res = await listShareByRsUsingPost({
    pageSize: pageSize.value,
    current: current.value,
    reviewStatus: 0,
  })
  if (res.code === 0) {
    data.value = res.data.records
    total.value = res.data.total
    console.log(data)
  } else {
    message.error(res.msg)
  }
}
onMounted(() => {
  refreshData()
})

watch(pageSize, () => {
  refreshData()
})
watch(current, () => {
  refreshData()
})

const value = ref<string>('')

const onSearch = (searchValue: string) => {
  console.log('use value', searchValue)
  console.log('or use this.value', value.value)
}


const storeup = (shareId)=>{
  message.info('storeup' + shareId)
}
</script>

<template>
  <div id="home">
    <a-input-search
      v-model:value="value"
      placeholder="input search text"
      style="width: 300px; margin-left: 60px"
      @search="onSearch"
    />

    <div class="show">
      <a-card
        hoverable
        class="share"
        v-for="(item, index) in data"
        :key="index"
      >
        <template #cover >
          <img alt="example" :src="'http://localhost:10086/api/' + item.sharePicture"
               @click="router.push({ path: `/share/info/${item.id}` })"/>
        </template>

        <a-card-meta :title="item.shareTitle" :description="item.shareDetail"
                     @click="router.push({ path: `/share/info/${item.id}` })">
<!--          <template #avatar>-->
<!--            <a-avatar :size="40">{{ item.userId }}</a-avatar>-->
<!--          </template>-->
        </a-card-meta>

        <template #actions>
          <heart-outlined key="favorite" @click="storeup(item.id)"/>
          <ShareAltOutlined key="edit" @click="message.info('链接已复制，快去分享吧')"/>
          <ellipsis-outlined key="ellipsis" @click="message.info('其他功能正在完善中')"/>
        </template>
      </a-card>
    </div>

    <div class="pageSize">
      <a-pagination
        v-model:current="current"
        v-model:pageSize="pageSize"
        show-size-changer
        :total="total"
        v-model:pageSizeOptions="pageSizeOptions"
      />
    </div>

    <a-back-top />
  </div>
</template>

<style>
#home {
  .show {
    display: flex;

    flex-wrap: wrap;
    padding: 20px;
    gap: 20px;
  }
  .share {
    margin-left: 40px;
    width: 300px;
  }
  .pageSize {
    width: 100%;
    margin-top: auto;
    padding: 16px 0;
    text-align: center;
  }
}
</style>
