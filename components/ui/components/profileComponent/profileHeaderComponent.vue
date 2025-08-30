<template>
  <div class="profile-header">
    <div class="avatar">
      <span>{{ userInfo?.avatar || "👤" }}</span>
    </div>
    <div class="info">
      <h2>{{ userInfo?.username || "未登录" }}</h2>
      <p>ID: {{ userInfo?.id || "N/A" }}</p>
      <p>Email: {{ userInfo?.email || "未绑定" }}</p>
    </div>
  </div>
</template>

<script setup>
import { defineProps } from 'vue';

// 接收父组件传递的用户信息
const props = defineProps({
  userInfo: {
    type: Object,
    default: () => ({})
  }
});
</script>

<style scoped>
.profile-header {
  display: flex;
  align-items: center;
  margin-bottom: 24px;
  border-bottom: 1px solid #e2e8f0;
  padding-bottom: 20px;
}

.avatar {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  background: #edf2f7;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 36px;
  margin-right: 20px;
}

.info h2 {
  font-size: 20px;
  font-weight: 700;
  margin: 0;
  color: #2d3748;
}

.info p {
  margin: 4px 0;
  color: #718096;
  font-size: 14px;
}
</style>
