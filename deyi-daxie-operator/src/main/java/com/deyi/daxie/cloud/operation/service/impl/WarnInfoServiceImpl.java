package com.deyi.daxie.cloud.operation.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.handler.impl.FillStyleCellWriteHandler;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deyi.daxie.cloud.common.utils.StringUtils;
import com.deyi.daxie.cloud.operation.domain.VelWarnData;
import com.deyi.daxie.cloud.operation.domain.dto.WarnCountDto;
import com.deyi.daxie.cloud.operation.domain.vo.WarnCountVo;
import com.deyi.daxie.cloud.operation.mapper.VelWarnDataMapper;
import com.deyi.daxie.cloud.operation.service.WarnInfoService;
import com.deyi.daxie.cloud.operation.utils.SpiltDateUtil;
import com.deyi.daxie.cloud.operation.utils.WarnDataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class WarnInfoServiceImpl implements WarnInfoService {
    @Autowired
    private VelWarnDataMapper mapper;

    @Override
    public void exportByTime(WarnCountVo vo, HttpServletResponse response) {
        List<WarnCountDto> map = typeCountList(vo);
        // Java 8, Convert all Map values  to a List
        List<String> result = new ArrayList<>();
        for (WarnCountDto warnCountDto : map) {
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
    public List<WarnCountDto> typeCountList(WarnCountVo vo) {
        WarnCountDto ls = null;
        List map =new ArrayList();
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
                String time="";
                SpiltDateUtil.Range range = new SpiltDateUtil.Range();
                range.setStart(dateformat.parse(vo.getStartTime()));
                range.setEnd(dateformat.parse(vo.getEndTime()));
                QueryWrapper<VelWarnData> wrapper = getQueryWrapperByTime(vo);
                List<VelWarnData> wd = mapper.selectList(wrapper);
                time=vo.getStartTime()+"-"+vo.getEndTime();
                if(null!=wd&&wd.size()>0){
                    WarnDataUtil.getTypeListByTime(ls, wd,time);
                    map.add(ls);
                }else{
                    ls.setDeviceTime(time);
                    ls.setDeviceNum(vo.getDeviceNum());
                    ls.setCount(null);
                    map.add(ls);
                }
            }
            return map;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<VelWarnData> queryByTime() {
        // 获取当前日期
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        QueryWrapper<VelWarnData> wrapper = new QueryWrapper<>();
        wrapper.like("device_time",format);
        List<VelWarnData> wd = mapper.selectList(wrapper);
        return wd;
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

    private List<WarnCountDto> getList(WarnCountVo vo, List<SpiltDateUtil.Range> result) {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<WarnCountDto> map = new ArrayList<>();
        for (SpiltDateUtil.Range range : result) {
            WarnCountDto ls = new WarnCountDto();
            String startTime = dateformat.format(range.getStart());
            String endTime = dateformat.format(range.getEnd());
            vo.setStartTime(startTime);
            vo.setEndTime(endTime);
            QueryWrapper<VelWarnData> wrapper = getQueryWrapperByTime(vo);
            List<VelWarnData> wd = mapper.selectList(wrapper);
            String time ="";
            if(null!=wd&&wd.size()>0){
                time=startTime+"-"+endTime;
                WarnDataUtil.getTypeListByTime(ls, wd,time);
                map.add(ls);
            }else{
                time=startTime+"-"+endTime;
                ls.setDeviceTime(time);
                ls.setDeviceNum(vo.getDeviceNum());
                ls.setCount(null);
                map.add(ls);
            }
        }
        return map;
    }
    private QueryWrapper<VelWarnData> getQueryWrapperByTime(WarnCountVo vo) {
        QueryWrapper<VelWarnData> wrapper = new QueryWrapper<>();
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
}
