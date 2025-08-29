import { createRouter, createWebHistory } from 'vue-router'
import CTFIndex from '@/components/ui/test/CTFIndex.vue'
import LeaderboardView from '@/components/ui/test/LeaderboardView.vue'
import CTFTeamInfo from '@/components/ui/test/CTFTeamInfo.vue'
import LoginView from '@/components/ui/test/LoginView.vue'
import RegisterView from '@/components/ui/test/RegisterView.vue'
import Profile from "@/components/ui/test/Profile.vue";
const routes = [
    {
        path: '/',
        name: 'Home',
        component: CTFIndex
    },
    {
        path: '/leaderboard',
        name: 'Leaderboard',
        component: LeaderboardView
    },
    {
        path: '/team',
        name: 'Team',
        component: CTFTeamInfo
    },
    {
        path: '/login',
        name: 'Login',
        component: LoginView
    },
    {
        path: '/register',
        name: 'Register',
        component: RegisterView
    },
    {
        path: '/profile',
        name: 'Profile',
        component: Profile
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router