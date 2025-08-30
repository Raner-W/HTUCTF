<template>
  <div class="social-login">
    <button class="social-btn github" @click="handleGithubLogin">
      <span class="social-icon">🐱</span>
      GitHub 登录
    </button>
    <button class="social-btn wechat" @click="handleWechatLogin">
      <span class="social-icon">💬</span>
      微信登录
    </button>
  </div>
</template>

<script setup>
const handleGithubLogin = () => {
  // GitHub 登录逻辑（如跳转授权页）
  console.log('触发 GitHub 登录');
};

const handleWechatLogin = () => {
  // 微信登录逻辑（如跳转授权页）
  console.log('触发 微信登录');
};
</script>

<style scoped>
.social-login {
  display: flex;
  flex-direction: column;
  gap: 12px;
  margin-bottom: 24px;
}
.social-btn {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 10px;
  border: 1px solid #e2e8f0;
  border-radius: 8px;
  background: white;
  cursor: pointer;
  transition: all 0.3s;
}
.social-btn:hover {
  background: #f7fafc;
  transform: translateY(-1px);
}
.social-icon {
  margin-right: 8px;
  font-size: 16px;
}
.github:hover {
  border-color: #2d3748;
}
.wechat:hover {
  border-color: #38a169;
}
</style>