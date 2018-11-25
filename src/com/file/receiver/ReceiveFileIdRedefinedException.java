package com.file.receiver;

/**
 * @ClassName: ReceiveFileIdRedefinedException
 * @Description: 文件fileId重复异常
 * @Author:
 * @Date: 2018/11/25 11:38
 * @Version: V1.0
 **/
public class ReceiveFileIdRedefinedException extends Exception {
    public ReceiveFileIdRedefinedException() {
    }

    public ReceiveFileIdRedefinedException(String message) {
        super(message);
    }
}
