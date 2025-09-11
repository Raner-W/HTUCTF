package org.example.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// 网关通过 Feign 调用 auth-service 的接口
@FeignClient(name = "auth-service")  // 对应 auth-service 的服务名
public interface AuthFeignClient {

    // 调用 auth-service 的 Token 验证接口
    @GetMapping("/user/verifyToken")
    Boolean verifyToken(@RequestParam("token") String token);
}