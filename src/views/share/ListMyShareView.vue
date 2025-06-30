<script setup lang="ts">
import dayjs from 'dayjs'
import { LikeFilled, LikeOutlined, DislikeFilled, DislikeOutlined } from '@ant-design/icons-vue'
import { onMounted, ref, watch } from 'vue'
import relativeTime from 'dayjs/plugin/relativeTime'
import { listMyShareUsingPost } from '@/api/shareController.ts'
import { message } from 'ant-design-vue'
import router from '@/router'
dayjs.extend(relativeTime)

const likes = ref<number>(0)
const dislikes = ref<number>(0)
const action = ref<string>()

const like = () => {
  likes.value = 1
  dislikes.value = 0
  action.value = 'liked'
}

const dislike = () => {
  likes.value = 0
  dislikes.value = 1
  action.value = 'disliked'
}

const current = ref(1)
const pageSize = ref(10)
const pageSizeOptions = ref([1, 2, 5, 10, 20])
let data = ref([])
let total = ref('0')
const refreshData = async () => {
  const res = await listMyShareUsingPost({
    pageSize: pageSize.value,
    current: current.value,
    reviewStatus: 0,
  })
  if (res.code === 0) {
    data.value = res.data.records
    total.value = res.data.total
    console.log(res)
  } else {
    message.error(res.msg)
  }
}
onMounted(() => {
  refreshData()
  console.log(data.value)
})

watch(pageSize, () => {
  refreshData()
})
watch(current, () => {
  refreshData()
})
</script>

<template>
  <div id="list-my-share">
    <a-divider orientation="left">我的分享</a-divider>

    <div class="show">
      <a-card hoverable v-for="(item, index) in data" :key="index" class="share">
        <template #cover>
          <img alt="example" :src="'http://localhost:10086/api/' + item.sharePicture" />
        </template>

        <a-card-meta :title="item.shareTitle">
          <template #description>{{ item.shareDetail }}</template>
        </a-card-meta>
      </a-card>
    </div>

    <div class="pageSize">
      <a-pagination
        v-model:current="current"
        v-model:pageSize="pageSize"
        show-size-changer
        :total="total"
        v-model:pageSizeOptions="pageSizeOptions"
        v-if="total !== '0'"
      />
      <a-empty v-else />
    </div>
  </div>
</template>

<style scoped>
#list-my-share {
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
  .shares {
    margin-bottom: 2px;
    transition: all 0.3s ease; /* 添加过渡效果使变化更平滑 */
    padding-left: 24px;
  }

  .shares:hover {
    background-color: #f5f5f5; /* 悬停时背景色变浅 */
    transform: translateY(-2px); /* 轻微上浮 */
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 添加阴影 */
  }
}
</style>
