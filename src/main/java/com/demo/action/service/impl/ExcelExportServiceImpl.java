package com.demo.action.service.impl;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.demo.action.domain.common.PageQuery;
import com.demo.action.domain.common.PageResult;
import com.demo.action.domain.dto.UserDTO;
import com.demo.action.domain.dto.UserExportDTO;
import com.demo.action.domain.dto.UserQueryDTO;
import com.demo.action.service.ExcelExportService;
import com.demo.action.service.FileService;
import com.demo.action.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Excel导出服务实现类
 *
 * @author jingLv
 * @date 2020/11/27
 */
@Slf4j
@Service
public class ExcelExportServiceImpl implements ExcelExportService {
    @Resource(name = "localFileServiceImpl")
    private FileService fileService;

    private final UserService userService;

    public ExcelExportServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void export(UserQueryDTO query, String filename) {
        // 字节数组输出流，字节数组的流不需要关闭
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // 1. 实现数据导出的Excel中
        export(outputStream, query);
        // 字节数组输入流
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        // 2. 实现上传
        fileService.upload(inputStream, filename);
    }

    /**
     * @param query
     * @param filename
     * @Async 标注，使用线程池执行方法
     */
    @Async("exportServiceExecutor")
    @Override
    public void asyncExport(UserQueryDTO query, String filename) {
        export(query, filename);
    }

    /**
     * 执行数据库查询和Excel导出，将数据写入到outputStream中
     *
     * @param outputStream 输出流
     * @param query        查询条件
     */
    private void export(OutputStream outputStream, UserQueryDTO query) {
        // 1. 需要创建一个EasyExcel导出对象
        ExcelWriter excelWriter = EasyExcelFactory.write(outputStream, UserExportDTO.class).build();
        // 2. 分批加载数
        final PageQuery<UserQueryDTO> pageQuery = new PageQuery<>();
        pageQuery.setQuery(query);
        pageQuery.setPageSize(2);

        int pageNo = 0;
        PageResult<List<UserDTO>> pageResult;

        do {
            // 先累加，再复制
            pageQuery.setPageNo(++pageNo);
            log.info("开始导出第[{}]页数据！", pageNo);
            pageResult = userService.query(pageQuery);
            // 数据转换：UserDTO转换成UserExportDTO
            List<UserExportDTO> userExportDTOList = Optional.ofNullable(pageResult.getData())
                    .map(List::stream)
                    .orElseGet(Stream::empty)
                    .map(userDTO -> {
                        UserExportDTO userExportDTO = new UserExportDTO();
                        // 对象转换
                        BeanUtils.copyProperties(userDTO, userExportDTO);
                        return userExportDTO;
                    }).collect(Collectors.toList());
            // 3. 导出分批加载的数据
            // 将数据写入到不同的sheet页中
            WriteSheet writeSheet = EasyExcelFactory.writerSheet(pageNo, "第" + pageNo + "页").build();
            excelWriter.write(userExportDTOList, writeSheet);
            log.info("结束导出第[{}]页数据！", pageNo);
            // 总页数大于当前页，说明还有数据，需要再次执行
        } while (pageResult.getPageNum() > pageNo);
        // 4. 收尾，执行finish，关闭Excel文件流
        excelWriter.finish();
        log.info("导出完成！");
    }
}
