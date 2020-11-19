package com.demo.action.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户DTO实体
 *
 * @author jingLv
 * @date 2020/11/19
 */
@Data
public class UserDTO implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6095717942938806940L;
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 版本号，用于乐观锁
     */
    private Long version;
}
