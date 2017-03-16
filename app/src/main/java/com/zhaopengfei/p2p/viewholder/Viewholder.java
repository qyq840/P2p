package com.zhaopengfei.p2p.viewholder;

import android.view.View;
import android.widget.TextView;

import com.zhaopengfei.p2p.R;
import com.zhaopengfei.p2p.bean.InvestAllBean;
import com.zhaopengfei.p2p.ui.MyProgress;
import com.zhaopengfei.p2p.utlis.Utlis;

import butterknife.Bind;

/**
 * Created by admin on 2017/3/14.
 */
public class Viewholder extends BaseViewHolder<InvestAllBean.DataBean> {
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

    @Override
    public View initView() {
        return Utlis.getView(R.layout.adapter_invest_all);

    }

    @Override
    protected void setchildData() {

        InvestAllBean.DataBean dataBean = getT();
        pName.setText(dataBean.getName());
        pMoney.setText(dataBean.getMoney());
        pYearlv.setText(dataBean.getYearRate());
        pSuodingdays.setText(dataBean.getSuodingDays());
        pMinzouzi.setText(dataBean.getMinTouMoney());
        pMinnum.setText(dataBean.getMemberNum());

        pProgresss.setProgress(Integer.parseInt(dataBean.getProgress()));
    }
}
