package com.deyi.daxie.cloud.vehicle.query.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

/**
 * Description:
 *
 * @author Huang ShuYing
 * @date 2022/9/23
 */
public class IsJsonObject {

    /**
     * 判断字符串是否可以转化为json对象
     * @param content 字符串
     * @return 结果
     */
    public static boolean isJsonObject(String content) {
        // 字符串判空
        if(StringUtils.isBlank(content)){
            return false;
        }
        try {
            JSONObject.parseObject(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
