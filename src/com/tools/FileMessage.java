package com.tools;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 功能描述：文件的信息
 *
 * @ClassName: FileMessage
 * @Author: Lily.
 * @Date: 2019/8/7 15:13
 * @Version: V1.0
 */
public class FileMessage {
    /**
     * 文件名字
     */
    private String name;

    /**
     * 文件绝对路径
     */
    private String absolutePath;

    /**
     * 文件相对路径
     */
    private String relativePath;


    public FileMessage() {
    }

    public FileMessage(String name, String absolutePath, String relativePath) {
        this.name = name;
        this.absolutePath = absolutePath;
        this.relativePath = relativePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }
}
