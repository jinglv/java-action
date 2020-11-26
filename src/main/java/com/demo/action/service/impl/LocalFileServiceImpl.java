package com.demo.action.service.impl;

import com.demo.action.excaption.BusinessException;
import com.demo.action.excaption.ErrorCodeEnum;
import com.demo.action.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * 本地文件上传服务实现类
 *
 * @author jingLv
 * @date 2020/11/26
 */
@Slf4j
@Service
public class LocalFileServiceImpl implements FileService {
    /**
     * 存储空间
     */
    private static final String BUCKET = "uploads";

    @Override
    public void upload(InputStream inputStream, String fileName) {
        // 拼接文件的存储路径
        String storagePath = BUCKET + "/" + fileName;
        try (
                // JDK8 TWR 不能关闭外部的资源，则需要进行转换
                InputStream innerInputStream = inputStream;
                FileOutputStream outputStream = new FileOutputStream(new File(storagePath))
        ) {
            // 拷贝缓冲区
            byte[] buffer = new byte[1024];
            // 读取文件流长度
            int len;
            // 循环读取inputStream中数据写入到outputStream
            while ((len = innerInputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }
            // 重新刷新流
            outputStream.flush();
        } catch (Exception e) {
            log.error("文件上传失败！", e);
            throw new BusinessException(ErrorCodeEnum.FILE_UPLOAD_FAILURE, e);
        }

    }

    @Override
    public void upload(File file) {
        try {
            upload(new FileInputStream(file), file.getName());
        } catch (FileNotFoundException e) {
            log.error("文件上传失败", e);
            throw new BusinessException(ErrorCodeEnum.FILE_UPLOAD_FAILURE, e);
        }
    }
}
