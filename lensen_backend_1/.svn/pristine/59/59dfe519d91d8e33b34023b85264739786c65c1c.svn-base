package com.lensen.backend.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
    /**
     * 获取文件后缀
     *
     * @param file
     * @return
     */
    public static String getFileExt(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (StringUtils.isBlank(fileName)) {
            return StringUtils.EMPTY;
        }
        int pointIndex = fileName.lastIndexOf(".");
        return fileName.substring(pointIndex + 1).toLowerCase();
    }
}
