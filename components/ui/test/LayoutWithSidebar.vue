<!-- LayoutWithSidebar.vue -->
<template>
  <div class="ctf-container">
    <CTFSidebar />
    <main class="ctf-content">
      <slot></slot>
    </main>
  </div>
</template>

<script>
import CTFSidebar from './CTFSidebar.vue';

export default {
  name: "LayoutWithSidebar",
  components: {
    CTFSidebar
  }
};
</script>

<style scoped>
.ctf-container {
  width: 100%;
  min-height: 100vh;
  display: flex;
  font-family: "Microsoft YaHei", sans-serif;
  background: #f0fdf4;
}

.ctf-content {
  flex: 1;
  padding: 30px 40px;
  overflow-y: auto;
  box-sizing: border-box;
  margin-left: 240px; /* 添加左边距，与侧边栏宽度一致 */
  transition: margin-left 0.3s ease; /* 添加过渡效果 */
}

/* 响应式设计 - 在小屏幕上调整布局 */
@media (max-width: 1024px) {
  .ctf-content {
    margin-left: 0;
    padding: 20px;
  }
}
</style>