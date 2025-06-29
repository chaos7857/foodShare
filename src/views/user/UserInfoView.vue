<script setup lang="ts">
import { userStore } from '@/stores/userStore.ts'
import { userLogoutUsingGet } from '@/api/userController.ts'
import router from '@/router'
import { message } from 'ant-design-vue'

const loginUser = userStore().loginUser

const userLogout = async ()=>{
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



import dayjs from 'dayjs';
import { LikeFilled, LikeOutlined, DislikeFilled, DislikeOutlined } from '@ant-design/icons-vue';
import { ref } from 'vue';
import relativeTime from 'dayjs/plugin/relativeTime';
dayjs.extend(relativeTime);

const likes = ref<number>(0);
const dislikes = ref<number>(0);
const action = ref<string>();

const like = () => {
  likes.value = 1;
  dislikes.value = 0;
  action.value = 'liked';
};

const dislike = () => {
  likes.value = 0;
  dislikes.value = 1;
  action.value = 'disliked';
};
</script>

<template>
<div id="user-info">
  <a-descriptions title="User Info">
    <a-descriptions-item
    v-for="(value, key, index) in loginUser"
    :key = index
    :label="key">
      {{ value }}
    </a-descriptions-item>
    <a-descriptions-item>
      <a-button type="text" @click="userLogout">退出</a-button>
    </a-descriptions-item>
  </a-descriptions>


  <a-divider orientation="left">我的分享</a-divider>
  <a-comment>
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
    <template #author><a>Han Solo</a></template>
    <template #avatar>
      <a-avatar src="https://joeschmoe.io/api/v1/random" alt="Han Solo" />
    </template>
    <template #content>
      <p>
        We supply a series of design principles, practical patterns and high quality design
        resources (Sketch and Axure), to help people create their product prototypes beautifully and
        efficiently.
      </p>
    </template>
    <template #datetime>
      <a-tooltip :title="dayjs().format('YYYY-MM-DD HH:mm:ss')">
        <span>{{ dayjs().fromNow() }}</span>
      </a-tooltip>
    </template>
  </a-comment>
</div>
</template>

<style scoped>
#user-info {
  margin-left: 16%;
}
</style>
