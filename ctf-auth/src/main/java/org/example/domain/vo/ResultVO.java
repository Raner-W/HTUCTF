package org.example.domain.vo;

import lombok.Data;

@Data
public class ResultVO<T> {
    private Integer code;       // 状态码（200成功，其他失败）
    private String message;    // 提示信息
    private T data;            // 业务数据（泛型，可放任何VO）

    // 快捷构造方法（成功/失败）
    public static <T> ResultVO<T> success(T data) {
        ResultVO<T> result = new ResultVO<>();
        result.setCode(200);
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    public static <T> ResultVO<T> fail(Integer code, String message) {
        ResultVO<T> result = new ResultVO<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(null);
        return result;
    }
}
