<template>
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
</template>

<script setup>
import { ref } from 'vue';
import { useAuthStore } from '@/src/stores/auth.js'; // 假设Pinia的authStore

const authStore = useAuthStore();
const emit = defineEmits(['loginSuccess']); // 向父组件传递登录成功事件

const loading = ref(false);
const form = ref({
  email: '',
  password: '',
  remember: false
});

const handleLogin = async () => {
  loading.value = true;
  try {
    await new Promise(resolve => setTimeout(resolve, 1000)); // 模拟接口延迟
    authStore.login(); // 调用Pinia登录方法
    emit('loginSuccess'); // 通知父组件“登录成功”
  } catch (error) {
    console.error('登录失败:', error);
    alert('登录失败，请检查邮箱和密码');
  } finally {
    loading.value = false;
  }
};
</script>

<style scoped>
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
</style>