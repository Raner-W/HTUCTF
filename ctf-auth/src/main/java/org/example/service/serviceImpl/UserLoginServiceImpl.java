package org.example.service.serviceImpl;


import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.dto.ResetPasswordDTO;
import org.example.domain.dto.UserLoginDTO;
import org.example.domain.dto.UserRegisterDTO;
import org.example.domain.po.User;
import org.example.domain.vo.ResultVO;
import org.example.domain.vo.UserLoginVO;
import org.example.domain.vo.UserRegisterVO;
import org.example.mapper.UserLoginMapper;
import org.example.service.UserLoginService;
import org.example.util.VerifyCodeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static org.example.domain.vo.ResultVO.fail;

@Slf4j
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private JavaMailSender javaMailSender;




    @Autowired
    private UserLoginMapper userLoginMapper;
    @Autowired
    private VerifyCodeUtil verifyCodeUtil;

    @Override
    public ResultVO register(UserRegisterDTO userRegisterDTO) {
        String email = userRegisterDTO.getEmail();
        String password = userRegisterDTO.getPassword();
        String nickname = userRegisterDTO.getName();
        String code = userRegisterDTO.getCaptcha();

        // 获取邮箱所对应的用户
        User user = userLoginMapper.findByEmail(email);
        log.info("用户注册:  {}", userRegisterDTO);

        // 判断邮箱是否已被注册
        if (user != null) {
            return ResultVO.fail(400, "邮箱已被注册");
        }

        // 校验必填字段
        if (nickname == null || nickname.isEmpty() ||
                password == null || password.isEmpty() ||
                email == null || email.isEmpty()) {
            return ResultVO.fail(400, "请填写完整信息");
        }

        // 初始化验证码并校验
        int verifyCode = verifyCodeUtil.checkVerifyCode(email, code, "register");
        if (verifyCode == 0) {
            return ResultVO.fail(400, "验证码错误");
        } else if (verifyCode == 2) {
            return ResultVO.fail(400, "验证码过期");
        }

        // 插入用户信息
        int rowsAffected = userLoginMapper.insert(userRegisterDTO);

        if (rowsAffected > 0) {
            User newUser = userLoginMapper.findByEmail(userRegisterDTO.getEmail());
            UserRegisterVO userRegisterVO = new UserRegisterVO();
            BeanUtils.copyProperties(newUser, userRegisterVO);
            return ResultVO.success(userRegisterVO);

        } else {
            return ResultVO.fail(500, "用户注册失败");
        }
    }


    @Override
    @Async
    public void asyncSendVerificationCode(String email, String type) {
        try {
            String key = "verify_" + type + "_" + email;
            ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
            if (ops.get(key) != null) {
                log.warn("验证码已存在，邮箱={}", email);
                return;
            }

            String code = verifyCodeUtil.generateVerifyCode(6);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("3363009478@qq.com");
            message.setTo(email);
            if ("reset".equalsIgnoreCase(type)) {
                message.setSubject("密码重置验证码");
                message.setText("您的验证码为：" + code + "，有效期5分钟");
            } else {
                message.setSubject("注册验证码");
                message.setText("您的验证码为：" + code + "，有效期5分钟");
            }
            javaMailSender.send(message);

            ops.set(key, code, 5, TimeUnit.MINUTES);
            log.info("验证码发送成功，类型={}", type);
        } catch (Exception e) {
            log.error("邮件发送失败: {}", e.getMessage());
        }
    }


    @Override
    public ResultVO<UserLoginVO> login(UserLoginDTO userLoginDTO) {
        User user = userLoginMapper.findByEmail(userLoginDTO.getEmail());
        if (user != null && user.getPassword().equals(userLoginDTO.getPassword())) {
            UserLoginVO userLoginVO = new UserLoginVO();
            BeanUtils.copyProperties(user, userLoginVO);
            return ResultVO.success(userLoginVO);
        }
        return ResultVO.fail(400, "邮箱或密码错误");
    }


    @Override
    public void resetPassword(ResetPasswordDTO resetPasswordDTO) {
        String email = resetPasswordDTO.getEmail();
        String newPassword = resetPasswordDTO.getPassword();
        String captcha = resetPasswordDTO.getCaptcha();
        String type = "reset";

        // 1. 校验邮箱是否注册
        User user = userLoginMapper.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("该邮箱未注册");
        }

        // 2. 校验验证码（需传入type参数）
        int verifyStatus = verifyCodeUtil.checkVerifyCode(email, captcha, "reset"); // 修改方法签名
        if (verifyStatus == 0) {
            throw new RuntimeException("验证码错误");
        } else if (verifyStatus == 2) {
            throw new RuntimeException("验证码过期");
        }

        // 3. 更新密码
        int rows = userLoginMapper.updatePasswordByEmail(email, newPassword);
        if (rows <= 0) {
            throw new RuntimeException("密码更新失败");
        }
        log.info("用户 {} 密码重置成功", email);

        // 4. 删除Redis中的验证码
        String key = "verify_" + type + "_" + email;
        stringRedisTemplate.delete(key);
    }
}
