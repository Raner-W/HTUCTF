<template>
  <div class="problem-detail-container">
    <!-- 未选题目提示 -->
    <div v-if="!currentProblem" class="empty-tip">
      <svg viewBox="0 0 24 24" class="empty-icon"><path d="M12 2a10 10 0 1 0 10 10A10 10 0 0 0 12 2zm0 18a8 8 0 1 1 8-8 8 8 0 0 1-8 8z"/><path d="M12 7a1 1 0 0 0-1 1v4a1 1 0 0 0 2 0V8a1 1 0 0 0-1-1z"/><path d="M12 16a1 1 0 1 0 1 1 1 1 0 0 0-1-1z"/></svg>
      <p>请从左侧分类中选择题目查看详情</p>
    </div>

    <!-- 题目详情卡片 -->
    <div v-else class="problem-detail">
      <div class="detail-header">
        <h2 class="detail-title">{{ currentProblem.title }}</h2>
        <div class="detail-meta">
          <span class="detail-score">分数: {{ currentProblem.score }}</span>
          <span class="detail-category">分类: {{ currentProblem.category }}</span>
        </div>
      </div>

      <div class="detail-section">
        <h3 class="section-title">题目描述</h3>
        <div class="section-content" v-html="currentProblem.description"></div>
      </div>

      <div class="detail-section">
        <h3 class="section-title">Flag格式</h3>
        <div class="section-content">
          <code>{{ currentProblem.flagFormat }}</code>
        </div>
      </div>

      <div class="detail-section" v-if="currentProblem.attachment">
        <h3 class="section-title">附件</h3>
        <div class="section-content">
          <a :href="currentProblem.attachment.url" class="attachment-link" target="_blank">
            <svg viewBox="0 0 24 24" class="attach-icon"><path d="M19 9h-4V3H9v6H5l7 7 7-7zM5 18v2h14v-2H5z"/></svg>
            {{ currentProblem.attachment.name }}
          </a>
        </div>
      </div>

      <div class="detail-submit">
        <input type="text" v-model="flagInput" placeholder="输入Flag并提交" class="flag-input">
        <button class="submit-btn">提交</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

// 接收父组件传递的“当前选中题目”
defineProps({
  currentProblem: {
    type: Object,
    default: null
  }
});

// 存储用户输入的Flag
const flagInput = ref('');
</script>

<style scoped>
/* 题目详情容器、卡片、分区等样式 */
.problem-detail-container { max-width: 980px; margin: 0 auto; padding: 16px; border-radius: 12px; background: var(--bg-soft); }
.problem-detail { background: var(--bg); border-radius: 8px; border: 1px solid var(--border); padding: 24px; box-shadow: 0 2px 8px rgba(0,0,0,.05); }
.detail-header { margin-bottom: 20px; padding-bottom: 12px; border-bottom: 1px solid var(--border); }
.detail-title { font-size: 20px; margin: 0 0 8px; color: var(--primary); }
.detail-meta { display: flex; gap: 16px; font-size: 14px; color: var(--muted); }
.detail-section { margin-bottom: 20px; }
.section-title { font-size: 16px; margin: 0 0 8px; color: var(--primary); font-weight: 600; }
.section-content { font-size: 14px; line-height: 1.6; color: var(--fg); }
.attachment-link { display: inline-flex; align-items: center; gap: 8px; color: var(--active-color); text-decoration: none; font-size: 14px; }
.attachment-link:hover { text-decoration: underline; }
.attach-icon { width: 16px; height: 16px; stroke: currentColor; fill: none; stroke-width: 2; }
.detail-submit { display: flex; gap: 12px; margin-top: 24px; padding-top: 16px; border-top: 1px solid var(--border); }
.flag-input { flex: 1; height: 40px; padding: 0 12px; border: 1px solid var(--border); border-radius: 6px; font-size: 14px; background: var(--bg); color: var(--fg); }
.flag-input:focus { outline: none; border-color: var(--active-color); box-shadow: 0 0 0 2px color-mix(in oklab, var(--active-color) 20%, transparent); }
.submit-btn { height: 40px; padding: 0 20px; border: none; border-radius: 6px; background: var(--active-color); color: #fff; font-size: 14px; font-weight: 500; cursor: pointer; transition: background .2s; }
.submit-btn:hover { background: color-mix(in oklab, var(--active-color) 80%, black); }
.empty-tip { display: flex; flex-direction: column; align-items: center; justify-content: center; padding: 48px 24px; color: var(--muted); text-align: center; }
.empty-icon { width: 48px; height: 48px; stroke: var(--muted); fill: none; stroke-width: 1.5; margin-bottom: 16px; }

/* 小屏幕适配 */
@media (max-width: 1080px) {
  .problem-detail { padding: 16px; }
  .detail-submit { flex-direction: column; }
}
</style>