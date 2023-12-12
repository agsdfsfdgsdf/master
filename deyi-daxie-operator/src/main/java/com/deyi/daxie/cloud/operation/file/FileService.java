package com.deyi.daxie.cloud.operation.file;

import com.deyi.daxie.cloud.operation.domain.dto.AjaxList;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    void parseFile (String filePath,int flag,String deviceNum);
    AjaxList handlerUpload(MultipartFile zipFile, String deviceNum) throws IOException;

}
