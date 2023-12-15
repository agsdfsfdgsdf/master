package com.deyi.daxie.cloud.operation.file;

import com.deyi.daxie.cloud.operation.domain.dto.AjaxList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService service;

    @PostMapping("/add")
    public void exportByTime(@RequestParam(value="filePath") String filePath,int flag,String deviceNum) {
        service.parseFile(filePath,flag,deviceNum);
    }
    @PostMapping("/upload")
    public String parseAndAdd(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "deviceNum")String deviceNum) throws IOException {
        AjaxList<String> ajaxList = service.handlerUpload(file, deviceNum);
        return ajaxList.getData();
    }
}
