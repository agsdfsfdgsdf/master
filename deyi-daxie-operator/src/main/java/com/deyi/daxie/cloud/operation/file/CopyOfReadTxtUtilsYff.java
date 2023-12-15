package com.deyi.daxie.cloud.operation.file;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deyi.daxie.cloud.operation.domain.JobDetail;
import com.deyi.daxie.cloud.operation.domain.OutCar;
import com.deyi.daxie.cloud.operation.domain.OutLane;
import com.deyi.daxie.cloud.operation.domain.dto.AjaxList;
import com.deyi.daxie.cloud.operation.domain.dto.FileMove;
import com.deyi.daxie.cloud.operation.mapper.JobDetailMapper;
import com.deyi.daxie.cloud.operation.service.OutCarService;
import com.deyi.daxie.cloud.operation.service.OutLaneService;
import com.deyi.daxie.cloud.operation.utils.FileTypeEnum;
import com.deyi.daxie.cloud.operation.utils.UnPackeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CopyOfReadTxtUtilsYff implements FileService {
    @Autowired
    private JobDetailMapper jobDetailMapper;
    @Autowired
    private OutLaneService outLaneService;
    @Autowired
    private OutCarService outCarService;

    /**
     * 文件入库
     * filePath:文件夹绝对路径
     */
    public void parseFile(String filePath, int flag, String deviceNum) {

        try {
            if (1 == 1) {
                String encoding = "GBK";
                File folder = new File(filePath);
//folder.list() 获取文件夹下所有文件名
                String[] fileArray = folder.list();
                if(fileArray==null||fileArray.length==0){
                    return;
                }
                for (String fileName : fileArray) {
                    System.out.println(fileName);
                    File file = new File(filePath + "/" + fileName);
                    fileName = fileName.substring(0, fileName.indexOf("."));
                    //判断文件是否存在
                    if (file.isFile() && file.exists()) {
                        InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
                        BufferedReader bufferedReader = new BufferedReader(read);
                        // 读取txt
                        if (flag == 2) {
                            saveOutLane(bufferedReader, deviceNum);
                        } else if (flag == 1) {
                            saveOutCar(bufferedReader, deviceNum);
                        }
                        read.close();
                        System.out.println("文件入库完成");
                    } else {
                        System.out.println("找不到指定的文件");
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void saveOutLane(BufferedReader bufferedReader, String deviceNum) {
        try {
            String lineTxt = null;
            List<OutLane> list = new ArrayList<>();
            while ((lineTxt = bufferedReader.readLine()) != null) {
                OutLane outLane = new OutLane();
                //lineTxt.replace(" ",",");
                String[] arrStrings = lineTxt.replace("\t", ",").split(",");
                outLane.setDeviceNum(deviceNum);
                outLane.setDeviceTime(arrStrings[0].toString());
                outLane.setRprecLat(arrStrings[1].toString());
                outLane.setRprecLon(arrStrings[2].toString());
                outLane.setRprecAzimuth(arrStrings[3].toString());
                outLane.setRprecHeel(arrStrings[4].toString());
                outLane.setRprecPitch(arrStrings[5].toString());
                outLane.setRprecAx(arrStrings[6].toString());
                outLane.setRprecAy(arrStrings[7].toString());
                outLane.setRprecAz(arrStrings[8].toString());
                outLane.setRprecNavspeed(arrStrings[9].toString());
                outLane.setRprecRtkstatusstring(arrStrings[10].toString());
                outLane.setRprecNavstatusstring(arrStrings[11].toString());
                outLane.setRprecRtklaterror(arrStrings[12].toString());
                outLane.setRprecRtklonerror(arrStrings[13].toString());
                outLane.setRprecRtkazierror(arrStrings[14].toString());
                outLane.setMobileLanelr0Lanequality(arrStrings[15].toString());
                outLane.setMobileLanelr0Viewrange(arrStrings[16].toString());
                outLane.setMobileLanelr0C0(arrStrings[17].toString());
                outLane.setMobileLanelr0C1(arrStrings[18].toString());
                outLane.setMobileLanelr0C2(arrStrings[19].toString());
                outLane.setMobileLanelr0C3(arrStrings[20].toString());
                outLane.setMobileLanelr1Lanequality(arrStrings[21].toString());
                outLane.setMobileLanelr1Viewrange(arrStrings[22].toString());
                outLane.setMobileLanelr1C0(arrStrings[23].toString());
                outLane.setMobileLanelr1C1(arrStrings[24].toString());
                outLane.setMobileLanelr1C2(arrStrings[25].toString());
                outLane.setMobileLanelr1C3(arrStrings[26].toString());
                list.add(outLane);
            }
            outLaneService.save(list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveOutCar(BufferedReader bufferedReader, String deviceNum) {
        try {
            String lineTxt = null;
            int batchSize = 1000;
            int count = 0;
            List<OutCar> list = new ArrayList<>();
            while ((lineTxt = bufferedReader.readLine()) != null) {
                OutCar outLane = new OutCar();
                //lineTxt.replace(" ",",");
                String[] arrStrings = lineTxt.replace("\t", ",").split(",");
                outLane.setDeviceNum(deviceNum);
                outLane.setDeviceTime(arrStrings[0].toString());
                outLane.setLat(arrStrings[1].toString());
                outLane.setLon(arrStrings[2].toString());
                outLane.setAzi(arrStrings[3].toString());
                outLane.setVh(arrStrings[4].toString());
                outLane.setAimspeed(arrStrings[5].toString());
                outLane.setAimsteer(arrStrings[6].toString());
                outLane.setObsdistance(arrStrings[7].toString());
                outLane.setVl(arrStrings[8].toString());
                outLane.setU(arrStrings[9].toString());
                outLane.setControlspeed(arrStrings[10].toString());
                outLane.setControlbrake(arrStrings[11].toString());
                outLane.setRealspeedrecF2(arrStrings[12].toString());
                outLane.setSteerreceive(arrStrings[13].toString());
                outLane.setRealpressure1(arrStrings[14].toString());
                outLane.setRealpressure2(arrStrings[15].toString());
                outLane.setTtc(arrStrings[16].toString());
                outLane.setTtcr(arrStrings[17].toString());
                outLane.setVt(arrStrings[18].toString());
                outLane.setAutomode(arrStrings[19].toString());
                outLane.setOffsetForm(arrStrings[20].toString());
                outLane.setLightflag(arrStrings[21].toString());
                outLane.setReadpressure3(arrStrings[22].toString());
                outLane.setY(arrStrings[23].toString());
                outLane.setBrakeswtich(arrStrings[24].toString());
                outLane.setGpsline0V1(arrStrings[25].toString());
                outLane.setGpsline0V2(arrStrings[26].toString());
                outLane.setGpsline0X(arrStrings[27].toString());
                outLane.setParkdis(arrStrings[28].toString());
                outLane.setStoplastdis(arrStrings[29].toString());
                outLane.setModestop(arrStrings[30].toString());
                outLane.setRecgear(arrStrings[31].toString());
                outLane.setGetgearrecord(arrStrings[32].toString());
                outLane.setStoprecorddistance(arrStrings[33].toString());
                outLane.setStoprecordmode1(arrStrings[34].toString());
                outLane.setStoprecordmode2(arrStrings[35].toString());
                outLane.setNextrpItem1(arrStrings[36].toString());
                outLane.setNextrpItem2(arrStrings[37].toString());
                outLane.setLongitudeacc(arrStrings[38].toString());
                outLane.setCanspeed2(arrStrings[39].toString());
                outLane.setGearstr(arrStrings[40].toString());
                outLane.setRecgeardetail(arrStrings[41].toString());
                outLane.setMotortorque(arrStrings[42].toString());
                outLane.setMotorspeed(arrStrings[43].toString());
                outLane.setRealspeedrecF1(arrStrings[44].toString());
                outLane.setWarn1(arrStrings[45].toString());
                outLane.setWarn2(arrStrings[46].toString());
                outLane.setWarn3(arrStrings[47].toString());
                outLane.setWarn4(arrStrings[48].toString());
                list.add(outLane);
            }
            outCarService.save(list);
            /*if (++count % batchSize == 0) {
                System.out.println(list.size());
                // 批量执行插入操作
                outCarService.save(list);
            }*/
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public AjaxList<String> handlerUpload(MultipartFile zipFile, String deviceNum) throws IOException {
        if (null == zipFile) {
            return AjaxList.createFail("请上传压缩文件!");
        }
        QueryWrapper<JobDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "uploadJob");
        wrapper.eq("device_num",deviceNum);
        wrapper.eq("flag",1);
        JobDetail wd = jobDetailMapper.selectOne(wrapper);
        String path = wd.getDict();
        boolean isZipPack = true;
        String fileContentType = zipFile.getContentType();
        //将压缩包保存在指定路径
        String packFilePath = path + File.separator + zipFile.getName();
        if (FileTypeEnum.FILE_TYPE_ZIP.type.equals(fileContentType)) {
            //zip解压缩处理
            packFilePath += FileTypeEnum.FILE_TYPE_ZIP.fileStufix;
        } else if (FileTypeEnum.FILE_TYPE_RAR.type.equals(fileContentType)) {
            //rar解压缩处理
            packFilePath += FileTypeEnum.FILE_TYPE_RAR.fileStufix;
            isZipPack = false;
        } else {
            return AjaxList.createFail("上传的压缩包格式不正确,仅支持rar和zip压缩文件!");
        }
        File file = new File(packFilePath);
        String dict =path+"/"+zipFile.getName();
        try {
            zipFile.transferTo(file);
        } catch (IOException e) {
            log.error("zip file save to " + path + " error", e.getMessage(), e);
            return AjaxList.createFail("保存压缩文件到:" + path + " 失败!");
        }
        if (isZipPack) {
            //zip压缩包
            UnPackeUtil.unPackZip(file, null, path);
                // 解压成功，删除压缩包
                 file.delete();
                 moveFile(path);
        } else {
            //rar压缩包
            UnPackeUtil.unPackRar(file, path);
            // 解压成功，删除压缩包
            file.delete();
            moveFile(path);
        }
        return AjaxList.createSuccess("解压成功");
    }

    public void moveFile(String basePath) throws IOException {
        File dir = new File(basePath);
        List<FileMove> allFileList = new ArrayList<>();
        // 判断文件夹是否存在
        if (!dir.exists()) {
            System.out.println("目录不存在");
            return;
        }
        getAllFile(dir, allFileList,basePath);
        for (FileMove file : allFileList) {
            file.getFile().renameTo(new File(basePath+"/"+file.getFile().getName()));
        }
        deleteFolder(new File(basePath));
        System.out.println("该文件夹下共有" + allFileList.size() + "个文件");
    }
    public static void getAllFile(File fileInput, List<FileMove> allFileList,String basePath) throws IOException {
        // 获取文件列表
        File[] fileList = fileInput.listFiles();
        assert fileList != null;
        String dict ="";
        for (File file : fileList) {
            if (file.isDirectory()) {
                // 递归处理文件夹
                // 如果不想统计子文件夹则可以将下一行注释掉
                dict = basePath+"/"+file.getName();
                getAllFile(file, allFileList,basePath);
            } else {
                FileMove fileMove = new FileMove();
                fileMove.setFile(file);
                fileMove.setPath(dict);
                System.out.println(dict);
                allFileList.add(fileMove);
            }
        }
    }
    public static void deleteFolder(File fileInput) {
        File[] files = fileInput.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteFolder(f);
                }
            }
        }
        //如果为空文件夹
        if (files.length < 1) {
            fileInput.delete();
        }
    }
}