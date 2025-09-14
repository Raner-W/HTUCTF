package org.example.service;

import jakarta.servlet.http.HttpServletResponse;
import org.example.domain.vo.CategoryWithChallengesVO;
import org.example.domain.vo.ChallengeVO;
import org.example.domain.vo.ResultVO;

import java.util.List;

public interface ChallengeService {
    List<CategoryWithChallengesVO> getCategoryWithChallenges();


    ChallengeVO getChallengeDetail(Integer id); // 改为返回 ChallengeVO

    void downloadAttachment(Integer id, HttpServletResponse response);

    String submitFlag(Integer id, String flag, Integer userId, Integer teamId, String ipAddress);

//    String submitFlag(Integer id, String flag);
    
}
