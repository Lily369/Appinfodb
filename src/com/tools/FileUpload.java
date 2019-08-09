package com.tools;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * 功能描述：
 *
 * @ClassName: FileUpload
 * @Author: Lily.
 * @Date: 2019/8/6 23:14
 * @Version: V1.0
 */
public class FileUpload {
    /**
     * 文件上传
     *
     * @param attachs  文件数组
     * @param request
     * @param path     绝对路径
     * @param fileSize 最大文件大小mb
     * @param fileType 文件类型 1：图片 2：apk文件
     * @return
     */
    public static FileMessage[] fileUpload(MultipartFile[] attachs, HttpServletRequest request, String path, int fileSize, int fileType) {
        //HttpServletRequest request;
        String errorInfo = null;
        boolean flag = true;
        FileMessage[] paths = new FileMessage[attachs.length];
        for (int i = 0; i < attachs.length; i++) {
            MultipartFile attach = attachs[i];//提取一个文件
            if (!attach.isEmpty()) {
                if (i == 0) {
                    errorInfo = "uploadFileError";
                } else if (i == 1) {
                    errorInfo = "uploadWpError";
                }
                String oldFileName = attach.getOriginalFilename();//原文件名
                String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
                int filesize = fileSize * 1024 * 1024;
                if (attach.getSize() > filesize) {//上传大小不得超过 filesize
                    request.setAttribute(errorInfo, " * 上传大小不得超过 " + fileSize + "mb");
                    flag = false;
                } else if (fileType == 1 && !(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
                        || prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg"))) {
                    System.out.println("文件格式错误");
                    return null;
                } else if (fileType == 2 && !prefix.equalsIgnoreCase("apk")) {
                    System.out.println("文件格式错误");
                    return null;
                }
                String fileName = null;
                if (fileType == 1) {
                    fileName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + "_Personal.jpg";
                } else if (fileType == 2) {
                    fileName = oldFileName;
                }
                File targetFile = new File(path, fileName);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                //保存
                try {
                    attach.transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute(errorInfo, " * 上传失败！");
                    flag = false;
                }
                //将绝对路径保存到数组中
                //paths[i] = path+File.separator+fileName;
                //相对路径
                String relativePath = "/statics/uploadfiles/" + fileName;
                FileMessage fileMessage = new FileMessage(fileName, path + File.separator + fileName, relativePath);
                paths[i] = fileMessage;
            }
        }
        return paths;
    }
}
