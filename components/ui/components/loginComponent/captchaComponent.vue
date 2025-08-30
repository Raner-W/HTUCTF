<template>
  <div class="captcha-group">
    <label for="captcha">验证码</label>
    <input
        id="captcha"
        type="text"
        v-model="captcha"
        placeholder="请输入验证码"
        required
    >
    <img :src="captchaSrc" alt="验证码" @click="refreshCaptcha">
  </div>
</template>

<script setup>
import { ref } from 'vue';

const captcha = ref('');
const captchaSrc = ref('/api/captcha'); // 假设验证码接口地址

const refreshCaptcha = () => {
  // 刷新验证码（加时间戳防缓存）
  captchaSrc.value = `/api/captcha?timestamp=${new Date().getTime()}`;
};
</script>

<style scoped>
.captcha-group {
  margin-bottom: 20px;
  display: flex;
  align-items: center;
  gap: 10px;
}
.captcha-group input {
  flex: 1;
  padding: 12px 16px;
  border: 1px solid #e2e8f0;
  border-radius: 8px;
  font-size: 14px;
}
.captcha-group img {
  width: 100px;
  height: 40px;
  cursor: pointer;
}
</style>