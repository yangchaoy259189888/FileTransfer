package com.file.sender;

/**
 * @ClassName: SendFileModel
 * @Description: 发送文件的model类，有文件绝对路径，相对路径，偏移量和长度四个属性
 * @Author:
 * @Date: 2018/11/25 12:58
 * @Version: V1.0
 **/
public class SendFileModel {
    private String filePath;
    private String absoluteRootPath;
    private long offset;
    private int length;

    public SendFileModel() {
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getAbsoluteRootPath() {
        return absoluteRootPath;
    }

    public void setAbsoluteRootPath(String absoluteRootPath) {
        this.absoluteRootPath = absoluteRootPath;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
