<template>
  <div class="main-layout">
    <!-- 左侧：分类列表（由父组件传递props控制） -->
    <ProblemCategoryList
        v-if="!sidebarCollapsed"
        :groups="groups"
        :current-problem="currentProblem"
        @toggle-group="onToggleGroup"
        @select-problem="onSelectProblem"
    />

    <!-- 右侧：内容插槽（灵活插入任意内容） -->
    <section class="content-area" ref="contentRef">
      <!-- 顶部折叠按钮（小屏幕显示） -->
      <button
          v-if="sidebarCollapsed && isMobile"
          class="expand-sidebar-btn"
          @click="onToggleSidebar"
      >
        <svg viewBox="0 0 24 24" class="expand-icon">
          <rect width="18" height="18" x="3" y="3" rx="2"/>
          <path d="M15 3v18"/>
        </svg>
      </button>

      <!-- 内容插槽 -->
      <slot />
    </section>
  </div>
</template>

<script setup>
import { defineProps, defineEmits, ref, computed } from 'vue'
import ProblemCategoryList from './ProblemCategoryList.vue'

// 接收父组件传递的props
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
})

// 定义触发的事件（透传分类列表的事件 + 布局自身事件）
const emit = defineEmits([
  'toggle-group',
  'select-problem',
  'toggle-sidebar' // 切换侧边栏显示/隐藏
])

// 内容区ref（用于滚动）
const contentRef = ref(null)

// 计算是否为移动设备（响应式判断）
const isMobile = computed(() => {
  return window.innerWidth <= 1080
})

// 透传分类展开/收起事件
const onToggleGroup = (groupKey) => {
  emit('toggle-group', groupKey)
}

// 透传题目选中事件
const onSelectProblem = (problem) => {
  emit('select-problem', problem)
}

// 切换侧边栏显示/隐藏
const onToggleSidebar = () => {
  emit('toggle-sidebar')
}

// 滚动到内容区顶部（对外暴露方法）
const scrollToContentTop = () => {
  contentRef.value?.scrollIntoView({ behavior: 'smooth', block: 'start' })
}

// 暴露方法给父组件
defineExpose({ scrollToContentTop })
</script>

<style scoped>
/* 左右布局核心样式 */
.main-layout {
  display: grid;
  grid-template-columns: 320px 1fr;
  min-height: 100vh;
}

/* 右侧内容区样式 */
.content-area {
  position: relative;
  padding: 18px;
  height: 100%;
  overflow: auto;
  background: var(--bg);
}

/* 小屏幕展开侧边栏按钮 */
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

.expand-icon {
  width: 20px;
  height: 20px;
  stroke: currentColor;
  fill: none;
  stroke-width: 2;
}

/* 响应式布局调整 */
@media (max-width: 1080px) {
  .main-layout {
    grid-template-columns: 0 1fr; /* 小屏幕默认隐藏左侧 */
  }

  .main-layout:not(.collapsed) {
    grid-template-columns: 320px 1fr; /* 展开时显示左侧 */
  }
}
</style>