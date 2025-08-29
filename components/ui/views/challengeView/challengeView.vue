<template>
  <indexSidebarLayout>
<!--  <ChallengeSidebarLayout groups="">-->
    <div class="page" :class="[{ dark: isDark }]">
      <ChallengeLayout
          :groups="groups"
          :current-problem="currentProblem"
          :sidebar-collapsed="sidebarCollapsed"
          @select-problem="currentProblem = $event"
          @toggle-sidebar="sidebarCollapsed = !sidebarCollapsed"
      >
        <!-- 右侧内容区（排行榜 + 题目详情） -->
        <div class="main-content">
          <!-- 顶栏 -->
          <header class="topbar">
            <button class="ghost" @click="sidebarCollapsed = !sidebarCollapsed">
              <svg viewBox="0 0 24 24"><rect width="18" height="18" x="3" y="3" rx="2"/><path d="M9 3v18"/></svg>
            </button>
            <h1 class="title">HTUCTF 2025</h1>
            <div class="spacer"></div>
            <div class="pill">练习模式</div>
            <button class="btn-notice">
              <svg viewBox="0 0 24 24"><path d="M12 22v-9"/><path d="M15.17 2.21a1.67 1.67 0 0 1 1.63 0L21 4.57a1.93 1.93 0 0 1 0 3.36L8.82 14.79a1.655 1.655 0 0 1-1.64 0L3 12.43a1.93 1.93 0 0 1 0-3.36z"/><path d="M20 13v3.87a2.06 2.06 0 0 1-1.11 1.83l-6 3.08a1.93 1.93 0 0 1-1.78 0l-6-3.08A2.06 2.06 0 0 1 4 16.87V13"/><path d="M21 12.43a1.93 1.93 0 0 0 0-3.36L8.83 2.2a1.64 1.64 0 0 0-1.63 0L3 4.57a1.93 1.93 0 0 0 0 3.36l12.18 6.86a1.636 1.636 0 0 0 1.63 0z"/></svg>
              <span>Notices</span>
              <b class="badge">7</b>
            </button>
          </header>

          <!-- 队伍信息浮卡 -->
          <div class="team-card">
            <div class="row">
              <svg viewBox="0 0 24 24"><path d="M2 13a2 2 0 0 0 2-2V7a2 2 0 0 1 4 0v13a2 2 0 0 0 4 0V4a2 2 0 0 1 4 0v13a2 2 0 0 0 4 0v-4a2 2 0 0 1 2-2"/></svg>
              <span>Quantum</span>
            </div>
            <div class="row">
              <svg viewBox="0 0 24 24"><path d="M4 18v3"/><path d="M8 14v7"/><path d="M12 16v5"/><path d="M16 14v7"/><path d="M20 10v11"/><path d="m22 3-8.646 8.646a.5.5 0 0 1-.708 0L9.354 8.354a.5.5 0 0 0-.707 0L2 15"/></svg>
              <span>1293 pts</span>
              <svg viewBox="0 0 24 24"><path d="M12 16v5"/><path d="M16 14v7"/><path d="M20 10v11"/><path d="M4 18v3"/><path d="M8 14v7"/></svg>
              <span>Rank 61</span>
            </div>
          </div>

          <!-- 排行榜内容 -->
          <article class="markdown">
            <h2>LilCTF 最终排行榜 🎉</h2>
            <p>请获奖队伍（包括以下抽奖）各派任意数量队员加入QQ群 <b>795577347</b> ，以便后续收集领奖信息。</p>

            <details>
              <summary>点击展开获奖名单</summary>
              <div class="table-wrap">
                <table>
                  <thead>
                  <tr><th>排名</th><th>队伍名称</th><th>分数</th><th>结果</th></tr>
                  </thead>
                  <tbody>
                  <tr><td>1</td><td>OTSASumuvior</td><td>5124</td><td>Tier1</td></tr>
                  <tr><td>2</td><td>彦门🙏</td><td>4736</td><td>Tier1</td></tr>
                  <tr><td>3</td><td>永雏塔菲</td><td>4069</td><td>Tier1</td></tr>
                  <tr><td>4</td><td>Volcania</td><td>3625</td><td>Tier2</td></tr>
                  <tr><td>5</td><td>POFP</td><td>3564</td><td>Tier2</td></tr>
                  </tbody>
                </table>
              </div>
            </details>

            <h2>比赛时间</h2>
            <p>北京时间 <b>2025-08-15 10:00</b> 至 <b>2025-08-17 22:00</b>（共 60 小时）。</p>

            <h2>比赛规则（节选）</h2>
            <ol>
              <li>1-6 人组队；每名选手仅使用一个账号答题。</li>
              <li>Flag 形式：<code>LILCTF{Me4n1ngFu1_w0rDs}</code>，请提交完整字符串。</li>
            </ol>
          </article>

          <!-- 题目详情组件（通过 props 传递当前选中题目） -->
          <ChallengeDetail :current-problem="currentProblem" />
        </div>
      </ChallengeLayout>
    </div>
  </indexSidebarLayout>
