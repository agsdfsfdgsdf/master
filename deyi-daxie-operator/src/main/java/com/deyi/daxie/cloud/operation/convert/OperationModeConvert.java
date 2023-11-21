package com.deyi.daxie.cloud.operation.convert;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * Description: 作业模式输出处理
 *
 * @author Chen Xu
 * @date 2023/6/9
 */
public class OperationModeConvert implements Converter<Integer> {

    @Override
    public WriteCellData<?> convertToExcelData(Integer value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        WriteCellData<String> cell = new WriteCellData<>();
        cell.setType(CellDataTypeEnum.STRING);
        cell.setStringValue("");
        if(value != null) {
            // 自动驾驶：0；人工驾驶：1；未登录：默认为-1
            switch (value) {
                case 0:
                    cell.setStringValue("自动驾驶");
                    break;
                case 1:
                    cell.setStringValue("人工驾驶");
                    break;
                case -1:
                    cell.setStringValue("未登录");
                    break;
                default:
            }
        }
        return cell;
    }
}
