package com.demo.action.domain.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用分页查询返回实体
 *
 * @author jingLv
 * @date 2020/11/24
 */
@Data
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 6358153615494624694L;
    /**
     * 当前的页号
     */
    private Integer pageNo;
    /**
     * 每页的行数
     */
    private Integer pageSize;
    /**
     * 总记录数
     */
    private Long total;
    /**
     * 总页数
     */
    private Long pageNum;
    /**
     * 动态内容
     */
    private T data;
}
