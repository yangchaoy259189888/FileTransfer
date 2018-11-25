package com.file.receiver;

/**
 * @ClassName: ReceiveFileModel
 * @Description: 接收文件的model，有文件路径，绝对路径，文件长度和是否接收过这四个属性
 * @Author:
 * @Date: 2018/11/25 11:06
 * @Version: V1.0
 **/
public class ReceiveFileModel {
    private String filePath;
    private String absoluteRootPath;
    private long fileLength;
    private boolean reveived;

    public ReceiveFileModel() {
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

    public long getFileLength() {
        return fileLength;
    }

    public void setFileLength(long fileLength) {
        this.fileLength = fileLength;
    }

    public boolean isReveived() {
        return reveived;
    }

    public void setReveived(boolean reveived) {
        this.reveived = reveived;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof ReceiveFileModel)) return false;

        ReceiveFileModel that = (ReceiveFileModel) object;

        if (fileLength != that.fileLength) return false;
        return filePath != null ? filePath.equals(that.filePath) : that.filePath == null;
    }

    public boolean equals(String fileName) {
        return filePath.equals(fileName);
    }

    @Override
    public int hashCode() {
        int result = filePath != null ? filePath.hashCode() : 0;
        result = 31 * result + (int) (fileLength ^ (fileLength >>> 32));
        return result;
    }
}
