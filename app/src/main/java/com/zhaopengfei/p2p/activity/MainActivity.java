package com.zhaopengfei.p2p.activity;


import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.zhaopengfei.p2p.R;
import com.zhaopengfei.p2p.fragment.HomeFragment;
import com.zhaopengfei.p2p.fragment.InvestFragment;
import com.zhaopengfei.p2p.fragment.MoreFragment;
import com.zhaopengfei.p2p.fragment.PropertFragment;
import com.zhaopengfei.p2p.utlis.AppManager;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.main_rg)
    RadioGroup mainRg;
    @Bind(R.id.main_fl)
    FrameLayout mainFl;
    private InvestFragment investFragment;
    private MoreFragment moreFragment;
    private PropertFragment propertFragment;
    private FragmentTransaction transaction;
    private HomeFragment homeFragment;


    public void initListener() {
        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switchFragment(checkedId);
            }
        });
    }


    private void switchFragment(int checkedId) {

        transaction = getSupportFragmentManager().beginTransaction();

        //隐藏
        hiddFragment(transaction);
        switch (checkedId) {
            case R.id.rb_main:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
//                    transaction.add(R.id.main_fl, homeFragment);
                    transaction.add(R.id.main_fl,homeFragment);
                }
                transaction.show(homeFragment);
                break;
            case R.id.rb_invest:
                if (investFragment == null) {
                    investFragment = new InvestFragment();
                    transaction.add(R.id.main_fl, investFragment);

                }
                transaction.show(investFragment);
                break;
            case R.id.rb_more:
                if (moreFragment == null) {
                    moreFragment = new MoreFragment();
                    transaction.add(R.id.main_fl, moreFragment);
                }
                transaction.show(moreFragment);
                break;
            case R.id.rb_propert:
                if (propertFragment == null) {
                    propertFragment = new PropertFragment();
                    transaction.add(R.id.main_fl, propertFragment);
                }
                transaction.show(propertFragment);
                break;
        }
        transaction.commit();

    }

    //隐藏所有的fragment
    private void hiddFragment(FragmentTransaction transaction) {

        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (moreFragment != null) {
            transaction.hide(moreFragment);
        }
        if (investFragment != null) {
            transaction.hide(investFragment);
        }
        if (propertFragment != null) {
            transaction.hide(propertFragment);
        }
    }


    public void initData() {
        //添加到APPManager
        AppManager.getInstance().addActivity(this);
        //默认选中首页
        switchFragment(R.id.rb_main);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    public int getLayoutId() {
        return  R.layout.activity_main;
    }


    private boolean isDouble = false;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isDouble) {
                //退出
                finish();
            }
            Toast.makeText(MainActivity.this, "再次点击，退出", Toast.LENGTH_SHORT).show();
            isDouble = true;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isDouble = false;
                }
            }, 2000);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
