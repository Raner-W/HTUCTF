// SolveMapper.java
package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.domain.po.Solve;

@Mapper
public interface SolveMapper {
    @Insert("INSERT INTO solves (challenge_id, user_id, team_id, earned_points) " +
            "VALUES (#{challengeId}, #{userId}, #{teamId}, #{earnedPoints})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Solve solve);

    @Select("SELECT COUNT(*) FROM solves WHERE challenge_id = #{challengeId} AND user_id = #{userId}")
    int existsByChallengeIdAndUserId(@Param("challengeId") Integer challengeId,
                                     @Param("userId") Integer userId);

    @Select("SELECT COUNT(*) FROM solves WHERE challenge_id = #{challengeId} AND team_id = #{teamId}")
    int existsByChallengeIdAndTeamId(@Param("challengeId") Integer challengeId,
                                     @Param("teamId") Integer teamId);
}