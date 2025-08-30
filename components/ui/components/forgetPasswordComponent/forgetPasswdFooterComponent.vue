<template>
  <div class="auth-footer">
    <p>{{ text }} <a :href="linkHref" class="link">{{ linkText }}</a></p>
  </div>
</template>

<script setup>
const props = defineProps({
  text: {
    type: String,
    required: true, // 前置文本（如“记得密码了？”）
    default: "跳转文本前缀"
  },
  linkText: {
    type: String,
    required: true, // 链接文字（如“立即登录”）
    default: "链接文本"
  },
  linkHref: {
    type: String,
    required: true, // 链接地址（如“/login”）
    default: "/"
  }
});
</script>

<style scoped>
.auth-footer {
  margin-top: 24px;
  text-align: center;
  font-size: 14px;
  color: #718096;
}
.auth-footer .link {
  color: #4299e1;
  text-decoration: none;
  font-weight: 500;
}
.auth-footer .link:hover {
  text-decoration: underline;
}
</style>