<script setup lang="ts">
import { userStore } from '@/stores/userStore.ts'

const loginUser = userStore().loginUser
console.log(loginUser)
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
  </a-descriptions>
</div>
</template>

<style scoped>

</style>
