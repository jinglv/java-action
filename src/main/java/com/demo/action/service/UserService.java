package com.demo.action.service;

import com.demo.action.domain.common.PageQuery;
import com.demo.action.domain.common.PageResult;
import com.demo.action.domain.dto.UserDTO;
import com.demo.action.domain.dto.UserQueryDTO;

import java.util.List;

/**
 * 用户服务接口
 *
 * @author jingLv
 * @date 2020/11/24
 */
public interface UserService {
    /**
     * 新增用户
     *
     * @param userDTO
     * @return
     */
    int save(UserDTO userDTO);

    /**
     * 更新用户
     *
     * @param id
     * @param userDTO
     * @return
     */
    int update(Long id, UserDTO userDTO);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 分页查询用户
     *
     * @param pageQuery
     * @return
     */
    PageResult<List<UserDTO>> query(PageQuery<UserQueryDTO> pageQuery);
}
