package com.zhaopengfei.p2p.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhaopengfei.p2p.R;
import com.zhaopengfei.p2p.utlis.AppManager;

import butterknife.Bind;

public class WelcomeActivity extends BaseActivity {

    @Bind(R.id.iv_welcome_icon)
    ImageView ivWelcomeIcon;
    @Bind(R.id.splash_tv_version)
    TextView splashTvVersion;
    @Bind(R.id.activity_splash)
    RelativeLayout activitySplash;


    @Override
    protected void initListener() {

    }


    public void initData() {
        AppManager.getInstance().addActivity(this);

        //设置版本号
        setVersion();
        //设置动画
        setAnimation();
    }

    @Override
    protected void initTitle() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    private void setAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        alphaAnimation.setDuration(2000);

        //动画监听
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            //动画登录过后跳转主页面
            @Override
            public void onAnimationEnd(Animation animation) {

                if(login()) {
                    //登录过进入主界面
                    startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                    finish();
                }else {
                    //没有登录过进入登录界面
                    startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
                    finish();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
          activitySplash.startAnimation(alphaAnimation);
    }

    private boolean login() {
        String name = getUser().getData().getName();
        if(TextUtils.isEmpty(name)) {
            return false;
        }
        return true;

    }


    private void setVersion() {
        splashTvVersion.setText(getVersion());
    }


    private String getVersion() {
        PackageManager packageManager =getPackageManager();

        try {
            PackageInfo packageInfo =packageManager.getPackageInfo(getPackageName(),0);
            int versionCode =packageInfo.versionCode;
            String versionName = packageInfo.versionName;
            return versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";

    }

}
