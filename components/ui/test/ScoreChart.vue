<template>
  <div class="score-chart">
    <div class="chart-header">
      <h2>分数趋势图</h2>
      <div class="chart-controls">
        <label>
          <input type="checkbox" v-model="showAllTeams" @change="updateChart"> 显示所有队伍
        </label>
      </div>
    </div>
    <canvas ref="chartCanvas"></canvas>
  </div>
</template>

<script>
import { Chart, registerables } from 'chart.js';

// 注册Chart.js所有组件
Chart.register(...registerables);

export default {
  name: "ScoreChart",
  props: {
    // 图表数据
    chartData: {
      type: Object,
      required: true
    },
    // 选中的团队
    selectedTeam: {
      type: Number,
      default: null
    }
  },
  data() {
    return {
      showAllTeams: true,
      chart: null
    };
  },
  watch: {
    chartData: {
      deep: true,
      handler() {
        this.updateChart();
      }
    },
    selectedTeam() {
      this.updateChart();
    },
    showAllTeams() {
      this.updateChart();
    }
  },
  mounted() {
    this.initChart();
  },
  beforeUnmount() {
    // 销毁图表实例，防止内存泄漏
    if (this.chart) {
      this.chart.destroy();
    }
  },
  methods: {
    // 初始化图表
    initChart() {
      const ctx = this.$refs.chartCanvas.getContext('2d');
      this.chart = new Chart(ctx, {
        type: 'line',
        data: this.getDisplayData(),
        options: this.getChartOptions()
      });
    },
    // 更新图表
    updateChart() {
      if (!this.chart) {
        this.initChart();
        return;
      }
      this.chart.data = this.getDisplayData();
      this.chart.options = this.getChartOptions();
      this.chart.update();
    },
    // 获取要显示的数据
    getDisplayData() {
      let datasets = [...this.chartData.datasets];

      // 如果没有选中团队或选择显示所有团队，则显示所有数据
      if (this.selectedTeam && !this.showAllTeams) {
        datasets = datasets.filter(dataset => dataset.id === this.selectedTeam);
      }

      return {
        labels: this.chartData.labels,
        datasets: datasets
      };
    },
    // 获取图表配置
    getChartOptions() {
      return {
        responsive: true,
        maintainAspectRatio: false,
        interaction: {
          mode: 'index',
          intersect: false,
        },
        plugins: {
          legend: {
            position: 'top',
            labels: {
              usePointStyle: true,
              cursor: 'pointer',
              onClick: (e, legendItem) => {
                const teamId = this.chartData.datasets[legendItem.datasetIndex].id;
                this.$emit('team-selected', teamId);
              }
            }
          },
          tooltip: {
            backgroundColor: 'rgba(255, 255, 255, 0.9)',
            titleColor: '#333',
            bodyColor: '#666',
            borderColor: '#ddd',
            borderWidth: 1,
            padding: 12,
            boxPadding: 6,
            usePointStyle: true,
            callbacks: {
              label: function(context) {
                return `${context.dataset.name}: ${context.raw} 分`;
              }
            }
          }
        },
        scales: {
          x: {
            grid: {
              display: false
            },
            title: {
              display: true,
              text: '时间'
            }
          },
          y: {
            beginAtZero: true,
            grid: {
              color: 'rgba(0, 0, 0, 0.05)'
            },
            title: {
              display: true,
              text: '分数'
            }
          }
        },
        animation: {
          duration: 1000,
          easing: 'easeOutQuart'
        }
      };
    }
  }
};
</script>

<style scoped>
.score-chart {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
}

.chart-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 15px;
}

.chart-header h2 {
  margin: 0;
  color: #333;
  font-size: 18px;
}

.chart-controls {
  color: #666;
  font-size: 14px;
}

.chart-controls input {
  margin-right: 6px;
}
</style>
