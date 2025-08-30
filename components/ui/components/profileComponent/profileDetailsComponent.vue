<template>
  <div class="profile-details">
    <h3>个人信息</h3>
    <ul>
      <li><strong>用户名：</strong>{{ userInfo?.username }}</li>
      <li><strong>邮箱：</strong>{{ userInfo?.email || "未绑定" }}</li>
      <li><strong>注册时间：</strong>{{ userInfo?.createdAt || "未知" }}</li>
      <li><strong>积分：</strong>{{ userInfo?.score || 0 }}</li>
      <li><strong>所属队伍：</strong>{{ userInfo?.team || "暂无" }}</li>
    </ul>
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
.profile-details {
  margin-bottom: 24px;
}

.profile-details h3 {
  font-size: 16px;
  font-weight: 600;
  margin-bottom: 12px;
  color: #2d3748;
}

.profile-details ul {
  list-style: none;
  padding: 0;
}

.profile-details li {
  padding: 8px 0;
  font-size: 14px;
  color: #4a5568;
  border-bottom: 1px solid #edf2f7;
}
</style>
