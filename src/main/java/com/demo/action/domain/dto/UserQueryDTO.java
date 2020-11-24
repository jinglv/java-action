package com.demo.action.domain.dto;

import lombok.Data;

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
    private String username;
}
