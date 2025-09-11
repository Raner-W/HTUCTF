package org.example.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 用户注册响应VO（只包含用户基本信息）
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterVO {
    /**
     * 用户ID（数据库自增ID）
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱（脱敏显示，如"xxx@xx.com" → "x**x@xx.com"，可选）
     */
    private String email;

    /**
     * 注册时间
     */
    private LocalDateTime createdAt;
}