package com.demo.action.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.action.domain.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserMapper
 *
 * @author jingLv
 * @date 2020/11/24
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
    
}
