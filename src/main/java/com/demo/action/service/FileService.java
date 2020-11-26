package com.demo.action.service;

import java.io.File;
import java.io.InputStream;

/**
 * 文件上传服务接口
 *
 * @author jingLv
 * @date 2020/11/26
 */
public interface FileService {
    /**
     * 文件上传
     *
     * @param inputStream 文件流
     * @param fileName    文件名
     */
    void upload(InputStream inputStream, String fileName);

    /**
     * 文件上传
     *
     * @param file 文件对象
     */
    void upload(File file);
}
