package com.lensen.mobile.service.upload.impl;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.lensen.backend.utils.FileUploadUtil;
import com.lensen.mobile.service.upload.FileUploadAO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileUploadAOImpl implements FileUploadAO {

    private static FastFileStorageClient fastFileStorageClient;

    @Autowired
    public void setFastDFSClient(FastFileStorageClient fastFileStorageClient) {
        FileUploadAOImpl.fastFileStorageClient = fastFileStorageClient;
    }

    @Value("${fdfs.web-server-url}")
    private String hostIP;

    @Override
    public String upload(MultipartFile file) {
        try {
            StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), FileUploadUtil.getFileExt(file), null);
            return storePath.getFullPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return StringUtils.EMPTY;
    }
}
