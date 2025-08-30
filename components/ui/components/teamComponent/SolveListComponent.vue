<template>
  <section class="card solve-card">
    <h3 class="sub-title">解题情况</h3>
    <div class="solve-list">
      <div class="solve-item" v-for="s in solved" :key="s.id">
        <div class="solve-left">
          <div class="problem">{{ s.title }}</div>
          <div class="meta">解题者: {{ s.user }} ｜ 时间: {{ s.time }}</div>
        </div>
        <div class="solve-right">
          <span class="tag order">#{{ s.order }}</span>
          <span class="score">+{{ s.score }} pts</span>
        </div>
      </div>
    </div>
  </section>
</template>

<script setup>
import { defineProps } from 'vue';

// 定义组件接收的属性
const props = defineProps({
  solved: {
    type: Array,
    required: true,
    // 每个解题记录的结构验证
    validator: (value) => {
      return value.every(solve =>
          'id' in solve &&
          'title' in solve &&
          'user' in solve &&
          'time' in solve &&
          'order' in solve &&
          'score' in solve
      );
    }
  }
});
</script>

<style scoped>
/* 解题情况卡片样式 */
.solve-card {
  background: #fff;
  padding: 20px;
  border-radius: 14px;
  box-shadow: 0 2px 10px rgba(0,0,0,.06);
}

.sub-title {
  font-size: 18px;
  font-weight: 700;
  margin-bottom: 12px;
}

.solve-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.solve-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: #f8f9fa;
  border-radius: 10px;
  padding: 12px 14px;
}

.problem {
  font-weight: 700;
}

.meta {
  font-size: 12px;
  color: #666;
  margin-top: 2px;
}

.score {
  color: #16a34a;
  font-weight: 800;
  margin-left: 10px;
}

.solve-right {
  display: flex;
  align-items: center;
  gap: 10px;
}

.tag.order {
  background: #eef3ff;
  color: #2f54eb;
  display: inline-flex;
  align-items: center;
  padding: 2px 8px;
  border-radius: 8px;
  font-size: 12px;
}
</style>
