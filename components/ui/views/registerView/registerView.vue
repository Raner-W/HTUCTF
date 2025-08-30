<template>
  <indexSidebarLayout>
    <div class="auth-container">
      <registerFormComponent @registerSuccess="handleRegisterSuccess" />
    </div>
  </indexSidebarLayout>
</template>

<script setup lang="ts">
import indexSidebarLayout from '@/components/ui/layouts/indexSidebarLayout/indexSidebarLayout.vue'
import registerFormComponent from '@/components/ui/components/registerComponent/registerFormComponent.vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const handleRegisterSuccess = () => {
  router.push('/login?registered=true')
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
