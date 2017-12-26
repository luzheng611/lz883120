package com.quzhao.CustomUI;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.PageTransformer;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2017/12/26.
 */

public class IViewpagerTransform implements PageTransformer {

    private Context context;
    public IViewpagerTransform(Context context) {
        super();
        this.context=context;
    }

    @Override
    public void transformPage(@NonNull View page, float position) {
        if (position < -1) {//左2

        } else if (position >= -1 && position < 0) {//左1

        } else if (position >= 0 && position < 1) {//当前
            ViewPager.LayoutParams layoutParams= (ViewPager.LayoutParams) page.getLayoutParams();
            layoutParams.width=context.getResources().getDisplayMetrics().widthPixels/3;
            layoutParams.height=context.getResources().getDisplayMetrics().widthPixels/3;
            page.setLayoutParams(layoutParams);
            Log.e("transformPage  ！~！~","切换动画：：：    "+position+"      page对象"+ page);

        } else if (position >= 1 && position < 2) {//右1

        } else if (position >= 2) {//右2

        }
    }
}
