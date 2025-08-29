<template>
  <indexSidebarLayout>
    <div class="auth-container">
      <div class="auth-card">
        <div class="auth-header">
          <h1>登录</h1>
          <p>欢迎回到HTUCTF平台</p>
        </div>

        <form class="auth-form" @submit.prevent="handleLogin">
          <div class="form-group">
            <label for="email">邮箱</label>
            <input
                id="email"
                type="email"
                v-model="form.email"
                placeholder="请输入邮箱"
                required
            >
          </div>

          <div class="form-group">
            <label for="password">密码</label>
            <input
                id="password"
                type="password"
                v-model="form.password"
                placeholder="请输入密码"
                required
            >
          </div>

          <div class="form-options">
            <label class="checkbox">
              <input type="checkbox" v-model="form.remember">
              <span>记住我</span>
            </label>
            <router-link to="/forgot-password" class="forgot-link">
              忘记密码？
            </router-link>
          </div>

          <button type="submit" class="auth-btn" :disabled="loading">
            <span v-if="loading">登录中...</span>
            <span v-else>登录</span>
          </button>
        </form>

        <div class="auth-divider">
          <span>或</span>
        </div>

        <div class="social-login">
          <button class="social-btn github">
            <span class="social-icon">🐱</span>
            GitHub 登录
          </button>
          <button class="social-btn wechat">
            <span class="social-icon">💬</span>
            微信登录
          </button>
        </div>

        <div class="auth-footer">
          <p>还没有账号？ <router-link to="/register">立即注册</router-link></p>
        </div>
      </div>
    </div>
  </indexSidebarLayout>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useAuthStore } from '@/src/stores/auth.js';
import indexSidebarLayout from '@/components/ui/layouts/indexSidebarLayout/indexSidebarLayout.vue';

const authStore = useAuthStore();
const router = useRouter();

const loading = ref(false);
const form = ref({
  email: '',
  password: '',
  remember: false
});

const handleLogin = async () => {
  loading.value = true;

  try {
    // 模拟登录过程
    await new Promise(resolve => setTimeout(resolve, 1000));

    // 调用Pinia store的登录方法
    authStore.login();

    // 跳转到首页
    router.push('/');
  } catch (error) {
    console.error('登录失败:', error);
    alert('登录失败，请检查邮箱和密码');
  } finally {
    loading.value = false;
  }
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

.auth-form {
  margin-bottom: 24px;
}

.form-group {
  margin-bottom: 20px;
}

.form-group label {
  display: block;
  margin-bottom: 8px;
  font-weight: 500;
  color: #4a5568;
}

.form-group input {
  width: 100%;
  padding: 12px 16px;
  border: 1px solid #e2e8f0;
  border-radius: 8px;
  font-size: 14px;
  transition: all 0.3s;
}

.form-group input:focus {
  outline: none;
  border-color: #4299e1;
  box-shadow: 0 0 0 3px rgba(66, 153, 225, 0.1);
}

.form-options {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 24px;
}

.checkbox {
  display: flex;
  align-items: center;
  cursor: pointer;
}

.checkbox input {
  margin-right: 8px;
}

.checkbox span {
  font-size: 14px;
  color: #4a5568;
}

.forgot-link {
  font-size: 14px;
  color: #4299e1;
  text-decoration: none;
}

.forgot-link:hover {
  text-decoration: underline;
}

.auth-btn {
  width: 100%;
  padding: 12px;
  background: #4299e1;
  color: white;
  border: none;
  border-radius: 8px;
  font-size: 16px;
  font-weight: 600;
  cursor: pointer;
  transition: background 0.3s;
}

.auth-btn:hover:not(:disabled) {
  background: #3182ce;
}

.auth-btn:disabled {
  background: #cbd5e0;
  cursor: not-allowed;
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

.social-login {
  display: flex;
  flex-direction: column;
  gap: 12px;
  margin-bottom: 24px;
}

.social-btn {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 10px;
  border: 1px solid #e2e8f0;
  border-radius: 8px;
  background: white;
  cursor: pointer;
  transition: all 0.3s;
}

.social-btn:hover {
  background: #f7fafc;
  transform: translateY(-1px);
}

.social-icon {
  margin-right: 8px;
  font-size: 16px;
}

.github:hover {
  border-color: #2d3748;
}

.wechat:hover {
  border-color: #38a169;
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