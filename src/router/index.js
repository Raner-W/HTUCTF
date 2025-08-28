import { createRouter, createWebHistory } from 'vue-router'
import CTFIndex from '@/components/ui/test/CTFIndex.vue' // 导入主布局组件
import Leaderboard from '@/components/ui/test/Leaderboard.vue' // 导入排行榜组件

const routes = [
    {
        path: '/',
        name: 'Home',
        component: CTFIndex // 使用主布局组件
    },
    {
        path: '/leaderboard',
        name: 'Leaderboard',
        component: Leaderboard
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router