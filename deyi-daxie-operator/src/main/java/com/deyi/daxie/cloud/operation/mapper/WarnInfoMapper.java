package com.deyi.daxie.cloud.operation.mapper;

import com.deyi.daxie.cloud.operation.domain.WarnInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WarnInfoMapper {
    List<WarnInfo> selectByTime(String format);
    void insert(WarnInfo warnInfo);
}
