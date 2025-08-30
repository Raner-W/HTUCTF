<template>
  <indexSidebarLayout>
    <div class="profile-container">
      <div class="profile-card">
        <!-- 引入头部组件 -->
        <profileHeaderComponent :user-info="authStore.userInfo" />

        <!-- 引入详情组件 -->
        <profileDetailsComponent :user-info="authStore.userInfo" />

        <!-- 引入操作按钮组件，并监听事件 -->
        <profileActionsComponent
            @editProfile="editProfile"
            @logout="handleLogout"
        />
      </div>
    </div>
  </indexSidebarLayout>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { useAuthStore } from '@/src/stores/auth.js';
import indexSidebarLayout from '@/components/ui/layouts/indexSidebarLayout/indexSidebarLayout.vue';
import profileHeaderComponent from '@/components/ui/components/profileComponent/profileHeaderComponent.vue';
import profileDetailsComponent from '@/components/ui/components/profileComponent/profileDetailsComponent.vue';
import profileActionsComponent from '@/components/ui/components/profileComponent/profileActionsComponent.vue';

const authStore = useAuthStore();
const router = useRouter();

// 处理退出登录
const handleLogout = () => {
  authStore.logout();
  router.push('/login');
};

// 处理编辑资料
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
</style>
