package org.example.domain.vo;

import lombok.Data;



/**
 * 分类下的题目简要信息 VO（用于分类展开后显示题目）
 */
@Data
public class ChallengeInCategoryVO {
    // 题目分值（如 200、150）
    private Integer points;
    // 题目名称（如 "RSA入门"、"简单SQL注入"）
    private String title;
    // 题目ID（可选，用于跳转题目详情）
    private Integer challengeId;
}
