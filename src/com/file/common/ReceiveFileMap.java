package com.file.common;

import com.file.receiver.ReceiveFileIdRedefinedException;
import com.file.receiver.ReceiveFileModel;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ReceiveFileMap
 * @Description: 根据文件的id和ReceiveFileModel形成键值对，构建一个HashMap，这样，就能根据fileId唯一的查找
 * 到一个文件的ReceiveFileModel了。
 * @Author:
 * @Date: 2018/11/25 11:05
 * @Version: V1.0
 **/
public class ReceiveFileMap {
    private Map<Integer, ReceiveFileModel> fileMap;

    public ReceiveFileMap() {
        fileMap = new HashMap<>();
    }

    public int getFileId(String fileName) {
        /**
         * @param: [fileName]
         * @return: int
         * @date: 2018/11/25
         * @Description: 根据fileName,得到文件的id
         */
        for (int fileId : fileMap.keySet()) {
            ReceiveFileModel receiveFileModel = fileMap.get(fileId);
//            在equal方法中，根据fileName文件名，把文件强转为了ReceiveFileModel类型
            if (receiveFileModel.equals(fileName)) {
                return fileId;
            }
        }

        return -1;
    }

    public void addReceiveFile(Integer fileId, ReceiveFileModel receiveFileModel) throws ReceiveFileIdRedefinedException {
        /**
         * @param: [fileId, receiveFileModel]
         * @return: void
         * @date: 2018/11/25
         * @Description: 添加一个文件对应的ReceiveFileModel到fileMap中;若文件fileId重复,则抛出异常
         */
        ReceiveFileModel orgFile = fileMap.get(fileId);
        if (orgFile != null) {
            throw new ReceiveFileIdRedefinedException("文件编号fileId【" + fileId + "】重复");
        }
        fileMap.put(fileId, receiveFileModel);
    }

    public ReceiveFileModel getReceiveFile(Integer fileId) {
        /**
         * @param: [fileId]
         * @return: com.file.receiver.ReceiveFileModel
         * @date: 2018/11/25
         * @Description: 根据fileId，得到一个文件的ReceiveFileModel
         */
        return fileMap.get(fileId);
    }
}