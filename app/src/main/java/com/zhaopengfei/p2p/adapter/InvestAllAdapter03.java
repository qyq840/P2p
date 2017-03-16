package com.zhaopengfei.p2p.adapter;

import com.zhaopengfei.p2p.bean.InvestAllBean;
import com.zhaopengfei.p2p.viewholder.BaseViewHolder;
import com.zhaopengfei.p2p.viewholder.Viewholder;

import java.util.List;

/**
 * Created by admin on 2017/3/14.
 */

public class InvestAllAdapter03 extends BaseInvestAllAdapter03<InvestAllBean.DataBean> {


    public InvestAllAdapter03(List<InvestAllBean.DataBean> list) {
        super(list);
    }

    @Override
    public BaseViewHolder getviewHolder() {
        return new Viewholder();
    }
}
