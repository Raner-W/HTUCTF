<template>
  <LayoutWithSidebar>
    <div class="leaderboard-view-container">
      <div class="header">
        <h1>排行榜</h1>
        <div class="controls">
          <button class="filter-btn" :class="{ active: filter === 'all' }" @click="filter = 'all'">全部</button>
          <button class="filter-btn" :class="{ active: filter === 'individual' }" @click="filter = 'individual'">个人</button>
          <button class="filter-btn" :class="{ active: filter === 'team' }" @click="filter = 'team'">团队</button>
        </div>
      </div>

      <!-- 曲线图组件 -->
      <div class="chart-container">
        <ScoreChart :chart-data="chartData" :selected-team="selectedTeam" @team-selected="handleTeamSelected" />
      </div>

      <!-- 排行榜表格组件 -->
      <div class="table-container">
        <LeaderboardTable
            :leaderboard-data="filteredData"
            :highlighted-id="selectedTeam"
            @row-clicked="handleRowClicked"
        />
      </div>
    </div>
  </LayoutWithSidebar>
</template>

<script>
import LayoutWithSidebar from './LayoutWithSidebar.vue';
import LeaderboardTable from './LeaderboardTable.vue';
import ScoreChart from './ScoreChart.vue';

export default {
  name: "LeaderboardView",
  components: {
    LayoutWithSidebar,
    LeaderboardTable,
    ScoreChart
  },
  data() {
    return {
      filter: 'all',
      selectedTeam: null,
      // 完整的排行榜数据
      leaderboardData: [
        { id: 1, name: "CTFMaster", score: 2450, solved: 12, type: "individual" },
        { id: 2, name: "HackThePlanet", score: 2300, solved: 11, type: "team" },
        { id: 3, name: "BinaryNinjas", score: 2150, solved: 10, type: "team" },
        { id: 4, name: "CryptoCrew", score: 2000, solved: 9, type: "team" },
        { id: 5, name: "WebWarriors", score: 1850, solved: 8, type: "individual" },
        { id: 6, name: "FlagFinders", score: 1700, solved: 7, type: "team" },
        { id: 7, name: "ZeroDayHeroes", score: 1550, solved: 6, type: "individual" },
        { id: 8, name: "ByteBandits", score: 1400, solved: 5, type: "team" },
        { id: 9, name: "ShellSec", score: 1250, solved: 4, type: "individual" },
        { id: 10, name: "NetNinjas", score: 1100, solved: 3, type: "team" }
      ],
      // 时间序列数据，用于曲线图
      timeSeriesData: {
        labels: ["1月", "2月", "3月", "4月", "5月", "6月"],
        datasets: [
          { id: 1, name: "CTFMaster", data: [1200, 1500, 1800, 2100, 2300, 2450] },
          { id: 2, name: "HackThePlanet", data: [1100, 1300, 1600, 1900, 2100, 2300] },
          { id: 3, name: "BinaryNinjas", data: [1000, 1200, 1500, 1700, 1950, 2150] },
          { id: 4, name: "CryptoCrew", data: [900, 1100, 1400, 1600, 1800, 2000] },
          { id: 5, name: "WebWarriors", data: [800, 1000, 1300, 1500, 1700, 1850] }
        ]
      }
    };
  },
  computed: {
    // 根据筛选条件过滤数据
    filteredData() {
      if (this.filter === 'all') return this.leaderboardData;
      return this.leaderboardData.filter(item => item.type === this.filter);
    },
    // 准备图表数据
    chartData() {
      return {
        labels: this.timeSeriesData.labels,
        datasets: this.timeSeriesData.datasets.map(dataset => ({
          ...dataset,
          borderColor: this.getRandomColor(),
          backgroundColor: this.getRandomColor(0.1),
          borderWidth: 2,
          tension: 0.3,
          fill: true
        }))
      };
    }
  },
  methods: {
    // 生成随机颜色
    getRandomColor(alpha = 1) {
      const r = Math.floor(Math.random() * 255);
      const g = Math.floor(Math.random() * 255);
      const b = Math.floor(Math.random() * 255);
      return `rgba(${r}, ${g}, ${b}, ${alpha})`;
    },
    // 处理团队选择
    handleTeamSelected(teamId) {
      this.selectedTeam = teamId;
    },
    // 处理表格行点击
    handleRowClicked(teamId) {
      this.selectedTeam = teamId;
    }
  }
};
</script>

<style scoped>
.leaderboard-view-container {
  padding: 20px;
  max-width: 1200px;
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
  margin: 0;
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

.filter-btn.active {
  background: #2f54eb;
  color: white;
  border-color: #2f54eb;
}

.chart-container {
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.05);
  padding: 20px;
  margin-bottom: 30px;
  height: 400px;
}

.table-container {
  width: 100%;
}
</style>
