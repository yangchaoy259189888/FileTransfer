package com.file.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @ClassName: CloseResource
 * @Description: 关闭dis，dos，socket连接
 * @Author:
 * @Date: 2018/11/25 13:09
 * @Version: V1.0
 **/
public class CloseResource {
    public static void close(DataInputStream dis, DataOutputStream dos, Socket socket) {
        /**
         * @param: [dis, dos, socket]
         * @return: void
         * @date: 2018/11/25
         * @Description: 关闭dis，dos，socket连接
         */
        if (dis != null) try {
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            dis = null;
        }
        if (dos != null) {
            try {
                dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                dos = null;
            }
        }

        if (socket != null && !socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                socket = null;
            }
        }
    }
}
