<template>
  <div class="regedit-card">
    <div class="regedit-header">
      <h1>注册</h1>
      <p>加入HTUCTF平台，开启CTF之旅</p>
    </div>

    <form class="regedit-form" @submit.prevent="handleRegedit">
      <div class="form-group">
        <label for="username">用户名</label>
        <input
            id="username"
            type="text"
            v-model="form.username"
            placeholder="请输入用户名"
            required
        >
      </div>

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

      <div class="form-group">
        <label for="confirmPassword">确认密码</label>
        <input
            id="confirmPassword"
            type="password"
            v-model="form.confirmPassword"
            placeholder="请再次输入密码"
            required
        >
      </div>

      <div class="form-options">
        <label class="checkbox">
          <input type="checkbox" v-model="form.agree" required>
          <span>我已阅读并同意<a href="#">服务条款</a>和<a href="#">隐私政策</a></span>
        </label>
      </div>

      <button type="submit" class="regedit-btn" :disabled="loading">
        <span v-if="loading">注册中...</span>
        <span v-else>注册</span>
      </button>
    </form>

    <div class="regedit-divider">
      <span>或</span>
    </div>

    <div class="social-login">
      <button class="social-btn github">
        <span class="social-icon">🐱</span>
        GitHub 注册
      </button>
      <button class="social-btn wechat">
        <span class="social-icon">💬</span>
        微信注册
      </button>
    </div>

    <div class="regedit-footer">
      <p>已有账号？ <router-link to="/login">立即登录</router-link></p>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useAuthStore } from '@/src/stores/auth.js';

const authStore = useAuthStore();
const router = useRouter();

const loading = ref(false);
const form = ref({
  username: '',
  email: '',
  password: '',
  confirmPassword: '',
  agree: false
});

const handleRegedit = async () => {
  // 表单验证
  if (form.value.password !== form.value.confirmPassword) {
    alert('两次输入的密码不一致');
    return;
  }

  if (!form.value.agree) {
    alert('请同意服务条款和隐私政策');
    return;
  }

  loading.value = true;

  try {
    // 模拟注册过程
    await new Promise(resolve => setTimeout(resolve, 1000));

    // 注册成功后自动登录
    authStore.login();

    // 跳转到首页
    router.push('/');
  } catch (error) {
    console.error('注册失败:', error);
    alert('注册失败，请稍后重试');
  } finally {
    loading.value = false;
  }
};
</script>

<style scoped>
.regedit-card {
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
  padding: 40px;
  width: 100%;
  max-width: 400px;
}

.regedit-header {
  text-align: center;
  margin-bottom: 30px;
}

.regedit-header h1 {
  font-size: 24px;
  font-weight: 700;
  color: #2d3748;
  margin-bottom: 8px;
}

.regedit-header p {
  color: #718096;
  font-size: 14px;
}

.regedit-form {
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
  margin-bottom: 24px;
}

.checkbox {
  display: flex;
  align-items: flex-start;
  cursor: pointer;
}

.checkbox input {
  margin-right: 8px;
  margin-top: 2px;
}

.checkbox span {
  font-size: 14px;
  color: #4a5568;
  line-height: 1.4;
}

.checkbox a {
  color: #4299e1;
  text-decoration: none;
}

.checkbox a:hover {
  text-decoration: underline;
}

.regedit-btn {
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

.regedit-btn:hover:not(:disabled) {
  background: #3182ce;
}

.regedit-btn:disabled {
  background: #cbd5e0;
  cursor: not-allowed;
}

.regedit-divider {
  position: relative;
  text-align: center;
  margin: 24px 0;
}

.regedit-divider::before {
  content: '';
  position: absolute;
  top: 50%;
  left: 0;
  right: 0;
  height: 1px;
  background: #e2e8f0;
}

.regedit-divider span {
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

.regedit-footer {
  text-align: center;
}

.regedit-footer p {
  color: #718096;
  font-size: 14px;
}

.regedit-footer a {
  color: #4299e1;
  text-decoration: none;
  font-weight: 500;
}

.regedit-footer a:hover {
  text-decoration: underline;
}
</style>
