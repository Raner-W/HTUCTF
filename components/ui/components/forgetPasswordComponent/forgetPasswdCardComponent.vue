<template>
  <div class="auth-card">
    <!-- 内容插槽：供页面注入标题、表单等 -->
    <slot></slot>
  </div>
</template>

<script setup>
// 无业务逻辑，仅提供样式容器
</script>

<style scoped>
.auth-card {
  width: 100%;
  max-width: 420px;
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.08);
  padding: 36px;
  box-sizing: border-box;

  /* 响应式适配（统一处理） */
  @media (max-width: 1024px) {
    padding: 24px;
  }
}
</style>