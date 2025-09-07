// 这是第一种设计选择：为每个操作定义独立的详细类型
export interface LoginResponse {
    code: number;
    msg: string;
    data?: {
        token: string;
        userInfo: {
            id: number;
            username: string;
            email: string;
            createdAt?: string; // 后端返回的字段
            // 前端期望的nickname和avatar可能需要从username和其他字段映射
        };
    };
}

export interface RegisterResponse {
    code: number;
    msg: string;
    data?: {
        id: number | string;
        email: string;
        nickname: string;
    };
}