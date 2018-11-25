package com.file.sender;

import com.file.common.CloseResource;
import com.file.common.ReceiveFileMap;
import com.file.util.ByteAndString;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.util.List;

/**
 * @ClassName: ResourceSender
 * @Description: 文件发送客户端
 * @Author:
 * @Date: 2018/11/25 9:34
 * @Version: V1.0
 **/
public class ResourceSender implements Runnable {
    public static final int BUFFER_SIZE = 1 << 24;  // 32KB

    private Socket socket;
    private DataOutputStream dos;
    private List<SendFileModel> sendFileModelList;
    private ReceiveFileMap sendFileMap;

    public ResourceSender(Socket socket, List<SendFileModel> sendFileModelList,
                          ReceiveFileMap sendFileMap) throws IOException {
        this.socket = socket;
        this.sendFileModelList = sendFileModelList;
        this.sendFileMap = sendFileMap;
        dos = new DataOutputStream(socket.getOutputStream());
        new Thread(this, "ResourceSender").start();
    }

    @Override
    public void run() {
        if (sendFileModelList == null) {
            return;
        }

        byte[] header = new byte[16];
        byte[] buffer = new byte[BUFFER_SIZE];

        for (SendFileModel sendFileModel : sendFileModelList) {
            String absolutePath = sendFileModel.getAbsoluteRootPath();
            String filePath = sendFileModel.getFilePath();
            int fileId = sendFileMap.getFileId(filePath);
            long offset = sendFileModel.getOffset();
            int length = sendFileModel.getLength();

            ByteAndString.setIntAt(header, 0, fileId);
            ByteAndString.setLongAt(header, 4, offset);
            ByteAndString.setIntAt(header, 12, length);

            try {
//                先发送文件头部header的16字节
                dos.write(header);
                dos.flush();

                RandomAccessFile raf = new RandomAccessFile(absolutePath + filePath,
                        "r");
                raf.seek(offset);
                int len;
                int realReadLen;

//                文件没发完
                while (length > 0) {
                    realReadLen = length >= BUFFER_SIZE ? BUFFER_SIZE : length;
                    len = raf.read(buffer, 0, realReadLen);
//                    从文件中读了多少，那就得发多少，每次读BUFFER_SIZE个长度
                    dos.write(buffer, 0, len);
                    length -= len;
                }

                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
                CloseResource.close(null, dos, socket);
                break;
            }
        }

        ByteAndString.setIntAt(header, 0, -1);
        ByteAndString.setLongAt(header, 4, 0);
        ByteAndString.setIntAt(header, 12, 0);
        try {
            if (dos != null) {
                dos.write(header);
                dos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        CloseResource.close(null, dos, socket);
    }
}
