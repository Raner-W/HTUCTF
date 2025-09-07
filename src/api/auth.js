import request from '@/src/utils/request';

// 发送验证码 - 调用 Java 后端 /sendCode 接口
export const sendCaptcha = (email, type = 'register') => {
    return request({
        url: '/user/sendCaptcha',
        method: 'GET',
        params: { email, type }
    });
};

// 注册 - 调用 Java 后端注册接口
// 注册接口调整
export const register = (data) => {
    return request({
        url: '/user/register',
        method: 'POST',
        data: {
            email: data.email,
            password: data.password,
            name: data.nickname, // 后端期望的是name而不是nickname
            captcha: data.code // 后端期望的是captcha而不是code
        }
    });
};

// 登录接口调整
export const login = (data) => {
    return request({
        url: '/user/login',
        method: 'POST',
        data: {
            email: data.email,
            password: data.password
        }
    });
};
//忘记密码
export const resetPassword = (data) => {
    return request({
        url: '/user/resetPassword',
        method: 'POST',
        data: {
            email: data.email,
            password: data.password,
            captcha: data.code // 后端期望的是captcha而不是code
        }
    });
};

// auth.js 中的导出方式（默认导出）
export default {
    sendCaptcha,
    register,
    login,
    resetPassword
};