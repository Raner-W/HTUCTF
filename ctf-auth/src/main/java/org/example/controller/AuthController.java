package org.example.controller;

import org.example.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Token 验证接口（供网关调用）
     */
    @GetMapping("/user/verifyToken")
    public Boolean verifyToken(@RequestParam("token") String token) {
        try {
            // 从 token 中提取用户名
            String username = jwtUtil.extractUsername(token);
            // 由于没有 UserDetails 对象，我们只检查 token 是否有效和是否过期
            // 这里我们直接检查 token 是否过期
            return !jwtUtil.isTokenExpired(token);
        } catch (Exception e) {
            // 验证失败（如 Token 过期、篡改）
            return false;
        }
    }

    // 你现有的 /user/login、/user/register 等接口...
}
