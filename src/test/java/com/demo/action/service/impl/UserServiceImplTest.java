package com.demo.action.service.impl;

import com.demo.action.domain.common.PageQuery;
import com.demo.action.domain.common.PageResult;
import com.demo.action.domain.dto.UserDTO;
import com.demo.action.domain.dto.UserQueryDTO;
import com.demo.action.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author jingLv
 * @date 2020/11/24
 */
@Slf4j
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Test
    void save() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("xiaohong");
        userDTO.setPassword("123123");
        userDTO.setEmail("xiaohong@qq.com");
        userDTO.setAge(20);
        userDTO.setPhone("18512344321");
        userDTO.setVersion(1L);
        int save = userService.save(userDTO);
        log.info("{}", save);
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void query() {
        PageQuery<UserQueryDTO> query = new PageQuery<>();
        UserQueryDTO userQueryDTO = new UserQueryDTO();
        query.setPageNo(1);
        query.setPageSize(10);
        userQueryDTO.setUsername("xiaohong");
        query.setQuery(userQueryDTO);
        PageResult<List<UserDTO>> listPageResult = userService.query(query);
        System.out.println(listPageResult);
    }
}