package com.deyi.daxie.cloud.operation.convert;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * Description: 车灯期望输出处理
 *
 * @author Chen Xu
 * @date 2023/6/9
 */
public class LightConvert implements Converter<String> {

    @Override
    public WriteCellData<?> convertToExcelData(String value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        WriteCellData<String> cell = new WriteCellData<>();
        cell.setType(CellDataTypeEnum.STRING);
        if(value != null){
            //  取值范围：L、R、E、N，代表左转向、右转向、双闪、无
            switch (value){
                case "L":
                    cell.setStringValue("左转向");
                    break;
                case "R":
                    cell.setStringValue("右转向");
                    break;
                case "E":
                    cell.setStringValue("双闪");
                    break;
                case "N":
                    cell.setStringValue("无");
                    break;
                default:
                    cell.setStringValue("");
            }
        }
        return cell;
    }
}
