package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.mmall.service.IFileService;
import com.mmall.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * File 服务
 *
 * @author facedamon facedamon
 * @create 2018/3/24
 */
@Service("iFileService")
public class FileServiceImpl implements IFileService{

    private static Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Override
    public String upload(MultipartFile file,String path){
        String fileName = file.getOriginalFilename();
        String fileExtensionName = fileName.substring(fileName.lastIndexOf("."));
        String uploadFileName = UUID.randomUUID().toString()+fileExtensionName;

        logger.info("开始上传文件，上传文件的文件名：{},文件路径：{},新文件名：{}",fileName,path,uploadFileName);

        File fileDir = new File(path);//文件夹
        if(!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }

        File targetFile = new File(path,uploadFileName);

        try {
            file.transferTo(targetFile);
            //上传至vsftp服务器
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            //删除本地文件
            targetFile.delete();
        } catch (IOException e) {
            logger.error("文件上传失败",e);
            return null;
        }

        return targetFile.getName();
    }
}
