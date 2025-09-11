// File: ctf-challenge/src/main/java/org/example/domain/po/User.java
package org.example.domain.po;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;

    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    // 只保留Challenge实体需要的字段
    // 不需要包含密码等敏感信息
}
