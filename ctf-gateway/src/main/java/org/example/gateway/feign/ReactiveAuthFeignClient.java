//package org.example.gateway.feign;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import reactivefeign.spring.config.ReactiveFeignClient;
//import reactor.core.publisher.Mono;
//
//@ReactiveFeignClient(name = "auth-service", path = "/user")
//public interface ReactiveAuthFeignClient {
//    /**
//     * Token 验证接口（供网关调用）
//     */
//    @GetMapping("/verifyToken")
//    Mono<Boolean> verifyToken(@RequestParam("token") String token);
//}
