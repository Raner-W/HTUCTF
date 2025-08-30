<template>
  <div class="error-tip" v-if="message">{{ message }}</div>
</template>

<script setup>
const props = defineProps({
  message: {
    type: String,
    default: "" // 错误信息（为空时不显示）
  }
});
</script>

<style scoped>
.error-tip {
  margin-bottom: 20px;
  padding: 12px;
  background: rgba(239, 68, 68, 0.1);
  border-left: 4px solid #ef4444;
  color: #ef4444;
  font-size: 14px;
  border-radius: 4px;
}
</style>