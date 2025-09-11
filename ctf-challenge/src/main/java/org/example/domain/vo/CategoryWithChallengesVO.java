package org.example.domain.vo;

import lombok.Data;
import java.util.List;
/**
 * 分类及旗下题目列表 VO（用于侧边栏分组展示）
 */
@Data
public class CategoryWithChallengesVO {
    // 分类名称（如 "crypto"、"web"）
    private String categoryName;
    // 该分类下的题目数量（显示在括号里，如 "(1)"）
    private Integer challengeCount;
    // 该分类下的题目列表（展开后显示的具体题目）
    private List<ChallengeInCategoryVO> challenges;
}
