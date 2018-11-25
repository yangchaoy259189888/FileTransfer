package com.file.util;

/**
 * @ClassName: ByteAndString
 * @Description: 位运算操作的工具类
 * @Author:
 * @Date: 2018/11/25 9:33
 * @Version: V1.0
 **/

public class ByteAndString {
    public static final String hex = "0123456789ABCDEF";

    public static String toHex(byte[] buffer) {
        /**
         * @param: [buffer]
         * @return: java.lang.String
         * @date: 2018/11/25
         * @Description: 把一个字节转换为十六进制
         */
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < buffer.length; i++) {
            byte bv = buffer[i];
            result.append(i == 0 ? "" : ' ')
                    .append(hex.charAt((bv >> 4) & 0x0F))
                    .append(hex.charAt(bv & 0x0F));
        }

        return result.toString();
    }

    public static void setIntAt(byte[] buffer, int offset, int value) {
        /**
         * @param: [buffer, offset, value]
         * @return: void
         * @date: 2018/11/25
         * @Description: 按高低低高原则，把四字节int类型的value存入偏移量为offset的缓冲区buffer
         */
        buffer[offset + 0] = (byte) ((value >> 24) & 0x00FF);
        buffer[offset + 1] = (byte) ((value >> 16) & 0x00FF);
        buffer[offset + 2] = (byte) ((value >> 8) & 0x00FF);
        buffer[offset + 3] = (byte) (value & 0x00FF);
    }

    public static void setLongAt(byte[] buffer, int offset, long value) {
        /**
         * @param: [buffer, offset, value]
         * @return: void
         * @date: 2018/11/25
         * @Description: 按高低低高原则，把八字节long类型的value存入偏移量为offset的缓冲区buffer
         */
        buffer[offset + 0] = (byte) ((value >> 56) & 0x00FF);
        buffer[offset + 1] = (byte) ((value >> 48) & 0x00FF);
        buffer[offset + 2] = (byte) ((value >> 40) & 0x00FF);
        buffer[offset + 3] = (byte) ((value >> 32) & 0x00FF);
        buffer[offset + 4] = (byte) ((value >> 24) & 0x00FF);
        buffer[offset + 5] = (byte) ((value >> 16) & 0x00FF);
        buffer[offset + 6] = (byte) ((value >> 8) & 0x00FF);
        buffer[offset + 7] = (byte) (value & 0x00FF);
    }

    public static int getIntAt(byte[] buffer, int offset) {
        /**
         * @param: [buffer, offset]
         * @return: int
         * @date: 2018/11/25
         * @Description: 从偏移量为offset的缓冲区buffer中取出四字节int类型的value
         */
        int value = 0;

        value |= (buffer[offset + 0] << 24) & 0xFF000000;
        value |= (buffer[offset + 1] << 16) & 0x00FF0000;
        value |= (buffer[offset + 2] << 8) & 0x0000FF00;
        value |= (buffer[offset + 3]) & 0x000000FF;

        return value;
    }

    public static long getLongAt(byte[] buffer, int offset) {
        /**
         * @param: [buffer, offset]
         * @return: long
         * @date: 2018/11/25
         * @Description: 从偏移量为offset的缓冲区buffer中取出八字节long类型的value
         */
        long value = 0;

        value |= (long) (buffer[offset + 0] << 56) & 0xFF00000000000000L;
        value |= (long) (buffer[offset + 1] << 48) & 0x00FF000000000000L;
        value |= (long) (buffer[offset + 2] << 40) & 0x0000FF0000000000L;
        value |= (long) (buffer[offset + 3] << 32) & 0x000000FF00000000L;
        value |= (long) (buffer[offset + 4] << 24) & 0x00000000FF000000L;
        value |= (long) (buffer[offset + 5] << 16) & 0x0000000000FF0000L;
        value |= (long) (buffer[offset + 6] << 8) & 0x000000000000FF00L;
        value |= (long) (buffer[offset + 7]) & 0x00000000000000FFL;

        return value;
    }
}
