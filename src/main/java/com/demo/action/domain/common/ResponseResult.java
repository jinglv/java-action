package com.demo.action.domain.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回结果模型
 *
 * @author jingLv
 * @date 2020/11/24
 */
@Data
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = 904064375034106275L;
    /**
     * 请求是否成功
     */
    private Boolean success;
    /**
     * 返回编码，具体错误标识
     */
    private String code;
    /**
     * 描述信息
     */
    private String message;
    /**
     * 返回结果
     */
    private T result;

    /**
     * 成功
     *
     * @param result
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> success(T result) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setSuccess(Boolean.TRUE);
        responseResult.setResult(result);
        return responseResult;
    }

}
