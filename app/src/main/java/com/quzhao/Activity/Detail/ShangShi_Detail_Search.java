package com.quzhao.Activity.Detail;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.quzhao.Base.BaseActivity;
import com.quzhao.Base.ICardView;
import com.quzhao.R;
import com.quzhao.Util.IUtils;
import com.quzhao.Util.LConstants;
import com.quzhao.Util.LogUtil;
import com.quzhao.Util.ToastUtil;

/**
 * Created by Administrator on 2018/1/1.
 */

public class ShangShi_Detail_Search extends BaseActivity {
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

         final AMap aMap2 = mapView.getMap();
        aMap2.moveCamera(CameraUpdateFactory.zoomIn());
        MyLocationStyle myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap2.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap2.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。


        aMap2.setOnMapLoadedListener(new AMap.OnMapLoadedListener() {
            @Override
            public void onMapLoaded() {
                MarkerOptions markerOptions=new MarkerOptions();
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
                markerOptions.title("雅居乐").position(LConstants.YAJULE);
                Marker marker=aMap2.addMarker(markerOptions);
                marker.showInfoWindow();


                MarkerOptions markerOptions3=new MarkerOptions();
                markerOptions3.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
                markerOptions3.title("银泰城").position(LConstants.YINGTAI);
                Marker marker3=aMap2.addMarker(markerOptions3);
                marker3.showInfoWindow();
                MarkerOptions markerOptions4=new MarkerOptions();
                markerOptions4.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
                markerOptions4.title("双流机场").position(LConstants.SHUNGALIUJICHANG);
                Marker marker4=aMap2.addMarker(markerOptions4);
                marker4.showInfoWindow();
                MarkerOptions markerOptions2=new MarkerOptions();
                markerOptions2.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
                markerOptions2.title("海昌极地公园").position(LConstants.HAICHANG);
                Marker marker2=aMap2.addMarker(markerOptions2);
                marker2.showInfoWindow();
                LogUtil.e("设置marker");
            }
        });

        aMap2.setOnInfoWindowClickListener(new AMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                ToastUtil.showToastShort("跳转到"+marker.getTitle()+"商市");
                startActivity(new Intent(ShangShi_Detail_Search.this,ShangShi_Detail.class));
            }
        });

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
