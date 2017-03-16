package com.zhaopengfei.p2p.utlis;

import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by admin on 2017/3/13.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private CrashHandler() {
    }

    private static CrashHandler crashHandler = new CrashHandler();

    public static CrashHandler getInstance(){
        return  crashHandler;
    }

    public void  init(){
        //把当前的类设置成默认的处理未捕获异常
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Log.i("uncaughtException", "uncaughtException: ");
        new Thread(){
            @Override
            public void run() {
                super.run();
                Looper.prepare();
                Toast.makeText(Utlis.getContext(), "aaa", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();

        collection(e);
        //销毁所有的activiyiy
        AppManager.getInstance().removeAll();
        //结束当前进错
        android.os.Process.killProcess( android.os.Process.myPid());

        //关闭虚拟机
        System.exit(0);
    }

    private void collection(Throwable e) {

    }
}
