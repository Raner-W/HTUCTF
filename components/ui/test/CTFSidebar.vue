<!-- CTFSidebar.vue -->
<template>
  <aside class="ctf-sidebar">
    <div class="sidebar-header">
      <span class="logo">HTUCTF</span>
    </div>

    <nav class="sidebar-nav">
      <router-link to="/challenges" class="nav-item">
        <span class="nav-icon">📝</span>
        <span class="nav-text">题目</span>
      </router-link>
      <router-link to="/team" class="nav-item">
        <span class="nav-icon">👥</span>
        <span class="nav-text">队伍管理</span>
      </router-link>
      <router-link
          to="/leaderboard"
          class="nav-item"
          exact-active-class="router-link-active"
      >
        <span class="nav-icon">🏆</span>
        <span class="nav-text">排行榜</span>
      </router-link>
    </nav>

    <div class="sidebar-footer">
      <button class="footer-btn">
        <span class="btn-icon">🌙</span>
        <span class="btn-text">黑夜模式</span>
      </button>
      <button class="footer-btn">
        <span class="btn-icon">🌐</span>
        <span class="btn-text">切换语言</span>
      </button>
      <button class="footer-btn">
        <span class="btn-icon">👤</span>
        <span class="btn-text">个人中心</span>
      </button>
    </div>
  </aside>
</template>

<script>
export default {
  name: "CTFSidebar"
};
</script>

<style scoped>
/* 样式保持不变 */
.ctf-sidebar {
  width: 220px;
  height: 100vh;
  padding: 24px 16px;
  border-right: 1px solid #dce5f2;
  display: flex;
  flex-direction: column;
  box-sizing: border-box;
  background: #fff;
}

.sidebar-header {
  padding: 0 8px;
}
.logo {
  font-size: 20px;
  font-weight: bold;
  color: #2f54eb;
}

.sidebar-nav {
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: center;
  gap: 24px;
  padding: 0 8px;
  margin: 20px 0;
}
.nav-item {
  display: flex;
  align-items: center;
  gap: 12px;
  color: #666;
  text-decoration: none;
  padding: 10px 12px;
  border-radius: 6px;
  transition: all 0.3s;
}
.nav-item:hover {
  color: #2f54eb;
  background: #f0f5ff;
}
.nav-icon {
  font-size: 16px;
}
.nav-text {
  font-size: 14px;
}

.sidebar-footer {
  display: flex;
  flex-direction: column;
  gap: 12px;
  padding: 0 8px;
}
.footer-btn {
  display: flex;
  align-items: center;
  gap: 12px;
  width: 100%;
  padding: 8px 12px;
  border: none;
  border-radius: 6px;
  background: transparent;
  color: #666;
  font-size: 14px;
  cursor: pointer;
  transition: all 0.3s;
}
.footer-btn:hover {
  color: #2f54eb;
  background: #f0f5ff;
}
.btn-icon {
  font-size: 14px;
}
.btn-text {
  font-size: 13px;
}

/* 添加路由链接激活状态样式 */
.router-link-active {
  color: #2f54eb;
  background: #f0f5ff;
  font-weight: bold;
}
</style>