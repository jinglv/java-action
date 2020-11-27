package com.demo.action.domain.dto;

import com.demo.action.util.InsertValidationGroup;
import com.demo.action.util.updateValidationGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;

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
    @NotBlank(message = "用户名不能为空", groups = {InsertValidationGroup.class})
    private String username;

    /**
     * 用户密码
     */
    @NotBlank(message = "密码不能为空", groups = {InsertValidationGroup.class})
    @Length(min = 6, max = 18, message = "密码长度不能少于6位，不能多于18位！")
    private String password;

    /**
     * 邮箱
     */
    @NotEmpty(message = "邮箱不能为空", groups = {InsertValidationGroup.class})
    @Email(message = "必须为有效有效")
    private String email;

    /**
     * 年龄
     */
    @NotNull(message = "年龄不能为空", groups = {InsertValidationGroup.class})
    @Max(value = 60, message = "年龄不能大于60岁！")
    @Min(value = 18, message = "年龄不能小于18岁！")
    private Integer age;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空", groups = {InsertValidationGroup.class})
    private String phone;

    /**
     * 版本号，用于乐观锁
     */
    @NotNull(message = "版本号不能为空", groups = {updateValidationGroup.class})
    private Long version;
    /**
     * 创建时间
     */
    private LocalDateTime created;
}
