package com.topsports.mysocket.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Date 2018/10/18
 * Time 14:04
 *
 * @author wentong.chen
 */
public class SocketClient {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8765;

    public static void main(String[] args) {
        try {
            //建立连接
//            SocketConnect socketConnect = new SocketConnect.Build().host(HOST).port(PORT)
//                    .build();
//            socketConnect.startup();
//            socketConnect.sendRequest("这是客服端向服务器发送的消息");
            final Socket socket = new Socket(HOST, PORT);

            new Thread() {
                @Override
                public void run() {
                    try {
                        InputStream inputStream = socket.getInputStream();
                        while (true) {
                            byte[] bytes = new byte[1024 * 8];
                            inputStream.read(bytes);
                            System.out.println(new String(bytes));
                            Thread.sleep(500);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            new Thread() {
                @Override
                public void run() {
                    try {
                        OutputStream outputStream = socket.getOutputStream();
                        int index = 0;
                        while (true) {
                            index ++;
                            outputStream.write(("每隔1秒向服务器发送消息" + index).getBytes());
                            outputStream.flush();
                            Thread.sleep(500);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        } catch (IOException e) {
            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
        }
    }
}
