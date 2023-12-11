package com.deyi.daxie.cloud.operation.video;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DownloadServiceImpl implements DownloadService{
    @Override
    public Map<String, String> downloadType() {
        return downloadType;
    }

    @Override
    public Map<String, String> channelType() {
        return channelType;
    }

    private static Map<String, String> downloadType = new HashMap<>();

    static {
        downloadType.put("1", "T901");
        downloadType.put("2", "T902");
        downloadType.put("3", "T903");
        downloadType.put("4", "T904");
        downloadType.put("5", "T905");
        downloadType.put("6", "T906");
        downloadType.put("7", "T907");
        downloadType.put("8", "T908");
    }

    private static Map<String, String> channelType = new HashMap<>();

    static {
        channelType.put("1", "上");
        channelType.put("2", "下");
        channelType.put("3", "左");
        channelType.put("4", "右");
        channelType.put("5", "前");
        channelType.put("6", "后");
        channelType.put("7", "室内");
    }
}
