package org.example.domain.po;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "contests")
public class Contest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "start_time", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime startTime; // 用LocalDateTime适配数据库DATETIME，兼顾时间处理灵活性

    @Column(name = "end_time", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime endTime;

    @Column(name = "is_public")
    private Boolean isPublic = true; // 对应tinyint(1)，默认值1（true）

    @Column(name = "password", length = 100)
    private String password; // 竞赛密码（可选，用于私有竞赛）

    @Column(name = "logo_url", length = 255)
    private String logoUrl; // 竞赛logo链接（可选）

    @Enumerated(EnumType.STRING) // 枚举值以字符串形式存储（与数据库enum类型匹配）
    @Column(name = "status", nullable = false)
    private ContestStatus status = ContestStatus.upcoming; // 默认状态"upcoming"

    @CreationTimestamp // 自动填充创建时间，且后续不允许修改
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp // 自动填充更新时间，每次修改实体时更新
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;
}