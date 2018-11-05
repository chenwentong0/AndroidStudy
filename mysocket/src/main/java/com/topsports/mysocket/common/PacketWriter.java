package com.topsports.mysocket.common;

import com.topsports.mysocket.Packet;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.concurrent.LinkedBlockingDeque;

import okio.BufferedSink;
import okio.Okio;

/**
 * Date 2018/10/18
 * Time 14:19
 *
 * @author wentong.chen
 * packet的写流封装
 */
public class PacketWriter {
    private BufferedSink mWriter;
    private Thread mWriterThread;
    private boolean mRunning;

    private LinkedBlockingDeque<Packet> mQueue = new LinkedBlockingDeque<>(500);


    public PacketWriter(OutputStream outputStream) {
        mWriter = Okio.buffer(Okio.sink(outputStream));
        startup();
    }

    public void sendPacket(Packet packet) throws IOException, InterruptedException {
        mQueue.put(packet);
    }

    public void startup() {
        mRunning = true;
        if (mWriterThread == null || !mWriterThread.isAlive()) {
            mWriterThread = new WriterThrad();
            mWriterThread.setName("writer thread");
            //是否是守护线程
            mWriterThread.setDaemon(true);
            mWriterThread.start();
        }
    }

    public void shutdown() {
        mRunning = false;
        if (mWriterThread != null && mWriterThread.isAlive()) {
            mWriterThread.interrupt();
            mWriterThread = null;
        }
    }

    class WriterThrad extends Thread {
        @Override
        public void run() {
            try {
                while (mRunning) {
                    //如果没有请求要发送，线程会阻塞在这里
                    Packet packet = mQueue.take();
                    if (packet != null) {
                        Packet.writePacket(packet, mWriter);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                mRunning = false;
                if (mWriter != null) {
                    try {
                        mWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
