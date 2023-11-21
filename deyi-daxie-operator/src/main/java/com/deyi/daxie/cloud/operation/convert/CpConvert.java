package com.deyi.daxie.cloud.operation.convert;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * Description: 对位输出处理
 *
 * @author Chen Xu
 * @date 2023/6/9
 */
public class CpConvert implements Converter<Integer> {

    @Override
    public WriteCellData<?> convertToExcelData(Integer value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        WriteCellData<String> cell = new WriteCellData<>();
        cell.setType(CellDataTypeEnum.STRING);
        if(value != null){
            //  对位中1，非对位0
            switch (value){
                case 0:
                    cell.setStringValue("非对位");
                    break;
                case 1:
                    cell.setStringValue("对位中");
                    break;
                default:
                    cell.setStringValue("");
            }
        }
        return cell;
    }
}
