package com.topsports.mysocket.common;

import android.text.TextUtils;

import com.orhanobut.logger.Logger;
import com.topsports.mysocket.Packet;

import java.io.IOException;
import java.io.InputStream;

import okio.BufferedSource;
import okio.Okio;

/**
 * Date 2018/10/18
 * Time 14:19
 *
 * @author wentong.chen
 */
public class PacketReader {
    private BufferedSource mReader;
    private Thread mReaderThread;
    private boolean mRunning;

    public PacketReader(InputStream inputStream) {
        mReader = Okio.buffer(Okio.source(inputStream));
        startup();
    }

    public void startup() {
        if (mReaderThread == null || !mReaderThread.isAlive()) {
            mReaderThread = new ReaderThread();
            mReaderThread.setName("reader thread");
            mReaderThread.setDaemon(true);
            mReaderThread.start();
        }
        mRunning = true;
    }

    public void shutdown() {
        mRunning = false;
        if (mReaderThread != null && mReaderThread.isAlive()) {
            mReaderThread.interrupt();
            mReaderThread = null;
        }
    }

    class ReaderThread extends Thread {
        @Override
        public void run() {
            try {
                while (mRunning) {
                    Packet packet = Packet.readPacket(mReader);
                    if (packet != null && TextUtils.isEmpty(packet.getContent())) {
                        System.out.println("收到服务器的消息：" + packet.getContent());
                    }
                    Thread.sleep(500);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                mRunning = false;
                if (mReader != null) {
                    try {
                        mReader.close();
                        mReader = null;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}
