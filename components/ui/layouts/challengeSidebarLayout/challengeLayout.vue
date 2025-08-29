<template>
  <div class="main-layout">
    <!-- 左侧：题目分类列表（小屏幕默认隐藏） -->
    <challengeSidebarLayout
        v-if="!sidebarCollapsed"
        :groups="groups"
        :current-problem="currentProblem"
        @select-problem="emit('select-problem', $event)"
        @toggle-group="emit('toggle-group', $event)"
    />

    <!-- 右侧：内容区（插槽，可插入任意内容） -->
    <section class="content-area" ref="contentRef">
      <!-- 小屏幕“展开侧边栏”按钮 -->
      <button
          v-if="sidebarCollapsed && isMobile"
          class="expand-sidebar-btn"
          @click="emit('toggle-sidebar')"
      >
        <svg viewBox="0 0 24 24" class="expand-icon">
          <rect width="18" height="18" x="3" y="3" rx="2"/>
          <path d="M15 3v18"/>
        </svg>
      </button>

      <!-- 内容插槽（父组件传入的内容会显示在这里） -->
      <slot />
    </section>
  </div>
</template>

<script setup>
import { defineProps, defineEmits, ref, computed } from 'vue';
import challengeSidebarLayout from '@/components/ui/layouts/challengeSidebarLayout/challengeSidebarLayout.vue';

// 接收父组件传递的分类、当前题目、侧边栏折叠状态
const props = defineProps({
  groups: {
    type: Array,
    required: true,
    default: () => []
  },
  currentProblem: {
    type: Object,
    default: null
  },
  sidebarCollapsed: {
    type: Boolean,
    default: false
  }
});

// 向父组件发射“选中题目”“切换分类”“切换侧边栏”事件
const emit = defineEmits(['select-problem', 'toggle-group', 'toggle-sidebar']);

const contentRef = ref(null);

// 计算是否为移动设备（小屏幕判断）
const isMobile = computed(() => window.innerWidth <= 1080);

// 暴露“滚动到内容顶部”方法给父组件
defineExpose({
  scrollToContentTop: () => {
    contentRef.value?.scrollIntoView({ behavior: 'smooth', block: 'start' });
  }
});
</script>

<style scoped>
/* 左右布局核心样式 */
.main-layout { display: grid; grid-template-columns: 320px 1fr; min-height: 100vh; }

/* 右侧内容区样式 */
.content-area { position: relative; padding: 18px; height: 100%; overflow: auto; background: var(--bg); }

/* 小屏幕“展开侧边栏”按钮 */
.expand-sidebar-btn {
  position: fixed;
  top: 20px;
  left: 20px;
  z-index: 10;
  width: 40px;
  height: 40px;
  border-radius: 8px;
  border: 1px solid var(--border);
  background: var(--bg);
  box-shadow: 0 2px 8px rgba(0,0,0,.1);
  cursor: pointer;
  display: grid;
  place-items: center;
}
.expand-icon { width: 20px; height: 20px; stroke: currentColor; fill: none; stroke-width: 2; }

/* 小屏幕布局适配 */
@media (max-width: 1080px) {
  .main-layout { grid-template-columns: 0 1fr; }
  .main-layout:not(.collapsed) { grid-template-columns: 320px 1fr; }
}
</style>