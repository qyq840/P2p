package com.zhaopengfei.p2p.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.zhaopengfei.p2p.viewholder.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/3/14.
 */

public abstract class BaseInvestAllAdapter03<T> extends BaseAdapter {

    private List<T> list = new ArrayList<>();

    public BaseInvestAllAdapter03(List<T> list) {
        if (list != null && list.size() > 0) {
            this.list.clear();
            this.list.addAll(list);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = getviewHolder();
        } else {
            viewHolder = (BaseViewHolder) convertView.getTag();
        }
        T t = list.get(position);
        viewHolder. setData(t);
        return viewHolder.getView();
    }
    public abstract BaseViewHolder getviewHolder();
}
