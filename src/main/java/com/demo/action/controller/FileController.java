package com.demo.action.controller;

import com.demo.action.domain.common.ResponseResult;
import com.demo.action.excaption.BusinessException;
import com.demo.action.excaption.ErrorCodeEnum;
import com.demo.action.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * 文件服务Controller
 *
 * @author jingLv
 * @date 2020/11/26
 */
@Slf4j
@RestController
@RequestMapping("api/files")
@Validated
@ResponseBody
public class FileController {

    @Resource(name = "localFileServiceImpl")
    private FileService fileService;

    /**
     * 文件上传
     * MultipartFile 接收前端上传的文件
     *
     * @param file
     * @return
     */
    @PostMapping("upload")
    public ResponseResult<Object> upload(@NotNull MultipartFile file) {
        // 文件上传
        try {
            fileService.upload(file.getInputStream(), file.getOriginalFilename());
        } catch (IOException e) {
            log.error("文件上传失败", e);
            throw new BusinessException(ErrorCodeEnum.FILE_UPLOAD_FAILURE, e);
        }
        return ResponseResult.success(file.getOriginalFilename());
    }
}
