package com.zhaopengfei.p2p.fragment;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhaopengfei.p2p.R;
import com.zhaopengfei.p2p.adapter.InvesAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by admin on 2017/3/10.
 */

public class InvestFragment extends BaseFragment {


    @Bind(R.id.base_title)
    TextView baseTitle;
    @Bind(R.id.base_back)
    ImageView baseBack;
    @Bind(R.id.base_setting)
    ImageView baseSetting;
    @Bind(R.id.invest_vp)
    ViewPager investVp;
    @Bind(R.id.tv_invest_all)
    TextView tvInvestAll;
    @Bind(R.id.tv_invest_recommend)
    TextView tvInvestRecommend;
    @Bind(R.id.tv_invest_hot)
    TextView tvInvestHot;

    @Override
    protected int getLayoutid() {
        return R.layout.fragment_invest;
    }

    @Override
    protected void initData(String json) {

        initlistener();
        //设置标题
        initTitle();

        //初始化Fragment
        initFragment();

        //初始化viewPager
        initViewPager();


        //设置默认选中的tab
        initTab();
    }

    private void initTab() {
        selectText(0);
    }

    private void initViewPager() {
        investVp.setAdapter(new InvesAdapter(getChildFragmentManager(), baseFragments));
    }

    private List<BaseFragment> baseFragments = new ArrayList<>();

    private void initFragment() {
        baseFragments.add(new InvestAllFragment());
        baseFragments.add(new InvestRecommendFragment());
        baseFragments.add(new InvestHotFragment());

    }

    private void initTitle() {

        baseSetting.setVisibility(View.GONE);
        baseTitle.setText("投资");
        baseBack.setVisibility(View.GONE);
    }

    @Override
    protected void initlistener() {
        investVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectText(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

         tvInvestAll.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 investVp.setCurrentItem(0);
             }
         });

        tvInvestHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                investVp.setCurrentItem(2);
            }
        });
        tvInvestRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                investVp.setCurrentItem(1);
            }
        });

    }

    private void selectText(int id) {
        //吧所有的颜色还原成默认值
        hiddenTextViewState();

        switch (id){
            case 0:
                tvInvestAll.setBackgroundColor(Color.YELLOW);
                break;
            case 1:
                tvInvestRecommend.setBackgroundColor(Color.YELLOW);
                break;
            case 2:

                tvInvestHot.setBackgroundColor(Color.YELLOW);
                break;
        }
    }

    //恢复默认状态
    private void hiddenTextViewState() {
        tvInvestRecommend.setBackgroundColor(Color.RED);
        tvInvestHot.setBackgroundColor(Color.RED);
        tvInvestAll.setBackgroundColor(Color.RED);

    }

    @Override
    public String getChildUrl() {
        return null;
    }

}
