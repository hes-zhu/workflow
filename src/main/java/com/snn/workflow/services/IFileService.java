package com.snn.workflow.services;

import com.snn.workflow.common.ServiceResponse;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @className IFileService
 * @Author SNN
 * @Date 2019/9/24 17:54
 **/
public interface IFileService {

    // 舍弃
    String upload(MultipartFile file, String path);

    // 舍弃
    byte[] downloadFile(String filePath, String fileName);

    String upload(MultipartFile file);

    List<String> manyUpload(MultipartFile[] files);

    ServiceResponse fileDownload(HttpServletResponse res, HttpServletRequest req, String fileName);
}
