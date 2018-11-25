package com.file.receiver;

import com.file.common.CloseResource;
import com.file.util.ByteAndString;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @ClassName: ResourceReceiver
 * @Description: 文件“接收服务器”
 * @Author:
 * @Date: 2018/11/25 9:33
 * @Version: V1.0
 **/
public class ResourceReceiver implements Runnable {
    private Socket sender;
    private DataInputStream dis;
    private ResourceReceiverServer resourceReceiverServer;

    public ResourceReceiver(Socket sender, ResourceReceiverServer resourceReceiverServer,
                            int number) throws IOException {
        this.sender = sender;
        this.resourceReceiverServer = resourceReceiverServer;
        dis = new DataInputStream(sender.getInputStream());
        new Thread(this, "ResourceReceiver-" + number).start();
    }

    @Override
    public void run() {
        boolean finished = false;

        while (!finished) {
            try {
                finished = receiveOneBlock();
            } catch (IOException e) {
                // TODO 发送方异常掉线！
                e.printStackTrace();
                finished = true;
            }
        }

        CloseResource.close(dis, null, sender);
    }

    private boolean receiveOneBlock() throws IOException {
        /**
         * @param: []
         * @return: boolean
         * @date: 2018/11/25
         * @Description: 接收一个文件块
         */
        byte[] header = receiveBytes(16);
        // 需要从header这个字节数组中，分解出fileId，offset和length
        int fileId = ByteAndString.getIntAt(header, 0);
        long offset = ByteAndString.getLongAt(header, 4);
        int length = ByteAndString.getIntAt(header, 12);

        if (fileId == -1) {
            return true;
        }

//        把length字节，即文件的长度存入缓冲区
        byte[] buffer = receiveBytes(length);
        ResourceFileBlock resourceFileBlock = new ResourceFileBlock();
        resourceFileBlock.setFileId(fileId);
        resourceFileBlock.setOffset(offset);
        resourceFileBlock.setLength(length);
        resourceFileBlock.setContent(buffer);

        try {
            resourceFileBlock.writeFileBlock(resourceReceiverServer.getReceiveFileMap());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private byte[] receiveBytes(int length) throws IOException {
        /**
         * @param: [length]
         * @return: byte[]
         * @date: 2018/11/25
         * @Description: 给缓冲区buffer中读入length长度的字节
         */
        byte[] buffer = new byte[length];
        int realReceiveLength;
        int offset = 0;

        while (length > 0) {
            realReceiveLength = dis.read(buffer, offset, length);
            offset += realReceiveLength;
            length -= realReceiveLength;
        }

        return buffer;
    }
}
