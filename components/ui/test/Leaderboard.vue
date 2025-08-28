<!-- Leaderboard.vue -->
<template>
  <LayoutWithSidebar>
    <div class="leaderboard-container">
      <div class="header">
        <h1>排行榜</h1>
        <div class="controls">
          <button class="filter-btn">全部</button>
          <button class="filter-btn">个人</button>
          <button class="filter-btn">团队</button>
        </div>
      </div>

      <div class="leaderboard-content">
        <div class="table-header">
          <span class="rank">排名</span>
          <span class="team">队伍/个人</span>
          <span class="score">得分</span>
          <span class="solved">解题数</span>
        </div>

        <div class="leaderboard-list">
          <div class="leaderboard-item" v-for="(item, index) in leaderboardData" :key="item.id">
            <span class="rank">{{ index + 1 }}</span>
            <span class="team">
              <span class="team-avatar">👤</span>
              {{ item.name }}
            </span>
            <span class="score">{{ item.score }} pts</span>
            <span class="solved">{{ item.solved }}</span>
          </div>
        </div>
      </div>
    </div>
  </LayoutWithSidebar>
</template>

<script>
import LayoutWithSidebar from './LayoutWithSidebar.vue';

export default {
  name: "Leaderboard",
  components: {
    LayoutWithSidebar
  },
  data() {
    return {
      leaderboardData: [
        { id: 1, name: "CTFMaster", score: 2450, solved: 12 },
        { id: 2, name: "HackThePlanet", score: 2300, solved: 11 },
        { id: 3, name: "BinaryNinjas", score: 2150, solved: 10 },
        { id: 4, name: "CryptoCrew", score: 2000, solved: 9 },
        { id: 5, name: "WebWarriors", score: 1850, solved: 8 },
        { id: 6, name: "FlagFinders", score: 1700, solved: 7 },
        { id: 7, name: "ZeroDayHeroes", score: 1550, solved: 6 },
        { id: 8, name: "ByteBandits", score: 1400, solved: 5 },
        { id: 9, name: "ShellSec", score: 1250, solved: 4 },
        { id: 10, name: "NetNinjas", score: 1100, solved: 3 }
      ]
    };
  }
};
</script>

<style scoped>
.leaderboard-container {
  padding: 20px;
  max-width: 1000px;
  margin: 0 auto;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 30px;
}

.header h1 {
  color: #333;
  font-size: 28px;
}

.controls {
  display: flex;
  gap: 10px;
}

.filter-btn {
  padding: 8px 16px;
  border: 1px solid #dce5f2;
  border-radius: 6px;
  background: white;
  color: #666;
  cursor: pointer;
  transition: all 0.3s;
}

.filter-btn:hover {
  background: #f0f5ff;
  color: #2f54eb;
}

.table-header {
  display: grid;
  grid-template-columns: 80px 1fr 120px 120px;
  padding: 15px 20px;
  background: #f8f9fa;
  border-radius: 8px;
  font-weight: bold;
  color: #666;
  margin-bottom: 10px;
}

.leaderboard-item {
  display: grid;
  grid-template-columns: 80px 1fr 120px 120px;
  padding: 15px 20px;
  margin-bottom: 10px;
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.05);
  align-items: center;
}

.team-avatar {
  margin-right: 10px;
}

.rank {
  font-weight: bold;
  color: #2f54eb;
}

.score {
  font-weight: bold;
  color: #ff6633;
}

.solved {
  color: #16a34a;
}
</style>