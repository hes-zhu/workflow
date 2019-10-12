package com.snn.workflow.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * @className IFileService
 * @Author SNN
 * @Date 2019/9/24 17:54
 **/
public interface IFileService {
    String upload(MultipartFile file, String path);

    byte[] downloadFile(String filePath, String fileName);
}
