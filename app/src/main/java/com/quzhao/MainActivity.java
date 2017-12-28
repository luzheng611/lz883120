package com.quzhao;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager =findViewById(R.id.viewpager_main);
        System.out.println(subZeroAndDot("1"));     // 转换后为1
        System.out.println(subZeroAndDot("10"));    // 转换后为10
        System.out.println(subZeroAndDot("1.0"));   // 转换后为1
        System.out.println(subZeroAndDot("1.010")); // 转换后为1.01
        System.out.println(subZeroAndDot("1.01"));  // 转换后为1.01
        System.out.println(subZeroAndDot("1.00"));  //
        System.out.println(subZeroAndDot("1."));  //
    }

    public void  initMain(){

    }

    /**
     * 使用java正则表达式去掉多余的.与0
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s){
        if(s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }
}
