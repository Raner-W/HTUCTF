<template>
  <div class="ctf-container">
    <!-- 引入独立的左侧侧边栏组件 -->
    <CTFSidebar />

    <!-- 右侧主内容区（保持不变） -->
    <main class="ctf-content">
      <div class="ctf-main">
        <CTFInfo />   <!-- 赛事详情组件 -->
        <CTFUserPanel /> <!-- 用户信息面板组件 -->
      </div>
    </main>
  </div>
</template>

// TODO 整合layout
<script>
// 引入所有子组件
import CTFSidebar from './CTFSidebar.vue'
import CTFInfo from './CTFInfo.vue'
import CTFUserPanel from './CTFUserPanel.vue'

export default {
  name: "CTFIndex",
  components: {
    CTFSidebar,   // 注册侧边栏组件
    CTFInfo,      // 注册赛事详情组件
    CTFUserPanel  // 注册用户信息面板组件
  }
};
</script>

<style scoped>
/* 全局布局：左侧侧边栏 + 右侧主内容（仅保留布局逻辑） */
.ctf-container {
  width: 100%;
  height: 100vh;
  display: flex;
  font-family: "Microsoft YaHei", sans-serif;
  background: #f0fdf4;
}

/* 右侧主内容区（样式不变） */
.ctf-content {
  flex: 1;
  padding: 30px 40px;
  overflow-y: auto;
  box-sizing: border-box;
}

.ctf-main {
  display: flex;
  gap: 40px;
  width: 100%;
  max-width: 1400px;
  margin: 0 auto;
}
</style>