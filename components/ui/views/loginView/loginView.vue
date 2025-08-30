<template>
  <indexSidebarLayout>
    <div class="auth-container">
      <div class="auth-card">
        <div class="auth-header">
<!--          <h1>登录</h1>-->
<!--          <p>欢迎回到HTUCTF平台，继续你的竞赛之旅</p>-->
        </div>

        <!-- 使用登录表单组件 -->
        <loginFormComponent @loginSuccess="handleLoginSuccess" />
      </div>
    </div>
  </indexSidebarLayout>
</template>

<script setup>
import indexSidebarLayout from '@/components/ui/layouts/indexSidebarLayout/indexSidebarLayout.vue'
import loginFormComponent from '@/components/ui/components/loginComponent/loginFormComponent.vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const handleLoginSuccess = () => {
  router.push('/')
}
</script>
