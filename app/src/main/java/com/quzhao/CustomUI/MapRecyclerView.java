package com.quzhao.CustomUI;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.quzhao.Util.LogUtil;

/**
 * Created by Administrator on 2018/1/6.
 */

public class MapRecyclerView extends RecyclerView {
    private Context context;
    private View view;//需要处理滑动的view

    public MapRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public MapRecyclerView(Context context) {
        super(context);
        this.context = context;
    }

    public MapRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    public void setMapScrollView(View view) {
        this.view = view;
    }



    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        float x = motionEvent.getRawX();
        float y = motionEvent.getRawY();
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int vy = location[1];
        int width = view.getWidth();
        int height = view.getHeight();
        if (x >= location[0] && x <= location[0] + width && y >= location[1] && y <= location[1] + height) {//按在室内view上，取消recyclerview事件相应
            LogUtil.e("不消耗该事件");
            return false;
        }

        return super.onTouchEvent(motionEvent);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {

        float x = motionEvent.getRawX();
        float y = motionEvent.getRawY();
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int vy = location[1];
        int width = view.getWidth();
        int height = view.getHeight();
        if (x >= location[0] && x <= location[0] + width && y >= location[1] && y <= location[1] + height) {//按在室内view上，取消recyclerview事件相应
            LogUtil.e("不拦截该事件");
            return false;
        }

        return super.onInterceptTouchEvent(motionEvent);


    }
}
