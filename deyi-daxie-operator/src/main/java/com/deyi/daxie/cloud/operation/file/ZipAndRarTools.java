package com.deyi.daxie.cloud.operation.file;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import java.io.*;
import java.util.Enumeration;
public class ZipAndRarTools {
    /**
     * 解压Zip文件
     *
     * @param zipFileName  需要解压缩的文件位置
     * @param descFileName 将文件解压到某个路径
     * @throws IOException
     */
    public static void unZip(String zipFileName, String descFileName) throws IOException {
        System.out.println("文件解压开始...");
        String descFileNames = descFileName;
        if (!descFileNames.endsWith(File.separator)) {
            descFileNames = descFileNames + File.separator;
        }
        try {
            ZipFile zipFile = new ZipFile(zipFileName);
            ZipEntry entry = null;
            String entryName = null;
            String descFileDir = null;
            byte[] buf = new byte[4096];
            int readByte = 0;
            @SuppressWarnings("rawtypes")
            Enumeration enums = zipFile.getEntries();
            while (enums.hasMoreElements()) {
                entry = (ZipEntry) enums.nextElement();
                entryName = entry.getName();
                descFileDir = descFileNames + entryName;
                if (entry.isDirectory()) {
                    new File(descFileDir).mkdir();
                    continue;
                } else {
                    new File(descFileDir).getParentFile().mkdir();
                }
                File file = new File(descFileDir);
                OutputStream os = new FileOutputStream(file);
                InputStream is = zipFile.getInputStream(entry);
                while ((readByte = is.read(buf)) != -1) {
                    os.write(buf,0 , readByte);
                }
                os.close();
                is.close();
            }
            zipFile.close();
            System.out.println("文件解压成功!");
        } catch (Exception e) {
            System.out.println("文件解压失败!");
            e.printStackTrace();
        }
    }
}