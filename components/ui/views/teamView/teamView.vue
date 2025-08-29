<template>
  <indexSidebarLayout>
    <div class="ctf-center">
      <!-- 战队信息 -->
      <section class="card">
        <div class="team-header">
          <div class="team-avatar">Qu</div>
          <div>
            <h2 class="title">Quantum</h2>
            <p class="desc">这个战队很神秘，什么都没有留下</p>
          </div>
        </div>

        <div class="info-grid">
          <div class="info-row">
            <span class="label">分数</span>
            <span class="value strong">1293</span>
            <span class="tag rank">排名 #61</span>
          </div>
          <div class="info-row">
            <span class="label">Hash</span>
            <span class="value mono">9350a861f5128954</span>
          </div>
          <div class="info-row">
            <span class="label">所属队伍</span>
            <span class="value">Public</span>
          </div>
          <div class="info-row">
            <span class="label">邀请码</span>
            <span class="value mono">Quantum-b4e36cda-ab36-4fc0-93a4-c5f3588e0775</span>
            <button class="btn-ghost" @click="copy(inviteCode)">复制</button>
          </div>
        </div>
      </section>

      <!-- 成员列表 -->
      <section class="card">
        <h3 class="sub-title">战队成员</h3>
        <div class="member-list">
          <div class="member" v-for="m in members" :key="m.name">
            <span class="avatar">{{ m.short }}</span>
            <span class="name">{{ m.name }}</span>
            <span v-if="m.isCaptain" class="tag captain">队长</span>
          </div>
        </div>
      </section>

      <!-- 解题情况 -->
      <section class="card">
        <h3 class="sub-title">解题情况</h3>
        <div class="solve-list">
          <div class="solve-item" v-for="s in solved" :key="s.id">
            <div class="solve-left">
              <div class="problem">{{ s.title }}</div>
              <div class="meta">解题者: {{ s.user }} ｜ 时间: {{ s.time }}</div>
            </div>
            <div class="solve-right">
              <span class="tag order">#{{ s.order }}</span>
              <span class="score">+{{ s.score }} pts</span>
            </div>
          </div>
        </div>
      </section>
    </div>
  </indexSidebarLayout>
</template>

<script>
import indexSidebarLayout from '@/components/ui/layouts/indexSidebarLayout/indexSidebarLayout.vue';

export default {
  name: "CTFTeamInfo",
  components: {indexSidebarLayout},
  data() {
    return {
      inviteCode: "Quantum-b4e36cda-ab36-4fc0-93a4-c5f3588e0775",
      members: [
        { name: "墨斐斐", short: "墨斐", isCaptain: true },
        { name: "Ran", short: "Ra", isCaptain: false },
        { name: "Later_More", short: "La", isCaptain: false },
        { name: "Agent", short: "Ag", isCaptain: false },
        { name: "Jjj", short: "Jj", isCaptain: false },
        { name: "衣十十", short: "衣十", isCaptain: false }
      ],
      solved: [
        { id: 1, title: "是谁没有阅读参赛须知？", user: "Jjj",  time: "2025-08-15 10:07:24", order: 172, score: 50 },
        { id: 2, title: "ez_math",              user: "Agent", time: "2025-08-15 13:10:01", order: 218, score: 50 },
        { id: 3, title: "ez_bottle",            user: "Ran",   time: "2025-08-15 13:29:26", order: 48,  score: 100 },
        { id: 4, title: "Linear",               user: "Agent", time: "2025-08-15 17:08:56", order: 37,  score: 112 },
        { id: 5, title: "mid_math",             user: "Agent", time: "2025-08-15 17:50:13", order: 90,  score: 50 },
        { id: 6, title: "Ekko_note",            user: "Ran",   time: "2025-08-15 18:31:37", order: 35,  score: 101 },
        { id: 7, title: "签到",                   user: "墨斐斐", time: "2025-08-15 18:54:32", order: 142, score: 50 },
        { id: 8, title: "Space Travel",         user: "Jjj",   time: "2025-08-15 19:40:51", order: 8,   score: 234 },
        { id: 9, title: "ARM ASM",              user: "Agent", time: "2025-08-15 21:43:23", order: 117, score: 100 },
        { id:10, title: "v我50(R)MB",            user: "墨斐斐", time: "2025-08-16 15:06:54", order: 57,  score: 114 },
        { id:11, title: "PNG Master",           user: "墨斐斐", time: "2025-08-16 19:09:46", order: 50,  score: 101 },
        { id:12, title: "baaaaaag",             user: "衣十十", time: "2025-08-17 18:40:02", order: 42,  score: 181 },
        { id:13, title: "反馈调查",               user: "Later_More", time: "2025-08-17 20:41:21", order: 79,  score: 50 }
      ]
    }
  },
  methods: {
    copy(text) {
      navigator.clipboard?.writeText(text)
          .then(() => alert("邀请码已复制"))
          .catch(() => alert("复制失败，请手动复制"));
    }
  }
}
</script>

