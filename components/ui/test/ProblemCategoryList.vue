<template>
  <aside class="sidebar">
    <!-- 品牌Logo -->
    <div class="brand">
      <img src="/public/images/logo.jpg" alt="HTUCTF" />
      <span>A1CTF Platform</span>
    </div>

    <!-- 分类列表 -->
    <div class="groups">
      <div v-for="(g, idx) in groups" :key="g.key" class="group">
        <!-- 分类头部（展开/收起） -->
        <button class="group-head" @click="onToggleGroup(g.key)">
          <div class="group-title">
            <span class="icon" v-html="g.icon"></span>
            <strong>{{ g.title }} ({{ g.problems.length }})</strong>
          </div>
          <svg
              class="chev"
              viewBox="0 0 24 24"
              :style="{ transform: g.open ? 'rotate(180deg)' : 'rotate(0)' }"
          >
            <path d="m6 9 6 6 6-6"/>
          </svg>
        </button>

        <!-- 题目列表 -->
        <ul v-show="g.open" class="group-list">
          <li
              v-for="(problem, pIdx) in g.problems"
              :key="pIdx"
              class="problem-item"
              :class="{ active: currentProblem?.id === problem.id }"
              @click="onSelectProblem(problem)"
          >
            <span class="problem-score">{{ problem.score }}</span>
            <span class="problem-name">{{ problem.title }}</span>
          </li>
        </ul>
      </div>
    </div>
  </aside>
</template>

<script setup>
import { defineProps, defineEmits } from 'vue'

// 接收父组件传递的props
const props = defineProps({
  // 分类与题目数据
  groups: {
    type: Array,
    required: true,
    default: () => []
  },
  // 当前选中的题目
  currentProblem: {
    type: Object,
    default: null
  }
})

// 定义触发的事件
const emit = defineEmits([
  // 切换分类展开/收起（传递分类key）
  'toggle-group',
  // 选中题目（传递题目对象）
  'select-problem'
])

// 触发分类展开/收起事件
const onToggleGroup = (groupKey) => {
  emit('toggle-group', groupKey)
}

// 触发题目选中事件
const onSelectProblem = (problem) => {
  emit('select-problem', problem)
}
</script>

<style scoped>
/* 左侧分类列表样式（仅负责自身渲染） */
.sidebar {
  position: sticky;
  border-right: 1px solid var(--border);
  padding: 16px 12px;
  background: var(--bg);
  align-self: start;
  top: 0;
  height: 100vh;
}

.brand {
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 10px 6px 16px;
  border-bottom: 1px solid var(--border);
  margin-bottom: 12px;
}

.brand img {
  width: 40px;
  height: 40px;
  border-radius: 8px;
}

.brand span {
  font-weight: 700;
  font-size: 18px;
}

.groups {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.group {
  border: 1px solid var(--border);
  border-radius: 12px;
  overflow: hidden;
  background: var(--bg-soft);
}

.group-head {
  width: 100%;
  background: transparent;
  border: none;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px 12px;
  cursor: pointer;
  transition: background 0.2s;
}

.group-head:hover {
  background: var(--accent);
}

.group-title {
  display: flex;
  align-items: center;
  gap: 8px;
  font-size: 14px;
}

.group-title .icon svg {
  width: 20px;
  height: 20px;
  stroke: currentColor;
  fill: none;
  stroke-width: 2;
}

.chev {
  width: 20px;
  height: 20px;
  stroke: currentColor;
  fill: none;
  stroke-width: 2;
  transition: transform 0.2s;
}

.group-list {
  list-style: none;
  margin: 0;
  padding: 8px 0;
  background: var(--bg);
}

/* 题目列表项样式 */
.problem-item {
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 8px 14px;
  cursor: pointer;
  transition: background 0.2s;
  font-size: 14px;
}

.problem-item:hover {
  background: var(--accent);
}

.problem-item.active {
  background: color-mix(in oklab, var(--active-color) 10%, transparent);
  color: var(--active-color);
  font-weight: 500;
}

.problem-score {
  display: inline-block;
  width: 40px;
  text-align: right;
  color: var(--muted);
  font-size: 13px;
}

.problem-name {
  flex: 1;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

/* 响应式：小屏幕隐藏自身（由布局控制） */
@media (max-width: 1080px) {
  .sidebar {
    display: none;
  }
}
</style>