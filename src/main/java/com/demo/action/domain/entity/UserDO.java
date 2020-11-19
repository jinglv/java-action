package com.demo.action.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户DO实体
 *
 * @author jingLv
 * @date 2020/11/19
 */
@Data
public class UserDO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 2903546874037370385L;

    /***用户主信息***/

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

    /***系统主信息***/
    /**
     * 数据库主键
     */
    private Long id;
    /**
     * 数据的创建时间
     */
    private LocalDateTime created;
    /**
     * 数据的创建时间
     */
    private LocalDateTime modified;
    /**
     * 创建者
     */
    private String creator;
    /**
     * 最后修改者
     */
    private String operator;
    /**
     * 逻辑删除字段：0：正常 1：逻辑删除
     */
    private Integer status;
    /**
     * 版本号，用于乐观锁
     */
    private Long version;
}
