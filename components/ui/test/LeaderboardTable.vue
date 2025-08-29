<template>
  <div class="leaderboard-table">
    <div class="table-header">
      <span class="rank">排名</span>
      <span class="team">队伍/个人</span>
      <span class="score">得分</span>
      <span class="solved">解题数</span>
    </div>

    <div class="leaderboard-list">
      <div
          class="leaderboard-item"
          v-for="(item, index) in leaderboardData"
          :key="item.id"
          :class="{ highlighted: item.id === highlightedId }"
          @click="handleRowClick(item.id)"
      >
        <span class="rank">{{ index + 1 }}</span>
        <span class="team">
          <span class="team-avatar">{{ item.type === 'team' ? '👥' : '👤' }}</span>
          {{ item.name }}
        </span>
        <span class="score">{{ item.score }} pts</span>
        <span class="solved">{{ item.solved }}</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "LeaderboardTable",
  props: {
    // 排行榜数据
    leaderboardData: {
      type: Array,
      required: true,
      default: () => []
    },
    // 需要高亮显示的ID
    highlightedId: {
      type: Number,
      default: null
    }
  },
  methods: {
    // 处理行点击事件
    handleRowClick(id) {
      this.$emit('row-clicked', id);
    }
  }
};
</script>

<style scoped>
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

.leaderboard-list {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.leaderboard-item {
  display: grid;
  grid-template-columns: 80px 1fr 120px 120px;
  padding: 15px 20px;
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.05);
  align-items: center;
  cursor: pointer;
  transition: all 0.2s;
}

.leaderboard-item:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0,0,0,0.1);
}

.leaderboard-item.highlighted {
  border: 2px solid #2f54eb;
  background-color: #f0f5ff;
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
