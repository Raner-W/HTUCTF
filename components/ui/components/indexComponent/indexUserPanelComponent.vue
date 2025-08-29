<template>
  <section class="right-panel">
    <div class="illustration-card">
      <!-- 占位图片，可替换为实际图片 -->
      <img
          src="/images/logo.jpg"
          alt="HTUCTF插画"
          class="illustration-img"
      />
      <div class="illustration-label">
        <span class="main-title">HTUCTF</span>
        <span class="sub-title">河师大 CTF 新生赛</span>
      </div>
    </div>

    <div class="countdown">
      <span>距离 结束 还有</span>
      <span class="time-display">1003:06:14</span>
    </div>

    <div class="tags-group">
      <span class="tag single">单人赛</span>
      <span class="tag open">开放赛事</span>
    </div>

    <div class="user-status">
      <span class="agent">Agent #802b72</span>
      <span class="status-text">Passed 无组织</span>
      <span class="rank">No.1177</span>
      <span class="score">450 pts</span>
    </div>

    <button class="challenge-btn">攻克挑战！</button>
  </section>
</template>

<script>
export default {
  name: "CTFUserPanel" // 组件名称
};
</script>

<style scoped>
/* 用户信息面板样式（原右侧面板样式） */
.right-panel {
  flex: 1; /* 占主内容区1/3宽度 */
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 24px;
}

/* 插画卡片 */
.illustration-card {
  width: 100%;
  background: #fff;
  padding: 16px;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.05);
  text-align: center;
}

.illustration-img {
  width: 100%;
  border-radius: 6px;
  object-fit: cover;
  margin-bottom: 12px;
}

.illustration-label {
  text-align: center;
}

.main-title {
  font-size: 18px;
  font-weight: bold;
  color: #333;
}

.sub-title {
  font-size: 14px;
  color: #999;
  margin-left: 8px;
}

/* 倒计时 */
.countdown {
  width: 100%;
  text-align: center;
  color: #666;
  font-size: 14px;
  padding: 12px;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.05);
}

.time-display {
  font-weight: bold;
  color: #ff6633;
  margin-left: 8px;
  font-size: 15px;
}

/* 标签组 */
.tags-group {
  width: 100%;
  display: flex;
  justify-content: center;
  gap: 8px;
}

.tag {
  padding: 4px 12px;
  border-radius: 16px;
  font-size: 14px;
}

.single {
  background: #cfe2ff;
  color: #2f54eb;
}

.open {
  background: #d1fae5;
  color: #16a34a;
}

/* 用户状态 */
.user-status {
  width: 100%;
  background: #fff;
  padding: 16px;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.05);
  text-align: center;
  display: flex;
  flex-direction: column;
  gap: 6px;
}

.agent {
  color: #333;
  font-weight: bold;
  font-size: 15px;
}

.status-text {
  color: #999;
  font-size: 14px;
}

.rank, .score {
  color: #666;
  font-size: 14px;
}

/* 挑战按钮 */
.challenge-btn {
  width: 100%;
  padding: 12px 0;
  background: #3459d3;
  color: #fff;
  border: none;
  border-radius: 6px;
  font-size: 16px;
  cursor: pointer;
  transition: background 0.3s;
  box-shadow: 0 2px 8px rgba(52, 89, 211, 0.2);
}

.challenge-btn:hover {
  background: #103ab9;
}
</style>