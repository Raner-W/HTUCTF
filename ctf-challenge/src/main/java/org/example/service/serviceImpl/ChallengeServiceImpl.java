package org.example.service.serviceImpl;

import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.config.OssConfig;
import org.example.domain.po.Category;
import org.example.domain.po.Challenge;
import org.example.domain.vo.CategoryWithChallengesVO;
import org.example.domain.vo.ChallengeInCategoryVO;
import org.example.domain.vo.ChallengeVO;
import org.example.domain.vo.ResultVO;
import org.example.mapper.CategoryMapper;
import org.example.mapper.ChallengeMapper;
import org.example.service.ChallengeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.commons.io.IOUtils; // 使用 Apache Commons IO

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ChallengeServiceImpl implements ChallengeService {
    private final CategoryMapper categoryMapper;
    private final ChallengeMapper challengeMapper;
    @Autowired
    private OssConfig ossConfig;

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

    @Override
    public ChallengeVO getChallengeDetail(Integer id) {
        // 1. 查询题目详情
        Challenge challenge = challengeMapper.selectById(id);
        if (challenge == null) {
            log.warn("未查询到id为{}的题目", id);
            return null;
        }

        // 2. 构建题目详情VO
        ChallengeVO challengeVO = new ChallengeVO();
        challengeVO.setId(challenge.getId());
        challengeVO.setTitle(challenge.getTitle());
        challengeVO.setDescription(challenge.getDescription());
        challengeVO.setPoints(challenge.getPoints());
        challengeVO.setDifficulty(challenge.getDifficulty().name());
        challengeVO.setAttachmentUrl(challenge.getAttachmentUrl());
        challengeVO.setSolvesCount(challenge.getSolvesCount());
        challengeVO.setIsVisible(challenge.getIsVisible());
        challengeVO.setCreatedAt(challenge.getCreatedAt());
        challengeVO.setUpdatedAt(challenge.getUpdatedAt());

        // 直接使用已有的 category 对象
        if (challenge.getCategory() != null) {
            challengeVO.setCategoryName(challenge.getCategory().getName());
        }

        // 3. 返回题目详情VO
        return challengeVO;
    }
    @Override
    public void downloadAttachment(Integer id, HttpServletResponse response) {
        try {
            // 1. 查询题目详情
            Challenge challenge = challengeMapper.selectById(id);
            if (challenge == null) {
                log.warn("未查询到id为{}的题目", id);
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("题目不存在");
                return;
            }

            // 2. 检查是否有附件
            String attachmentUrl = challenge.getAttachmentUrl();
            if (attachmentUrl == null || attachmentUrl.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("附件不存在");
                return;
            }

            // 3. 从OSS下载文件
            OSS ossClient = new OSSClientBuilder().build(
                    ossConfig.getEndpoint(),
                    ossConfig.getAccessKeyId(),
                    ossConfig.getAccessKeySecret()
            );

            try {
                // 假设attachmentUrl存储的是OSS中的对象名
                OSSObject ossObject = ossClient.getObject(ossConfig.getBucketName(), attachmentUrl);

                // 4. 设置响应头
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; filename=" +
                        URLEncoder.encode(attachmentUrl, "UTF-8"));

                // 5. 将文件内容写入响应
                IOUtils.copy(ossObject.getObjectContent(), response.getOutputStream());
                response.getOutputStream().flush();

                // 6. 记录下载日志
                log.info("成功下载题目(id={})的附件: {}", id, attachmentUrl);

                // 关闭OSS对象
                ossObject.close();
            } finally {
                if (ossClient != null) {
                    ossClient.shutdown();
                }
            }
        } catch (Exception e) {
            log.error("下载附件失败，题目id：{}", id, e);
            try {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("下载附件失败");
            } catch (IOException ioException) {
                log.error("写入响应失败", ioException);
            }
        }
    }






}
