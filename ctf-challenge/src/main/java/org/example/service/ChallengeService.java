package org.example.service;

import org.example.domain.vo.CategoryWithChallengesVO;

import java.util.List;

public interface ChallengeService {
    List<CategoryWithChallengesVO> getCategoryWithChallenges();
}
