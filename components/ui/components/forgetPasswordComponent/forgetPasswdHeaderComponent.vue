<template>
  <div class="auth-header">
    <h1>{{ title }}</h1>
    <p>{{ desc }}</p>
  </div>
</template>

<script setup>
// 接收外部传递的标题和描述
const props = defineProps({
  title: {
    type: String,
    required: true, // 必须传递标题
    default: "操作标题"
  },
  desc: {
    type: String,
    required: true, // 必须传递描述
    default: "操作描述"
  }
});
</script>

<style scoped>
.auth-header {
  margin-bottom: 32px;
  text-align: center;
}
.auth-header h1 {
  font-size: 24px;
  font-weight: 700;
  color: #1a202c;
  margin-bottom: 8px;
}
.auth-header p {
  font-size: 14px;
  color: #718096;
}
</style>