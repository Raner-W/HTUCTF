package org.example.service;


import org.example.domain.dto.ResetPasswordDTO;
import org.example.domain.dto.UserLoginDTO;
import org.example.domain.dto.UserRegisterDTO;
import org.example.domain.po.User;
import org.example.domain.vo.ResultVO;
import org.example.domain.vo.UserLoginVO;
import org.example.domain.vo.UserRegisterVO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public interface UserLoginService {
    ResultVO<UserRegisterVO> register(UserRegisterDTO userRegisterDTO);

    ResultVO<UserLoginVO> login(UserLoginDTO userLoginDTO);

    void resetPassword(ResetPasswordDTO resetPasswordDTO);

    @Async
    void asyncSendVerificationCode(String email, String type);
}
