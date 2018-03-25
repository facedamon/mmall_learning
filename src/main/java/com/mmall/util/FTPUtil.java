package com.mmall.util;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Ftp 服务
 *
 * @author facedamon facedamon
 * @create 2018/3/24
 */
public class FTPUtil {
    private static Logger logger = LoggerFactory.getLogger(FTPUtil.class);

    //初始化
    private static String ftpIp = PropertiesUtil.getProperty("ftp.server.ip");
    private static String ftpUser = PropertiesUtil.getProperty("ftp.user");
    private static String ftpPwd = PropertiesUtil.getProperty("ftp.pass");

    public static boolean uploadFile(List<File> files) throws IOException {
        FTPUtil ftpUtil = new FTPUtil(ftpIp,21,ftpUser,ftpPwd);
        logger.info("开始连接ftp服务器");
        boolean result = ftpUtil.uploadFile(files,"img");
        logger.info("上传结束，上传结果{}",result);
        return result;
    }

    private boolean uploadFile(List<File> files,String remotepath) throws IOException {
        boolean uploaded = true;
        FileInputStream fis = null;
        //连接ftp服务
        if(connectionServer(this.ip,this.port,this.user,this.pwd)){
            try {
                ftpClient.changeWorkingDirectory(remotepath);
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();

                for(File fileItem : files){
                    fis = new FileInputStream(fileItem);
                    ftpClient.storeFile(fileItem.getName(),fis);
                }
            } catch (IOException e) {
                logger.error("上传文件异常",e);
                uploaded = false;
            }finally {
                fis.close();
                ftpClient.disconnect();
            }

        }

        return uploaded;
    }

    private boolean connectionServer(String ip, int port, String user, String pwd){
        boolean isCoinectioned = false;
        ftpClient = new FTPClient();

        try {
            ftpClient.connect(ip,port);
            isCoinectioned = ftpClient.login(user,pwd);
        } catch (IOException e) {
            logger.error("连接ftp服务失败",e);
        }

        return isCoinectioned;
    }

    private String ip;
    private int port;
    private String user;
    private String pwd;
    private FTPClient ftpClient;

    public FTPUtil(String ip, int port, String user, String pwd) {
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.pwd = pwd;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        FTPUtil.logger = logger;
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
}
