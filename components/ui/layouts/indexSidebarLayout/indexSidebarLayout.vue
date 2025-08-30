<template>
  <div class="ctf-container">
    <aside class="ctf-sidebar">
      <div class="sidebar-header">
        <span class="logo">HTUCTF</span>
        <span class="subtitle">河师大CTF平台</span>
      </div>

      <nav class="sidebar-nav">
        <router-link to="/challenge" class="nav-item">
          <span class="nav-icon">📝</span>
          <span class="nav-text">题目</span>
        </router-link>
        <router-link to="/team" class="nav-item">
          <span class="nav-icon">👥</span>
          <span class="nav-text">队伍管理</span>
        </router-link>
        <router-link to="/leaderboard" class="nav-item">
          <span class="nav-icon">🏆</span>
          <span class="nav-text">排行榜</span>
        </router-link>
      </nav>

      <div class="sidebar-footer">
        <button class="footer-btn" @click="toggleTheme">
          <span class="btn-icon">{{ isDarkMode ? '☀️' : '🌙' }}</span>
          <span class="btn-text">{{ isDarkMode ? '日间模式' : '黑夜模式' }}</span>
        </button>

        <button class="footer-btn">
          <span class="btn-icon">🌐</span>
          <span class="btn-text">切换语言</span>
        </button>

        <div class="profile-container">
          <div class="footer-btn profile-btn" @click="toggleProfileDropdown">
            <span class="btn-icon">👤</span>
            <span class="btn-text">个人中心</span>
            <span class="arrow">{{ isProfileOpen ? '▲' : '▼' }}</span>
          </div>

          <transition name="dropdown">
            <div class="profile-dropdown" v-if="isProfileOpen">
              <div class="dropdown-content">
                <template v-if="!authStore.isLoggedIn">
                  <button class="dropdown-item" @click="handleLogin">
                    <span class="dropdown-icon">🔑</span>
                    登录（跳转页面）
                  </button>
                  <button class="dropdown-item" @click="mockLogin">
                    <span class="dropdown-icon">📌</span>
                    模拟登录（测试）
                  </button>
                </template>
                <template v-else>
                  <button class="dropdown-item" @click="handleProfile">
                    <span class="dropdown-icon">👤</span>
                    个人信息 ({{ authStore.userInfo?.username }})
                  </button>
                  <button class="dropdown-item" @click="handleLogout">
                    <span class="dropdown-icon">🚪</span>
                    登出
                  </button>
                </template>
              </div>
            </div>
          </transition>
        </div>
      </div>
    </aside>
    <main class="ctf-content">
      <slot></slot>
    </main>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue';
import { useRouter } from 'vue-router';
import { useAuthStore } from '@/src/stores/auth.ts';

const authStore = useAuthStore();
const router = useRouter();
const isProfileOpen = ref(false);
const isDarkMode = ref(false);

const mockLogin = () => {
  authStore.login({
    username: 'CTF玩家',
    id: 1001,
    avatar: '👤'
  });
  isProfileOpen.value = false;
};

const toggleProfileDropdown = () => {
  isProfileOpen.value = !isProfileOpen.value;
};

const closeDropdown = (event) => {
  const profileContainer = document.querySelector('.profile-container');
  if (profileContainer && !profileContainer.contains(event.target)) {
    isProfileOpen.value = false;
  }
};

const toggleTheme = () => {
  isDarkMode.value = !isDarkMode.value;
  document.body.classList.toggle('dark-theme', isDarkMode.value);
};

const handleLogin = () => {
  router.push('/login');
  isProfileOpen.value = false;
};

const handleProfile = () => {
  router.push('/profile');
  isProfileOpen.value = false;
};

const handleLogout = () => {
  authStore.logout();
  isProfileOpen.value = false;
  router.push('/');
};

onMounted(() => {
  document.addEventListener('click', closeDropdown);
});

onUnmounted(() => {
  document.removeEventListener('click', closeDropdown);
});
</script>

<style scoped>
.ctf-container {
  width: 100%;
  min-height: 100vh;
  display: flex;
  font-family: "Microsoft YaHei", sans-serif;
  background: #f0fdf4;
}

.ctf-content {
  flex: 1;
  padding: 30px 40px;
  overflow-y: auto;
  box-sizing: border-box;
  margin-left: 240px;
  transition: margin-left 0.3s ease;
}

@media (max-width: 1024px) {
  .ctf-content {
    margin-left: 0;
    padding: 20px;
  }
}

