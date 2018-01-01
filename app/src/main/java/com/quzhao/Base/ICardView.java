package com.quzhao.Base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.quzhao.R;
import com.quzhao.Util.IUtils;

/**
 * Created by Administrator on 2017/12/29.
 */

public class ICardView {

    public static View getNewInstance(Context context){//用于直接添加
        View view= LayoutInflater.from(context).inflate(R.layout.custom_cardview,null);
        initCardView(context,view);
        return view;
    }

    public  static void  initCardView(Context context,View view){//用于布局实例化，初始化
        TextView textView1=view.findViewById(R.id.xingcheng);
        TextView textView2=view.findViewById(R.id.shequ);
        TextView textView3=view.findViewById(R.id.wuliu);
        TextView textView4=view.findViewById(R.id.cuxiao);



        int dp50= IUtils.dip2px(context,40);
        int dp16= IUtils.dip2px(context,14);


        Drawable xingcheng= ContextCompat.getDrawable(context,R.drawable.xingcheng);
        Drawable shequ= ContextCompat.getDrawable(context,R.drawable.shequ);
        Drawable wuliu= ContextCompat.getDrawable(context,R.drawable.wuliu);
        Drawable cuxiao= ContextCompat.getDrawable(context,R.drawable.cuxiao);
        xingcheng.setBounds(0,0,dp50,dp50*xingcheng.getIntrinsicHeight()/xingcheng.getIntrinsicWidth());
        shequ.setBounds(0,0,dp50,dp50*shequ.getIntrinsicHeight()/shequ.getIntrinsicWidth());
        wuliu.setBounds(0,0,dp50,dp50*wuliu.getIntrinsicHeight()/wuliu.getIntrinsicWidth());
        cuxiao.setBounds(0,0,dp50,dp50*cuxiao.getIntrinsicHeight()/cuxiao.getIntrinsicWidth());
        textView1.setCompoundDrawables(null,null,xingcheng,null);
        textView2.setCompoundDrawables(null,null,shequ,null);
        textView3.setCompoundDrawables(null,null,wuliu,null);
        textView4.setCompoundDrawables(null,null,cuxiao,null);

        SpannableString ss1=new SpannableString("行程信息\n大促销全场8折起");
        SpannableString ss2=new SpannableString("社区信息\n暂无最新消息");
        SpannableString ss3=new SpannableString("物流信息\n暂无最新消息");
        SpannableString ss4=new SpannableString("促销信息\n暂无最新消息");
        ss1.setSpan(new ForegroundColorSpan(Color.parseColor("#ff0000")),4,ss1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss1.setSpan(new AbsoluteSizeSpan(dp16),4,ss1.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView1.setText(ss1);
        ss2.setSpan(new ForegroundColorSpan(Color.parseColor("#a3a3a3")),4,ss2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss2.setSpan(new AbsoluteSizeSpan(dp16),4,ss2.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView2.setText(ss2);
        ss3.setSpan(new ForegroundColorSpan(Color.parseColor("#a3a3a3")),4,ss3.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss3.setSpan(new AbsoluteSizeSpan(dp16),4,ss3.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView3.setText(ss3);
        ss4.setSpan(new ForegroundColorSpan(Color.parseColor("#a3a3a3")),4,ss4.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss4.setSpan(new AbsoluteSizeSpan(dp16),4,ss4.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView4.setText(ss4);
    }

}
