package org.example.domain.vo;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class ChallengeVO {
    private Integer id;
    private String title;
    private String description;
    private String categoryName;
    private Integer points;
    private String difficulty;
    private String attachmentUrl;
    private Integer solvesCount;
    private Boolean isVisible;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
