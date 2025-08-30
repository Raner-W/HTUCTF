<template>
  <button
      class="auth-btn"
      :disabled="disabled"
      @click="onClick"
  >
    <span v-if="!isLoading">{{ text }}</span>
    <span v-if="isLoading">{{ loadingText }}</span>
  </button>
</template>

<script setup>
const props = defineProps({
  text: { type: String, required: true }, // 按钮默认文本
  loadingText: { type: String, default: "处理中..." },// 加载状态文本
  isLoading: { type: Boolean, default: false },// 是否加载中
  disabled: { type: Boolean, default: false },// 是否禁用
  onClick: { type: Function, default: () => {} }// 点击回调
});
</script>

<style scoped>
/* 与登录页auth-btn样式完全统一 */
.auth-btn {
  width: 100%;
  height: 48px;
  background: linear-gradient(90deg, #4299e1 0%, #63b3ed 100%);
  color: #fff;
  border: none;
  border-radius: 8px;
  font-size: 16px;
  font-weight: 600;
  cursor: pointer;
  transition: opacity 0.3s ease;
  display: flex;
  justify-content: center;
  align-items: center;
}
.auth-btn:disabled {
  opacity: 0.7;
  cursor: not-allowed;
}
</style>