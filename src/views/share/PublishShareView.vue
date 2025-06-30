<script setup lang="ts">
import { onMounted, reactive, ref } from 'vue'
import { InboxOutlined } from '@ant-design/icons-vue'
import { message, type UploadProps } from 'ant-design-vue'
import { addShareUsingPost, listMyShareUsingPost } from '@/api/shareController.ts'
onMounted(async () => {
  const tepm = await listMyShareUsingPost({
    pageSize: 1,
    current: 1,
    reviewStatus: 0,
  })
})

const formItemLayout = {
  labelCol: { span: 6 },
  wrapperCol: { span: 8 },
}

const formState = reactive<Record<string, any>>({
  title: '',
  detail: '',
  tags: [],
})
const fileList = ref<UploadProps['fileList']>([])
const uploading = ref<boolean>(false)

const onFinish = (values: any) => {
  // console.log('Success:', values);
}

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo)
}

const handleRemove: UploadProps['onRemove'] = () => {
  fileList.value = []
}

const beforeUpload: UploadProps['beforeUpload'] = async (file) => {
  fileList.value = [file]
  return false
}

const handleUpload = async () => {
  const res = await addShareUsingPost({},formState,fileList.value[0].originFileObj);
  if (res.code === 0) {
    message.success('提交成功')
  } else {
    message.error(res.msg)
  }
  uploading.value = false
}
</script>

<template>
  <div id="publish-share">
    <a-form
      :model="formState"
      name="validate_other"
      v-bind="formItemLayout"
      @finishFailed="onFinishFailed"
      @finish="onFinish"
    >
      <a-form-item
        name="title"
        label="标题"
        :rules="[
          { required: true, message: '标题不能为空' },
          { min: 4, message: '标题过短' },
        ]"
      >
        <a-input v-model:value="formState.title" placeholder="请输入分享标题" allow-clear />
      </a-form-item>

      <a-form-item name="detail" label="详情">
        <a-textarea v-model:value="formState.detail" placeholder="详细分享信息" :rows="4" />
      </a-form-item>

      <a-form-item
        name="tags"
        label="标签"
        :rules="[
          { required: true, message: 'Please select your favourite colors!', type: 'array' },
        ]"
      >
        <a-select v-model:value="formState.tags" mode="tags" placeholder="分享标签">
          <a-select-option value="1">烧烤</a-select-option>
          <a-select-option value="2">火锅</a-select-option>
          <a-select-option value="3">面</a-select-option>
        </a-select>
      </a-form-item>

      <a-form-item label="美食图片" :rules="[{ required: true, message: '图片必须展示' }]">
        <a-form-item name="file" no-style>
          <a-upload-dragger
            v-model:fileList="fileList"
            :before-upload="beforeUpload"
            @remove="handleRemove"
          >
            <p class="ant-upload-drag-icon">
              <InboxOutlined />
            </p>
            <p class="ant-upload-text">点此上传图片</p>
          </a-upload-dragger>
        </a-form-item>
      </a-form-item>

      <a-form-item :wrapper-col="{ span: 12, offset: 6 }">
        <a-button
          type="primary"
          html-type="submit"
          :loading="uploading"
          @click="handleUpload"
          :disabled="fileList.length === 0 || formState.title === ''"
          >提交</a-button
        >
      </a-form-item>
    </a-form>
  </div>
</template>

<style scoped>
#publish-share {
  margin-top: 5%;
}
</style>
