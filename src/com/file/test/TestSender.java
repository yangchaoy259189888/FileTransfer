package com.file.test;

import com.file.receiver.ReceiveFileIdRedefinedException;
import com.file.common.ReceiveFileMap;
import com.file.receiver.ReceiveFileModel;
import com.file.sender.ResourceSender;
import com.file.sender.ResourceSenderCenter;
import com.file.sender.SendFileModel;
import sun.security.util.Length;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TestSender
 * @Description:
 * @Author:
 * @Date: 2018/11/25 9:32
 * @Version: V1.0
 **/
public class TestSender {
    public static void main(String[] args) {
        try {
            String absoluteRootPath = "E:\\资料\\MEC\\java\\";
            String filePath = "MECjava\\201806060030100.mp4";
            int fileLength = 131500723;

            ReceiveFileModel receiveFileModel = new ReceiveFileModel();
            receiveFileModel.setAbsoluteRootPath(absoluteRootPath);
            receiveFileModel.setFilePath(filePath);
            receiveFileModel.setFileLength(fileLength);
            ReceiveFileMap receiveFileMap = new ReceiveFileMap();
            receiveFileMap.addReceiveFile(1, receiveFileModel);

            List<SendFileModel> sendFileModelList = new ArrayList<>();
            long offset = 0;

            while (fileLength > 0) {
                int len = fileLength > ResourceSender.BUFFER_SIZE
                        ? ResourceSender.BUFFER_SIZE : fileLength;

                SendFileModel sendFile = new SendFileModel();
                sendFile.setAbsoluteRootPath(absoluteRootPath);
                sendFile.setFilePath(filePath);
                sendFile.setOffset(offset);
                sendFile.setLength(len);

                sendFileModelList.add(sendFile);

                fileLength -= len;
                offset += len;
            }

            ResourceSenderCenter senderCenter = new ResourceSenderCenter(
                    "127.0.0.1", 54190);
            senderCenter.setSendFileMap(receiveFileMap);
            senderCenter.setSendFileList(sendFileModelList);
            senderCenter.startSendding();
        } catch (ReceiveFileIdRedefinedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
