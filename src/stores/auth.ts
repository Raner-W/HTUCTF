// @/src/stores/auth.ts
import { defineStore } from 'pinia';

// @/src/stores/auth.js
export const useAuthStore = defineStore('auth', {
    state: () => ({
        token: localStorage.getItem('auth_token') || '', // 只存1个token
        userInfo: JSON.parse(localStorage.getItem('user_info') || '{}'),
        isLogin: !!localStorage.getItem('auth_token')
    }),

    actions: {
        // 修复：去掉refreshToken参数，只接收需要的字段
        loginSuccess(data) {
            // 存储token（带Bearer前缀）
            this.token = data.accessToken;
            // 存储用户信息
            this.userInfo = {
                username: data.username,
                email: data.email
            };
            this.isLogin = true;

            // 按"记住我"选择存储方式
            if (data.remember) {
                // 长期存储（localStorage）
                localStorage.setItem('auth_token', this.token);
                localStorage.setItem('user_info', JSON.stringify(this.userInfo));
            } else {
                // 会话存储（关闭浏览器失效）
                sessionStorage.setItem('auth_token', this.token);
                sessionStorage.setItem('user_info', JSON.stringify(this.userInfo));
            }
        },

        // 登出方法（保留原逻辑）
        logout() {
            this.token = '';
            this.userInfo = {};
            this.isLogin = false;
            localStorage.removeItem('auth_token');
            localStorage.removeItem('user_info');
            sessionStorage.removeItem('auth_token');
            sessionStorage.removeItem('user_info');
        }
    }
})