package com.lensen.mobile;

import com.github.tobato.fastdfs.FdfsClientConfig;
import com.lensen.backend.utils.UploadConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@MapperScan("com.lensen.backend.dal.dao.*")
@Import({FdfsClientConfig.class, UploadConfig.class})
public class MobileDataApplication {
    public static void main(String[] args) {
        SpringApplication.run(MobileDataApplication.class, args);
    }
}
