// SubmissionMapper.java
package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.example.domain.po.Submission;

@Mapper
public interface SubmissionMapper {
    @Insert("INSERT INTO submissions (challenge_id, user_id, team_id, submitted_flag, is_correct, ip_address) " +
            "VALUES (#{challengeId}, #{userId}, #{teamId}, #{submittedFlag}, #{isCorrect}, #{ipAddress})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Submission submission);
}