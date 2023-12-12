package com.deyi.daxie.cloud.operation.utils;

import lombok.Getter;

@Getter
public enum DeviceNumEnum {
    NUMONE("T901", "10.5.68.76","admin","deyixigu2023"),
    NUMTWO("T902", "10.5.68.76","admin","deyixigu2023"),
    NUMTHREE("T903", "10.5.68.76","admin","deyixigu2023"),
    NUMFOUR("T904", "10.5.68.28","admin","deyixigu2023"),
    NUMFIVE("T905", "10.5.68.44","admin","deyixigu2023"),
    NUMSIX("T906", "10.5.68.60","admin","deyixigu2023"),
    NUMSEVEN("T907", "10.5.68.76","admin","deyixigu2023"),
    NUMEIGHT("T908", "10.5.68.92","admin","deyixigu2023");
    /**
     * 接口类型
     */
    private final String deviceNum;
    /**
     * 接口描述
     */
    private final String ip;
    /**
     * 接口描述
     */
    private final String userName;
    /**
     * 接口描述
     */
    private final String passWord;
    /**
     * 构造函数
     *
     * @param deviceNum  type
     * @param ip alias
     */
    DeviceNumEnum(String deviceNum, String ip,String userName,String passWord) {
        this.deviceNum = deviceNum;
        this.ip = ip;
        this.userName = userName;
        this.passWord = passWord;
    }

    /**
     * 获取值
     *
     * @return String
     */
    public String getDeviceNum() {
        return this.deviceNum;
    }

    /**
     * 获取接口描述
     *
     * @return String
     */
    public String getIp() {
        return this.ip;
    }

    /**
     * 获取值
     *
     * @return String
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * 获取接口描述
     *
     * @return String
     */
    public String getPassWord() {
        return this.passWord;
    }
    /**
     * @param deviceNum value
     * @return DataType
     */
    public static String getIp(String deviceNum) {
        for (DeviceNumEnum value : DeviceNumEnum.values()) {
            if (value.deviceNum.equals(deviceNum)) {
                return value.ip;
            }
        }
        return null;
    }
    public static String getPassWord(String deviceNum) {
        for (DeviceNumEnum value : DeviceNumEnum.values()) {
            if (value.deviceNum.equals(deviceNum)) {
                return value.passWord;
            }
        }
        return null;
    }
    public static String getUserName(String deviceNum) {
        for (DeviceNumEnum value : DeviceNumEnum.values()) {
            if (value.deviceNum.equals(deviceNum)) {
                return value.userName;
            }
        }
        return null;
    }
}
