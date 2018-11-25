package com.file.receiver;

import com.file.common.ReceiveFileMap;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName: ResourceReceiverServer
 * @Description: 文件接收的总控服务器,功能有：资源管理、发送端管理、两者关系管理
 * @Author:
 * @Date: 2018/11/25 9:33
 * @Version: V1.0
 **/
public class ResourceReceiverServer implements Runnable {
    private ServerSocket serverSocket;
    private int port;
    private volatile int senderCount;
    private volatile boolean continueWaittingSender;
    private ReceiveFileMap receiveFileMap;


    public ResourceReceiverServer() {
    }

    public ResourceReceiverServer(int port) {
        this.port = port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setSenderCount(int senderCount) {
        this.senderCount = senderCount;
    }

    public void setReceiveFileMap(ReceiveFileMap receiveFileMap) {
        this.receiveFileMap = receiveFileMap;
    }

    public ReceiveFileMap getReceiveFileMap() {
        return receiveFileMap;
    }

    public void startUpReceiverServer() throws IOException {
        /**
         * @param: []
         * @return: void
         * @date: 2018/11/25
         * @Description: 启动主控接收文件的服务器，并设置“执行等待的发送者”为true
         */
        serverSocket = new ServerSocket(port);
        continueWaittingSender = true;
        Thread thread = new Thread(this, "ResourceReceiverServer");
        synchronized (ResourceReceiverServer.class) {
            thread.start();
            try {
                ResourceReceiverServer.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
//        当前发送者个数
        int currentSenderCount = 0;

        synchronized (ResourceReceiverServer.class) {
            ResourceReceiverServer.class.notify();
        }

        while (currentSenderCount < senderCount && continueWaittingSender) {
            try {
//                侦听“发送客户端”请求，侦听一个，处理一个
                Socket sender = serverSocket.accept();
                new ResourceReceiver(sender, currentSenderCount);
                currentSenderCount++;
                // TODO 可能存在一些需要让资源接收者参与的某些工作
                // 连接到一个发送端
                // 开始启动资源接收过程
            } catch (IOException e) {
                e.printStackTrace();
                continueWaittingSender = false;
                // TODO 因为网络故障，造成无法再继续连接发送端
                // 应该进行“断点续传”前的资源信息查询和整合：
                // 即，对之前已经接收到的资源和还缺失的资源进行整合。
            }
        }
    }
}
