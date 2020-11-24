package com.demo.action.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.action.domain.common.PageQuery;
import com.demo.action.domain.common.PageResult;
import com.demo.action.domain.dto.UserDTO;
import com.demo.action.domain.dto.UserQueryDTO;
import com.demo.action.domain.entity.UserDO;
import com.demo.action.mapper.UserMapper;
import com.demo.action.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 用户服务实现类
 *
 * @author jingLv
 * @date 2020/11/24
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public int save(UserDTO userDTO) {
        // 数据转换，将UserDTO转换为UserDO
        UserDO userDO = new UserDO();
        // TODO 浅拷贝，属性名相同才可以拷贝
        BeanUtils.copyProperties(userDTO, userDO);
        return userMapper.insert(userDO);
    }

    @Override
    public int update(Long id, UserDTO userDTO) {
        // 数据转换，将UserDTO转换为UserDO
        UserDO userDO = new UserDO();
        // TODO 浅拷贝，属性名相同才可以拷贝
        BeanUtils.copyProperties(userDTO, userDO);
        userDO.setId(id);
        return userMapper.updateById(userDO);
    }

    @Override
    public int delete(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public PageResult<List<UserDTO>> query(PageQuery<UserQueryDTO> pageQuery) {
        // 参数构造
        Page<UserDO> page = new Page<>(pageQuery.getPageNo(), pageQuery.getPageSize());
        UserDO query = new UserDO();
        BeanUtils.copyProperties(pageQuery.getQuery(), query);
        // TODO 如果属性不一致，需要做特殊处理
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>(query);
        // 查询
        IPage<UserDO> userPage = userMapper.selectPage(page, queryWrapper);
        // 结果解析
        PageResult<List<UserDTO>> pageResult = new PageResult<>();
        pageResult.setPageNo((int) userPage.getCurrent());
        pageResult.setPageSize((int) userPage.getSize());
        pageResult.setTotal(userPage.getTotal());
        pageResult.setPageNum(userPage.getPages());
        // 对数据进行转换
        List<UserDTO> userDTOList = Optional.ofNullable(userPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(userDO -> {
                    UserDTO userDTO = new UserDTO();
                    BeanUtils.copyProperties(userDO, userDTO);
                    return userDTO;
                })
                .collect(Collectors.toList());
        pageResult.setData(userDTOList);
        return pageResult;
    }
}

