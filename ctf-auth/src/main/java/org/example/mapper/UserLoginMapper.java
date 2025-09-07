package org.example.mapper;


import org.apache.ibatis.annotations.*;
import org.example.domain.dto.UserRegisterDTO;
import org.example.domain.po.User;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserLoginMapper {
    @Select("select * from users where email = #{email}")
    User findByEmail(String email);
    @Insert("insert into users (email, password, username, created_at) values (#{email}, #{password}, #{username}, #{created_at})")
    int insert(UserRegisterDTO userRegisterDTO);
    // 修改这行：将password改为newPassword保持一致性
    @Update("UPDATE users SET password = #{newPassword} WHERE email = #{email}")
    int updatePasswordByEmail(@Param("email") String email, @Param("newPassword") String newPassword);

    @Insert("INSERT INTO users(username, email, password, created_at) VALUES(#{username}, #{email}, #{password}, #{createdAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(User user);

}
