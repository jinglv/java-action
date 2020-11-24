package com.demo.action.controller;

import com.demo.action.domain.common.PageResult;
import com.demo.action.domain.common.ResponseResult;
import com.demo.action.domain.dto.UserDTO;
import com.demo.action.domain.dto.UserQueryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 用户Controller
 *
 * @author jingLv
 * @date 2020/11/24
 */
@Slf4j
@RestController
@RequestMapping("api/users")
@ResponseBody
public class UserController {

    /**
     * 用户保存操作
     * POST /api/users UserDTO
     */
    @PostMapping
    public ResponseResult save(@RequestBody UserDTO userDTO) {
        return ResponseResult.success("新增成功");
    }

    /**
     * 用户信息更新
     * PUT /api/users/{id} UserDTO
     *
     * @return
     */
    @PutMapping
    public ResponseResult update(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        return ResponseResult.success("更新成功");
    }

    /**
     * 用户信息删除
     * DELETE /api/users/{id}
     *
     * @return
     */
    @DeleteMapping
    public ResponseResult delete(@PathVariable("id") Long id) {
        return ResponseResult.success("删除成功");
    }

    /**
     * 用户信息查询(分页)
     * GET
     *
     * @return
     */
    @GetMapping
    public ResponseResult<PageResult> query(Integer pageNo, Integer pageSize, UserQueryDTO query) {
        return ResponseResult.success(new PageResult());
    }
}
