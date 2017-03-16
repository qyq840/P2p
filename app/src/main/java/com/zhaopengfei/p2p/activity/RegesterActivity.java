package com.zhaopengfei.p2p.activity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhaopengfei.p2p.R;
import com.zhaopengfei.p2p.utlis.AppNetConfig;
import com.zhaopengfei.p2p.utlis.LoadNet;
import com.zhaopengfei.p2p.utlis.LoadNetHttp;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

public class RegesterActivity extends BaseActivity {

    @Bind(R.id.base_title)
    TextView baseTitle;
    @Bind(R.id.base_back)
    ImageView baseBack;
    @Bind(R.id.base_setting)
    ImageView baseSetting;
    @Bind(R.id.et_register_number)
    EditText etRegisterNumber;
    @Bind(R.id.et_register_name)
    EditText etRegisterName;
    @Bind(R.id.et_register_pwd)
    EditText etRegisterPwd;
    @Bind(R.id.et_register_pwdagain)
    EditText etRegisterPwdagain;
    @Bind(R.id.btn_register)
    Button btnRegister;

    @Override
    protected void initListener() {

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //校验
                String name = etRegisterName.getText().toString().trim();
                String number = etRegisterNumber.getText().toString().trim();
                String pwd = etRegisterPwd.getText().toString().trim();
                String pwdAgain = etRegisterPwdagain.getText().toString().trim();

                //
                if(TextUtils.isEmpty(name)|| TextUtils.isEmpty(number)||TextUtils.isEmpty(pwd)|| TextUtils.isEmpty(pwdAgain)) {
                    showToast("不能为空");
                    return;
                }

                Map<String ,String> map =new HashMap<String, String>();

                map.put("name",name);
                map.put("password",pwd);
                map.put("phone",number);

                LoadNet.getDataPost(AppNetConfig.REGISTER, map, new LoadNetHttp() {
                    @Override
                    public void success(String context) {
                        JSONObject jsonObject = JSON.parseObject(context);
                        Boolean isExist = jsonObject.getBoolean("isExist");

                        Log.e("TAG", "RegesterActivity success()" +isExist);

                        if(isExist) {
                            showToast("账号已经注册过了");
                        }else {
                            showToast("注册完成");
                            finish();
                        }
                    }

                    @Override
                    public void failure(String error) {

                    }
                });
            }


        });
    }

    public void  isEmpty(String name, String alert){
        if(TextUtils.isEmpty(name)) {
            showToast(alert);
            return;
        }

    }
    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {

        baseBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        baseSetting.setVisibility(View.GONE);
        baseTitle.setText("注册");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_regester;
    }

}
