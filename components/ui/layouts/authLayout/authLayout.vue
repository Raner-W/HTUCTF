<template>
  <div class="auth-page">
    <div class="auth-card">
      <header class="auth-header">
        <slot name="logo">
          <h1 class="brand">A1CTF Platform</h1>
        </slot>
        <slot name="title"><h2>登录</h2></slot>
        <slot name="subtitle"></slot>
      </header>

      <main class="auth-main">
        <slot />
      </main>

      <footer class="auth-footer">
        <slot name="footer"></slot>
      </footer>
    </div>
  </div>
</template>

<script setup lang="ts">
// 纯布局，无逻辑
</script>

<style scoped>
.auth-page { min-height: 100vh; display:flex; align-items:center; justify-content:center; }
.auth-card { width: 420px; max-width: 92vw; border:1px solid var(--border,#e5e7eb); border-radius:16px; padding:24px; box-shadow:0 8px 24px rgba(0,0,0,.08); background:var(--bg,#fff); }
.auth-header { display:flex; flex-direction:column; gap:6px; margin-bottom:14px; }
.auth-main { display:flex; flex-direction:column; gap:16px; }
.auth-footer { margin-top:8px; font-size:12px; color:#6b7280; }
.brand { font-size:18px; }
</style>
