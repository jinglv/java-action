package com.demo.action.service;

import com.demo.action.domain.dto.UserQueryDTO;

/**
 * Excel导出服务
 *
 * @author jingLv
 * @date 2020/11/27
 */
public interface ExcelExportService {
    /**
     * 同步导出服务
     *
     * @param query
     * @param filename
     */
    void export(UserQueryDTO query, String filename);

    /**
     * 异步导出服务
     *
     * @param query
     * @param filename
     */
    void asyncExport(UserQueryDTO query, String filename);
}
