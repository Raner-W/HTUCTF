package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.domain.po.Challenge;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ChallengeMapper {

    /**
     * 根据分类ID查询该分类下的可见题目（只返回必要字段）
     * 方法名简化为selectByCategoryId，与Service调用保持一致
     */
    @Select("SELECT id, title, points " +
            "FROM challenges " +
            "WHERE category_id = #{categoryId} " +
            "AND is_visible = 1 " +  // 只查可见题目
            "ORDER BY points ASC")
    List<Challenge> selectByCategoryId(@Param("categoryId") Integer categoryId);
}