<style scoped>
/* 容器 */
.ctf-center {
  flex: 1;
  display: flex;
  flex-direction: column;
  gap: 30px;
}

/* 卡片通用 */
.card {
  background: #fff;
  padding: 20px;
  border-radius: 14px;
  box-shadow: 0 2px 10px rgba(0,0,0,.06);
}

/* 战队信息 */
.team-header {
  display: flex;
  align-items: center;
  gap: 14px;
  margin-bottom: 12px;
}
.team-avatar {
  width: 52px;
  height: 52px;
  border-radius: 14px;
  background: rgba(0,0,0,.75);
  color: #fff;
  display: grid;
  place-items: center;
  font-weight: 700;
  letter-spacing: .5px;
}
.title {
  font-size: 22px;
  font-weight: 700;
}
.desc {
  color: #888;
  margin-top: 2px;
}

/* 信息网格 */
.info-grid {
  display: grid;
  gap: 10px;
  margin-top: 10px;
}
.info-row {
  display: flex;
  align-items: center;
  gap: 10px;
  flex-wrap: wrap;
}
.label {
  width: 80px;
  color: #666;
}
.value {
  font-weight: 500;
}
.value.mono {
  font-family: ui-monospace, SFMono-Regular, Menlo, Consolas, monospace;
}
.strong {
  font-weight: 800;
}
.tag {
  display: inline-flex;
  align-items: center;
  padding: 2px 8px;
  border-radius: 8px;
  font-size: 12px;
  background: #f2f4f7;
  color: #555;
}
.rank { color: #2f54eb; background: #eef3ff; }
.captain { color: #fff; background: #2f54eb; }
.order { background: #eef3ff; color: #2f54eb; }

.btn-ghost {
  margin-left: auto;
  padding: 6px 10px;
  border-radius: 8px;
  border: 1px solid #e5e7eb;
  background: #fff;
  cursor: pointer;
}
.btn-ghost:hover { background: #f8fafc; }

/* 成员列表 */
.sub-title {
  font-size: 18px;
  font-weight: 700;
  margin-bottom: 12px;
}
.member-list {
  display: flex;
  flex-direction: column;
  gap: 10px;
}
.member {
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 10px 8px;
  border: 1px solid #f0f0f0;
  border-radius: 10px;
}
.avatar {
  background: #e5e7eb;
  padding: 6px 10px;
  border-radius: 50%;
  font-size: 12px;
}
.name { font-weight: 600; }

/* 解题列表 */
.solve-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
}
.solve-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: #f8f9fa;
  border-radius: 10px;
  padding: 12px 14px;
}
.problem { font-weight: 700; }
.meta { font-size: 12px; color: #666; margin-top: 2px; }
.score { color: #16a34a; font-weight: 800; margin-left: 10px; }
.solve-right { display: flex; align-items: center; gap: 10px; }
</style>
