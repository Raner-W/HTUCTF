<template>
  <indexSidebarLayout>
    <div class="ctf-main">
      <indexInfoComponent />
      <indexUserPanelComponent />
    </div>
  </indexSidebarLayout>
</template>

<script>
import indexInfoComponent from "@/components/ui/components/indexComponent/indexInfoComponent.vue";
import indexUserPanelComponent from "@/components/ui/components/indexComponent/indexUserPanelComponent.vue";
import indexSidebarLayout from "@/components/ui/layouts/indexSidebarLayout/indexSidebarLayout.vue";
export default {
  name: "indexView",
  components: {
    indexInfoComponent,
    indexUserPanelComponent,
    indexSidebarLayout
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