<!--  </ChallengeSidebarLayout>-->
</template>

<script setup>
import { ref } from 'vue';
import ChallengeSidebarLayout from '@/components/ui/layouts/challengeSidebarLayout/challengeSidebarLayout.vue'; // 假设全局布局组件
import ChallengeLayout from '@/components/ui/layouts/challengeSidebarLayout/challengeLayout.vue';
import ChallengeDetail from '@/components/ui/components/challengeComponent/challengeDetailComponent.vue';
import indexSidebarLayout from '@/components/ui/layouts/indexSidebarLayout/indexSidebarLayout.vue'; // 导入 indexSidebarLayout
// 主题、侧边栏折叠、当前选中题目等状态
const isDark = ref(false);
const sidebarCollapsed = ref(false);
const currentProblem = ref(null);

// 分类与题目数据（示例数据）
const groups = ref([
  {
    key: 'blockchain',
    title: 'blockchain',
    problems: [],
    open: false,
    icon: `<svg viewBox="0 0 24 24"><path d="M11.767 19.089c4.924.868 6.14-6.025 1.216-6.894m-1.216 6.894L5.86 18.047m5.908 1.042-.347 1.97m1.563-8.864c4.924.869 6.14-6.025 1.215-6.893m-1.215 6.893-3.94-.694m5.155-6.2L8.29 4.26m5.908 1.042.348-1.97M7.48 20.364l3.126-17.727"/></svg>`
  },
  {
    key: 'crypto',
    title: 'crypto',
    problems: [
      {
        id: 'crypto-rsa-101',
        title: 'RSA入门',
        score: 200,
        category: 'Crypto',
        description: `
          <p>小明刚学习RSA加密，他写了一个简单的加密脚本，但忘记了私钥，你能帮他恢复吗？</p>
          <p>已知信息：</p>
          <ul>
            <li>公钥 e = 65537</li>
            <li>模数 n = 152260502792253336053561837813263742971</li>
            <li>加密后的密文 c = 86374910175782054936634799841062334868</li>
          </ul>
          <p>提示：n可以分解为两个较小的质数乘积</p>
        `,
        flagFormat: 'LILCTF{decrypted_plaintext}',
        attachment: { name: 'rsa_encrypt.py', url: '/attachments/rsa_encrypt.py' }
      }
    ],
    open: true,
    icon: `<svg viewBox="0 0 24 24"><path d="M19 15v-2a2 2 0 1 0-4 0v2"/><path d="M9 17H7l-4 4V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2v3.5"/><rect x="13" y="15" width="8" height="5" rx="1"/></svg>`
  },
  {
    key: 'misc',
    title: 'misc',
    problems: [],
    open: false,
    icon: `<svg viewBox="0 0 24 24"><path d="M19.07 4.93A10 10 0 0 0 6.99 3.34"/><path d="M4 6h.01"/><path d="M2.29 9.62A10 10 0 1 0 21.31 8.35"/><path d="M16.24 7.76A6 6 0 1 0 8.23 16.67"/><path d="M12 18h.01"/><path d="M17.99 11.66A6 6 0 0 1 15.77 16.67"/><circle cx="12" cy="12" r="2"/><path d="m13.41 10.59 5.66-5.66"/></svg>`
  },
  {
    key: 'pwn',
    title: 'pwn',
    problems: [],
    open: false,
    icon: `<svg viewBox="0 0 24 24"><path d="M9 7.13v-1a3.003 3.003 0 1 1 6 0v1"/><path d="M12 20c-3.3 0-6-2.7-6-6v-3a4 4 0 0 1 4-4h4a4 4 0 0 1 4 4v3c0 3.3-2.7 6-6 6"/><path d="M12 20v-9"/></svg>`
  },
  {
    key: 'reverse',
    title: 'reverse',
    problems: [],
    open: false,
    icon: `<svg viewBox="0 0 24 24"><rect x="14" y="14" width="4" height="6" rx="2"/><rect x="6" y="4" width="4" height="6" rx="2"/><path d="M6 20h4"/><path d="M14 10h4"/><path d="M6 14h2v6"/><path d="M14 4h2v6"/></svg>`
  },
  {
    key: 'web',
    title: 'web',
    problems: [
      {
        id: 'web-sql-101',
        title: '简单SQL注入',
        score: 150,
        category: 'Web',
        description: `
          <p>这是一个简单的登录页面，输入用户名和密码即可登录，但开发者似乎没有做好安全防护...</p>
          <p>目标：绕过登录验证，获取后台的Flag</p>
          <p>提示：</p>
          <ul>
            <li>用户名输入框存在SQL注入漏洞</li>
            <li>尝试使用万能密码逻辑</li>
            <li>登录成功后页面会显示Flag</li>
          </ul>
          <p>题目链接：<a href="http://10.10.10.10:8080/login" target="_blank">http://10.10.10.10:8080/login</a></p>
        `,
        flagFormat: 'LILCTF{sql_injection_bypass_success}',
        attachment: null
      }
    ],
    open: false,
    icon: `<svg viewBox="0 0 24 24"><path d="M15.686 15A14.5 14.5 0 0 1 12 22a14.5 14.5 0 0 1 0-20 10 10 0 1 0 9.542 13"/><path d="M2 12h8.5"/><path d="M20 6V4a2 2 0 1 0-4 0v2"/><rect width="8" height="5" x="14" y="6" rx="1"/></svg>`
  }
]);
</script>

