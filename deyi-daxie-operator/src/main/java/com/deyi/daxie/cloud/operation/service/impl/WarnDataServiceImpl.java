package com.deyi.daxie.cloud.operation.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.handler.impl.FillStyleCellWriteHandler;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deyi.daxie.cloud.common.core.page.TableDataInfo;
import com.deyi.daxie.cloud.common.utils.StringUtils;
import com.deyi.daxie.cloud.operation.domain.dto.*;
import com.deyi.daxie.cloud.operation.domain.vo.WarnCountVo;
import com.deyi.daxie.cloud.operation.mapper.JobDetailMapper;
import com.deyi.daxie.cloud.operation.utils.FileTypeEnum;
import com.deyi.daxie.cloud.operation.utils.SpiltDateUtil;
import com.deyi.daxie.cloud.operation.utils.UnPackeUtil;
import com.deyi.daxie.cloud.operation.utils.WarnDataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.deyi.daxie.cloud.operation.domain.WarnData;
import com.deyi.daxie.cloud.operation.domain.vo.WarnDataVo;
import com.deyi.daxie.cloud.operation.mapper.WarnDataMapper;
import com.deyi.daxie.cloud.operation.service.WarnDataService;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class WarnDataServiceImpl implements WarnDataService {

    @Autowired
    private WarnDataMapper mapper;

    @Autowired
    private JobDetailMapper jobDetailMapper;
    @Override
    public TableDataInfo<WarnDataTypeDto> list(WarnDataVo vo) {
        QueryWrapper<WarnData> wrapper = getQueryWrapper(vo);
        Page<WarnData> page = new Page<>(vo.getCurrent(), vo.getPageSize());
        mapper.selectPage(page, wrapper);
        List<WarnDataTypeDto> ls = new ArrayList<>();
        WarnDataUtil.getTypeList(ls, page.getRecords());
        if (StrUtil.isNotEmpty(vo.getType())) {
            ls = ls.stream().filter(it -> StrUtil.equals(WarnDataUtil.getTypeField().get(vo.getType()), it.getType())).collect(Collectors.toList());
        }
        return new TableDataInfo<>(ls, page.getTotal());
    }

    @Override
    public void export(WarnDataVo vo, HttpServletResponse response) {

        QueryWrapper<WarnData> wrapper = getQueryWrapper(vo);
        List<WarnData> data = mapper.selectList(wrapper);
        List<WarnDataTypeDto> list = new ArrayList<>();
        WarnDataUtil.getTypeList(list, data);

        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = "warn_data";
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

            EasyExcel.write(response.getOutputStream(), WarnDataTypeDto.class)
                    .registerWriteHandler(new FillStyleCellWriteHandler())
                    .sheet("sheet")
                    .doWrite(list);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public Map<String, String> typeList() {
        return WarnDataUtil.getTypeField();
    }

    @Override
    public List<WarnData> getByRange(Date startTime, Date endTime) {
        return mapper.getByRange(startTime, endTime);
    }

    @Override
    public void exportByTime(WarnCountVo vo, HttpServletResponse response) {
        Map<SpiltDateUtil.Range,WarnCountDto> map = typeCountList(vo);
        // Java 8, Convert all Map values  to a List
        List<String> result = new ArrayList<>();
        for (WarnCountDto warnCountDto : map.values()) {
            result.add(String.valueOf(warnCountDto));
        }
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = "warnCountDto_data";
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

            EasyExcel.write(response.getOutputStream(), WarnCountDto.class)
                    .registerWriteHandler(new FillStyleCellWriteHandler())
                    .sheet("sheet")
                    .doWrite(result);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public Map<SpiltDateUtil.Range,WarnCountDto> typeCountList(WarnCountVo vo) {
        WarnCountDto ls = null;
        Map<SpiltDateUtil.Range,WarnCountDto> map =new HashMap<>();
        try {
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start = dateformat.parse(vo.getStartTime());
            Date end = dateformat.parse(vo.getEndTime());
            ls = new WarnCountDto();
            //按星期统计
            if (vo.getMark() == 1) {
                List<SpiltDateUtil.Range> result = SpiltDateUtil.splitToWeeks(start, end);
                map = getList(vo,result);
                //按月统计
            } else if (vo.getMark() == 2) {
                List<SpiltDateUtil.Range> result = SpiltDateUtil.splitToMonths(start, end);
                map = getList(vo,result);
                //按天统计
            } else {
                SpiltDateUtil.Range range = new SpiltDateUtil.Range();
                range.setStart(dateformat.parse(vo.getStartTime()));
                range.setEnd(dateformat.parse(vo.getEndTime()));
                QueryWrapper<WarnData> wrapper = getQueryWrapperByTime(vo);
                List<WarnData> wd = mapper.selectList(wrapper);
                WarnDataUtil.getTypeListByTime(ls, wd);
                map.put(range,ls);
            }
            return map;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, String> markType() {
        return typeMark;
    }
    private static Map<String, String> typeMark = new HashMap<>();
    static {
        typeMark.put("1", "周");
        typeMark.put("2", "月");
        typeMark.put("3", "日");
    }

    private Map<SpiltDateUtil.Range,WarnCountDto> getList(WarnCountVo vo, List<SpiltDateUtil.Range> result) {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<SpiltDateUtil.Range, WarnCountDto> map = new HashMap<>();
        for (SpiltDateUtil.Range range : result) {
            WarnCountDto ls = new WarnCountDto();
            String startTime = dateformat.format(range.getStart());
            String endTime = dateformat.format(range.getEnd());
            vo.setStartTime(startTime);
            vo.setEndTime(endTime);
            QueryWrapper<WarnData> wrapper = getQueryWrapperByTime(vo);
            List<WarnData> wd = mapper.selectList(wrapper);
            if(null!=wd&&wd.size()>0){
                WarnDataUtil.getTypeListByTime(ls, wd);
                map.put(range, ls);
            }else{
                map.put(range, null);
            }
        }
        return map;
    }
    private QueryWrapper<WarnData> getQueryWrapperByTime(WarnCountVo vo) {
        QueryWrapper<WarnData> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(vo.getType())) {
            wrapper.eq(StrUtil.toUnderlineCase(vo.getType()), Boolean.TRUE);
        }
        if (StringUtils.isNotEmpty(vo.getDeviceNum())) {
            wrapper.like("device_num", vo.getDeviceNum());
        }
        if (StringUtils.isNotEmpty(vo.getStartTime())) {
            wrapper.ge("device_time", vo.getStartTime());
        }
        if (StringUtils.isNotEmpty(vo.getEndTime())) {
            wrapper.le("device_time", vo.getEndTime());
        }
        return wrapper;
    }

    private QueryWrapper<WarnData> getQueryWrapper(WarnDataVo vo) {
        QueryWrapper<WarnData> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(vo.getType())) {
            wrapper.eq(StrUtil.toUnderlineCase(vo.getType()), Boolean.TRUE);
        }
        if (StringUtils.isNotEmpty(vo.getDeviceNum())) {
            wrapper.like("device_num", vo.getDeviceNum());
        }
        return wrapper;
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
        String dict =path+"\\"+zipFile.getName();
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
            file.getFile().renameTo(new File(basePath+"\\"+file.getFile().getName()));
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
                dict = basePath+"\\"+file.getName();
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
