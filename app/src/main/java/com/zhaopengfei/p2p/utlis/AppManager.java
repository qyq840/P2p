package com.zhaopengfei.p2p.utlis;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by admin on 2017/3/13.
 */

public class AppManager {
    private AppManager(){};

    private  static  AppManager appManager =new AppManager();
    public static  AppManager getInstance(){
        return  appManager;
    }

    private Stack<Activity> stack =new Stack<>();

    public  void addActivity(Activity activity){
        if(activity !=null) {
            stack.add(activity);

            //初始化未捕获的异常
            CrashHandler.getInstance().init();
        }
    }
    public void  removeActivity(Activity activity){
        //校验
        if(activity !=null) {
            for (int i =stack.size()-1 ; i>=0; i--){
                Activity currActivity =stack.get(i);
                if(currActivity.getClass().equals(activity.getClass())) {
                    currActivity.finish();
                    stack.remove(currActivity);
                }
            }
        }

    }
    public void removeAll(){
        for (int i = stack.size()-1;i>=0; i--){
            Activity curActivity =stack.get(i);
            curActivity.finish();;
            stack.remove(curActivity);
        }
    }

    public void removeCurrentActivity(){
        Activity activity = stack.lastElement();
        activity.finish();
        stack.remove(activity);
    }

     public  int getStackSize(){
         return  stack.size();
     }

    public  void remove(Activity activity){
        if(activity !=null) {
            for (int i =stack.size(); i>=0;i--){
                Activity currentActivity =stack.get(i);
                if(currentActivity == activity) {
                    stack.remove(currentActivity);
                }
            }
        }

    }
}