<style scoped>
/* 页面基础样式、顶栏、队伍浮卡、Markdown 等样式 */
.page {
  --bg: #ffffff;
  --bg-soft: #f6f7f9;
  --fg: #111827;
  --muted: #6b7280;
  --border: #e5e7eb;
  --primary: #111827;
  --accent: #f1f5f9;
  --active-color: #2563eb;
  background: var(--bg);
  color: var(--fg);
  display: block;
  min-height: 100vh;
}
.page.dark {
  --bg: #0b0c0f;
  --bg-soft: #12141a;
  --fg: #e6e6e6;
  --muted: #9aa3af;
  --border: #20222a;
  --primary: #e6e6e6;
  --accent: #181b22;
  --active-color: #3b82f6;
}
.main-content { position: relative; padding: 18px; height: 100%; overflow: auto; }
.topbar { height: 70px; display: flex; align-items: center; gap: 10px; padding: 0 16px; border-bottom: 1px solid var(--border); position: sticky; top: 0; background: var(--bg); z-index: 5; }
.ghost { background: transparent; border: 1px solid var(--border); width: 32px; height: 32px; border-radius: 8px; display: grid; place-items: center; cursor: pointer; }
.ghost svg { width: 18px; height: 18px; stroke: currentColor; fill: none; stroke-width: 2; }
.title { font-size: 18px; font-weight: 800; white-space: nowrap; overflow: hidden; text-overflow: ellipsis; }
.spacer { flex: 1; }
.pill { background: #000; color: #fff; padding: 4px 12px; border-radius: 999px; mix-blend-mode: normal; }
.btn-notice { margin-left: 8px; display: inline-flex; align-items: center; gap: 8px; border: 1px solid var(--border); background: var(--bg); height: 36px; padding: 0 10px; border-radius: 10px; cursor: pointer; }
.btn-notice svg { width: 18px; height: 18px; stroke: currentColor; fill: none; stroke-width: 2; }
.badge { background: #ef4444; color: #fff; padding: 0 6px; border-radius: 6px; font-size: 11px; }
.team-card { position: fixed; right: 24px; bottom: 24px; backdrop-filter: blur(8px); background: color-mix(in oklab, var(--bg) 70%, transparent); border: 1px solid var(--border); border-radius: 16px; padding: 10px 14px; display: flex; flex-direction: column; gap: 8px; box-shadow: 0 8px 24px rgba(0,0,0,.12); }
.team-card .row { display: flex; align-items: center; gap: 8px; }
.team-card svg { width: 18px; height: 18px; stroke: currentColor; fill: none; stroke-width: 2; }
.markdown { max-width: 980px; margin: 0 auto; line-height: 1.7; font-size: 15px; margin-bottom: 24px; }
.markdown h2 { font-size: 22px; margin: 18px 0 10px; }
.markdown a { color: #2563eb; text-decoration: none; }
.markdown a:hover { text-decoration: underline; }
.markdown details { margin: 10px 0; border: 1px solid var(--border); border-radius: 8px; background: var(--bg-soft); padding: 8px 12px; }
.markdown summary { cursor: pointer; font-weight: 600; }
.table-wrap { overflow: auto; border: 1px solid var(--border); border-radius: 8px; background: var(--bg); }
table { width: 100%; border-collapse: collapse; font-size: 14px; }
th, td { border-bottom: 1px solid var(--border); padding: 8px 10px; text-align: left; }
tbody tr:hover { background: var(--accent); }

/* 小屏幕适配 */
@media (max-width: 1080px) {
  .team-card { position: static; margin: 12px 0; }
}
</style>