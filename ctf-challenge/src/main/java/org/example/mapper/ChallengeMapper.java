package org.example.mapper;

import org.apache.ibatis.annotations.*;
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
    /**
     * 根据题目ID查询题目详情
     */
    @Select("SELECT id, title, description, category_id, points, difficulty, attachment_url, solves_count, is_visible, created_at, updated_at " +
            "FROM challenges WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "points", column = "points"),
            @Result(property = "difficulty", column = "difficulty"),
            @Result(property = "attachmentUrl", column = "attachment_url"),
            @Result(property = "solvesCount", column = "solves_count"),
            @Result(property = "isVisible", column = "is_visible"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "category", column = "category_id",
                    one = @One(select = "org.example.mapper.CategoryMapper.selectCategoryById"))
    })
    Challenge selectById(Integer id);


    @Update("UPDATE challenges SET title = #{title}, description = #{description}, category_id = #{categoryId}, points = #{points}, difficulty = #{difficulty}, attachment_url = #{attachmentUrl}, solves_count = #{solvesCount}, is_visible = #{isVisible}, updated_at = #{updatedAt} WHERE id = #{id}")
    void updateById(Challenge challenge);



    // 新增方法：获取包含flag的完整题目信息（用于验证答案）
    @Select("SELECT id, title, description, category_id, points, difficulty, flag, attachment_url, solves_count, is_visible, created_at, updated_at " +
            "FROM challenges WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "points", column = "points"),
            @Result(property = "difficulty", column = "difficulty"),
            @Result(property = "flag", column = "flag"),
            @Result(property = "attachmentUrl", column = "attachment_url"),
            @Result(property = "solvesCount", column = "solves_count"),
            @Result(property = "isVisible", column = "is_visible"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "category", column = "category_id",
                    one = @One(select = "org.example.mapper.CategoryMapper.selectCategoryById"))
    })
    Challenge selectByIdWithFlag(Integer id);


    // ChallengeMapper.java
    @Update("UPDATE challenges SET solves_count = #{solvesCount}, updated_at = NOW() WHERE id = #{id}")
    int updateSolvesCount(@Param("id") Integer id, @Param("solvesCount") Integer solvesCount);
}

