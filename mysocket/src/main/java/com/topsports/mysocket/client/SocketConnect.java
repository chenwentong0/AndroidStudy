package com.topsports.mysocket.client;

import com.orhanobut.logger.Logger;
import com.topsports.mysocket.Packet;
import com.topsports.mysocket.common.PacketReader;
import com.topsports.mysocket.common.PacketWriter;

import java.io.IOException;
import java.net.Socket;

/**
 * Date 2018/10/18
 * Time 14:20
 *
 * @author wentong.chen
 */
public class SocketConnect {
    private String host;
    private int port;
    private long timeout;
    /**
     * 是否建立连接
     */
    private boolean hasConnect;
    private Socket mSocket;
    private PacketReader mPacketReader;
    private PacketWriter mPacketWriter;

    public SocketConnect() {

    }

    /**
     * 建立连接
     * @throws IOException
     */
    private void connect() throws IOException {
        mSocket = new Socket(host, port);
        mSocket.setKeepAlive(true);
        mSocket.setTcpNoDelay(true);
    }

    public void sendRequest(String content) throws IOException, InterruptedException {
        if (hasConnect && mPacketWriter != null) {
            mPacketWriter.sendPacket(new Packet(content));
        } else {

        }
    }

    /**
     * 开启连接
     */
    public void startup() throws IOException {
        if (hasConnect) {
            return;
        }
        connect();
        System.out.println("客户端与服务端建立连接");
        initReaderWriter();
        hasConnect = true;
    }

    private void initReaderWriter() throws IOException {
        mPacketReader = new PacketReader(mSocket.getInputStream());
        mPacketWriter = new PacketWriter(mSocket.getOutputStream());
    }

    /**
     * 关闭连接
     */
    public void shutdown() throws IOException {
        if (mPacketReader != null) {
            mPacketReader.shutdown();
        }
        if (mPacketWriter != null) {
            mPacketWriter.shutdown();
        }
        if (mSocket != null) {
            mSocket.close();
            mSocket = null;
        }
        hasConnect = false;
    }

    public static class Build {
        private String host;
        private int port;
        private long timeout;

        public Build() {

        }

        public Build host(String host) {
            this.host = host;
            return this;
        }

        public Build port(int port) {
            this.port = port;
            return this;
        }

        public Build timeout(long timeout) {
            this.timeout = timeout;
            return this;
        }

        public SocketConnect build() {
            SocketConnect socketConnect = new SocketConnect();
            socketConnect.host = this.host;
            socketConnect.port = this.port;
            socketConnect.timeout = this.timeout;
            return socketConnect;
        }
    }

}
