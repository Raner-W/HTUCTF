package org.example.domain.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class ResetPasswordDTO {
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 密码（明文，后端需加密存储）
     * 约束：非空，长度8-20位（包含数字、字母等，具体规则可在业务层补充）
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 验证码（用于防机器人注册，前端通过图形验证码或短信验证码获取）
     * 约束：非空，长度通常为4-6位
     */
    @NotBlank(message = "验证码不能为空")
    private String captcha;
}
