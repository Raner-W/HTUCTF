package org.example.controller;

import com.alibaba.nacos.api.model.v2.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.vo.CategoryWithChallengesVO;
import org.example.domain.vo.ChallengeVO;
import org.example.domain.vo.ResultVO;
import org.example.service.ChallengeService;
import org.example.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/challenge")
public class ChallengeController {

    //获取题目分类列表，会在侧边显示不同类型题目名称以及分值
    @Autowired
    private ChallengeService challengeService;

    @Autowired
    private JwtUtil jwtUtil;
    @GetMapping("/categories")
    public ResultVO<List<CategoryWithChallengesVO>> getCategoryList() {
        try {
            List<CategoryWithChallengesVO> categoryList = challengeService.getCategoryWithChallenges();
            return ResultVO.success(categoryList);
        } catch (Exception e) {
            log.error("获取题目分类列表失败", e);
            return ResultVO.fail(500,"获取分类列表失败，请稍后重试");
        }
    }
    //获取题目详情
    @GetMapping("/{id}")
    public ResultVO<ChallengeVO> getChallengeDetail(@PathVariable("id") Integer id) {
        log.info("获取题目详情，题目id：{}", id);
        try {
            ChallengeVO challengeDetail = challengeService.getChallengeDetail(id);
            if (challengeDetail == null) {
                return ResultVO.fail(404, "题目不存在");
            }
            return ResultVO.success(challengeDetail);
        } catch (Exception e) {
            log.error("获取题目详情失败", e);
            return ResultVO.fail(500, "获取题目详情失败，请稍后重试");
        }
    }


    //下载题目附件
    @GetMapping("/{id}/attachment")
    public void downloadAttachment(@PathVariable("id") Integer id, HttpServletResponse response) {
        //下载题目的id和 附件url
        log.info("下载题目附件，题目id{} 和 附件地址{} " , id, response );
        challengeService.downloadAttachment(id, response);
    }



//提交flag
    @PostMapping("/{id}/submit")
    public String submitFlag(@PathVariable("id") Integer id,
                             @RequestParam("flag") String flag,
                             HttpServletRequest request) {

        // 从请求头获取Token
        String token = request.getHeader("Authorization");
        if (token == null) {
            return "未授权访问";
        }

        // 解析Token获取用户ID
        Integer userId;
        try {
            userId = jwtUtil.extractUserId(token);
        } catch (Exception e) {
            return "Token无效或已过期";
        }

        // 获取客户端IP
        String ipAddress = getClientIp(request);

        // 团队ID暂时设为null（如果需要可以从Token中解析或从数据库查询）
        Integer teamId = null;

        return challengeService.submitFlag(id, flag, userId, teamId, ipAddress);
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
