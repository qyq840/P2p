package com.zhaopengfei.p2p.adapter;

import android.view.View;
import android.widget.TextView;

import com.zhaopengfei.p2p.R;
import com.zhaopengfei.p2p.bean.InvestAllBean;
import com.zhaopengfei.p2p.utlis.Utlis;

import java.util.List;

/**
 * Created by admin on 2017/3/14.
 */

public class InvestAllAdapter2 extends  BaseInvestAllAdapter02<InvestAllBean.DataBean> {

    public InvestAllAdapter2(List<InvestAllBean.DataBean> list) {
        super(list);
    }

    @Override
    protected View InitView() {
        return Utlis.getView(R.layout.adapter_invest_all);
    }

    @Override
    public void setData(InvestAllBean.DataBean dataBean, View convertView) {
        TextView view = (TextView) convertView.findViewById(R.id.p_name);
        view.setText(dataBean.getName());
    }
}
