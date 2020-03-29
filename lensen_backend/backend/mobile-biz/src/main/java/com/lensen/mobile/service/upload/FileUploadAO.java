package com.lensen.mobile.service.upload;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadAO {
    String upload(MultipartFile file);
}
