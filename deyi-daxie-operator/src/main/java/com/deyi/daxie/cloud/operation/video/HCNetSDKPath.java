package com.deyi.daxie.cloud.operation.video;

import java.io.UnsupportedEncodingException;

public class HCNetSDKPath {
    public static String DLL_PATH;
    static {
        String path = (HCNetSDKPath.class.getResource("/").getPath()).
                replaceAll("%20", " ").substring(1).replace("bin", "SDK").replace("/","\\");
        System.out.println(path);
        try {
            DLL_PATH = java.net.URLDecoder.decode(path, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
