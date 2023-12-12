package com.deyi.daxie.cloud.operation.utils;

import lombok.Getter;

@Getter
public enum WarnEnum {
    LEVELALARMONE("LevelAlarmOne", "一级报警"),
    LEVELALARMTWO("LevelAlarmTwo", "二级报警");
    /**
     * 接口类型
     */
    private final String type;
    /**
     * 接口描述
     */
    private final String desc;
    /**
     * 构造函数
     *
     * @param type  type
     * @param desc alias
     */
    WarnEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    /**
     * 获取值
     *
     * @return String
     */
    public String getType() {
        return this.type;
    }

    /**
     * 获取接口描述
     *
     * @return String
     */
    public String getDesc() {
        return this.desc;
    }

}
