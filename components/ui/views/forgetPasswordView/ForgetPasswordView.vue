<template>
  <indexSidebarLayout>
    <div class="auth-container">
      <forgetPasswordFormComponent  @resetPasswordSuccess="handleforgetPasswordSuccess" />
    </div>
  </indexSidebarLayout>
</template>

<script setup lang="ts">
import indexSidebarLayout from '@/components/ui/layouts/indexSidebarLayout/indexSidebarLayout.vue'
import forgetPasswordFormComponent from '@/components/ui/components/forgetPasswordComponent/forgetPasswordComponent.vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const handleforgetPasswordSuccess = () => {
  router.push('/login?forgetPassworded=true')
}
</script>

<style scoped>
.auth-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 80vh;
  padding: 40px 20px;
}
</style>
