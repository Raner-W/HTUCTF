package org.example.service.serviceImpl;

import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.po.Category;
import org.example.domain.po.Challenge;
import org.example.domain.vo.CategoryWithChallengesVO;
import org.example.domain.vo.ChallengeInCategoryVO;
import org.example.mapper.CategoryMapper;
import org.example.mapper.ChallengeMapper;
import org.example.service.ChallengeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ChallengeServiceImpl implements ChallengeService {
    private final CategoryMapper categoryMapper;
    private final ChallengeMapper challengeMapper;

    // 构造器注入Mapper
    public ChallengeServiceImpl(CategoryMapper categoryMapper, ChallengeMapper challengeMapper) {
        this.categoryMapper = categoryMapper;
        this.challengeMapper = challengeMapper;
    }

    @Override
    public List<CategoryWithChallengesVO> getCategoryWithChallenges() {
        // 1. 查询所有分类
        List<Category> categories = categoryMapper.selectAllCategories();
        if (CollectionUtils.isEmpty(categories)) {
            log.warn("未查询到任何题目分类");
            return Collections.emptyList();
        }

        // 2. 遍历分类，查询每个分类下的题目并转换为VO
        List<CategoryWithChallengesVO> result = new ArrayList<>();
        for (Category category : categories) {
            // 2.1 查询当前分类下的题目
            List<Challenge> challenges = challengeMapper.selectByCategoryId(category.getId());

            // 2.2 转换题目为ChallengeInCategoryVO
            List<ChallengeInCategoryVO> challengeVOs = challenges.stream()
                    .map(challenge -> {
                        ChallengeInCategoryVO vo = new ChallengeInCategoryVO();
                        vo.setChallengeId(challenge.getId());
                        vo.setTitle(challenge.getTitle());
                        vo.setPoints(challenge.getPoints());
                        return vo;
                    })
                    .collect(Collectors.toList());

            // 2.3 构建分类VO
            CategoryWithChallengesVO categoryVO = new CategoryWithChallengesVO();
            categoryVO.setCategoryName(category.getName());
            categoryVO.setChallengeCount(challengeVOs.size());
            categoryVO.setChallenges(challengeVOs);

            result.add(categoryVO);
        }

        log.info("查询到{}个题目分类及旗下题目信息", result.size());
        return result;
    }
}
