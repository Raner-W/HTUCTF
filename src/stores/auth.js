// @/src/stores/auth.js
import { defineStore } from 'pinia';

export const useAuthStore = defineStore('auth', {
    state: () => ({
        // 从本地存储读取状态，模拟持久化
        isLoggedIn: localStorage.getItem('isLoggedIn') === 'true',
        userInfo: JSON.parse(localStorage.getItem('userInfo') || 'null')
    }),
    actions: {
        // 模拟登录（接受用户信息参数）
        login(userInfo = { username: '测试用户', id: 1 }) {
            this.isLoggedIn = true;
            this.userInfo = userInfo;
            // 保存到本地存储，模拟登录状态持久化
            localStorage.setItem('isLoggedIn', 'true');
            localStorage.setItem('userInfo', JSON.stringify(userInfo));
        },
        // 模拟登出
        logout() {
            this.isLoggedIn = false;
            this.userInfo = null;
            localStorage.removeItem('isLoggedIn');
            localStorage.removeItem('userInfo');
        }
    }
});