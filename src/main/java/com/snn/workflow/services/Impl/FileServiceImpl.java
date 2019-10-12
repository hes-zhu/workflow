package com.snn.workflow.services.Impl;

import com.google.common.collect.Lists;
import com.snn.workflow.services.IFileService;
import com.snn.workflow.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @className FileServiceImpl
 * @Author SNN
 * @Date 2019/9/24 17:55
 **/
@Service("iFileService")
public class FileServiceImpl implements IFileService {

    private Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);

    @Override
    public String upload(MultipartFile file, String path) {
        String fileName = file.getOriginalFilename();
        //扩展名
        String fileExtendsionName = fileName.substring(fileName.lastIndexOf(".")+1);
        String uploadFileName = UUID.randomUUID().toString()+"."+fileExtendsionName;
        LOGGER.info("开始上传文件,上传的文件名:{},上传的路径:{},新文件名:{}", fileName, path, uploadFileName);

        File fileDir = new File(path);
        if(!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
//        File targetFile = new File(path, uploadFileName);
        File targetFile = new File(path, fileName);
        try {
            file.transferTo(targetFile);
            //文件已经上传成功了
            //将targetFile上传到我们的FTP服务器上
            FTPUtil.uploadFile(Lists.<File>newArrayList(targetFile));
            //上传完之后，删除upload下面的文件
            targetFile.delete();
        } catch (IOException e) {
            LOGGER.error("上传文件异常", e);
            return null;
        }
        return targetFile.getName();
    }

    @Override
    public byte[] downloadFile(String filePath, String fileName) {
        byte[] outPutStream = null;
        try {
            outPutStream = FTPUtil.downFile(filePath, fileName);

            if(outPutStream != null) {
                LOGGER.info("下载文件成功");
                return outPutStream;
            }
            LOGGER.error("===========下载失败，文件不存在=========");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("下载文件失败：" + e.toString());
        }
        return outPutStream;
    }
}
