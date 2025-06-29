<script setup lang="ts">
import TheWelcome from '../components/TheWelcome.vue'
import { message } from 'ant-design-vue';
message.success('This is a success message');
</script>

<template>
  <main>
    <TheWelcome />
  </main>
</template>
