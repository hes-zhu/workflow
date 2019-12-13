package com.snn.workflow.controller;

import com.snn.workflow.common.ServiceResponse;
import com.snn.workflow.services.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @className FileController
 * @Author lulu
 * @Date 2019/11/23 下午6:41
 **/
@RestController
@RequestMapping("api/file")
public class FileController {

    @Autowired
    private IFileService fileService;

//    @PostMapping
    /**
     * @auther: lulu
     * @Description: 上传文件（够用了）
     * @MethodName: upload
     * @return: com.snn.workflow.common.ServiceResponse
     * @param: [file, projectId]
     * @date: 2019/11/27 下午6:52
     **/
//    public ServiceResponse upload(@RequestParam("file") MultipartFile file, @RequestParam("projectId") String projectId) {
//        return fileService.upload(file, Integer.parseInt(projectId));
//    }

//    @PostMapping("morefile")
    /**
     * @auther: lulu
     * @Description: 上传文件
     * @MethodName: uploadMore
     * @return: com.snn.workflow.common.ServiceResponse
     * @param: [files, projectId]
     * @date: 2019/11/27 下午6:53
     **/
//    public ServiceResponse uploadMore(@RequestParam("files") MultipartFile[] files, @RequestParam("projectId") String projectId) {
//        return fileService.manyUpload(files, Integer.parseInt(projectId));
//    }

    @GetMapping
    /**
     * @auther: lulu
     * @Description: 下载文件
     * @MethodName: fileDownload
     * @return: com.snn.workflow.common.ServiceResponse
     * @param: [res, request, fileName]
     * @date: 2019/11/27 下午6:53
     **/
    public ServiceResponse fileDownload(HttpServletResponse res, HttpServletRequest req, String fileName) {
        return fileService.fileDownload(res, req, fileName);
    }

}
