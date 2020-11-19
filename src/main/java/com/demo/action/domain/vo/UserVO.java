package com.demo.action.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户VO实体
 *
 * @author jingLv
 * @date 2020/11/19
 */
@Data
public class UserVO implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7122161302705974373L;
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
}
