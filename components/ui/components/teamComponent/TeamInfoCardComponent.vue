<template>
  <section class="card team-card">
    <div class="team-header">
      <div class="team-avatar">{{ teamAvatar }}</div>
      <div>
        <h2 class="title">{{ teamName }}</h2>
        <p class="desc">{{ teamDescription }}</p>
      </div>
    </div>

    <div class="info-grid">
      <div class="info-row">
        <span class="label">分数</span>
        <span class="value strong">{{ teamScore }}</span>
        <span class="tag rank">排名 #{{ teamRank }}</span>
      </div>
      <div class="info-row">
        <span class="label">Hash</span>
        <span class="value mono">{{ teamHash }}</span>
      </div>
      <div class="info-row">
        <span class="label">所属队伍</span>
        <span class="value">{{ teamAffiliation }}</span>
      </div>
      <div class="info-row">
        <span class="label">邀请码</span>
        <span class="value mono">{{ inviteCode }}</span>
        <button class="btn-ghost" @click="copy(inviteCode)">复制</button>
      </div>
    </div>
  </section>
</template>

<script setup>
import { defineProps } from 'vue';

// 定义组件接收的属性
const props = defineProps({
  teamAvatar: {
    type: String,
    required: true
  },
  teamName: {
    type: String,
    required: true
  },
  teamDescription: {
    type: String,
    required: true
  },
  teamScore: {
    type: Number,
    required: true
  },
  teamRank: {
    type: Number,
    required: true
  },
  teamHash: {
    type: String,
    required: true
  },
  teamAffiliation: {
    type: String,
    required: true
  },
  inviteCode: {
    type: String,
    required: true
  }
});

// 复制邀请码方法
const copy = (text) => {
  navigator.clipboard?.writeText(text)
      .then(() => alert("邀请码已复制"))
      .catch(() => alert("复制失败，请手动复制"));
};
</script>

<style scoped>
/* 战队信息卡片样式 */
.team-card {
  background: #fff;
  padding: 20px;
  border-radius: 14px;
  box-shadow: 0 2px 10px rgba(0,0,0,.06);
}

.team-header {
  display: flex;
  align-items: center;
  gap: 14px;
  margin-bottom: 12px;
}

.team-avatar {
  width: 52px;
  height: 52px;
  border-radius: 14px;
  background: rgba(0,0,0,.75);
  color: #fff;
  display: grid;
  place-items: center;
  font-weight: 700;
  letter-spacing: .5px;
}

.title {
  font-size: 22px;
  font-weight: 700;
}

.desc {
  color: #888;
  margin-top: 2px;
}

/* 信息网格样式 */
.info-grid {
  display: grid;
  gap: 10px;
  margin-top: 10px;
}

.info-row {
  display: flex;
  align-items: center;
  gap: 10px;
  flex-wrap: wrap;
}

.label {
  width: 80px;
  color: #666;
}

.value {
  font-weight: 500;
}

.value.mono {
  font-family: ui-monospace, SFMono-Regular, Menlo, Consolas, monospace;
}

.strong {
  font-weight: 800;
}

.tag {
  display: inline-flex;
  align-items: center;
  padding: 2px 8px;
  border-radius: 8px;
  font-size: 12px;
  background: #f2f4f7;
  color: #555;
}

.rank {
  color: #2f54eb;
  background: #eef3ff;
}

.btn-ghost {
  margin-left: auto;
  padding: 6px 10px;
  border-radius: 8px;
  border: 1px solid #e5e7eb;
  background: #fff;
  cursor: pointer;
}

.btn-ghost:hover {
  background: #f8fafc;
}
</style>
