package com.zhaopengfei.p2p.viewholder;

import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by admin on 2017/3/14.
 */

public abstract class BaseViewHolder<T> {
    private View view ;
    private T t;

    public View getView() {
        return view;
    }

    public T getT() {
        return t;
    }

    public BaseViewHolder() {
        view = initView();
        ButterKnife.bind(this,view);
        view.setTag(this);
    }

    public abstract View initView() ;


    public  void setData(T data) {
        this.t = data;
        setchildData();
    }

    protected abstract void setchildData();
}
