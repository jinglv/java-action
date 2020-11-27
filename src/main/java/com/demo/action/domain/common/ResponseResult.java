package com.demo.action.domain.common;

import com.demo.action.excaption.ErrorCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回结果模型
 *
 * @author jingLv
 * @date 2020/11/24
 */
@Data
@ApiModel(value = "统一返回结果实体", description = "封装统一返回结果信息实体")
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = 904064375034106275L;
    /**
     * 请求是否成功
     */
    @ApiModelProperty(name = "success", value = "是否成功", required = true, dataType = "Boolean")
    private Boolean success;
    /**
     * 返回编码，具体错误标识
     */
    @ApiModelProperty(name = "code", value = "编码", required = false, dataType = "String")
    private String code;
    /**
     * 描述信息
     */
    @ApiModelProperty(name = "message", value = "描述信息", required = false, dataType = "String")
    private String message;
    /**
     * 返回结果
     */
    @ApiModelProperty(name = "result", value = "返货结果", required = false, dataType = "Object")
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

    /**
     * 失败
     *
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> failure(String code, String message) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setSuccess(Boolean.FALSE);
        responseResult.setCode(code);
        responseResult.setMessage(message);
        return responseResult;
    }

    /**
     * 失败
     *
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> failure(ErrorCodeEnum codeEnum) {
        return failure(codeEnum.getCode(), codeEnum.getMessage());
    }
}
