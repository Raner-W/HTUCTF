package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,
        MailSenderAutoConfiguration.class
        // 移除 WebFluxAutoConfiguration.class 排除
})
@ComponentScan(
        basePackages = "org.example",
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "org\\.example\\.controller\\..*"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "org\\.example\\.util\\.JwtUtil"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*\\.serviceImpl\\..*"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*\\.service\\.serviceImpl\\..*")
        }
)
// 移除 @EnableReactiveFeignClients 注解
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
