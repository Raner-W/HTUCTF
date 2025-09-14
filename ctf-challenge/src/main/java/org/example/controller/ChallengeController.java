package org.example.controller;

import com.alibaba.nacos.api.model.v2.Result;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.vo.CategoryWithChallengesVO;
import org.example.domain.vo.ChallengeVO;
import org.example.domain.vo.ResultVO;
import org.example.service.ChallengeService;
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
    public ResultVO<String> submitFlag(@PathVariable("id") Integer id, @RequestBody String flag) {
        log.info("提交flag，题目id：{}，flag：{}", id, flag);
        try {
            //提交flag
            String result = challengeService.submitFlag(id, flag);
            return ResultVO.success(result);
        } catch (Exception e) {
            log.error("提交flag失败", e);
            return ResultVO.fail(500, "提交flag失败，请稍后重试");
        }
    }
}
