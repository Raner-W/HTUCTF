// 这是第一种设计选择：为每个操作定义独立的详细类型
export interface LoginResponse {
    code: number;
    msg: string;
    data?: {
        token: string;
        userInfo: {
            id: number | string;
            email: string;
            username?: string;
            nickname?: string;
            avatar?: string;
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