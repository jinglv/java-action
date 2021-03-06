package com.demo.action.controller;

import com.demo.action.domain.common.PageQuery;
import com.demo.action.domain.common.PageResult;
import com.demo.action.domain.common.ResponseResult;
import com.demo.action.domain.dto.UserDTO;
import com.demo.action.domain.dto.UserQueryDTO;
import com.demo.action.domain.vo.UserVO;
import com.demo.action.excaption.ErrorCodeEnum;
import com.demo.action.service.ExcelExportService;
import com.demo.action.service.UserService;
import com.demo.action.util.InsertValidationGroup;
import com.demo.action.util.updateValidationGroup;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 用户Controller
 *
 * @author jingLv
 * @date 2020/11/24
 */
@Slf4j
@RestController
@RequestMapping("api/users")
@Validated
@Api(value = "用户管理Controller", protocols = "https, https", hidden = false)
@ResponseBody
public class UserController {

    private final UserService userService;
    private final ExcelExportService excelExportService;

    public UserController(UserService userService, ExcelExportService excelExportService) {
        this.userService = userService;
        this.excelExportService = excelExportService;
    }

    /**
     * 用户保存操作
     * POST /api/users UserDTO
     * 只要有数据更新，就会删除缓存
     */
    @CacheEvict(cacheNames = "users-cache", allEntries = true)
    @PostMapping
    public ResponseResult<Object> save(@Validated(InsertValidationGroup.class) @RequestBody UserDTO userDTO) {
        int save = userService.save(userDTO);
        if (save == 1) {
            return ResponseResult.success("新增成功");
        } else {
            return ResponseResult.failure(ErrorCodeEnum.INSERT_FAILURE);
        }
    }

    /**
     * 用户信息更新
     * PUT /api/users/{id} UserDTO
     *
     * @return
     */
    @PutMapping("{id}")
    @ApiOperation(value = "更新用户信息", notes = "备注说明信息", response = ResponseResult.class, httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "id",
                    value = "参数说明：用户主键",
                    required = true,
                    paramType = "path",
                    dataType = "Long",
                    example = "123456"
            ),
            @ApiImplicitParam(
                    name = "userDTO",
                    value = "用户信息",
                    required = true,
                    paramType = "body",
                    dataType = "UserDTO",
                    dataTypeClass = UserDTO.class
            )
    })
    @ApiResponses({
            @ApiResponse(code = 0000, message = "操作成功"),
            @ApiResponse(code = 3004, message = "更新失败")
    })
    public ResponseResult<Object> update(@NotNull @PathVariable("id") Long id, @Validated(updateValidationGroup.class) @RequestBody UserDTO userDTO) {
        int update = userService.update(id, userDTO);
        if (update == 1) {
            return ResponseResult.success("更新成功");
        } else {
            return ResponseResult.failure(ErrorCodeEnum.UPDATE_FAILURE);
        }

    }

    /**
     * 用户信息删除
     * DELETE /api/users/{id}
     *
     * @return
     */
    @DeleteMapping("{id}")
    public ResponseResult<Object> delete(@NotNull(message = "用户id不能为空") @PathVariable("id") Long id) {
        int delete = userService.delete(id);
        if (delete == 1) {
            return ResponseResult.success("删除成功");
        } else {
            return ResponseResult.failure(ErrorCodeEnum.DELETE_FAILURE);
        }
    }

    /**
     * 用户信息查询(分页)
     * GET
     *
     * @return
     */
    @Cacheable(cacheNames = "users-cache")
    @GetMapping
    public ResponseResult<PageResult<List<UserVO>>> query(@NotNull Integer pageNo, @NotNull Integer pageSize, @Validated UserQueryDTO query) {
        log.info("未使用缓存！");
        // 构造查询条件
        PageQuery<UserQueryDTO> pageQuery = new PageQuery<>();
        pageQuery.setPageNo(pageNo);
        pageQuery.setPageSize(pageSize);
        pageQuery.setQuery(query);
        PageResult<List<UserDTO>> pageResult = userService.query(pageQuery);
        // 实体转换
        List<UserVO> userVOList = Optional.ofNullable(pageResult.getData())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(userDTO -> {
                    UserVO userVO = new UserVO();
                    BeanUtils.copyProperties(userDTO, userVO);
                    // 对特殊字段处理
                    userVO.setPassword("********");
                    if (!StringUtils.isEmpty(userDTO.getPhone())) {
                        userVO.setPhone(userDTO.getPhone().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
                    }
                    return userVO;
                }).collect(Collectors.toList());
        // 封装返回结果
        PageResult<List<UserVO>> listPageResult = new PageResult<>();
        BeanUtils.copyProperties(pageResult, listPageResult);
        listPageResult.setData(userVOList);
        return ResponseResult.success(listPageResult);
    }

    /**
     * 用户数据导出
     *
     * @param query
     * @param filename
     * @return
     */
    @GetMapping("export")
    public ResponseResult<Boolean> export(@Validated UserQueryDTO query, @NotNull String filename) {
        log.info("接收到导出请求！filename：{}", filename);
        // 数据导出
        // excelExportService.export(query, filename);
        // 异步导出
        excelExportService.asyncExport(query, filename);
        return ResponseResult.success(Boolean.TRUE);
    }
}
