package com.zhaopengfei.p2p.fragment;

import android.graphics.Color;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhaopengfei.p2p.R;
import com.zhaopengfei.p2p.activity.MainActivity;
import com.zhaopengfei.p2p.bean.UserInfo;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.ColorFilterTransformation;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by admin on 2017/3/10.
 */
public class PropertFragment extends BaseFragment {
    @Bind(R.id.tv_settings)
    TextView tvSettings;
    @Bind(R.id.iv_me_icon)
    ImageView ivMeIcon;
    @Bind(R.id.rl_me_icon)
    RelativeLayout rlMeIcon;
    @Bind(R.id.tv_me_name)
    TextView tvMeName;
    @Bind(R.id.rl_me)
    RelativeLayout rlMe;
    @Bind(R.id.recharge)
    ImageView recharge;
    @Bind(R.id.withdraw)
    ImageView withdraw;
    @Bind(R.id.ll_touzi)
    TextView llTouzi;
    @Bind(R.id.ll_touzi_zhiguan)
    TextView llTouziZhiguan;
    @Bind(R.id.ll_zichan)
    TextView llZichan;

    @Override
    protected int getLayoutid() {
        return R.layout.fragment_propert;
    }

    @Override
    protected void initData(String json) {

        MainActivity activity = (MainActivity) getActivity();
        UserInfo userInfo =activity.getUser();

        //设置用户名
        tvMeName.setText(userInfo.getData().getName());
        //设置头像
      Picasso.with(getActivity()).load("http://www.th7.cn/d/file/p/2017/01/24/e9e1b476d96f8f3c9cb152e5775d038c.jpg").
//              load(AppNetConfig.BASE_URL+"/images/tx.png").
              transform(new CropCircleTransformation())
              .transform(new ColorFilterTransformation(Color.parseColor("#66ffccff")))
              .into(ivMeIcon);

    }


    @Override
    protected void initlistener() {

    }

    @Override
    public String getChildUrl() {
        return null;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
