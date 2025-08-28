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
  height: 100vh;
  display: flex;
  font-family: "Microsoft YaHei", sans-serif;
  background: #f0fdf4;
}

.ctf-content {
  flex: 1;
  padding: 30px 40px;
  overflow-y: auto;
  box-sizing: border-box;
}
</style>