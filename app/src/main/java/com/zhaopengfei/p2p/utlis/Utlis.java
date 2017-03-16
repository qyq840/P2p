package com.zhaopengfei.p2p.utlis;

import android.content.Context;
import android.os.Process;
import android.view.View;

/**
 * Created by admin on 2017/3/10.
 */

public class Utlis {

    public static Context getContext(){
        return MyApplication.getmContext();
    }

    public static View getView(int layoutid){
        return View.inflate(getContext(),layoutid,null);
    }

    public static int getColor(int color){
        return getContext().getResources().getColor(color);
    }

    public static String[] getStringArrary(int stringid){
        return getContext().getResources().getStringArray(stringid);
    }

    //与屏幕分辨率相关的
    public static int dp2px(int dp){
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5);

    }

    public static int px2dp(int px){
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);
    }

    public static void runOnUiThread(Runnable runnable) {
        if(MyApplication.getThreadid() == Process.myPid()) {
            runnable.run();
        }else {
            MyApplication.getHandler().post(runnable);
        }
    }
}
