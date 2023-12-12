package com.deyi.daxie.cloud.operation.email;

import cn.hutool.extra.mail.MailUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deyi.daxie.cloud.operation.domain.OutCar;
import com.deyi.daxie.cloud.operation.domain.VelWarnData;
import com.deyi.daxie.cloud.operation.domain.WarnInfo;
import com.deyi.daxie.cloud.operation.domain.dto.WarnInfoDto;
import com.deyi.daxie.cloud.operation.mapper.OutCarMapper;
import com.deyi.daxie.cloud.operation.mapper.WarnInfoMapper;
import com.deyi.daxie.cloud.operation.utils.CreateExcelFile;
import com.deyi.daxie.cloud.operation.utils.TypEnum;
import com.deyi.daxie.cloud.operation.utils.WarnEnum;
import com.deyi.daxie.cloud.system.mapper.SysDeptMapper;
import com.deyi.daxie.cloud.system.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: cuixb
 * date 2023/10/31 17:49
 */
@Service
@Slf4j
public class EmailServiceImpl implements EmailService{
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private WarnInfoMapper warnInfoMapper;
    @Resource
    private SysDeptMapper sysDeptMapper;
    @Resource
    private OutCarMapper outCarMapper;

    @Async
    @Override
    public void createAlertInfo(List<VelWarnData> vehInfo, String fileDir, String path, String deptName, int flag) {
        List<WarnInfoDto> ls = new ArrayList<>();
        getTypeListByTime(ls, vehInfo);
        //告警信息生成
        List<WarnInfo> alertInfo = initAlert(ls);
        String sheetName = "warn_info";
        File file = new File(fileDir);
        String[] title = {"集卡号", "报警时间", "报警级别", "报警类型", "维度", "经度"};
        List<Map<String, String>> userList = new ArrayList<Map<String, String>>();
        for (WarnInfoDto warnInfoDto : ls) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("集卡号", warnInfoDto.getDeviceNum());
            map.put("报警时间", warnInfoDto.getDeviceTime());
            map.put("报警级别", warnInfoDto.getLevel());
            map.put("报警类型", warnInfoDto.getType());
            map.put("维度", warnInfoDto.getLat());
            map.put("经度", warnInfoDto.getLon());
            userList.add(map);
        }
        try {
            CreateExcelFile.createExcelXls(fileDir, sheetName, title);
            CreateExcelFile.writeToExcelXls(fileDir, sheetName, userList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (WarnInfo warnInfo : alertInfo) {
            warnInfoMapper.insert(warnInfo);
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        List<WarnInfo> wd = warnInfoMapper.selectByTime(format);
        for(WarnInfo warnDto :wd){
            sendMail(warnDto,file,deptName);
        }
        CreateExcelFile.deleteExcel(fileDir);
        CreateExcelFile.deleteDiretory(path);
    }
    public List<WarnInfo> initAlert(List<WarnInfoDto> warnInfoVo) {
        List<WarnInfo> wf = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for (WarnInfoDto warnInfoDto : warnInfoVo) {
            WarnInfo warnInfo = new WarnInfo();
            builder.append("<h1>您好！</h1><br />");
            builder.append("<h2>&nbsp;&nbsp;").append(warnInfoDto.getDeviceNum()).append("在")
                    .append(warnInfoDto.getDeviceTime()).append("发出").append(warnInfoDto.getLevel()).append("告警通知").append(",请注意查看,详细信息见附件！");
            builder.append("</h1><br />");
            warnInfo.setContent(builder.toString());
            warnInfo.setDeviceTime(warnInfoDto.getDeviceTime());
            warnInfo.setDeviceNum(warnInfoDto.getDeviceNum());
            warnInfo.setGrade(warnInfoDto.getLevel());
            warnInfo.setType(warnInfoDto.getType());
            warnInfo.setLat(warnInfoDto.getLat());
            warnInfo.setLon(warnInfoDto.getLon());
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            warnInfo.setCreateTime(dateFormat.format(date));
            wf.add(warnInfo);
        }
        return wf;
    }

    @Async
    public void sendMail(WarnInfo warnInfo, File file, String deptName) {
        String subject = "告警通知";
        //String deptName1 = "大榭项目技术支持组";
        int deptId = sysDeptMapper.selectOne(deptName);
        Integer a = new Integer(deptId);
        long b = a.longValue();
        Date date = new Date();
        List<String> email = queryEmailList(b);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder builder = new StringBuilder();
        builder.append("<h1>您好！</h1><br />");
        builder.append("<h2>&nbsp;&nbsp;").append(warnInfo.getDeviceNum()).append("在")
                .append(dateFormat.format(date)).append("发出").append(warnInfo.getGrade()).append("告警通知").append(warnInfo.getTypeCount()).append("条,请注意查看,详细信息见附件！");
        builder.append("</h1><br />");
        MailUtil.send(email, subject, builder.toString(), true, file);
    }

    /**
     * 查询审批角色邮箱信息
     */
    public List<String> queryEmailList(long deptId) {
        List<String> EmailList = sysUserMapper.selectByDept(deptId);
        List<String> newList = EmailList.stream().distinct().collect(Collectors.toList());
        return newList;
    }

    public void getTypeListByTime(List<WarnInfoDto> ls, List<VelWarnData> data) {
        data.stream().forEach(it -> {
            Class clazz = it.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmmss");
                    field.setAccessible(true);
                    if (field.getType() == Boolean.class && (Boolean) field.get(it)) {
                        if (TypEnum.valueOf(field.getName()).getDesc().equals(WarnEnum.LEVELALARMONE.getDesc()) && ((Boolean) field.get(it)).booleanValue() && field.get(it).equals(true)) {
                            WarnInfoDto wfd = new WarnInfoDto();
                            QueryWrapper<OutCar> wrapper = new QueryWrapper<>();
                            Date f1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(it.getDeviceTime());
                            wrapper.eq("device_num",it.getDeviceNum());
                            wrapper.like("device_time", sdf.format(f1));
                            List<OutCar> wd = outCarMapper.selectList(wrapper);
                            OutCar lastElement = wd.stream().reduce((first, second) -> second).orElse(null);
                            wfd.setLat(lastElement.getLat());
                            wfd.setLon(lastElement.getLon());
                            wfd.setDeviceTime(it.getDeviceTime());
                            wfd.setType(TypEnum.valueOf(field.getName()).getAlias());
                            wfd.setDeviceTime(it.getDeviceTime());
                            wfd.setDeviceNum(it.getDeviceNum());
                            wfd.setLevel(WarnEnum.LEVELALARMONE.getDesc());
                            ls.add(wfd);
                        } else if (TypEnum.valueOf(field.getName()).getDesc().equals(WarnEnum.LEVELALARMTWO.getDesc()) && ((Boolean) field.get(it)).booleanValue() && field.get(it).equals(true)) {
                            WarnInfoDto wfd = new WarnInfoDto();
                            QueryWrapper<OutCar> wrapper = new QueryWrapper<>();
                            Date f1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(it.getDeviceTime());
                            wrapper.eq("device_num",it.getDeviceNum());
                            wrapper.like("device_time", sdf.format(f1));
                            List<OutCar> wd = outCarMapper.selectList(wrapper);
                            OutCar lastElement = wd.stream().reduce((first, second) -> second).orElse(null);
                            wfd.setDeviceTime(it.getDeviceTime());
                            wfd.setLat(lastElement.getLat());
                            wfd.setLon(lastElement.getLon());
                            wfd.setDeviceTime(it.getDeviceTime());
                            wfd.setType(field.getName());
                            wfd.setDeviceNum(it.getDeviceNum());
                            wfd.setLevel(WarnEnum.LEVELALARMTWO.getDesc());
                            ls.add(wfd);
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
