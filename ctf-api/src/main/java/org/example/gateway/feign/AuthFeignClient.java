package org.example.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "auth-service",path="/user")
public interface AuthFeignClient {
    /**
     * Token 验证接口（供网关调用）
     */
    @GetMapping("/verifyToken")
    Boolean verifyToken(@RequestParam("token") String token);
}
