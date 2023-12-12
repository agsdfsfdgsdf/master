package com.deyi.daxie.cloud.operation.domain.dto;

import lombok.Data;

import java.io.File;

@Data
public class FileMove {
    /**
     * 任务名称
     */
    private File file;

    /**
     * 文件路径
     */
    private String path;


    private static final long serialVersionUID = 1L;
}
