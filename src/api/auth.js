import request from '@/src/utils/request';

// 发送验证码 - 调用 Java 后端 /sendCode 接口
export const sendCaptcha = (email, type = 'register') => {
    return request({
        url: '/user/sendCode',
        method: 'GET',
        params: { email, type }
    });
};

// 注册 - 调用 Java 后端注册接口
export const register = (data) => {
    return request({
        url: '/user/register',
        method: 'POST',
        data: {
            email: data.email,
            password: data.password,
            nickname: data.nickname, // 添加昵称字段
            code: data.code // 使用 code 而不是 captcha
        }
    });
};

// 登录 - 调用 Java 后端登录接口
export const login = (data) => {
    return request({
        url: '/user/login',
        method: 'POST',
        data: {
            email: data.email,
            password: data.password
            // 根据你的 Java 后端，可能不需要验证码
        }
    });
};

// auth.js 中的导出方式（默认导出）
export default {
    sendCaptcha,
    register,
    login
};