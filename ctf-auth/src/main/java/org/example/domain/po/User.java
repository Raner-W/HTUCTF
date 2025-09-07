package org.example.domain.po;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * 用户表PO实体类（与数据库users表映射）
 */
@Data
@Entity
@Table(name = "users") // 指定对应数据库表名
public class User {

    /**
     * 主键ID（自增）
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 对应MySQL自增主键
    private Integer id;

    /**
     * 用户名（唯一）
     */
    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;

    /**
     * 邮箱（唯一）
     */
    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    /**
     * 加密后的密码
     */
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    /**
     * 所属组织
     */
    @Column(name = "organization", length = 100)
    private String organization;

    /**
     * 头像URL
     */
    @Column(name = "avatar_url", length = 255)
    private String avatarUrl; // 数据库字段是avatar_url，实体类用驼峰命名

    /**
     * 创建时间（自动填充）
     */
    @CreationTimestamp // 插入时自动生成当前时间
    @Column(name = "created_at", updatable = false) // 禁止更新
    private LocalDateTime createdAt;

    /**
     * 更新时间（自动更新）
     */
    @UpdateTimestamp // 更新时自动刷新当前时间
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

