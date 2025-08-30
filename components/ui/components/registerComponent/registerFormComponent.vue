<template>
  <div class="auth-card">
    <!-- 顶部标题区 -->
    <div class="auth-header">
      <h1>注册</h1>
      <p>创建HTUCTF平台账号，开启竞赛之旅</p>
    </div>

    <!-- 注册表单 -->
    <form class="auth-form" @submit.prevent="handleRegister" novalidate>
      <!-- 昵称 -->
      <div class="form-group">
        <label for="nickname">昵称</label>
        <input
            id="nickname"
            type="text"
            v-model="form.nickname"
            placeholder="请输入昵称"
            class="custom-input"
            :class="{ 'input-error': errors.nickname }"
            @input="clearError('nickname')"
            autocomplete="name"
        >
        <p class="error-message" v-if="errors.nickname">{{ errors.nickname }}</p>
      </div>

      <!-- 邮箱 -->
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
        >
        <p class="error-message" v-if="errors.email">{{ errors.email }}</p>
      </div>

      <!-- 密码 -->
      <div class="form-group">
        <label for="password">密码</label>
        <input
            id="password"
            type="password"
            v-model="form.password"
            placeholder="请输入至少8位密码"
            class="custom-input"
            :class="{ 'input-error': errors.password }"
            @input="clearError('password')"
            autocomplete="new-password"
        >
        <p class="error-message" v-if="errors.password">{{ errors.password }}</p>
      </div>

      <!-- 验证码 -->
      <div class="form-group">
        <label for="code">验证码</label>
        <div class="captcha-group">
          <input
              id="code"
              type="text"
              v-model="form.code"
              placeholder="请输入验证码"
              class="custom-input captcha-input"
              :class="{ 'input-error': errors.code }"
              @input="clearError('code')"
              autocomplete="one-time-code"
          >
          <button
              type="button"
              class="send-captcha-btn"
              :disabled="isCountingDown"
              @click="sendVerificationCode"
          >
            {{ captchaButtonText }}
          </button>
        </div>
        <p class="error-message" v-if="errors.code">{{ errors.code }}</p>
      </div>

      <!-- 注册按钮 -->
      <button type="submit" class="auth-btn" :disabled="loading">
        <span v-if="loading">注册中...</span>
        <span v-else>立即注册</span>
      </button>

      <!-- 注册错误提示 -->
      <p class="error-message auth-error" v-if="registerError">{{ registerError }}</p>
    </form>

    <!-- 底部链接区 -->
    <div class="auth-footer">
      <p>已有账号？ <router-link to="/login">立即登录</router-link></p>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, computed, onUnmounted } from 'vue'
import { useRouter } from 'vue-router'
import AuthAPI from '@/src/api/auth.js'

const router = useRouter()
const emit = defineEmits(['registerSuccess'])

// 表单数据
const form = reactive({
  nickname: '',
  email: '',
  password: '',
  code: ''
})

// 错误信息
const errors = reactive({
  nickname: '',
  email: '',
  password: '',
  code: ''
})

// 全局错误提示
const registerError = ref<string | null>(null)

// 验证码倒计时相关
const countdown = ref(0)
let countdownTimer: NodeJS.Timeout | null = null

// 加载状态
const loading = ref(false)

// 计算属性：是否正在倒计时
const isCountingDown = computed(() => countdown.value > 0)

// 计算属性：验证码按钮文本
const captchaButtonText = computed(() => {
  return isCountingDown.value ? `${countdown.value}秒后重试` : '获取验证码'
})

// 清除单个输入框错误
const clearError = (field: keyof typeof errors) => {
  errors[field] = ''
  registerError.value = null
}

// 验证码倒计时核心逻辑
const startCountdown = () => {
  countdown.value = 60
  if (countdownTimer) clearInterval(countdownTimer)

  countdownTimer = setInterval(() => {
    countdown.value--
    if (countdown.value <= 0) {
      clearInterval(countdownTimer!)
      countdownTimer = null
    }
  }, 1000)
}

// 发送验证码
const sendVerificationCode = async () => {
  clearError('email')
  clearError('code')
  registerError.value = null

  // 邮箱格式校验
  const emailReg = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
  if (!form.email) {
    errors.email = '请输入邮箱'
    return
  } else if (!emailReg.test(form.email)) {
    errors.email = '请输入有效的邮箱格式'
    return
  }

  // 点击后立即启动倒计时，无论请求成功与否
  startCountdown()

  try {
    await AuthAPI.sendCaptcha(form.email, 'register')
    alert('验证码已发送至邮箱，请查收')
  } catch (error: any) {
    errors.code = error.response?.data?.message || '验证码发送失败，请稍后重试'
  }
}

// 表单校验
const validateForm = (): boolean => {
  let isValid = true
  const emailReg = /^[^\s@]+@[^\s@]+\.[^\s@]+$/

  // 昵称校验
  if (!form.nickname.trim()) {
    errors.nickname = '请输入昵称'
    isValid = false
  } else if (form.nickname.length < 2 || form.nickname.length > 10) {
    errors.nickname = '昵称长度需在2-10个字符之间'
    isValid = false
  }

  // 邮箱校验
  if (!form.email.trim()) {
    errors.email = '请输入邮箱'
    isValid = false
  } else if (!emailReg.test(form.email)) {
    errors.email = '请输入有效的邮箱格式'
    isValid = false
  }

  // 密码校验
  if (!form.password.trim()) {
    errors.password = '请输入密码'
    isValid = false
  } else if (form.password.length < 8) {
    errors.password = '密码长度至少8位'
    isValid = false
  }

  // 验证码校验
  if (!form.code.trim()) {
    errors.code = '请输入验证码'
    isValid = false
  }

  return isValid
}

// 处理注册
const handleRegister = async () => {
  // 表单校验
  if (!validateForm()) return

  loading.value = true
  registerError.value = null

  try {
    await AuthAPI.register({
      email: form.email.trim(),
      nickname: form.nickname.trim(),
      password: form.password.trim(),
      code: form.code.trim()
    })

    emit('registerSuccess')
    alert('注册成功！即将跳转到登录页')
    setTimeout(() => {
      router.push('/login?registered=true')
    }, 1500)
  } catch (error: any) {
    registerError.value = error.response?.data?.message || '注册失败，请稍后重试'
  } finally {
    loading.value = false
  }
}

// 组件卸载清除定时器
onUnmounted(() => {
  if (countdownTimer) clearInterval(countdownTimer)
})
</script>


<style scoped>
/* 保留原有样式，但移除与布局相关的样式 */
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

.input-error {
  border-color: #e53e3e;
}

.error-message {
  margin-top: 4px;
  font-size: 12px;
  color: #e53e3e;
  margin-bottom: 0;
}

.auth-error {
  text-align: center;
  margin-top: 16px;
}

.captcha-group {
  display: flex;
  gap: 10px;
  align-items: center;
}

.captcha-input {
  flex: 1;
}

.send-captcha-btn {
  padding: 12px 16px;
  border: none;
  border-radius: 8px;
  background: #4299e1;
  color: #fff;
  font-size: 14px;
  cursor: pointer;
  transition: background 0.3s;
  white-space: nowrap;
}

.send-captcha-btn:hover:not(:disabled) {
  background: #3182ce;
}

.send-captcha-btn:disabled {
  background: #cbd5e0;
  cursor: not-allowed;
}

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
