<script setup lang="ts">
import { listShareByRsUsingPost } from '@/api/shareController.ts'
import { onMounted, reactive, ref } from 'vue'
import { message } from 'ant-design-vue'
import { addReviewUsingPost } from '@/api/reviewController.ts'
let data = ref([])
let total = ref('0')
const refreshData = async () => {
  const res = await listShareByRsUsingPost({
    pageSize: 20,
    current: 1,
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
onMounted(async () => {
  await refreshData()
})

const columns = [
  {
    title: '标题',
    dataIndex: 'shareTitle',
    key: 'shareTitle',
  },
  {
    title: '描述',
    dataIndex: 'shareDetail',
    key: 'shareDetail',
  },
  {
    title: '图片',
    dataIndex: 'sharePicture',
    key: 'sharePicture',
  },
  {
    title: '用户编号',
    dataIndex: 'userId',
    key: 'userId',
  },
  {
    title: '更新时间',
    dataIndex: 'updateTime',
    key: 'updateTime',
  },
  {
    title: '操作',
    dataIndex: 'actions',
    key: 'actions',
  },
]

const open = ref<boolean>(false);
const uploading = ref<boolean>(false);
const formState = reactive<Record<string, any>>({
  reviewStatus: 1,
  reviewMessage:'',
  shareId:0
})

const showModal = (shareId:number) => {
  formState.shareId = shareId
  open.value = true;
};

const handleOk = async () => {
  uploading.value = true;
  const res = await addReviewUsingPost(formState)
  if (res.code === 0) {
    message.info("审核成功");
  }else {
    message.error(res.msg);
  }
  await refreshData()
  open.value = false;
  uploading.value = false;
};


</script>

<template>
  <div id="review-share">
    <a-divider orientation="left">审核</a-divider>
    <a-table :dataSource="data" :columns="columns" bordered>
      <template #bodyCell="{ column, text, record }">
        <div class="share-img" v-if="'sharePicture' === column.dataIndex">
          <img :src="'http://localhost:10086/api/'+text" alt="图片" />
        </div>

        <div class="share-actions" v-if="'actions' === column.dataIndex">
          <a-button type="primary" @click="showModal(record.id)">审核</a-button>
        </div>
      </template>
    </a-table>

    <a-modal v-model:open="open" title="审核"
             :confirm-loading="uploading"
             @ok="handleOk">
      <a-form :model="formState" name="review">
        <a-form-item name="reviewStatus"
                     >
          <a-radio-group v-model:value="formState.reviewStatus">
            <a-radio :value="1">通过</a-radio>
            <a-radio :value="2">不通过</a-radio>
          </a-radio-group>
        </a-form-item>

        <a-form-item
        name="reviewMessage">
          <a-textarea v-model:value="formState.reviewMessage"
                      placeholder="审核信息" :rows="4" />
        </a-form-item>

      </a-form>
    </a-modal>
  </div>
</template>

<style scoped>
#review-share {
  margin-left: 64px;
  .share-img{
    img {
      width:300px;
    }
  }
}

</style>
