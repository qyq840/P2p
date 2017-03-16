package com.zhaopengfei.p2p.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhaopengfei.p2p.R;
import com.zhaopengfei.p2p.bean.InvestAllBean;
import com.zhaopengfei.p2p.ui.MyProgress;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by admin on 2017/3/14.
 */

public class InvestAllAdapter extends BaseAdapter {

    private List<InvestAllBean.DataBean> list = new ArrayList<>();

    public InvestAllAdapter(List<InvestAllBean.DataBean> list) {
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
        ViewHolder viewHolder =null;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.adapter_invest_all, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        InvestAllBean.DataBean dataBean = list.get(position);
        viewHolder.pName.setText(dataBean.getName());
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.p_name)
        TextView pName;
        @Bind(R.id.p_money)
        TextView pMoney;
        @Bind(R.id.p_yearlv)
        TextView pYearlv;
        @Bind(R.id.p_suodingdays)
        TextView pSuodingdays;
        @Bind(R.id.p_minzouzi)
        TextView pMinzouzi;
        @Bind(R.id.p_minnum)
        TextView pMinnum;
        @Bind(R.id.p_progresss)
        MyProgress pProgresss;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
