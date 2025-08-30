<template>
  <indexSidebarLayout>
    <div class="regedit-container">
      <!-- 引入注册表单组件 -->
      <RegeditFormComponent />
    </div>
  </indexSidebarLayout>
</template>

<script setup>
// 引入布局组件和注册表单组件
import RegeditFormComponent from '@/components/ui/components/regeditComponent/RegeditFormComponent.vue';
import indexSidebarLayout from '@/components/ui/layouts/indexSidebarLayout/indexSidebarLayout.vue';
</script>

<style scoped>
/* 页面级样式：控制表单容器的布局 */
.regedit-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 80vh;
  padding: 40px 20px;
}
</style>
