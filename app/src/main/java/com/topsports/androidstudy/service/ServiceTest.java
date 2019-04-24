package com.topsports.androidstudy.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.FileDescriptor;

/**
 * Date 2019/3/17
 * Time 21:36
 *
 * @author wentong.chen
 */
public class ServiceTest extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder() {
            @Nullable
            @Override
            public String getInterfaceDescriptor() throws RemoteException {
                return null;
            }

            @Override
            public boolean pingBinder() {
                return false;
            }

            @Override
            public boolean isBinderAlive() {
                return false;
            }

            @Nullable
            @Override
            public IInterface queryLocalInterface(@NonNull String descriptor) {
                return null;
            }

            @Override
            public void dump(@NonNull FileDescriptor fd, @Nullable String[] args) throws RemoteException {

            }

            @Override
            public void dumpAsync(@NonNull FileDescriptor fd, @Nullable String[] args) throws RemoteException {

            }

            @Override
            public boolean transact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
                return false;
            }

            @Override
            public void linkToDeath(@NonNull DeathRecipient recipient, int flags) throws RemoteException {

            }

            @Override
            public boolean unlinkToDeath(@NonNull DeathRecipient recipient, int flags) {
                return false;
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //当service被kill之后需要自动重启，将返回值设置成这个
//        startForeground(2,null);将Service设置成前台应用
        return START_STICKY;
//        return super.onStartCommand(intent, flags, startId);
    }

    public abstract class MyBinder implements IBinder {

    }
}
