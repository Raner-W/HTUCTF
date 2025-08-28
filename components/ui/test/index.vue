<template>
  <div class="ctf-container">
    <!-- 左侧侧边栏（原顶部导航改造） -->
    <aside class="ctf-sidebar">
      <div class="sidebar-top">
        <span class="logo">HTUCTF</span>
        <nav class="nav-links">
          <a href="#">题目</a>
          <a href="#">积分板</a>
          <a href="#">队伍管理</a>
          <a href="#" class="return-btn">返回</a>
        </nav>
        <span class="clock">1803:66:15</span>
      </div>
      <div class="sidebar-bottom">
        <div class="icons-group">
          <!-- 占位图标，可替换为实际SVG/FontAwesome -->
          <span class="icon">♡</span>
          <span class="icon">⚙</span>
          <span class="icon">⊕</span>
          <span class="icon">A</span>
        </div>
      </div>
    </aside>

    <!-- 右侧主内容区（包含两个子组件） -->
    <main class="ctf-content">
      <div class="ctf-main">
        <!-- 引入赛事详情组件 -->
        <CTFInfo />
        <!-- 引入用户信息面板组件 -->
        <CTFUserPanel />
      </div>
    </main>
  </div>
</template>

<script>
// 引入子组件
import CTFInfo from './CTFInfo.vue'
import CTFUserPanel from './CTFUserPanel.vue'

export default {
  name: "CTFIndex",
  components: {
    CTFInfo,    // 注册赛事详情组件
    CTFUserPanel // 注册用户信息面板组件
  }
};
</script>

<style scoped>
/* 全局布局：左侧侧边栏 + 右侧主内容 */
.ctf-container {
  width: 100%;
  height: 100vh;
  display: flex;
  font-family: "Microsoft YaHei", sans-serif;
  background: #f0fdf4;
}

/* 左侧侧边栏样式（原顶部导航改造） */
.ctf-sidebar {
  width: 220px;
  height: 100vh;
  padding: 24px 20px;
  border-right: 1px solid #dce5f2;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  box-sizing: border-box;
}

.sidebar-top {
  display: flex;
  flex-direction: column;
  gap: 30px;
}

.logo {
  font-size: 18px;
  font-weight: bold;
  color: #2f54eb;
}

/* 侧边栏导航链接（纵向排列） */
.nav-links {
  display: flex;
  flex-direction: column;
  gap: 18px;
}

.nav-links a {
  color: #666;
  text-decoration: none;
  transition: color 0.3s;
  font-size: 14px;
}

.nav-links a:hover {
  color: #2f54eb;
}

.return-btn {
  color: #ff6633 !important;
}

/* 侧边栏时间 */
.clock {
  color: #999;
  font-size: 14px;
}

/* 侧边栏底部图标组 */
.sidebar-bottom {
  display: flex;
  justify-content: center;
}

.icons-group {
  display: flex;
  gap: 16px;
  align-items: center;
}

.icon {
  color: #999;
  font-size: 16px;
  cursor: pointer;
  transition: color 0.3s;
}

.icon:hover {
  color: #2f54eb;
}

/* 右侧主内容区 */
.ctf-content {
  flex: 1;
  padding: 30px 40px;
  overflow-y: auto; /* 内容溢出时滚动 */
  box-sizing: border-box;
}

/* 主内容区布局（保持原flex结构） */
.ctf-main {
  display: flex;
  gap: 40px;
  width: 100%;
  max-width: 1400px; /* 限制最大宽度，避免大屏拉伸 */
  margin: 0 auto;
}
</style>