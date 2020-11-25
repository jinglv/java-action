package com.demo.action.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 用户查询实体
 *
 * @author jingLv
 * @date 2020/11/24
 */
@Data
public class UserQueryDTO implements Serializable {

    private static final long serialVersionUID = -3845094600548199745L;

    /**
     * 用户名
     */
    @NotEmpty(message = "用户姓名不能为空！")
    private String username;
}
