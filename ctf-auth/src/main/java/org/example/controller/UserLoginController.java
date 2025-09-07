package org.example.controller;


import com.alibaba.nacos.api.model.v2.Result;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.dto.ResetPasswordDTO;
import org.example.domain.dto.UserLoginDTO;
import org.example.domain.dto.UserRegisterDTO;
import org.example.domain.po.User;
import org.example.domain.vo.ResultVO;
import org.example.domain.vo.UserLoginVO;
import org.example.domain.vo.UserRegisterVO;
import org.example.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.util.JwtUtil;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/user")
public class UserLoginController {
    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private JwtUtil jwtUtil;

    //用户qq注册
    @PostMapping("/register")
    public ResultVO<UserRegisterVO> register(@RequestBody UserRegisterDTO userRegisterDTO) { // 修复@RequestBody注解位置
        //记录日志
        log.info("用户qq注册:  {}", userRegisterDTO);
        //调用服务层的注册方法
        ResultVO<UserRegisterVO> resultVO = userLoginService.register(userRegisterDTO);
        //返回结果
        return ResultVO.success(resultVO.getData());
    }


    // 异步发送验证码（独立接口）
    @GetMapping("/sendCaptcha")
    public ResponseEntity<String> sendCaptcha(
            @RequestParam String email,
            @RequestParam(required = false, defaultValue = "register") String type
    ) {
        log.info("异步发送验证码: 邮箱={}, 类型={}", email, type);
        userLoginService.asyncSendVerificationCode(email, type);
        return ResponseEntity.ok("验证码发送请求已接收");
    }

    //用户登录
    //懒得写loginvo了就用registervo了
    @PostMapping("/login")
    public ResultVO<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("用户登录:  {}", userLoginDTO);
        UserLoginVO  userloginVO = userLoginService.login(userLoginDTO).getData();


        //弄个Token
        //生成JWT token
        // 生成JWT Token
        String token = jwtUtil.generateAccessToken(userloginVO.getId());
        //构建返回对象
        return ResultVO.success(userloginVO);
    }


    // 忘记密码-重置密码
    @PostMapping("/resetPassword")
    public ResultVO<String> resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {
        log.info("密码重置请求: {}", resetPasswordDTO.getEmail());
        userLoginService.resetPassword(resetPasswordDTO);
        return ResultVO.success("密码重置成功");
    }
}
