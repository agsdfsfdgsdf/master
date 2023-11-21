package com.deyi.daxie.cloud.operation.convert;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * Description: 所在位置输出处理
 *
 * @author Chen Xu
 * @date 2023/6/9
 */
public class PositionConvert implements Converter<Integer> {

    @Override
    public WriteCellData<?> convertToExcelData(Integer value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        WriteCellData<String> cell = new WriteCellData<>();
        cell.setType(CellDataTypeEnum.STRING);
        cell.setStringValue("");
        if(value != null){
            //     1、集中装卸锁站 2、0W查验场地 3、熏蒸场地 4、CFS过磅场地 5、H986查验场地 6、调箱门场地 7、其它指定点位
            switch (value){
                case 1:
                    cell.setStringValue("集中装卸锁站");
                    break;
                case 2:
                    cell.setStringValue("0W查验场地");
                    break;
                case 3:
                    cell.setStringValue("熏蒸场地");
                    break;
                case 4:
                    cell.setStringValue("CFS过磅场地");
                    break;
                case 5:
                    cell.setStringValue("H986查验场地");
                    break;
                case 6:
                    cell.setStringValue("调箱门场地");
                    break;
                case 7:
                    cell.setStringValue("其它指定点位");
                    break;
                default:
            }
        }
        return cell;
    }
}
