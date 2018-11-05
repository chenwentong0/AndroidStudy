package com.topsports.mysocket.server;

import com.orhanobut.logger.Logger;
import com.topsports.mysocket.Packet;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

/**
 * Date 2018/10/18
 * Time 13:11
 *
 * @author wentong.chen
 */
public class SocketServer {
    private static String host;
    private static int port = 8765;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            //建立连接
            System.out.println("等待客户端建立了连接");
            Socket socket = serverSocket.accept();
            System.out.println("客户端与服务端建立了连接");
            ConnectThread connectThread = new ConnectThread(socket);
            connectThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 建立连接的线程
     */
    static class ConnectThread extends Thread {
        private BufferedSource mReader;
        private BufferedSink mWriter;
        private Socket mSocket;
        //是否执行线程
        private boolean mRunning = true;

        public ConnectThread(Socket socket) {
            mSocket = socket;
        }

        @Override
        public void run() {
            try {
                mReader = Okio.buffer(Okio.source(mSocket.getInputStream()));
                mWriter = Okio.buffer(Okio.sink(mSocket.getOutputStream()));
                while (mRunning) {
                    Packet packet = Packet.readPacket(mReader);
                    Logger.d(packet.toString());
                    Thread.sleep(2000);
                    String backStr = "来自服务器的问候";
                    Packet.writePacket(new Packet(backStr), mWriter);
                    mWriter.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (mReader != null) {
                        mReader.close();
                        mReader = null;
                    }
                    if (mWriter != null) {
                        mWriter.close();
                        mWriter = null;
                    }
                    if (mSocket != null) {
                        mSocket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
