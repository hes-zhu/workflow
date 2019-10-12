package com.snn.workflow.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

/**
 * @className FtpUtil
 * @Author SNN
 * @Date 2019/9/24 16:58
 **/
public class FTPUtil {
    private static final Logger logger = LoggerFactory.getLogger(FTPUtil.class);

    private static String ftpIp = PropertiesUtil.getProperty("ftp.server.ip");
    private static String ftpUser = PropertiesUtil.getProperty("ftp.user");
    private static String ftpPass = PropertiesUtil.getProperty("ftp.pass");

    public FTPUtil(String ip, int port, String user, String pwd) {
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.pwd = pwd;
    }

    public static boolean uploadFile(List<File> fileList) {
        FTPUtil ftpUtil = new FTPUtil(ftpIp, 21, ftpUser, ftpPass);
        logger.info("开始连接ftp服务器，开始上传文件：");
        boolean result = ftpUtil.uploadFile("img", fileList);
        logger.info("断开ftp服务器, 结束上传，上传结果:{}", result);
        return result;
    }

    public static byte[] downFile(String filePath, String fileName) {
        FTPUtil ftpUtil = new FTPUtil(ftpIp, 21, ftpUser, ftpPass);
        logger.info("开始连接ftp服务器，开始下载文件：");
        byte[] result = ftpUtil.downFiles(filePath, fileName);
        logger.info("断开连接ftp服务器, 结束下载.");
        return result;
    }

    private boolean uploadFile(String remotePath, List<File> fileList) {
        boolean uploaded = true;
        FileInputStream fileInputStream = null;
        //连接ftp服务器
        if(connectServer(this.ip, this.port, this.user, this.pwd)) {
            try {
                ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                for(File fileItem : fileList) {
                    fileInputStream = new FileInputStream(fileItem);
                    ftpClient.storeFile(fileItem.getName(), fileInputStream);
                }
            } catch (IOException e) {
                logger.error("上传文件异常", e);
                uploaded = false;
                e.printStackTrace();
            } finally {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    logger.error("文件输入流(fileInputStream)关闭异常", e);
                    e.printStackTrace();
                }
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    logger.error("ftp连接(disconnect)关闭异常", e);
                    e.printStackTrace();
                }
            }
        } else {
            return uploaded=false;
        }
        return uploaded;
    }

    private byte[] downFiles(String filePath, String fileName) {
        InputStream in = null;
        connectServer(this.ip, this.port, this.user, this.pwd);
        try {
            ftpClient.changeWorkingDirectory(filePath);
            FTPFile[] fs = ftpClient.listFiles();
            // 遍历所有文件，找到指定的文件
            for (FTPFile file : fs) {
                if (file.getName().equals(fileName)) {
                    File localFile = new File("C:\\Users\\SNN\\SNN\\Desktop\\"+file.getName());
                    OutputStream is = new FileOutputStream(localFile);
                    Boolean isSuccess =  ftpClient.retrieveFile(file.getName(), is);

                    System.out.println(isSuccess);

                    ftpClient.setBufferSize(1024);
                    ftpClient.setControlEncoding("UTF-8");
                    in = ftpClient.retrieveFileStream(fileName);
                    byte[] b = new byte[in.available()];
                    return b;
                }
            }
        } catch (Exception e) {
            logger.error("下载文件异常", e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private Boolean connectServer(String ip, int port, String user, String pwd) {
        boolean isSuccess = false;
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip);
            //ftpClient.enterLocalActiveMode();    //主动模式
            ftpClient.enterLocalPassiveMode();
            isSuccess = ftpClient.login(user, pwd);
            if(isSuccess) {
                logger.info("FTP服务器登录成功");
            } else {
                logger.error("FTP服务器登录失败");
            }
        } catch (IOException e) {
            logger.error("连接FTP服务器异常", e);
        }
        return isSuccess;
    }

    private String ip;
    private int port;
    private String user;
    private String pwd;
    private FTPClient ftpClient;

    public static String getFtpIp() {
        return ftpIp;
    }

    public static void setFtpIp(String ftpIp) {
        FTPUtil.ftpIp = ftpIp;
    }

    public static String getFtpUser() {
        return ftpUser;
    }

    public static void setFtpUser(String ftpUser) {
        FTPUtil.ftpUser = ftpUser;
    }

    public static String getFtpPass() {
        return ftpPass;
    }

    public static void setFtpPass(String ftpPass) {
        FTPUtil.ftpPass = ftpPass;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }
}
