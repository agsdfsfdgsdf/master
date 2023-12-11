package com.deyi.daxie.cloud.operation.video;

import com.deyi.daxie.cloud.common.annotation.Log;
import com.deyi.daxie.cloud.common.core.domain.ResultEntity;
import com.deyi.daxie.cloud.common.core.page.TableDataInfo;
import com.deyi.daxie.cloud.common.enums.BusinessType;
import com.deyi.daxie.cloud.common.utils.StringUtils;
import com.deyi.daxie.cloud.operation.domain.vo.VideoParamVo;
import com.deyi.daxie.cloud.operation.utils.DeviceNumEnum;
import com.deyi.daxie.cloud.operation.video.Common.osSelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * @create 2020-12-24-17:55
 */
@RestController
@RequestMapping("/download")
@Slf4j
class VideoController {
    @Autowired
    private DownloadService downloadService;

    int iErr = 0;
    static HCNetSDK hCNetSDK = null;
    static PlayCtrl playControl = null;
    static int lUserID = -1;//用户句柄
    static int lDChannel;  //预览通道号
    static boolean bSaveHandle = false;
    Timer Playbacktimer;//回放用定时器

    static FExceptionCallBack_Imp fExceptionCallBack;
    static int FlowHandle;

    static class FExceptionCallBack_Imp implements HCNetSDK.FExceptionCallBack {
        public void invoke(int dwType, int lUserID, int lHandle, Pointer pUser) {
            System.out.println("异常事件类型:"+dwType);
            return;
        }
    }

