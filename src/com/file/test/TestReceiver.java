package com.file.test;

import com.file.receiver.ReceiveFileIdRedefinedException;
import com.file.common.ReceiveFileMap;
import com.file.receiver.ReceiveFileModel;
import com.file.receiver.ResourceReceiverServer;

import java.io.IOException;

/**
 * @ClassName: TestReceiver
 * @Description:
 * @Author:
 * @Date: 2018/11/25 9:31
 * @Version: V1.0
 **/
public class TestReceiver {
    public static void main(String[] args) {
        ResourceReceiverServer rrs = new ResourceReceiverServer(54190);
        rrs.setSenderCount(1);
        try {
            rrs.startUpReceiverServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        String absoluteRootPath = "E:\\迅雷下载";
//        String filePath = "201806060030100.mp4";
//        int fileLength = 131500723;
//
//        ReceiveFileModel receiveFileModel = new ReceiveFileModel();
//        receiveFileModel.setAbsoluteRootPath(absoluteRootPath);
//        receiveFileModel.setFilePath(filePath);
//        receiveFileModel.setFileLength(fileLength);
//
//        try {
//            ReceiveFileMap receiveFileMap = new ReceiveFileMap();
////            给receiveFileMap中添加一个fileId为1的receiveFileModel
//            receiveFileMap.addReceiveFile(1, receiveFileModel);
//
////            创建文件接收的总控服务器
//            ResourceReceiverServer rrs = new ResourceReceiverServer(54190);
////            让总控接收服务器来管理这个receiveFileMap
//            rrs.setReceiveFileMap(receiveFileMap);
////            暂定为一个发送端
//            rrs.setSenderCount(1);
//            rrs.startUpReceiverServer();
//        } catch (ReceiveFileIdRedefinedException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
