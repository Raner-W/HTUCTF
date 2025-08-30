import { defineStore } from 'pinia';
import { ref, computed } from 'vue';
import { UserState, LoginRequest, LoginResponse } from '@/src/types/auth';
import { login } from '@/src/api/auth';

export const useAuthStore = defineStore('auth', () => {
    const token = ref(localStorage.getItem('htuctf_token') || '');
    const userInfo = ref(JSON.parse(localStorage.getItem('htuctf_user') || '{}'));
    const isLogin = computed(() => !!token.value);

    // 登录方法
    const userLogin = async (form: LoginRequest) => {
        const res = await login(form);

        // 存储 Token 和用户信息
        const storage = form.remember ? localStorage : sessionStorage;
        storage.setItem('htuctf_token', res.data!.token);
        storage.setItem('htuctf_user', JSON.stringify(res.data!.userInfo));

        // 更新状态
        token.value = res.data!.token;
        userInfo.value = res.data!.userInfo;

        return res.data!;
    };

    // 登出方法
    const logout = () => {
        localStorage.removeItem('htuctf_token');
        localStorage.removeItem('htuctf_user');
        sessionStorage.removeItem('htuctf_token');
        sessionStorage.removeItem('htuctf_user');

        token.value = '';
        userInfo.value = {};
    };

    return { token, userInfo, isLogin, login: userLogin, logout };
});