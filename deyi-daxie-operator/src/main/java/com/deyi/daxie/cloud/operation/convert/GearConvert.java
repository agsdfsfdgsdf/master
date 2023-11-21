package com.deyi.daxie.cloud.operation.convert;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * Description: 挡位控制输出处理
 *
 * @author Chen Xu
 * @date 2023/6/9
 */
public class GearConvert implements Converter<String> {

    @Override
    public WriteCellData<?> convertToExcelData(String value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        WriteCellData<String> cell = new WriteCellData<>();
        cell.setType(CellDataTypeEnum.STRING);
        if(value != null){
            //     取值范围：D、R、P、N，代表前进挡、倒车档、驻车档、空挡
            switch (value){
                case "D":
                    cell.setStringValue("前进挡");
                    break;
                case "R":
                    cell.setStringValue("倒车档");
                    break;
                case "P":
                    cell.setStringValue("驻车档");
                    break;
                case "N":
                    cell.setStringValue("空挡");
                    break;
                default:
                    cell.setStringValue("");
            }
        }
        return cell;
    }
}
