package org.example.mapper;

import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.*;
import org.example.domain.dto.UserRegisterDTO;
import org.example.domain.po.User;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserLoginMapper {
    @Select("select * from users where email = #{email}")
    User findByEmail(String email);
    @Insert("insert into users (email, password, nickname, created_at) values (#{email}, #{password}, #{nickname}, #{created_at})")
    int insert(UserRegisterDTO userRegisterDTO);
    @Update("UPDATE users SET password = #{newPassword} WHERE email = #{email}")
    int updatePasswordByEmail(@Param("email") String email, @Param("newPassword") String newPassword);
}
