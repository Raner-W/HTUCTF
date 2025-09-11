package org.example.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

// Token验证过滤器
@Component
public class TokenAuthFilter extends AbstractGatewayFilterFactory<TokenAuthFilter.Config> implements ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(TokenAuthFilter.class);

    private ApplicationContext applicationContext;
    private WebClient webClient;

    public static class Config {
        private List<String> whiteList;
        public List<String> getWhiteList() { return whiteList; }
        public void setWhiteList(List<String> whiteList) { this.whiteList = whiteList; }
    }

    public TokenAuthFilter() {
        super(Config.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.webClient = applicationContext.getBean(WebClient.Builder.class).build();
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String requestPath = exchange.getRequest().getPath().value();
            log.info("处理请求路径: {}", requestPath);

            if (config.getWhiteList() != null && config.getWhiteList().contains(requestPath)) {
                log.info("路径在白名单中，跳过Token验证: {}", requestPath);
                return chain.filter(exchange);
            }

            String token = exchange.getRequest().getHeaders().getFirst("Authorization");
            if (StringUtils.isEmpty(token) || !token.startsWith("Bearer ")) {
                log.warn("Token格式错误或缺失");
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            token = token.substring(7);
            log.info("验证Token: {}", token.substring(0, Math.min(token.length(), 10)) + "...");

            // 使用WebClient进行非阻塞调用
            return webClient.get()
                    .uri("http://auth-service/user/verifyToken?token={token}", token)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .doOnNext(isTokenValid -> log.info("Token验证结果: {}", isTokenValid))
                    .flatMap(isTokenValid -> {
                        if (isTokenValid == null || !isTokenValid) {
                            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                            return exchange.getResponse().setComplete();
                        }
                        return chain.filter(exchange);
                    })
                    .onErrorResume(e -> {
                        log.error("Token验证过程中发生异常", e);
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        return exchange.getResponse().setComplete();
                    });
        };
    }

    @Override
    public String name() {
        return "TokenAuth";
    }
}
