package com.deyi.daxie.cloud.operation.utils;

import lombok.Getter;

@Getter
public enum ChannelTypeEnum {
    UP("上", 33),
    DOWN("下", 34),
    LEFT("左", 35),
    RIGHT("有", 36),
    FRONT("前", 37),
    BEHIND("后", 38),
    INDOOR("室内", 39);
    /**
     * 接口描述
     */
    private final String desc;
    /**
     * 接口类型
     */
    private final int channel;
    /**
     * 接口描述
     */
    /**
     * 构造函数
     *
     * @param channel  channel
     * @param desc alias
     */
    ChannelTypeEnum(String desc,int channel) {
        this.desc = desc;
        this.channel = channel;
    }

    /**
     * 获取值
     *
     * @return String
     */
    public int getChannel() {
        return this.channel;
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
