// Submissions.java
package org.example.domain.po;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "submissions")
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "challenge_id", nullable = false)
    private Integer challengeId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "team_id")
    private Integer teamId;

    @Column(name = "submitted_flag", nullable = false, length = 255)
    private String submittedFlag;

    @Column(name = "is_correct")
    private Boolean isCorrect;

    @CreationTimestamp
    @Column(name = "submitted_at", nullable = false)
    private LocalDateTime submittedAt;

    @Column(name = "ip_address", length = 45)
    private String ipAddress;

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
