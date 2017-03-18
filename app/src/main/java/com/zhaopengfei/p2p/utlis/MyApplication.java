package com.zhaopengfei.p2p.utlis;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;

/**
 * Created by admin on 2017/3/13.
 */

public class MyApplication extends Application{

    private static Context mContext;
    private static Thread   mainThread;
    private static int threadid;
    private static Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext= this;

        threadid =android.os.Process.myPid();
        handler = new Handler();
//        CrashHandler.getInstance().init();
    }

    public static Context getmContext() {
        return mContext;
    }

    public static Thread getMainThread() {
        return mainThread;
    }

    public static int getThreadid() {
        return threadid;
    }

    public static Handler getHandler() {
        return handler;
    }



    // 生命周期
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
