// Solves.java
package org.example.domain.po;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "solves")
public class Solve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "challenge_id", nullable = false)
    private Integer challengeId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "team_id")
    private Integer teamId;

    @Column(name = "earned_points", nullable = false)
    private Integer earnedPoints;

    @CreationTimestamp
    @Column(name = "solved_at", nullable = false)
    private LocalDateTime solvedAt;

    // 如果需要关联查询，可以添加以下关系映射
    /*
    @ManyToOne
    @JoinColumn(name = "challenge_id", insertable = false, updatable = false)
    private Challenge challenge;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    private Team team;
    */
}
