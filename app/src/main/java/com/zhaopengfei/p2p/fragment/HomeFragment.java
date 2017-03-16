package com.zhaopengfei.p2p.fragment;


import android.content.Context;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.zhaopengfei.p2p.R;
import com.zhaopengfei.p2p.bean.HomeBean;
import com.zhaopengfei.p2p.ui.MyProgress;
import com.zhaopengfei.p2p.utlis.AppNetConfig;
import com.zhaopengfei.p2p.utlis.TheradPool;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by admin on 2017/3/10.
 */
public class HomeFragment extends BaseFragment {
    @Bind(R.id.base_title)
    TextView baseTitle;
    @Bind(R.id.base_back)
    ImageView baseBack;
    @Bind(R.id.base_setting)
    ImageView baseSetting;
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.tv_home_product)
    TextView tvHomeProduct;
    @Bind(R.id.tv_home_yearrate)
    TextView tvHomeYearrate;
    @Bind(R.id.home_progress)
    MyProgress homeProgress;




    @Override
    protected int getLayoutid() {
        return R.layout.fragment_home;
    }

    @Override
    public String getChildUrl() {
        return AppNetConfig.INDEX;
    }

    @Override
    protected void initlistener() {
        baseTitle.setText("首页");
        baseBack.setVisibility(View.INVISIBLE);
        baseSetting.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void initData(String json) {

        HomeBean homeBean = JSON.parseObject(json, HomeBean.class);


        // tvHomeYearrate.setText(Double.parseDouble(homeBean.getProInfo().getYearRate()) / 100 + "%");
        tvHomeYearrate.setText(homeBean.getProInfo().getYearRate() +"%");
        tvHomeProduct.setText(homeBean.getProInfo().getName());
        //展示UI一定要判断是不是主线程
        initorigress(homeBean.getProInfo());
        initBanner(homeBean);
        //}
    }



    private void initorigress(final HomeBean.ProInfoBean proInfo) {
        TheradPool.getInstance().getGlobalThrad().execute(new Runnable() {
            @Override
            public void run() {
                int progress= Integer.parseInt(proInfo.getProgress());
                for (int i = 0; i <= progress; i++) {
                    SystemClock.sleep(20); //睡眠
                    if(homeProgress ==null) {
                        return;
                    }
                    homeProgress.setProgress(i);//循环进度
                }
            }
        });
    }


    private void initBanner(HomeBean homeBean) {
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());

        List<String> urls = new ArrayList<>();

        for (int i = 0; i < homeBean.getImageArr().size(); i++) {
            urls.add(AppNetConfig.BASE_URL + homeBean.getImageArr().get(i).getIMAURL());
        }
        banner.setImages(urls);
        banner.start();

    }


    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */
            //Picasso 加载图片简单用法
            Picasso.with(context).load((String) path).into(imageView);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
