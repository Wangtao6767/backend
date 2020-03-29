package com.lensen.mobile.web;

import com.lensen.backend.utils.BaseResponse;
import com.lensen.backend.utils.CommonUtil;
import com.lensen.backend.utils.Status;
import com.lensen.mobile.service.upload.FileUploadAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class FileUploadController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private FileUploadAO fileUploadAO;

    @RequestMapping("/file")
    public String file(MultipartFile file) {
        BaseResponse<Object> result = new BaseResponse<>();
        if (file == null) {
            result.setCode(Status.FILE_NULL.getCode());
            result.setMsg(Status.FILE_NULL.getMessage());
            return CommonUtil.responseFromObject(result);
        }
        String path = fileUploadAO.upload(file);
        result.setResponse(path);
        return CommonUtil.responseFromObject(result);
    }
}
