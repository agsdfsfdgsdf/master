package com.deyi.daxie.cloud.common.exception.file;

import com.deyi.daxie.cloud.common.exception.base.BaseException;

/**
 * 文件信息异常类
 * 
 * @author Huang ShuYing
 */
public class FileException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }

}
