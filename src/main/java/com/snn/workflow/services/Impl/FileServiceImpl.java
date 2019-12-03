package com.snn.workflow.services.Impl;

import com.google.common.collect.Lists;
import com.snn.workflow.common.ServiceResponse;
import com.snn.workflow.dao.ProjectfileMapper;
import com.snn.workflow.entity.Projectfile;
import com.snn.workflow.services.IFileService;
import com.snn.workflow.util.FTPUtil;
import com.snn.workflow.util.FileUtil;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * @className FileServiceImpl
 * @Author SNN
 * @Date 2019/9/24 17:55
 **/
@Service("iFileService")
public class FileServiceImpl implements IFileService {

    @Autowired
    private ProjectfileMapper projectfileMapper;

    private Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);
//    private static final String SUFFIX = "csv/txt/zip/";
    private static final String resource = System.getProperty("user.dir");
    private static final String FILEPATH = resource+"/src/main/resources/static/file";

    // 舍弃
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

    // 舍弃
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

    @Override
    public ServiceResponse upload(MultipartFile file, Integer projectId) {
        //获取上传文件名
        String fileName = file.getOriginalFilename();
        //1.判断文件是否为空(是否上传文件 / 文件内容是否为空)
        if (file.isEmpty()){
            return ServiceResponse.createByErrorMessage("上传文件不可以为空");
        }
        //2.判断文件后缀名是否符合要求
        String fileNameSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        // if (!SUFFIX.contains(fileNameSuffix)){
//        if (SUFFIX.indexOf(fileNameSuffix) < 0) {
//            return ServiceResponse.createByErrorMessage("文件类型不正确");
//        }
        //3.判断文件大小是否符合要求
        // 获取上传文件大小
        int size = (int) file.getSize();
        //4.将文件重命名，避免文件名相同覆盖文件
        String fileNamePrefix = fileName.substring(0 , fileName.lastIndexOf("."));
        fileName = fileNamePrefix + "-" + System.currentTimeMillis() + "." + fileNameSuffix;//获取上传文件名
        // 文件名存放数据库
        saveFileUrl(FILEPATH+"/"+fileName, projectId);
        //5.判断文件夹是否存在
        File targetFile = new File(FILEPATH + "/" + fileName);
        if (!targetFile.getParentFile().exists()) {
            //不存在创建文件夹
            targetFile.getParentFile().mkdirs();
        }
        try {
            //6.将上传文件写到服务器上指定的文件
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ServiceResponse.createBySuccess();
    }

    @Override
    public ServiceResponse manyUpload(MultipartFile[] files, Integer projectId) {
        //文件上传位置
        for (int i = 0; i < files.length; i++){
            //1.判断文件是否为空
            if (files[i].isEmpty()){
                String errMsg = "第"+ (i+1) +"个文件为空";
                return ServiceResponse.createByErrorMessage(errMsg);
            }
            //2.判断文件后缀名是否符合要求
            //获取上传文件名
            String fileName = files[i].getOriginalFilename();
            String fileNameSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
//            if (SUFFIX.indexOf(fileNameSuffix) < 0) {
//                String errMsg = "第"+ (i+1) +"文件类型不正确";
//                return ServiceResponse.createByErrorMessage(errMsg);
//            }
            //3.判断文件大小是否符合要求
            // int size = (int) files[i].getSize();//获取上传文件大小
            //4.将文件重命名，避免文件名相同覆盖文件
            // 获取上传文件名
            String fileNamePrefix = fileName.substring(0 , fileName.lastIndexOf("."));
            fileName = fileNamePrefix + "-" + UUID.randomUUID().toString() + "." + fileNameSuffix;
            // 文件名存放数据库
            saveFileUrl(FILEPATH+"/"+fileName, projectId);
            //5.判断文件夹是否存在
            File targetFile = new File(FILEPATH + "/" + fileName);
            if (!targetFile.getParentFile().exists()) {
                //不存在创建文件夹
                targetFile.getParentFile().mkdirs();
            }
            try {
                //6.将上传文件写到服务器上指定的文件
                files[i].transferTo(targetFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ServiceResponse.createBySuccess();
    }

    @Override
    public ServiceResponse fileDownload(HttpServletResponse res, HttpServletRequest req, String fileName) {
        File file = new File(FILEPATH + "/" + fileName);
        if (file.exists()){//判断文件是否存在
            //判断浏览器是否为火狐
            try {
                if ("FF".equals(getBrowser(req))) {
                    // 火狐浏览器 设置编码new String(realName.getBytes("GB2312"), "ISO-8859-1");
                    fileName = new String(fileName.getBytes("GB2312"), "ISO-8859-1");
                }else{
                    fileName = URLEncoder.encode(fileName, "UTF-8");//encode编码UTF-8 解决大多数中文乱码
                    fileName = fileName.replace("+", "%20");//encode后替换空格  解决空格问题
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return ServiceResponse.createByErrorMessage("不支持浏览器编码");
            }
            res.setContentType("application/force-download");//设置强制下载
            res.setHeader("Content-Disposition", "attachment;filename=" + fileName);//设置文件名
            byte[] buff = new byte[1024];// 用来存储每次读取到的字节数组
            //创建输入流（读文件）输出流（写文件）
            BufferedInputStream bis = null;
            OutputStream os = null;
            try {
                os = res.getOutputStream();
                bis = new BufferedInputStream(new FileInputStream(file));
                int i = bis.read(buff);
                while (i != -1) {
                    os.write(buff, 0, buff.length);
                    os.flush();
                    i = bis.read(buff);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return ServiceResponse.createByErrorMessage("文件读取失败");
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (os != null){
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else {
            return ServiceResponse.createByErrorMessage("文件不存在！！！");
        }
        return ServiceResponse.createBySuccess();
    }

    /**
     * @Title: getBrowser
     * @Description: 判断客户端浏览器
     * @return String
     */
    private String getBrowser(HttpServletRequest request) {
        String UserAgent = request.getHeader("USER-AGENT").toLowerCase();
        if (UserAgent != null) {
            if (UserAgent.indexOf("msie") != -1)
                return "IE";
            if (UserAgent.indexOf("firefox") != -1)
                return "FF";
            if (UserAgent.indexOf("safari") != -1)
                return "SF";
        }
        return null;
    }

    /**
     * 将文件路径存放至数据库
     * @param url
     * @param projectId
     */
    private void saveFileUrl(String url, Integer projectId) {
        Projectfile projectfile = new Projectfile();
        projectfile.setProjectid(projectId);
        projectfile.setUrl(url);
        projectfileMapper.insert(projectfile);
    }
}
