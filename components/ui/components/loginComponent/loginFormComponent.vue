<template>
  <!-- 外层侧边栏布局：与注册页完全一致 -->
  <indexSidebarLayout>
    <div class="auth-container">
      <div class="auth-card">
        <!-- 顶部标题区：和注册页auth-header风格统一 -->
        <div class="auth-header">
          <h1>登录</h1>
          <p>欢迎回到HTUCTF平台，继续你的竞赛之旅</p>
        </div>

        <!-- 登录表单：结构与注册页form-group完全对齐 -->
        <form class="auth-form" @submit.prevent="handleLogin" novalidate>
          <!-- 邮箱输入框：复用注册页custom-input样式 -->
          <div class="form-group">
            <label for="email">邮箱</label>
            <input
                id="email"
                type="email"
                v-model="form.email"
                placeholder="请输入邮箱"
                class="custom-input"
                :class="{ 'input-error': errors.email }"
                @input="clearError('email')"
                autocomplete="email"
                required
            >
            <p class="error-message" v-if="errors.email">{{ errors.email }}</p>
          </div>

          <!-- 密码输入框：与注册页密码框样式一致 -->
          <div class="form-group">
            <label for="password">密码</label>
            <input
                id="password"
                type="password"
                v-model="form.password"
                placeholder="请输入密码"
                class="custom-input"
                :class="{ 'input-error': errors.password }"
                @input="clearError('password')"
                autocomplete="current-password"
                required
            >
            <p class="error-message" v-if="errors.password">{{ errors.password }}</p>
          </div>

          <!-- 记住我 + 忘记密码：保留原登录页交互逻辑 -->
          <div class="form-options">
            <label class="checkbox">
              <input
                  type="checkbox"
                  v-model="form.remember"
                  class="checkbox-input"
              >
              <span>记住我</span>
            </label>
            <router-link to="/forgot-password" class="forgot-link">
              忘记密码？
            </router-link>
          </div>

          <!-- 登录按钮：与注册页auth-btn样式完全统一 -->
          <button type="submit" class="auth-btn" :disabled="loading">
            <span v-if="loading">登录中...</span>
            <span v-else>立即登录</span>
          </button>

          <!-- 登录错误提示：和注册页错误提示风格一致 -->
          <p class="error-message auth-error" v-if="loginError">{{ loginError }}</p>
        </form>

        <!-- 分割线：与注册页视觉过渡一致 -->
        <div class="auth-divider">
          <span>或</span>
        </div>

        <!-- 第三方登录：整合提供的social-login组件，适配整体风格 -->
        <div class="social-login">
          <button class="social-btn github" @click="handleGithubLogin">
            <span class="social-icon">🐱</span>
            GitHub 登录
          </button>
          <button class="social-btn wechat" @click="handleWechatLogin">
            <span class="social-icon">💬</span>
            微信登录
          </button>
        </div>

        <!-- 底部注册链接：与注册页auth-footer呼应 -->
        <div class="auth-footer">
          <p>还没有账号？ <router-link to="/register">立即注册</router-link></p>
        </div>
      </div>
    </div>
  </indexSidebarLayout>
</template>
<script setup lang="ts">
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import indexSidebarLayout from '@/components/ui/layouts/indexSidebarLayout/indexSidebarLayout.vue'
import { useAuthStore } from '@/src/stores/auth.js'
import request from '@/src/utils/request.ts'

const router = useRouter()
const authStore = useAuthStore()
const emit = defineEmits(['loginSuccess'])

// 表单数据：保留原字段
const form = reactive({
  email: '',
  password: '',
  remember: false
})

// 错误提示：保留原结构
const errors = reactive({
  email: '',
  password: ''
})
const loginError = ref<string | null>(null)
const loading = ref(false)

// 清除错误：保留原逻辑
const clearError = (field: keyof typeof errors) => {
  errors[field] = ''
  loginError.value = null
}

// 表单校验：保留原逻辑
const validateForm = (): boolean => {
  let isValid = true
  const emailReg = /^[^\s@]+@[^\s@]+\.[^\s@]+$/

  if (!form.email.trim()) {
    errors.email = '请输入邮箱'
    isValid = false
  } else if (!emailReg.test(form.email)) {
    errors.email = '请输入有效的邮箱格式'
    isValid = false
  }

  if (!form.password.trim()) {
    errors.password = '请输入密码'
    isValid = false
  }

  return isValid
}

