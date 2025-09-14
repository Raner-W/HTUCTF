package org.example.domain.po;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "challenges")
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false,
            foreignKey = @ForeignKey(name = "challenges_ibfk_1"))
    private Category category;

    @Column(name = "points", nullable = false)
    private Integer points = 100;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false,
            foreignKey = @ForeignKey(name = "challenges_ibfk_2"))
    private User author; // 需确保 User 实体类已定义

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty", nullable = false)
    private Difficulty difficulty = Difficulty.medium;

    @Column(name = "flag", nullable = false, length = 255)
    private String flag;

    @Column(name = "attachment_url", length = 255)
    private String attachmentUrl;

    @Column(name = "solves_count")
    private Integer solvesCount = 0;

    @Column(name = "is_visible")
    private Boolean isVisible = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contest_id",
            foreignKey = @ForeignKey(name = "challenges_ibfk_3"))
    private Contest contest; // 需确保 Contest 实体类已定义

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;
}
