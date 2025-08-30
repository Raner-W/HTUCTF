<template>
  <section class="card member-card">
    <h3 class="sub-title">战队成员</h3>
    <div class="member-list">
      <div class="member" v-for="m in members" :key="m.name">
        <span class="avatar">{{ m.short }}</span>
        <span class="name">{{ m.name }}</span>
        <span v-if="m.isCaptain" class="tag captain">队长</span>
      </div>
    </div>
  </section>
</template>

<script setup>
import { defineProps } from 'vue';

// 定义组件接收的属性
const props = defineProps({
  members: {
    type: Array,
    required: true,
    // 每个成员对象的结构验证
    validator: (value) => {
      return value.every(member =>
          'name' in member &&
          'short' in member &&
          'isCaptain' in member
      );
    }
  }
});
</script>

<style scoped>
/* 成员列表卡片样式 */
.member-card {
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

.member-list {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.member {
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 10px 8px;
  border: 1px solid #f0f0f0;
  border-radius: 10px;
}

.avatar {
  background: #e5e7eb;
  padding: 6px 10px;
  border-radius: 50%;
  font-size: 12px;
}

.name {
  font-weight: 600;
}

.tag.captain {
  color: #fff;
  background: #2f54eb;
  margin-left: auto;
}
</style>
