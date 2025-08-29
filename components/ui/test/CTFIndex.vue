<template>
  <LayoutWithSidebar>
    <div class="ctf-main">
      <CTFInfo />
      <CTFUserPanel />
    </div>
  </LayoutWithSidebar>
</template>

<script>
import LayoutWithSidebar from './LayoutWithSidebar.vue'
import CTFInfo from './CTFInfo.vue'
import CTFUserPanel from './CTFUserPanel.vue'

export default {
  name: "CTFIndex",
  components: {
    LayoutWithSidebar,
    CTFInfo,
    CTFUserPanel
  }
};
</script>

<style scoped>
.ctf-main {
  display: flex;
  gap: 40px;
  width: 100%;
  max-width: 1400px;
  margin: 0 auto;
}

/* 响应式设计 - 在小屏幕上调整布局 */
@media (max-width: 1024px) {
  .ctf-main {
    flex-direction: column;
    gap: 20px;
  }
}
</style>