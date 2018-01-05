package com.quzhao.CustomUI;

/**
 * 作者：因陀罗网 on 2018/1/5 13:45
 * 公司：成都因陀罗网络科技有限公司
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ViewFlipper;

import java.util.List;

/**
 * 仿淘宝首页的 淘宝头条滚动的自定义View
 *
 *
 */
public class UPMarqueeView extends ViewFlipper {

    private Context mContext;
    private boolean isSetAnimDuration = false;
    private int interval = 2000;
    /**
     * 动画时间
     */
    private int animDuration = 200;

    public UPMarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        this.mContext = context;
        setFlipInterval(interval);
        Animation out = new TranslateAnimation(0, 0, 0, -getHeight());
        out.setDuration(200);
        out.setInterpolator(new AccelerateDecelerateInterpolator());
        Animation in = new TranslateAnimation(0, 0, getHeight(), 0);
        in.setInterpolator(new AccelerateDecelerateInterpolator());
        in.setDuration(200);
//        setInAnimation(in);
//        setOutAnimation(out);
    }


    /**
     * 设置循环滚动的View数组
     *
     * @param views
     */
    public void setViews(List<View> views) {
        if (views == null || views.size() == 0) return;
        removeAllViews();
        for (int i = 0; i < views.size(); i++) {
            addView(views.get(i));
        }
        startFlipping();
    }


}