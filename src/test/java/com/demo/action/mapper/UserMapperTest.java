package com.demo.action.mapper;

import com.demo.action.domain.entity.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

/**
 * @author jingLv
 * @date 2020/11/24
 */
@Slf4j
@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void find() {
        HashMap<String, Object> param = new HashMap<>();
        param.put("username", "xiaohong");
        List<UserDO> userDOList = userMapper.selectByMap(param);
        log.info("{}", userDOList);
    }
}