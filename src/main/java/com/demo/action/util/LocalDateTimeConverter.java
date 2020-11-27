package com.demo.action.util;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * LocalDateTime String转换器
 *
 * @author jingLv
 * @date 2020/11/27
 */
public class LocalDateTimeConverter implements Converter<LocalDateTime> {
    @Override
    public Class supportJavaTypeKey() {
        return LocalDateTime.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    /**
     * 导入时对象，将Excel转为Java对象
     *
     * @param cellData
     * @param excelContentProperty
     * @param globalConfiguration
     * @return
     * @throws Exception
     */
    @Override
    public LocalDateTime convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return null;
    }

    /**
     * 导出时使用，将Java对象导出为Excel
     *
     * @param localDateTime
     * @param excelContentProperty
     * @param globalConfiguration
     * @return
     * @throws Exception
     */
    @Override
    public CellData convertToExcelData(LocalDateTime localDateTime, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        if (excelContentProperty == null || excelContentProperty.getDateTimeFormatProperty() == null) {
            // 默认格式化格式
            return new CellData(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(localDateTime));
        } else {
            // 自定义格式化格式
            return new CellData(DateTimeFormatter.ofPattern(excelContentProperty.getDateTimeFormatProperty().getFormat()).format(localDateTime));
        }
    }
}
