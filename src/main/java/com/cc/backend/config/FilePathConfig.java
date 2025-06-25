package com.cc.backend.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

@Configuration
@Slf4j
public class FilePathConfig {
    @Bean
    public String pictureDir() throws FileNotFoundException {
        File path = new File(ResourceUtils.getURL("classpath:static").getPath());
        if(!path.exists()) {
            log.error("not found {}", path.getAbsolutePath());
            path = new File("src/main/resources/static");
            log.error("has created {}", path.getAbsolutePath());
        }
        File upload = new File(path.getAbsolutePath(),"/upload/");
        if(!upload.exists()) {
            upload.mkdirs();
        }
        return upload.getAbsolutePath();
    }

    @Bean
    public String recycleDir() throws FileNotFoundException {
        File recycleDir = new File(this.pictureDir(), "/recycle/");
        if (!recycleDir.exists()) {
            recycleDir.mkdirs();
        }
        return recycleDir.getAbsolutePath();
    }
}
