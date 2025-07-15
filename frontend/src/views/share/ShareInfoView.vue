<script setup lang="ts">
interface Props {
  id: string | number
}

const props = defineProps<Props>()
console.log(props.id)
</script>

<template>
<div id="share-info">
{{props.id}}
</div>
</template>

<style scoped>

</style>
