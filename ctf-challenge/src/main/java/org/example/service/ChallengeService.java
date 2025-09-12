package org.example.service;

import org.example.domain.vo.CategoryWithChallengesVO;
import org.example.domain.vo.ChallengeVO;
import org.example.domain.vo.ResultVO;

import java.util.List;

public interface ChallengeService {
    List<CategoryWithChallengesVO> getCategoryWithChallenges();


    ChallengeVO getChallengeDetail(Integer id); // 改为返回 ChallengeVO
}
