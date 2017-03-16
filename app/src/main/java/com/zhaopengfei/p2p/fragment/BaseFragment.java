package com.zhaopengfei.p2p.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhaopengfei.p2p.ui.LoadingPager;

import butterknife.ButterKnife;

/**
 * Created by admin on 2017/3/13.
 */

public abstract class BaseFragment extends Fragment {
    private LoadingPager loadingPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        loadingPager = new LoadingPager(getActivity()) {
            @Override
            protected void onSuccess(ResultState resultState, View sucessView) {
                ButterKnife.bind(BaseFragment.this, sucessView);
                initData(resultState.getJson());
            }

            @Override
            public int getviewId() {
                return getLayoutid();
            }

            @Override
            protected String getUrl() {
                return getChildUrl();
            }
        };

        return loadingPager;
    }




    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //初始化数据
        loadingPager.loadData();
    }

    protected abstract int getLayoutid();

    protected abstract void initData(String json);

    protected abstract void initlistener();
    //每一个fragment返回的地址
    public abstract String getChildUrl();

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this); //解绑
    }
}
