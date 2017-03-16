package com.zhaopengfei.p2p.utlis;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.Map;

/**
 * Created by admin on 2017/3/13.
 */

public class LoadNet {

    public  static void  getDataPost(String url, final LoadNetHttp http){
        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.post(url,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                if(http != null) {
                    http.success(content);
                }
            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);

                if(http !=null) {
                    http.failure(content);
                }
            }
        });
    }

 

    public  static void  getDataPost(String url, Map<String,String> map, final LoadNetHttp http){
        AsyncHttpClient httpClient = new AsyncHttpClient();
        if(map !=null && !map.isEmpty() ) {
            RequestParams params = new RequestParams();
            for (String key :map.keySet()){
                String value =map.get(key);
                params.put(key,value);


            }
            httpClient.post(url,params,new AsyncHttpResponseHandler(){
                @Override
                public void onSuccess(String content) {
                    super.onSuccess(content);
                    if(http != null) {
                        http.success(content);
                    }
                }

                @Override
                public void onFailure(Throwable error, String content) {
                    super.onFailure(error, content);

                    if(http !=null) {
                        http.failure(content);
                    }
                }
            });

        }else {
             getDataPost(url,http);
        }
        

    }
}