import { createRouter, createWebHistory } from 'vue-router'
import IndexView from "@/components/ui/views/indexView/indexView.vue";
import LeaderboardView from '@/components/ui/views/leaderboardView/leaderboardView.vue'
import CTFTeamInfo from '@/components/ui/views/teamView/teamView.vue'
import LoginView from '@/components/ui/views/loginView/loginView.vue'
import RegisterView from '@/components/ui/views/registerView/registerView.vue'
import Profile from "@/components/ui/views/profileView/profileView.vue";
import ChallengeView from "@/components/ui/views/challengeView/challengeView.vue";

const routes = [
    {
        path: '/',
        name: 'index',
        component: IndexView
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
        component: LoginView,
        meta: {
            requiresAuth: false
        }
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
    {
        path: '/challenge',
        name: 'Challenge',
        component: ChallengeView
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;