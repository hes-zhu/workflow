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

    @PostMapping
    public ServiceResponse upload(@RequestParam("file") MultipartFile file, @RequestParam("projectId") String projectId) {
        return fileService.upload(file, Integer.parseInt(projectId));
    }

    @PostMapping("morefile")
    public ServiceResponse uploadMore(@RequestParam("files") MultipartFile[] files, @RequestParam("projectId") String projectId) {
        return fileService.manyUpload(files, Integer.parseInt(projectId));
    }

    @GetMapping
    public ServiceResponse fileDownload(HttpServletResponse res, HttpServletRequest request, String fileName) {
        return fileService.fileDownload(res, request, fileName);
    }
}
