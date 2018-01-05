package com.quzhao.Activity.Detail;

import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps.MapView;
import com.quzhao.Base.BaseActivity;
import com.quzhao.Base.ICardView;
import com.quzhao.R;
import com.quzhao.Util.IUtils;
import com.quzhao.Util.LogUtil;

/**
 * Created by Administrator on 2018/1/1.
 */

public class ShangShi_Detail extends BaseActivity {
    private MapView mapView;
    private ImageView back;
    private Drawable loc;
    private TextView location;
    private ImageView down;
    private CardView cardView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shangshi_detail);
        mapView = findViewById(R.id.map);
        down = findViewById(R.id.down);
        cardView = findViewById(R.id.cardview);
        ICardView.initCardView(this, cardView);
        loc = ContextCompat.getDrawable(this, R.drawable.location);
        loc.setBounds(0, 0, IUtils.dip2px(this, 16), IUtils.dip2px(this, 16) * loc.getIntrinsicHeight() / loc.getIntrinsicWidth());
        location = findViewById(R.id.location);
        location.setCompoundDrawablePadding(IUtils.dip2px(this, 5));
        location.setCompoundDrawables(loc, null, null, null);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mapView.onCreate(savedInstanceState);


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        final int[] location = new int[2];
        cardView.getLocationOnScreen(location);
        final LinearLayout cardLyoaut = findViewById(R.id.card_layout);
        LogUtil.e("相对高度：" + location[0] + " 高度：：" + location[1]);

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                down.animate().rotation(down.getRotation()-180).setDuration(300).start();
                 ValueAnimator valueAnimator = ValueAnimator.ofFloat(getResources().getDisplayMetrics().heightPixels - location[1]);

                valueAnimator.setDuration(300);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        cardLyoaut.setTranslationY(((float) valueAnimator.getAnimatedValue()));
                    }
                });
                if(cardLyoaut.getTranslationY()!=0){
                    valueAnimator.reverse();
                    LogUtil.e("返回");
                }else{
                    valueAnimator.start();
                    LogUtil.e("下滑");
                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }

}
