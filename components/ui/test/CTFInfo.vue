<template>
  <section class="left-panel">
    <div class="section-block">
      <h2>比赛背景与介绍</h2>
      <p>
        欢迎来到HTUCTF，这是由河南师范大学网络安全协会主办的一场信息安全夺旗赛。本次比赛面向 25 级新生，为各位带来一场前所未有的技术之旅。无论你是否具备计算机专业背景，只要你怀揣对技术的向往，对黑客世界充满好奇，都能在这里体验到纯粹的快乐，找到属于自己的闪耀时刻。
      </p>
    </div>

    <div class="section-block">
      <h2>比赛亮点</h2>
      <ul>
        <li>由学生社团西电信息安全协会主办，面向 0 基础新生设计题目。</li>
        <li>面向所有学院，不限专业，兴趣是唯一的门票。</li>
        <li>丰富多彩的题目类型，涵盖多个安全领域，让每个人找到自己的兴趣点。</li>
        <li>社区与技术相结合，为你提供耐心指导和技术交流的机会。</li>
        <li>精美奖品等你来拿，展示你的技术实力。</li>
      </ul>
    </div>

    <div class="section-block">
      <h2>比赛详情</h2>
      <ul>
        <li>比赛时间：2025 年 8 月 9 日 - 2025 年 10 月 9 日。</li>
        <li>报名时间：8 月 2 日 08:00 开放报名，比赛期间可随时报名参赛。</li>
        <li>比赛赛制：单人线上解题模式。</li>
        <li>比赛内容：涵盖 Web 安全与渗透测试、软件逆向工程、二进制漏洞利用、现代密码学、取证与安全杂项等。</li>
      </ul>
      <p>MoeCTF 2025 是你迈入 CTF 世界的最佳起点，将为你开启一段充满碰撞思维、交流技术的新旅程。</p>
      <p>
        参赛同学可进入我们的赛事官方 QQ 群
        <a href="https://qm.qq.com/cgi-bin/qm/qr?k=1814114928" target="_blank">1814114928</a>，获取最新资讯，与各路技术达人互动交流。
      </p>
      <p>
        河师大同学请进入校内 QQ 群
        <a href="https://qm.qq.com/cgi-bin/qm/qr?k=774969568" target="_blank">774969568</a>。
      </p>
    </div>
  </section>
</template>

<script>
export default {
  name: "CTFInfo" // 组件名称
};
</script>

<style scoped>
/* 赛事详情面板样式（原左侧面板样式） */
.left-panel {
  flex: 2; /* 占主内容区2/3宽度 */
}

.section-block {
  margin-bottom: 40px;
  padding: 16px;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.05);
}

.section-block h2 {
  font-size: 16px;
  font-weight: bold;
  color: #333;
  margin-bottom: 12px;
  padding-bottom: 8px;
  border-bottom: 1px solid #f5f5f5;
}

.section-block p {
  line-height: 1.8;
  color: #666;
  margin-bottom: 8px;
  font-size: 14px;
}

.section-block ul {
  list-style: disc;
  margin-left: 20px;
  color: #666;
  line-height: 1.8;
  font-size: 14px;
}

.section-block a {
  color: #2f54eb;
  text-decoration: none;
  transition: underline 0.3s;
}

.section-block a:hover {
  text-decoration: underline;
}
</style>