package com.topsports.mysocket;

import java.io.IOException;
import java.nio.charset.Charset;

import okio.BufferedSink;
import okio.BufferedSource;

/**
 * Date 2018/10/18
 * Time 13:39
 *
 * @author wentong.chen
 * 发送或接收的报文
 */
public class Packet {

    public Packet() {}

    public Packet(String content) {
        this.content = content;
        if (content != null) {
            this.length = content.getBytes().length + 4;
        }
    }
    /**
     * 报文长度
     */
    private int length;
    private String content;

    /**
     * 将流的数据读取到packet中
     * @param reader
     * @return
     * @throws IOException
     */
    public static Packet readPacket(BufferedSource reader) throws IOException {
        Packet packet = new Packet("测试数据");
//        packet.length = reader.readInt();
//        packet.content = reader.readString(packet.length - 4, Charset.forName("utf-8"));
        return packet;
    }

    /**
     * 将数据写入流中
     * @param packet
     * @param writer
     * @throws IOException
     */
    public static void writePacket(Packet packet, BufferedSink writer) throws IOException {
        writer.writeInt(packet.length);
        writer.writeString(packet.content, Charset.forName("utf-8"));
        writer.flush();
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Packet{" +
                "length=" + length +
                ", content='" + content + '\'' +
                '}';
    }
}