// 核心登录逻辑：修复数据提取路径，匹配后端响应
const handleLogin = async () => {
  if (!validateForm()) return

  loading.value = true
  loginError.value = null

  try {
    // 1. 调用登录接口（和之前一致）
    const response = await request({
      url: '/user/login',
      method: 'POST',
      data: {
        email: form.email.trim(),
        password: form.password.trim()
      }
    })

    // 2. 关键修复：按后端实际响应提取数据（单层data，只有token，没有refreshToken）
    const { userInfo, token } = response.data; // 正确路径：response.data（统一响应）.data（业务数据）
    const username = userInfo.username; // 从userInfo里拿用户名
    const userEmail = userInfo.email; // 从userInfo里拿邮箱

    // 3. 调用authStore存储（token加Bearer前缀，适配axios拦截器）
    authStore.loginSuccess({
      accessToken: `Bearer ${token}`, // 后端只有1个token，用它当accessToken
      username: username,
      email: userEmail,
      remember: form.remember
    })

    // 4. 登录成功流程（和之前一致）
    emit('loginSuccess')
    router.push('/')

  } catch (error: any) {
    // 新增：打印错误详情，方便后续调试
    console.error('登录异常详情：', error);
    // 错误提示：优先用后端返回，无则默认
    loginError.value = error.response?.data?.message || '登录失败，请检查邮箱或密码'
  } finally {
    loading.value = false
  }
}

// 第三方登录：保留原逻辑（后续适配时再调整）
const handleGithubLogin = () => {
  console.log('触发GitHub登录')
  const redirectUri = encodeURIComponent(`${window.location.origin}/login/callback?provider=github`)
  window.location.href = `${import.meta.env.VITE_API_BASE_URL}/oauth/github?redirect_uri=${redirectUri}`
}

const handleWechatLogin = () => {
  console.log('触发微信登录')
  const redirectUri = encodeURIComponent(`${window.location.origin}/login/callback?provider=wechat`)
  window.location.href = `${import.meta.env.VITE_API_BASE_URL}/oauth/wechat?redirect_uri=${redirectUri}`
}
</script>

<style scoped>
/* 外层布局：与注册页完全复用 */
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
  max-width: 400px; /* 与注册页卡片宽度一致 */
}

/* 标题区：和注册页auth-header样式统一 */
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

/* 表单区：与注册页auth-form样式统一 */
.auth-form {
  margin-bottom: 20px; /* 与第三方登录区保留间距 */
}

/* 表单项：复用注册页form-group样式 */
.form-group {
  margin-bottom: 20px;
}

.form-group label {
  display: block;
  margin-bottom: 8px;
  font-weight: 500;
  color: #4a5568;
}

/* 输入框：与注册页custom-input完全一致 */
.custom-input {
  width: 100%;
  padding: 12px 16px;
  border: 1px solid #e2e8f0;
  border-radius: 8px;
  font-size: 14px;
  transition: all 0.3s;
  box-sizing: border-box;
}

.custom-input:focus {
  outline: none;
  border-color: #4299e1;
  box-shadow: 0 0 0 3px rgba(66, 153, 225, 0.1);
}

/* 输入框错误状态：与注册页一致 */
.input-error {
  border-color: #e53e3e;
}

/* 错误提示：复用注册页error-message样式 */
.error-message {
  margin-top: 4px;
  font-size: 12px;
  color: #e53e3e;
  margin-bottom: 0;
}

/* 全局登录错误：居中显示，与注册页一致 */
.auth-error {
  text-align: center;
  margin-top: 16px;
}

/* 记住我+忘记密码：保留原登录页布局 */
.form-options {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 24px;
  font-size: 14px;
}

.checkbox {
  display: flex;
  align-items: center;
  cursor: pointer;
  color: #4a5568;
}

.checkbox-input {
  margin-right: 8px;
  width: 14px;
  height: 14px;
  accent-color: #4299e1; /* 与主色调一致 */
}

.forgot-link {
  color: #4299e1;
  text-decoration: none;
}

.forgot-link:hover {
  text-decoration: underline; /* 与注册页链接hover效果一致 */
}

/* 登录按钮：与注册页auth-btn完全相同 */
.auth-btn {
  width: 100%;
  padding: 12px;
  background: #4299e1;
  color: #fff;
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

/* 分割线：与注册页视觉统一，过渡第三方登录区 */
.auth-divider {
  position: relative;
  text-align: center;
  margin: 20px 0;
}

.auth-divider::before {
  content: '';
  position: absolute;
  top: 50%;
  left: 0;
  right: 0;
  height: 1px;
  background: #e2e8f0; /* 与输入框边框色一致 */
}

.auth-divider span {
  position: relative;
  background: #fff;
  padding: 0 12px;
  color: #718096;
  font-size: 14px;
}

/* 第三方登录：整合提供的样式，适配整体风格 */
.social-login {
  display: flex;
  flex-direction: column;
  gap: 12px;
  margin-bottom: 24px; /* 与底部链接区保留间距 */
}

.social-btn {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 12px 16px; /* 与输入框内边距一致 */
  border: 1px solid #e2e8f0;
  border-radius: 8px; /* 与输入框/登录按钮圆角一致 */
  background: #fff;
  cursor: pointer;
  transition: all 0.3s;
  font-size: 14px;
  color: #4a5568;
}

.social-btn:hover {
  background: #f7fafc;
  transform: translateY(-1px);
}

.social-icon {
  margin-right: 8px;
  font-size: 16px;
}

/* GitHub登录按钮：hover边框色加深 */
.github:hover {
  border-color: #2d3748;
}

/* 微信登录按钮：hover边框色用微信绿 */
.wechat:hover {
  border-color: #38a169;
}

/* 底部注册链接：与注册页auth-footer完全一致 */
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