    /**
     * 动态库加载
     *
     * @return
     */
    private static boolean createSDKInstance() {
        if (hCNetSDK == null) {
            synchronized (HCNetSDK.class) {
                String strDllPath = "";
                try {
                    if (osSelect.isWindows())
                        //win系统加载库路径
                        strDllPath = System.getProperty("user.dir") + "\\lib\\HCNetSDK.dll";

                    else if (osSelect.isLinux())
                        //Linux系统加载库路径
                        strDllPath = System.getProperty("user.dir") + "\\lib\\libhcnetsdk.so";
                    //hCNetSDK = (HCNetSDK) Native.loadLibrary("C:\\Users\\m1771\\IdeaProjects\\test\\src\\sdk\\库文件\\HCNetSDK.dll", HCNetSDK.class);
                    hCNetSDK = (HCNetSDK) Native.loadLibrary(strDllPath, HCNetSDK.class);
                } catch (Exception ex) {
                    System.out.println("loadLibrary: " + strDllPath + " Error: " + ex.getMessage());
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 播放库加载
     *
     * @return
     */
    private static boolean createPlayInstance() {
        if (playControl == null) {
            synchronized (PlayCtrl.class) {
                String strPlayPath = "";
                try {
                    if (osSelect.isWindows())
                        //win系统加载库路径
                        strPlayPath = System.getProperty("user.dir") + "\\lib\\PlayCtrl.dll";
                    else if (osSelect.isLinux())
                        //Linux系统加载库路径
                        strPlayPath = System.getProperty("user.dir") + "/lib/libPlayCtrl.so";
                    //playControl=(PlayCtrl) Native.loadLibrary("C:\\Users\\m1771\\IdeaProjects\\test\\src\\sdk\\库文件\\PlayCtrl.dll",PlayCtrl.class);
                    playControl=(PlayCtrl) Native.loadLibrary(strPlayPath,PlayCtrl.class);
                } catch (Exception ex) {
                    System.out.println("loadLibrary: " + strPlayPath + " Error: " + ex.getMessage());
                    return false;
                }
            }
        }
        return true;
    }

    @PostMapping(value = "list")
    public TableDataInfo<List<VideoParamVo>> videoPlay(@RequestBody VideoParamVo videoParamVo) throws InterruptedException {
        String savePath="";
        if(videoParamVo.getSavePath()==null|| videoParamVo.getSavePath().length()<=0){
            savePath="D:\\Download";
        }else{
            savePath =videoParamVo.getSavePath();
        }
        List<VideoParamVo> ls = new ArrayList<>();
        VideoParamVo videoParam = new VideoParamVo();
        if(StringUtils.isEmpty(videoParamVo.getDeviceNum())||StringUtils.isEmpty(videoParamVo.getStartTime())
                ||StringUtils.isEmpty(videoParamVo.getEndTime())||StringUtils.isEmpty(videoParamVo.getChannel())){
            String msg = "请输入正确的参数";
            log.error(msg);
        }
        String startTime = videoParamVo.getStartTime();
        String endTime = videoParamVo.getEndTime();

        if (hCNetSDK == null&&playControl==null) {
            if (!createSDKInstance()) {
                log.error("Load SDK fail");
            }
            if (!createPlayInstance()) {
                log.error("Load PlayCtrl fail");
            }
        }
        //linux系统建议调用以下接口加载组件库
        if (osSelect.isLinux()) {
            HCNetSDK.BYTE_ARRAY ptrByteArray1 = new HCNetSDK.BYTE_ARRAY(256);
            HCNetSDK.BYTE_ARRAY ptrByteArray2 = new HCNetSDK.BYTE_ARRAY(256);
            //这里是库的绝对路径，请根据实际情况修改，注意改路径必须有访问权限
            String strPath1 = System.getProperty("user.dir") + "/lib/libcrypto.so.1.1";
            String strPath2 = System.getProperty("user.dir") + "/lib/libssl.so.1.1";

            System.arraycopy(strPath1.getBytes(), 0, ptrByteArray1.byValue, 0, strPath1.length());
            ptrByteArray1.write();
            hCNetSDK.NET_DVR_SetSDKInitCfg(3, ptrByteArray1.getPointer());

            System.arraycopy(strPath2.getBytes(), 0, ptrByteArray2.byValue, 0, strPath2.length());
            ptrByteArray2.write();
            hCNetSDK.NET_DVR_SetSDKInitCfg(4, ptrByteArray2.getPointer());

            String strPathCom = System.getProperty("user.dir") + "/lib/";
            HCNetSDK.NET_DVR_LOCAL_SDK_PATH struComPath = new HCNetSDK.NET_DVR_LOCAL_SDK_PATH();
            System.arraycopy(strPathCom.getBytes(), 0, struComPath.sPath, 0, strPathCom.length());
            struComPath.write();
            hCNetSDK.NET_DVR_SetSDKInitCfg(2, struComPath.getPointer());
        }

        //SDK初始化，一个程序只需要调用一次
        boolean initSuc = hCNetSDK.NET_DVR_Init();
        HCNetSDK.NET_DVR_PLAYCOND net_dvr_playcond = new HCNetSDK.NET_DVR_PLAYCOND();
        String year = startTime.substring(0, 4);
        String month = startTime.substring(5, 7);
        String day = startTime.substring(8, 10);
        String hour = startTime.substring(11, 13);
        String minute = startTime.substring(14, 16);
        String second = startTime.substring(17, 19);

        net_dvr_playcond.struStartTime.dwYear =Integer.parseInt(year);
        net_dvr_playcond.struStartTime.dwMonth =Integer.parseInt(month);
        net_dvr_playcond.struStartTime.dwDay =Integer.parseInt(day);
        net_dvr_playcond.struStartTime.dwHour =Integer.parseInt(hour);
        net_dvr_playcond.struStartTime.dwMinute =Integer.parseInt(minute);
        net_dvr_playcond.struStartTime.dwSecond =Integer.parseInt(second);

        String endYear = endTime.substring(0, 4);
        String endMonth = endTime.substring(5, 7);
        String endDay = endTime.substring(8, 10);
        String endHour = endTime.substring(11, 13);
        String endMinute = endTime.substring(14, 16);
        String endSecond = endTime.substring(17, 19);
        net_dvr_playcond.struStopTime.dwYear =Integer.parseInt(endYear);
        net_dvr_playcond.struStopTime.dwMonth =Integer.parseInt(endMonth);
        net_dvr_playcond.struStopTime.dwDay =Integer.parseInt(endDay);
        net_dvr_playcond.struStopTime.dwHour =Integer.parseInt(endHour);
        net_dvr_playcond.struStopTime.dwMinute =Integer.parseInt(endMinute);
        net_dvr_playcond.struStopTime.dwSecond =Integer.parseInt(endSecond);
        //异常消息回调
        if(fExceptionCallBack == null)
        {
            fExceptionCallBack = new FExceptionCallBack_Imp();
        }
        Pointer pUser = null;
        if (!hCNetSDK.NET_DVR_SetExceptionCallBack_V30(0, 0, fExceptionCallBack, pUser)) {
            return null;
        }

        log.info("设置异常消息回调成功");
        //启动SDK写日志
        hCNetSDK.NET_DVR_SetLogToFile(3, "./sdkLog", false);
        //login_V40("10.5.68.76",(short) 8000,"admin","deyixigu2023");
        login_V40(DeviceNumEnum.getIp(videoParamVo.getDeviceNum()),(short) 8000,DeviceNumEnum.getUserName(videoParamVo.getDeviceNum()),DeviceNumEnum.getPassWord(videoParamVo.getDeviceNum()));

        //注释掉的代码也可以参考，去掉注释可以运行
        //VideoDemo.getIPChannelInfo(lUserID); //获取IP通道

        //实时取流
        //VideoDemo.realPlay(lUserID,lDChannel);

        //按时间回放和下载
        // new VideoDemo().playBackBytime(lUserID,33);

        //按时间下载录像

        //String url =new VideoDemo().dowmloadRecordByTime(lUserID);
        String url =new VideoDemo().dowmloadRecordByTime(lUserID,net_dvr_playcond.struStartTime, net_dvr_playcond.struStopTime, Integer.parseInt(videoParamVo.getChannel()));
        //按时间回放和下载录像，需要等待回放和下载完成后调用注销和释放接口
//        while (true)
//        {
//
//        }
        //按录像文件回放和下载
//        VideoDemo.downloadRecordByFile(lUserID, 33);//录像文件查找下载
//
//        VideoDemo.playBackByfile(lUserID,33);
        Thread.sleep(3000);

        //退出程序时调用，每一台设备分别注销
        if (hCNetSDK.NET_DVR_Logout(lUserID)) {
            log.info("注销成功");
        }
        //SDK反初始化，释放资源，只需要退出时调用一次
        hCNetSDK.NET_DVR_Cleanup();
        String path  = downLoadByUrl(url,savePath);
        PageHelper.startPage(videoParamVo.getCurrent(),videoParamVo.getPageSize());
        videoParam.setDeviceNum(videoParamVo.getDeviceNum());
        videoParam.setStartTime(videoParamVo.getStartTime());
        videoParam.setEndTime(videoParamVo.getEndTime());
        videoParam.setUrl(path);
        videoParam.setChannel(videoParamVo.getChannel());
        ls.add(videoParam);
        PageInfo pageInfo = new PageInfo(ls);
        long total = pageInfo.getTotal();
        return new TableDataInfo<>(pageInfo.getList(),total);
    }

    /**
     * 设备登录V40 与V30功能一致
     * @param ip   设备IP
     * @param port SDK端口，默认设备的8000端口
     * @param user 设备用户名
     * @param psw  设备密码
     */
    public static void login_V40(String ip, short port, String user, String psw) {
        //注册
        HCNetSDK.NET_DVR_USER_LOGIN_INFO m_strLoginInfo = new HCNetSDK.NET_DVR_USER_LOGIN_INFO();//设备登录信息
        HCNetSDK.NET_DVR_DEVICEINFO_V40 m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V40();//设备信息

        String m_sDeviceIP = ip;//设备ip地址
        m_strLoginInfo.sDeviceAddress = new byte[HCNetSDK.NET_DVR_DEV_ADDRESS_MAX_LEN];
        System.arraycopy(m_sDeviceIP.getBytes(), 0, m_strLoginInfo.sDeviceAddress, 0, m_sDeviceIP.length());

        String m_sUsername = user;//设备用户名
        m_strLoginInfo.sUserName = new byte[HCNetSDK.NET_DVR_LOGIN_USERNAME_MAX_LEN];
        System.arraycopy(m_sUsername.getBytes(), 0, m_strLoginInfo.sUserName, 0, m_sUsername.length());

        String m_sPassword = psw;//设备密码
        m_strLoginInfo.sPassword = new byte[HCNetSDK.NET_DVR_LOGIN_PASSWD_MAX_LEN];
        System.arraycopy(m_sPassword.getBytes(), 0, m_strLoginInfo.sPassword, 0, m_sPassword.length());

        m_strLoginInfo.wPort = port;
        m_strLoginInfo.bUseAsynLogin = false; //是否异步登录：0- 否，1- 是
        m_strLoginInfo.byLoginMode=0;  //0- SDK私有协议，1- ISAPI协议
        m_strLoginInfo.write();

        lUserID = hCNetSDK.NET_DVR_Login_V40(m_strLoginInfo, m_strDeviceInfo);
        if (lUserID== -1) {
            log.error("登录失败，错误码为" + hCNetSDK.NET_DVR_GetLastError());
            return;
        } else {
            log.info(ip + ":设备登录成功！");
            //相机一般只有一个通道号，热成像相机有2个通道号，通道号为1或1,2
            //byStartDChan为IP通道起始通道号, 预览回放NVR的IP通道时需要根据起始通道号进行取值
            if ((int)m_strDeviceInfo.struDeviceV30.byStartDChan == 1 && (int)m_strDeviceInfo.struDeviceV30.byStartDChan == 33)
            {
                //byStartDChan为IP通道起始通道号, 预览回放NVR的IP通道时需要根据起始通道号进行取值,NVR起始通道号一般是33或者1开始
                lDChannel = (int)m_strDeviceInfo.struDeviceV30.byStartDChan;
                log.info("预览起始通道号："+lDChannel);
            }
            return;
        }
    }

    @GetMapping("/downloadType")
    @ApiOperation("集卡号列表")
    @Log(title = "下载列表->集卡号列表", businessType = BusinessType.OTHER)
    public ResultEntity<Map<String, String>> downloadType() {
        return ResultEntity.success(downloadService.downloadType());
    }
    @GetMapping("/channelType")
    @ApiOperation("渠道号列表")
    @Log(title = "下载列表->渠道列表", businessType = BusinessType.OTHER)
    public ResultEntity<Map<String, String>> channelType() {
        return ResultEntity.success(downloadService.channelType());
    }

    /**
     * 从网络Url中下载文件
     * @param urlStr url的路径
     * @throws IOException
     */
    public  String  downLoadByUrl(String urlStr,String savePath) {
        File file = null;
        try {
            String fileName = getFileName(urlStr);
            URL url = new URL("file:///"+urlStr);
            URLConnection conn = url.openConnection();
            //设置超时间为5秒
            conn.setConnectTimeout(5*1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //得到输入流
            InputStream inputStream = conn.getInputStream();
            //获取自己数组
            byte[] getData = readInputStream(inputStream);
            //文件保存位置
            File saveDir = new File(savePath);
            if(!saveDir.exists()){ // 没有就创建该文件
                saveDir.mkdir();
            }
            file = new File(saveDir+File.separator+fileName);
            System.out.println(file);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(getData);

            fos.close();
            inputStream.close();
            log.info("the file: "+url+" download success");
        }catch (Exception e){
            e.printStackTrace();
        }
        return String.valueOf(file);
    }
    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    private static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[4*1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }
    /**
     * 从src文件路径获取文件名
     * @param srcRealPath src文件路径
     * @return 文件名
     */
    private  String getFileName(String srcRealPath){
        String fileName= StringUtils.substringAfterLast(srcRealPath,"\\");
        return fileName;
    }
}



