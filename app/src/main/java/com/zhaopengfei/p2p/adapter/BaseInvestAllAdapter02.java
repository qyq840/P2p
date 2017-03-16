package com.zhaopengfei.p2p.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by admin on 2017/3/14.
 */

public abstract class BaseInvestAllAdapter02<T> extends BaseAdapter {

    private List<T> list = new ArrayList<>();

    public BaseInvestAllAdapter02(List<T> list) {
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = InitView();
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        T t = list.get(position);
        setData(t, convertView);
        return convertView;
    }

    protected abstract View InitView();

    public abstract void setData(T t, View convertView);

    static class ViewHolder {

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }
}
