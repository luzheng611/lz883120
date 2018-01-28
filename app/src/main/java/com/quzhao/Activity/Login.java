package com.quzhao.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.quzhao.Base.BaseActivity;
import com.quzhao.R;

/**
 * Created by Administrator on 2018/1/25.
 */

public class Login extends BaseActivity implements View.OnClickListener {
    private EditText phone,password;
    private TextView login,mima,find,xieyi;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login1);
        back=findViewById(R.id.back);
        back.setOnClickListener(this);
        title=findViewById(R.id.title);
        title.setText("登录");



        initLogin();
    }

    private void initLogin() {
        phone=findViewById(R.id.phone);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        mima=findViewById(R.id.toRegister);
        find=findViewById(R.id.toFindPassword);
        xieyi=findViewById(R.id.xieyi);


        login.setOnClickListener(this);
        mima.setOnClickListener(this);
        find.setOnClickListener(this);
        xieyi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.toRegister:
                break;
            case R.id.toFindPassword:
                break;
            case R.id.xieyi:
                break;
            case R.id.login:
                break;
        }
    }
}
