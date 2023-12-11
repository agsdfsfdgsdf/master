/*
package com.deyi.daxie.cloud.operation.video;
import com.alibaba.fastjson.JSONObject;
import com.deyi.daxie.cloud.service.util.FileNormalOperationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
@Controller
public class IPVideoStream {
    */
/*public static void main(String[] args) {
        String videoUrl = "C:/Users/m1771/Desktop/ziliao/54098737690756cbffa323cb2a5f2143.mp4";
        String savePath = "D:/sdk/video.mp4";

        try {
            // 创建URL对象
            URL url = new URL(videoUrl);

            // 打开链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // 设置请求方法为GET
            conn.setRequestMethod("GET");

            // 获取输入流
            InputStream inputStream = conn.getInputStream();

            // 创建输出流，并指定保存文件路径
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(savePath));

            // 缓冲区大小
            byte[] buffer = new byte[1024];
            int bytesRead;
            // 从输入流读取数据，并写入到输出流
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // 关闭流
            outputStream.close();
            inputStream.close();

            System.out.println("视频流保存成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*//*

    */
/**
     * @description: MP4文件在线播放
     * @author: Re、ZOO2
     * @date: 2021/7/25 22:55
     * @param: [request, response, floderPath文件夹路径, fileName文件名称]
     * @return: com.lvmvp.configconsts.constant.ResultView
     **//*

    @GetMapping(value = "/playMp4",produces ="application/json;charset=utf-8")
    public JSONObject playMp4(HttpServletRequest request, HttpServletResponse response
                             */
/* @PathVariable("fileName") String fileName*//*
){
        String floderPath = "10.5.68.28:8000";
        FileNormalOperationUtils.aloneVideoPlay(request,response,floderPath*/
/*,fileName*//*
);
        return null;
    }
}*/
