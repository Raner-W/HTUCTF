<template>
  <indexSidebarLayout>
    <div class="auth-container">
      <div class="auth-card">
        <div class="auth-header">
          <h1>登录</h1>
          <p>欢迎回到HTUCTF平台</p>
        </div>

        <!-- 引入“登录表单组件”，监听登录成功事件 -->
        <loginFormComponent @loginSuccess="handleLoginSuccess" />

        <div class="auth-divider">
          <span>或</span>
        </div>

        <!-- 引入“第三方登录按钮组件” -->
        <oauthButtonsComponent />

        <div class="auth-footer">
          <p>还没有账号？ <router-link to="/register">立即注册</router-link></p>
        </div>
      </div>
    </div>
  </indexSidebarLayout>
</template>

<script setup>
import { useRouter } from 'vue-router';
import indexSidebarLayout from '@/components/ui/layouts/indexSidebarLayout/indexSidebarLayout.vue';
import loginFormComponent from '@/components/ui/components/loginComponent/loginFormComponent.vue';
import oauthButtonsComponent from '@/components/ui/components/loginComponent/oauthButtonsComponent.vue';

const router = useRouter();

// 登录成功后跳转到首页
const handleLoginSuccess = () => {
  router.push('/');
};
</script>

<style scoped>
.auth-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 80vh;
  padding: 40px 20px;
}
.auth-card {
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
  padding: 40px;
  width: 100%;
  max-width: 400px;
}
.auth-header {
  text-align: center;
  margin-bottom: 30px;
}
.auth-header h1 {
  font-size: 24px;
  font-weight: 700;
  color: #2d3748;
  margin-bottom: 8px;
}
.auth-header p {
  color: #718096;
  font-size: 14px;
}
.auth-divider {
  position: relative;
  text-align: center;
  margin: 24px 0;
}
.auth-divider::before {
  content: '';
  position: absolute;
  top: 50%;
  left: 0;
  right: 0;
  height: 1px;
  background: #e2e8f0;
}
.auth-divider span {
  position: relative;
  background: white;
  padding: 0 12px;
  color: #718096;
  font-size: 14px;
}
.auth-footer {
  text-align: center;
}
.auth-footer p {
  color: #718096;
  font-size: 14px;
}
.auth-footer a {
  color: #4299e1;
  text-decoration: none;
  font-weight: 500;
}
.auth-footer a:hover {
  text-decoration: underline;
}
</style>