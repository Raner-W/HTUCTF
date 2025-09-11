package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.domain.po.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CategoryMapper {

    /**
     * 查询所有分类的基本信息（id、name）
     * 用于侧边栏分类列表展示
     */
    @Select("SELECT id, name, description FROM categories ORDER BY id ASC")
    List<Category> selectAllCategories();
}
