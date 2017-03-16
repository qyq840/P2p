package com.zhaopengfei.p2p.fragment;

import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.zhaopengfei.p2p.R;
import com.zhaopengfei.p2p.adapter.InvestAllAdapter;
import com.zhaopengfei.p2p.adapter.InvestAllAdapter03;
import com.zhaopengfei.p2p.bean.InvestAllBean;
import com.zhaopengfei.p2p.utlis.AppNetConfig;

import butterknife.Bind;

/**
 * Created by admin on 2017/3/14.
 */

public class InvestAllFragment extends BaseFragment {
    @Bind(R.id.lv_invest_all)
    ListView lvInvestAll;

    private InvestAllAdapter adapter ;
    @Override
    protected int getLayoutid() {
        return R.layout.fragment_invest_all;
    }

    @Override
    protected void initData(String json) {

        InvestAllBean investAllBean = JSON.parseObject(json, InvestAllBean.class);




        //设置适配器
        //adapter = new InvestAllAdapter(investAllBean.getData());

        //  InvestAllAdapter1 adapter1 =new InvestAllAdapter1(investAllBean.getData());
          InvestAllAdapter03 adapter1 =new InvestAllAdapter03(investAllBean.getData());

        lvInvestAll.setAdapter(adapter1);



    }

    @Override
    protected void initlistener() {

    }

    @Override
    public String getChildUrl() {
        return AppNetConfig.PRODUCT;
    }

}
