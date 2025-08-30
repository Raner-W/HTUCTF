import axios, { AxiosRequestConfig, AxiosError } from 'axios';
import { ElMessage } from 'element-plus'; // 假设用 Element Plus 做 UI（可替换成其他库）
import { useAuthStore } from '@/src/stores/auth.ts'; // 引入 Pinia 的 auth 状态

// 创建 axios 实例
const request = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL, // 从环境变量取接口前缀（避免硬编码）
    timeout: 5000, // 请求超时时间
    headers: {
        'Content-Type': 'application/json', // 默认 JSON 格式
    },
});

// 请求拦截器：添加 Token（登录后所有请求都需携带）
request.interceptors.request.use(
    (config: AxiosRequestConfig) => {
        const authStore = useAuthStore();
        // 若有 Token，添加到请求头（格式：Bearer xxx，和后端对齐）
        if (authStore.token) {
            config.headers = config.headers || {};
            config.headers.Authorization = authStore.token;
        }
        return config;
    },
    (error: AxiosError) => {
        ElMessage.error('请求参数错误，请检查');
        return Promise.reject(error);
    }
);

// 响应拦截器：统一处理错误（如 Token 过期、服务器错误）
request.interceptors.response.use(
    (response) => {
        const res = response.data;
        // 若后端返回非 200 状态码（如 401 未登录、403 无权限）
        if (res.code !== 200) {
            ElMessage.error(res.msg || '操作失败');
            // 特殊处理：Token 过期（需跳转登录页，清空状态）
            if (res.code === 401) {
                const authStore = useAuthStore();
                authStore.logout(); // 清空用户状态
                window.location.href = '/login'; // 强制跳转登录页
            }
            return Promise.reject(res.msg);
        }
        return res; // 成功时直接返回响应数据
    },
    (error: AxiosError) => {
        ElMessage.error('网络错误或服务器异常，请稍后再试');
        return Promise.reject(error);
    }
);

export default request;