.ctf-sidebar {
  width: 240px;
  height: 100vh;
  padding: 24px 0;
  background: linear-gradient(180deg, #2d3748 0%, #1a202c 100%);
  color: white;
  display: flex;
  flex-direction: column;
  box-shadow: 4px 0 15px rgba(0, 0, 0, 0.1);
  position: fixed;
  left: 0;
  top: 0;
  z-index: 1000;
}

.sidebar-header {
  padding: 0 24px 24px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.1);
  margin-bottom: 24px;
}

.logo {
  font-size: 24px;
  font-weight: 700;
  background: linear-gradient(90deg, #63b3ed 0%, #4299e1 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  display: block;
  margin-bottom: 5px;
}

.subtitle {
  font-size: 14px;
  color: #a0aec0;
  display: block;
}

.sidebar-nav {
  flex: 1;
  padding: 0 16px;
  overflow-y: auto;
}

.nav-item {
  display: flex;
  align-items: center;
  padding: 12px 14px;
  margin-bottom: 8px;
  border-radius: 8px;
  color: #cbd5e0;
  text-decoration: none;
  transition: all 0.3s ease;
  position: relative;
}

.nav-item:hover {
  background: rgba(66, 153, 225, 0.1);
  color: #fff;
  transform: translateX(5px);
}

.nav-item.router-link-active {
  background: linear-gradient(90deg, rgba(66, 153, 225, 0.2) 0%, rgba(66, 153, 225, 0.1) 100%);
  color: #fff;
  box-shadow: 0 4px 12px rgba(66, 153, 225, 0.15);
}

.nav-item.router-link-active::before {
  content: '';
  position: absolute;
  left: 0;
  top: 0;
  height: 100%;
  width: 4px;
  background: #4299e1;
  border-radius: 0 4px 4px 0;
}

.nav-icon {
  font-size: 18px;
  margin-right: 12px;
  width: 24px;
  text-align: center;
}

.nav-text {
  font-size: 15px;
  font-weight: 500;
}

.sidebar-footer {
  padding: 20px;
  border-top: 1px solid rgba(255, 255, 255, 0.1);
  padding-top: 24px;
  margin-top: auto;
  padding-bottom: 40px;
  margin-bottom: 15px;
  position: relative;
  z-index: 1;
}

.footer-btn {
  display: flex;
  align-items: center;
  width: 100%;
  padding: 10px 14px;
  background: transparent;
  border: none;
  border-radius: 6px;
  color: #cbd5e0;
  text-align: left;
  margin-bottom: 6px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.footer-btn:hover {
  background: rgba(255, 255, 255, 0.05);
  color: #fff;
}

.btn-icon {
  font-size: 16px;
  margin-right: 12px;
  width: 20px;
  text-align: center;
}

.btn-text {
  font-size: 14px;
  flex: 1;
}

.profile-container {
  position: relative;
}

.profile-btn {
  background: rgba(66, 153, 225, 0.15);
  color: #fff;
}

.profile-btn:hover {
  background: rgba(66, 153, 225, 0.25);
}

.arrow {
  font-size: 12px;
  transition: transform 0.3s ease;
}

.profile-dropdown {
  position: absolute;
  top: auto;
  bottom: 100%;
  left: 100%;
  margin-left: 8px;
  margin-bottom: 8px;
  z-index: 1001;
}

.dropdown-content {
  min-width: 140px;
  background: rgba(26, 32, 44, 0.95);
  border-radius: 8px;
  padding: 8px;
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
  backdrop-filter: blur(10px);
  border: 1px solid rgba(255, 255, 255, 0.1);
}

.dropdown-item {
  display: flex;
  align-items: center;
  width: 100%;
  padding: 10px 12px;
  background: transparent;
  border: none;
  border-radius: 6px;
  color: #cbd5e0;
  text-align: left;
  cursor: pointer;
  transition: all 0.2s ease;
  font-size: 14px;
}

.dropdown-item:hover {
  background: rgba(66, 153, 225, 0.2);
  color: #fff;
}

.dropdown-icon {
  margin-right: 8px;
  font-size: 14px;
}

.dropdown-content.logged-in .dropdown-item + .dropdown-item {
  border-top: 1px solid rgba(255, 255, 255, 0.1);
}

.dropdown-enter-active,
.dropdown-leave-active {
  transition: all 0.3s ease;
}

.dropdown-enter-from,
.dropdown-leave-to {
  opacity: 0;
  transform: translateX(-10px);
}

@media (max-width: 1024px) {
  .ctf-sidebar {
    transform: translateX(-100%);
    transition: transform 0.3s ease;
  }

  .ctf-sidebar.mobile-open {
    transform: translateX(0);
  }

  .profile-dropdown {
    position: fixed;
    top: auto;
    bottom: 60px;
    left: 20px;
    right: 20px;
    margin-left: 0;
  }
}
</style>