package com.demo.action.domain.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.demo.action.util.LocalDateTimeConverter;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Excel导出实体对象
 *
 * @author jingLv
 * @date 2020/11/27
 */
@Data
public class UserExportDTO implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1670016021911334019L;
    /**
     * 用户名
     */
    @ExcelProperty(value = "用户名")
    private String username;
    /**
     * 年龄
     */
    @ExcelProperty(value = "年龄")
    private Integer age;
    /**
     * 版本号，用于乐观锁
     */
    @ExcelProperty(value = "版本号")
    private Long version;
    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间", converter = LocalDateTimeConverter.class)
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒SSS毫秒")
    private LocalDateTime created;
}
