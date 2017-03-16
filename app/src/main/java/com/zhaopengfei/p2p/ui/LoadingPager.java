package com.zhaopengfei.p2p.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.zhaopengfei.p2p.R;
import com.zhaopengfei.p2p.utlis.Utlis;

/**
 * Created by admin on 2017/3/13.
 */

public abstract class LoadingPager extends FrameLayout {
    private final Context context;
    private View loadingView;
    private View errorView;
    private View emptyView;
    private View sucessView;
    private LayoutParams params;
    private ResultState resultState;


    public LoadingPager(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public LoadingPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private int STATE_LOADING = 1; //加载中
    private int STATE_ERROR = 2; //加载失败
    private int STATE_SUCCESS = 3; //加载成功
    private int STATE_EMPTY = 4; //空

    private int current_state = STATE_LOADING;

    private void init() {

        //设置全屏属性
        params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        //加载布局
        if (loadingView == null) {
            loadingView = View.inflate(context, R.layout.page_loading, null);
            this.addView(loadingView, params);
        }
        if (errorView == null) {
            errorView = View.inflate(context, R.layout.page_error, null);
            this.addView(errorView, params);
        }
        if (emptyView == null) {
            emptyView = View.inflate(context, R.layout.page_empty, null);
            this.addView(emptyView, params);
        }

        //展示布局
        showSafeView();
    }

    private void showSafeView() {
        Utlis.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showView();
            }
        });
    }

    private void showView() {
        errorView.setVisibility(current_state == STATE_ERROR ? View.VISIBLE : View.INVISIBLE);
        //是否展示加载界面
        loadingView.setVisibility(
                current_state == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);
        //是否展示空页面
        emptyView.setVisibility(
                current_state == STATE_EMPTY ? View.VISIBLE : View.INVISIBLE);

        if (sucessView == null) {
            sucessView = View.inflate(context, getviewId(), null);
            this.addView(sucessView,params);
        }
        //是否展示成功页面
        sucessView.setVisibility(current_state == STATE_SUCCESS ? View.VISIBLE : View.INVISIBLE);

    }

    public void loadData() {
        //加载网络
        AsyncHttpClient httpClient = new AsyncHttpClient();
        String url = getUrl();

        if(TextUtils.isEmpty(url)) {
            //如果是空不加载网络
            resultState = ResultState.SUCCESS;
           loadImage();

            return;
        }
        
        httpClient.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                if (TextUtils.isEmpty(content)) {
                    resultState = ResultState.EMPTY;
                    resultState.setJson("");
                } else {
                    resultState = ResultState.SUCCESS;
                    resultState.setJson(content);
                }
                loadImage();
            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
                resultState = ResultState.ERROR;
                resultState.setJson(content);
                loadImage();
            }
        });
    }

    private void loadImage() {
        switch (resultState) {
            case ERROR:
                current_state = STATE_ERROR;
                break;
            case EMPTY:
                current_state = STATE_EMPTY; //根据枚举值来赋值相应的状态
                break;
            case SUCCESS:
                current_state = STATE_SUCCESS; //根据枚举值来赋值相应的状态
                break;
        }
        showSafeView();
        if (current_state == STATE_SUCCESS) {
            onSuccess(resultState, sucessView);
        }
    }

    protected abstract void onSuccess(ResultState resultState, View sucessView);

    public enum ResultState {
        //相当于是三个ResultState对象
        ERROR(2), SUCCESS(3), EMPTY(4);
        private int state;

        ResultState(int state) {
            this.state = state;
        }

        private String json;

        public void setJson(String json) {
            this.json = json;
        }

        public String getJson() {
            return json;
        }
    }

    public abstract int getviewId();

    protected abstract String getUrl();
}
