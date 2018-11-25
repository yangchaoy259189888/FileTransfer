package com.file.sender;

import com.file.common.ReceiveFileMap;

import java.net.Socket;
import java.util.List;

/**
 * @ClassName: ResourceSenderCenter
 * @Description: 文件发送中心
 * @Author:
 * @Date: 2018/11/25 14:12
 * @Version: V1.0
 **/
public class ResourceSenderCenter {
    private String receiverIp;
    private int receiverPort;
    private Socket socket;
    private ReceiveFileMap sendFileMap;
    private List<SendFileModel> sendFileList;

    public ResourceSenderCenter(String receiverIp, int receiverPort) {
        this.receiverIp = receiverIp;
        this.receiverPort = receiverPort;
    }

    public void setSendFileMap(ReceiveFileMap sendFileMap) {
        this.sendFileMap = sendFileMap;
    }

    public void setSendFileList(List<SendFileModel> sendFileList) {
        this.sendFileList = sendFileList;
    }

    public void startSendding() throws Exception {
        if (sendFileMap == null || sendFileList == null) {
            System.out.println("搜都缪，弄撒咧！");
            return;
        }
        socket = new Socket(receiverIp, receiverPort);
        new ResourceSender(socket, sendFileList, sendFileMap);
    }
}
