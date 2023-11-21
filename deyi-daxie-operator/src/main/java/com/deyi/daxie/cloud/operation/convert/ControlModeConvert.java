package com.deyi.daxie.cloud.operation.convert;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * Description: 操作模式数据处理
 *
 * @author Chen Xu
 * @date 2023/6/9
 */
public class ControlModeConvert implements Converter<Boolean> {

    @Override
    public WriteCellData<?> convertToExcelData(Boolean value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        WriteCellData<String> cell = new WriteCellData<>();
        cell.setType(CellDataTypeEnum.STRING);
        if(value != null){
            // 操作模式,True自动对位 False 人工对位
            if (value) {
                cell.setStringValue("自动对位");
            } else {
                cell.setStringValue("人工对位");
            }
        }
        return cell;
    }
}
