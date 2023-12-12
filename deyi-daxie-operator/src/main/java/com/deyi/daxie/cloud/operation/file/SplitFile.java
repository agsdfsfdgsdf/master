package com.deyi.daxie.cloud.operation.file;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SplitFile {
    public static List<File> splitDataToSaveFile(int rows, String targetDirectoryPath) {
        File folder = new File(targetDirectoryPath);
        //folder.list() 获取文件夹下所有文件名
        String[] fileArray = folder.list();
        List<File> fileList = new ArrayList<>();
        int lineNo = 1, fileNum = 1;
        if (fileArray == null || fileArray.length == 0) {
            return null;
        }
        for (String fileName : fileArray) {
            System.out.println(fileName);
            File file = new File(targetDirectoryPath + "\\" + fileName);
            fileName = fileName.substring(0, fileName.indexOf("."));
            long startTime = System.currentTimeMillis();
            log.info("开始分割文件");
            File targetFile = new File(targetDirectoryPath);
            File file1 = new File("");
            if (!file.exists() || rows <= 0 || file.isDirectory()) {
                return null;
            }
            if (targetFile.exists()) {
                if (!targetFile.isDirectory()) {
                    return null;
                }
            } else {
                targetFile.mkdirs();
            }
            try (FileInputStream fileInputStream = new FileInputStream(file);
                 InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
                 BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                StringBuilder stringBuilder = new StringBuilder();
                String lineStr;

                while ((lineStr = bufferedReader.readLine()) != null) {
                    stringBuilder.append(lineStr).append("\r\n");
                    if (lineNo % rows == 0) {
                        file1 = new File(targetDirectoryPath + File.separator + fileNum + file.getName());
                        writeFile(stringBuilder.toString(), file1);
                        //清空文本
                        stringBuilder.delete(0, stringBuilder.length());
                        fileNum++;
                        fileList.add(file);
                    }
                    lineNo++;
                }
                if ((lineNo - 1) % rows != 0) {
                    file1 = new File(targetDirectoryPath + File.separator + fileNum + file.getName());
                    writeFile(stringBuilder.toString(), file1);
                    fileList.add(file1);
                }

                long endTime = System.currentTimeMillis();
                log.info("分割文件结束，耗时：{}秒", (endTime - startTime) / 1000);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(lineNo>rows){
                System.out.println(lineNo);
                if(file.delete()) {
                    System.out.println("文件已被删除");
                } else {
                    System.out.println("文件删除失败");
                }

                // CreateExcelFile.deleteExcel(file.toString());
            }
        }
        return  fileList;
    }

    private static void writeFile(String text, File file) {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter, 1024)
        ) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

/*    public static void main(String[] args) {
        splitDataToSaveFile(10000, "D:\\outcar902");
        //deleteFile(10000, "D:\\outcar902",1,"T902");
        //CreateExcelFile.deleteExcel("D:\\outcar902\\Out_Car_2023-11-02 11-57-00.953.txt");
    }*/
}
