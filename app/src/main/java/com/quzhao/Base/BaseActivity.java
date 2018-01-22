package com.quzhao.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.quzhao.R;
import com.quzhao.Util.StatusBarCompat;

/**
 * Created by Administrator on 2017/12/29.
 */

public class BaseActivity extends AppCompatActivity {
    public ImageView back,right_icon;
    public TextView title,right_text;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.compat(this, getResources().getColor(R.color.black));
    }
}
