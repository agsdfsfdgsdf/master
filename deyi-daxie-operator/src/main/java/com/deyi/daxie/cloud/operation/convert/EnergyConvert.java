package com.deyi.daxie.cloud.operation.convert;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * Description: 能源模式输出处理
 *
 * @author Chen Xu
 * @date 2023/6/9
 */
public class EnergyConvert implements Converter<String> {

    @Override
    public WriteCellData<?> convertToExcelData(String value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        WriteCellData<String> cell = new WriteCellData<>();
        cell.setType(CellDataTypeEnum.STRING);
        if(value != null){
            // 用油：O；用电：E；油电混合：M
            switch (value){
                case "O":
                    cell.setStringValue("用油");
                    break;
                case "E":
                    cell.setStringValue("用电");
                    break;
                case "M":
                    cell.setStringValue("油电混合");
                    break;
                default:
                    cell.setStringValue("");
            }
        }
        return cell;
    }
}
