package com.quzhao.Activity.Detail;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.amap.api.maps.MapView;
import com.quzhao.Base.BaseActivity;
import com.quzhao.R;
import com.quzhao.Util.IUtils;

/**
 * Created by Administrator on 2018/1/1.
 */

public class Search extends BaseActivity {
    private MapView mapView;
    private EditText search;
    private Drawable se_gray;
    private TextView back;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mapView=findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        search=findViewById(R.id.search);
        se_gray= ContextCompat.getDrawable(this,R.drawable.search_gray);
        se_gray.setBounds(0,0, IUtils.dip2px(this,20),IUtils.dip2px(this,20)*se_gray.getIntrinsicHeight()/se_gray.getIntrinsicWidth());
        search.setCompoundDrawables(se_gray,null,null,null);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tabLayout=findViewById(R.id.tablayout);

        tabLayout.addTab(tabLayout.newTab().setText("电子市场"));
        tabLayout.addTab(tabLayout.newTab().setText("服装"));
        tabLayout.addTab(tabLayout.newTab().setText("美食街"));
        tabLayout.addTab(tabLayout.newTab().setText("农贸市场"));
        tabLayout.addTab(tabLayout.newTab().setText("电器"));
    }
}
