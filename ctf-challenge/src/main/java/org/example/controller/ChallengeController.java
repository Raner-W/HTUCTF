package org.example.controller;

import com.alibaba.nacos.api.model.v2.Result;
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






//    //下载题目附件
//    @GetMapping("/{id}/attachment")
//
//
//
//
//    //提交flag
//    @PostMapping("/{id}/submit")
}
