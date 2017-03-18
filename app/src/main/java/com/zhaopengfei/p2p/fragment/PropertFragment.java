package com.zhaopengfei.p2p.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhaopengfei.p2p.R;
import com.zhaopengfei.p2p.activity.LineChartActivity;
import com.zhaopengfei.p2p.activity.MainActivity;
import com.zhaopengfei.p2p.activity.UserInfoActivity;
import com.zhaopengfei.p2p.bean.UserInfo;
import com.zhaopengfei.p2p.utlis.AppNetConfig;
import com.zhaopengfei.p2p.utlis.BitmapUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.Bind;
import jp.wasabeef.picasso.transformations.ColorFilterTransformation;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

import static com.zhaopengfei.p2p.R.id.ll_touzi;

/**
 * Created by admin on 2017/3/10.
 * 我的资产
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
    @Bind(ll_touzi)
    TextView llTouzi;
    @Bind(R.id.ll_touzi_zhiguan)
    TextView llTouziZhiguan;
    @Bind(R.id.ll_zichan)
    TextView llZichan;

    @Override
    protected int getLayoutid()  {
        return R.layout.fragment_propert;
    }

    @Override
    protected void initData(String json) {

        initlistener();

        MainActivity activity = (MainActivity) getActivity();
        UserInfo userInfo =activity.getUser();

        //设置用户名
        tvMeName.setText(userInfo.getData().getName());
        //设置头像
      Picasso.with(getActivity()).
             load(AppNetConfig.BASE_URL+"/images/tx.png").
              transform(new CropCircleTransformation())
              .transform(new ColorFilterTransformation(Color.parseColor("#66ffccff")))
              .into(ivMeIcon);

    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity activity = (MainActivity) getActivity();
        Boolean update = activity.isUpdata();
        if (update){
            File filesDir = null;
            FileInputStream is = null;
            try {
                //判断是否挂载了sd卡
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                    //外部存储路径
                    filesDir = getActivity().getExternalFilesDir("");
                }else{
                    filesDir = getActivity().getFilesDir(); //内部存储路径
                }
                //全路径
                File path = new File(filesDir,"123.png");

                if (path.exists()){
                    //输出流
                    is = new FileInputStream(path);
                    //第一个参数是图片的格式，第二个参数是图片的质量数值大的大质量高，第三个是输出流
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    Bitmap circleBitmap = BitmapUtils.circleBitmap(bitmap);
                    ivMeIcon.setImageBitmap(circleBitmap);
                    activity.saveImage(false);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }finally {
                try {
                    if (is != null){
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void initlistener() {

        //用户换头像
        tvSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UserInfoActivity.class));
            }
        });


        //投资
         llTouzi.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                startActivity(new Intent(getActivity(),LineChartActivity.class));
             }
         });
        //投资管理
        llTouziZhiguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),columnActivity.class));
            }
        });

        //充值
        recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ReChargeActivity.class));
            }
        });

        //提现
        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),WithDrawActivity.class));
            }
        });
    }





    @Override
    public String getChildUrl() {
        return null;
    }




}
