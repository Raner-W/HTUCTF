<template>
  <aside class="sidebar">
    <div class="brand">
      <img src="/public/images/logo.jpg" alt="HTUCTF" />
      <span>A1CTF Platform</span>
    </div>

    <div class="groups">
      <div v-for="(g, idx) in groups" :key="g.key" class="group">
        <button class="group-head" @click="toggleGroup(g)">
          <div class="group-title">
            <span class="icon" v-html="g.icon"></span>
            <strong>{{ g.title }} ({{ g.problems.length }})</strong>
          </div>
          <svg class="chev" viewBox="0 0 24 24" :style="{ transform: g.open ? 'rotate(180deg)' : 'rotate(0)' }">
            <path d="m6 9 6 6 6-6"/>
          </svg>
        </button>
        <ul v-show="g.open" class="group-list">
          <li
              v-for="(problem, pIdx) in g.problems"
              :key="pIdx"
              class="problem-item"
              :class="{ active: currentProblem?.id === problem.id }"
              @click="selectProblem(problem)"
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
import { defineProps, defineEmits } from 'vue';

// 接收父组件传递的分类、当前选中题目
const props = defineProps({
  groups: {
    type: Array,
    required: true,
    default: () => []
  },
  currentProblem: {
    type: Object,
    default: null
  }
});

// 向父组件发射“选中题目”“切换分类展开”事件
const emit = defineEmits(['select-problem', 'toggle-group']);

// 选中题目时，发射事件给父组件
const selectProblem = (problem) => {
  emit('select-problem', problem);
};

// 切换分类展开状态时，发射事件给父组件
const toggleGroup = (group) => {
  group.open = !group.open;
  emit('toggle-group', group);
};
</script>

<style scoped>
/* 侧边栏样式（分类列表、题目项等） */
.sidebar {
  position: sticky;
  border-right: 1px solid var(--border);
  padding: 16px 12px;
  background: var(--bg);
  align-self: start;
  top: 0;
}
.brand { display: flex; align-items: center; gap: 10px; padding: 10px 6px 16px; }
.brand img { width: 40px; height: 40px; }
.brand span { font-weight: 700; font-size: 18px; }
.groups { display: flex; flex-direction: column; gap: 12px; margin-top: 8px; }
.group { border: 1px solid var(--border); border-radius: 12px; overflow: hidden; background: var(--bg-soft); }
.group-head { width: 100%; background: transparent; border: none; display: flex; align-items: center; justify-content: space-between; padding: 10px 12px; cursor: pointer; }
.group-title { display: flex; align-items: center; gap: 8px; font-size: 14px; }
.group-title .icon svg { width: 20px; height: 20px; stroke: currentColor; fill: none; stroke-width: 2; }
.group .chev { width: 20px; height: 20px; stroke: currentColor; fill: none; stroke-width: 2; transition: transform .2s; }
.group-list { list-style: none; margin: 0; padding: 8px 0; background: var(--bg); }
.problem-item { display: flex; align-items: center; gap: 8px; padding: 8px 14px; cursor: pointer; transition: background .2s; font-size: 14px; }
.problem-item:hover { background: var(--accent); }
.problem-item.active { background: color-mix(in oklab, var(--active-color) 10%, transparent); color: var(--active-color); font-weight: 500; }
.problem-score { display: inline-block; width: 40px; text-align: right; color: var(--muted); font-size: 13px; }
.problem-name { flex: 1; white-space: nowrap; overflow: hidden; text-overflow: ellipsis; }

/* 小屏幕隐藏侧边栏 */
@media (max-width: 1080px) {
  .sidebar { display: none; }
}
</style>