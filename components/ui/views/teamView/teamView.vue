<template>
  <indexSidebarLayout>
    <div class="ctf-center">
      <!-- 战队信息卡片 -->
      <TeamInfoCardComponent
          :team-avatar="teamAvatar"
          :team-name="teamName"
          :team-description="teamDescription"
          :team-score="teamScore"
          :team-rank="teamRank"
          :team-hash="teamHash"
          :team-affiliation="teamAffiliation"
          :invite-code="inviteCode"
      />

      <!-- 成员列表 -->
      <MemberListComponent :members="members" />

      <!-- 解题情况 -->
      <SolveListComponent :solved="solved" />
    </div>
  </indexSidebarLayout>
</template>

<script setup>
import { ref } from 'vue';
// 引入布局组件
import indexSidebarLayout from '@/components/ui/layouts/indexSidebarLayout/indexSidebarLayout.vue';
// 引入战队相关组件
import TeamInfoCardComponent from '@/components/ui/components/teamComponent/TeamInfoCardComponent.vue';
import MemberListComponent from '@/components/ui/components/teamComponent/MemberListComponent.vue';
import SolveListComponent from '@/components/ui/components/teamComponent/SolveListComponent.vue';

// 战队信息数据
const teamAvatar = ref("Qu");
const teamName = ref("Quantum");
const teamDescription = ref("这个战队很神秘，什么都没有留下");
const teamScore = ref(1293);
const teamRank = ref(61);
const teamHash = ref("9350a861f5128954");
const teamAffiliation = ref("Public");
const inviteCode = ref("Quantum-b4e36cda-ab36-4fc0-93a4-c5f3588e0775");

// 成员数据
const members = ref([
  { name: "墨斐斐", short: "墨斐", isCaptain: true },
  { name: "Ran", short: "Ra", isCaptain: false },
  { name: "Later_More", short: "La", isCaptain: false },
  { name: "Agent", short: "Ag", isCaptain: false },
  { name: "Jjj", short: "Jj", isCaptain: false },
  { name: "衣十十", short: "衣十", isCaptain: false }
]);

// 解题情况数据
const solved = ref([
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
]);
</script>

<style scoped>
/* 页面容器样式 */
.ctf-center {
  flex: 1;
  display: flex;
  flex-direction: column;
  gap: 30px;
}
</style>
