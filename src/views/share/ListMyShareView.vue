<script setup lang="ts">
import dayjs from 'dayjs'
import { LikeFilled, LikeOutlined, DislikeFilled, DislikeOutlined } from '@ant-design/icons-vue'
import { onMounted, ref, watch } from 'vue'
import relativeTime from 'dayjs/plugin/relativeTime'
import { listMyShareUsingPost} from '@/api/shareController.ts'
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
    console.log(data)
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
<div class="list-my-share">
  <a-divider orientation="left">我的分享</a-divider>
  <a-comment
    class="shares"
    v-for="(item, index) in data"
    :key="index"
    @click="router.push('/share/info/' + item.id)"
  >
    <template #actions>
        <span key="comment-basic-like">
          <a-tooltip title="Like">
            <template v-if="action === 'liked'">
              <LikeFilled @click="like" />
            </template>
            <template v-else>
              <LikeOutlined @click="like" />
            </template>
          </a-tooltip>
          <span style="padding-left: 8px; cursor: auto">
            {{ likes }}
          </span>
        </span>
      <span key="comment-basic-dislike">
          <a-tooltip title="Dislike">
            <template v-if="action === 'disliked'">
              <DislikeFilled @click="dislike" />
            </template>
            <template v-else>
              <DislikeOutlined @click="dislike" />
            </template>
          </a-tooltip>
          <span style="padding-left: 8px; cursor: auto">
            {{ dislikes }}
          </span>
        </span>
      <span key="comment-basic-reply-to">Reply to</span>
    </template>
    <template #author
    ><a>{{ item.shareTitle }}</a></template
    >
    <template #avatar>
      <a-avatar src="https://joeschmoe.io/api/v1/random" alt="Han Solo" />
    </template>
    <template #content>
      <p>
        {{ item.shareDetail }}
      </p>
    </template>
    <template #datetime>
      <a-tooltip :title="dayjs().format('YYYY-MM-DD HH:mm:ss')">
        <span>{{ dayjs().fromNow() }}</span>
      </a-tooltip>
    </template>
  </a-comment>
  <div class="pageSize">
    <a-pagination
      v-model:current="current"
      v-model:pageSize="pageSize"
      show-size-changer
      :total="total"
      v-model:pageSizeOptions="pageSizeOptions"
    />
  </div>
</div>
</template>

<style scoped>
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
</style>
