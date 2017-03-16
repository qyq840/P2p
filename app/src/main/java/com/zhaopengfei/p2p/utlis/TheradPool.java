package com.zhaopengfei.p2p.utlis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by admin on 2017/3/13.
 */

public class TheradPool {

    private TheradPool() {
    }
    private  static  TheradPool theradPool = new TheradPool();

    public  static  TheradPool getInstance(){
        return  theradPool;
    }

    //线程池
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public  ExecutorService getGlobalThrad(){
        return executorService;
    }
}
