<template>
  <indexSidebarLayout>
    <div class="profile-container">
      <div class="profile-card">
        <!-- 头像与基本信息 -->
        <div class="profile-header">
          <div class="avatar">
            <span>{{ authStore.userInfo?.avatar || "👤" }}</span>
          </div>
          <div class="info">
            <h2>{{ authStore.userInfo?.username || "未登录" }}</h2>
            <p>ID: {{ authStore.userInfo?.id || "N/A" }}</p>
            <p>Email: {{ authStore.userInfo?.email || "未绑定" }}</p>
          </div>
        </div>

        <!-- 详细资料 -->
        <div class="profile-details">
          <h3>个人信息</h3>
          <ul>
            <li><strong>用户名：</strong>{{ authStore.userInfo?.username }}</li>
            <li><strong>邮箱：</strong>{{ authStore.userInfo?.email || "未绑定" }}</li>
            <li><strong>注册时间：</strong>{{ authStore.userInfo?.createdAt || "未知" }}</li>
            <li><strong>积分：</strong>{{ authStore.userInfo?.score || 0 }}</li>
            <li><strong>所属队伍：</strong>{{ authStore.userInfo?.team || "暂无" }}</li>
          </ul>
        </div>

        <!-- 操作按钮 -->
        <div class="profile-actions">
          <button class="action-btn primary" @click="editProfile">
            ✏️ 编辑资料
          </button>
          <button class="action-btn danger" @click="handleLogout">
            🚪 退出登录
          </button>
        </div>
      </div>
    </div>
  </indexSidebarLayout>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { useAuthStore } from '@/src/stores/auth.js';
import indexSidebarLayout from '@/components/ui/layouts/indexSidebarLayout/indexSidebarLayout.vue';

const authStore = useAuthStore();
const router = useRouter();

const handleLogout = () => {
  authStore.logout();
  router.push('/login');
};

const editProfile = () => {
  alert("编辑资料功能开发中...");
};
</script>

<style scoped>
.profile-container {
  display: flex;
  justify-content: center;
  padding: 40px 20px;
}

.profile-card {
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
  padding: 30px;
  width: 100%;
  max-width: 600px;
}

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

.profile-actions {
  display: flex;
  gap: 12px;
  justify-content: flex-end;
}

.action-btn {
  padding: 10px 16px;
  border-radius: 8px;
  font-size: 14px;
  font-weight: 600;
  cursor: pointer;
  border: none;
  transition: all 0.3s;
}

.action-btn.primary {
  background: #4299e1;
  color: #fff;
}

.action-btn.primary:hover {
  background: #3182ce;
}

.action-btn.danger {
  background: #e53e3e;
  color: #fff;
}

.action-btn.danger:hover {
  background: #c53030;
}
</style>
