package com.zhaopengfei.p2p.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.zhaopengfei.p2p.bean.DataBean;
import com.zhaopengfei.p2p.bean.UserInfo;
import com.zhaopengfei.p2p.utlis.AppManager;

import java.io.File;

import butterknife.ButterKnife;

/**
 * Created by admin on 2017/3/15.
 */

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initTitle();
        initData();
        initListener();
    }

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract void initTitle();

    public abstract int getLayoutId() ;



    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public void showToast(String context){
        Toast.makeText(this, context, Toast.LENGTH_SHORT).show();
    }

    public void saveUser(UserInfo userInfo){
        //保存用户信息
        SharedPreferences sp = getSharedPreferences("user_info",MODE_PRIVATE);
        SharedPreferences.Editor editor =sp.edit();

        editor.putString("imageurl",userInfo.getData().getImageurl());
        editor.putString("iscredit",userInfo.getData().getIscredit());
        editor.putString("name",userInfo.getData().getName());
        editor.putString("phone",userInfo.getData().getPhone());
        editor.commit();

        //获取用户信息
    }
    public UserInfo getUser(){
        SharedPreferences sp = getSharedPreferences("user_info",MODE_PRIVATE);
        String imageurl =sp.getString("imageurl","");
        String iscredit =sp.getString("iscredit","");
        String name =sp.getString("name","");
        String phone =sp.getString("phone","");

        UserInfo userInfo =new UserInfo();

        DataBean dataBean = new DataBean();
        dataBean.setImageurl(imageurl);
        dataBean.setIscredit(iscredit);
        dataBean.setName(name);
        dataBean.setPhone(phone);
        userInfo.setData(dataBean);
        return  userInfo;
    }


    public void saveImage(boolean isupdata){
        SharedPreferences sharedPreferences = getSharedPreferences("image",MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("updata",isupdata).commit();
    }

    public boolean isUpdata(){
        SharedPreferences sp =getSharedPreferences("image",MODE_PRIVATE);
        return  sp.getBoolean("updata",false);

    }


    public void clearSp(){
        SharedPreferences  user = getSharedPreferences("user_info",MODE_PRIVATE);
        SharedPreferences image= getSharedPreferences("image",MODE_PRIVATE);
        user.edit().clear().commit();//清除内容
        image.edit().clear().commit();
    }
    //删除file
    public  void  clearFile(){
        File filesDie=null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //外部存储路径
            filesDie = getExternalFilesDir("");
        } else {
            filesDie = getFilesDir(); //内部存储路径
        }
        //全路径
        File path = new File(filesDie, "123.png");

        if (path.exists()){
            path.delete();//删除目录中的内容
        }
    }

    //清除所有的activity
    public void removeAllActivity(){
        AppManager.getInstance().removeAll();
    }
    }


