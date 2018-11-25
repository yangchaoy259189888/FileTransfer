package com.file.receiver;

import com.file.common.ReceiveFileMap;

import java.io.RandomAccessFile;

/**
 * @ClassName: ResourceFileBlock
 * @Description:
 * @Author:
 * @Date: 2018/11/25 12:27
 * @Version: V1.0
 **/
public class ResourceFileBlock {
    private int fileId;
    private long offset;
    private int length;
    private byte[] content;

    public ResourceFileBlock() {
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
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

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public void writeFileBlock(ReceiveFileMap receiveFileMap) throws Exception {
        ReceiveFileModel receiveFileModel = receiveFileMap.getReceiveFile(fileId);
        if (receiveFileModel == null) {
            throw new Exception("文件号[" + fileId + "]不存在！");
        }

        String absoluteRootPath = receiveFileModel.getAbsoluteRootPath();
        String filePath = receiveFileModel.getFilePath();
        RandomAccessFile raf = new RandomAccessFile(absoluteRootPath + filePath, "rws");
        raf.seek(offset);
        raf.write(content);

        raf.close();
    }
}
