package com.demo.action.domain.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用分页查询对象
 *
 * @author jingLv
 * @date 2020/11/24
 */
@Data
public class PageQuery<T> implements Serializable {

    private static final long serialVersionUID = -5589100893440613565L;
    /**
     * 当前页
     */
    private Integer pageNo = 1;
    /**
     * 每页条数
     */
    private Integer pageSize = 20;
    /**
     * 动态查询条件
     */
    private T query;
}
