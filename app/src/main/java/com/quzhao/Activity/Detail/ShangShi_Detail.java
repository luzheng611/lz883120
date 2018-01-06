package com.quzhao.Activity.Detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.IndoorBuildingInfo;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Poi;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quzhao.Base.BaseActivity;
import com.quzhao.Base.ICardView;
import com.quzhao.CustomUI.IndoorFloorSwitchView;
import com.quzhao.CustomUI.MapRecyclerView;
import com.quzhao.R;
import com.quzhao.Util.IUtils;
import com.quzhao.Util.LConstants;
import com.quzhao.Util.LogUtil;
import com.quzhao.Util.MapUtils;
import com.quzhao.Util.ToastUtil;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by Administrator on 2018/1/1.
 */

public class ShangShi_Detail extends BaseActivity {
    private TextView join;
    private ImageView back;
    private MapRecyclerView recyclerView;
    private ExpandbleAdapter adapter;

    private TextureMapView mapView1;
    private IndoorFloorSwitchView indoorFloorSwitchView;
    private IndoorBuildingInfo mIndoorBuildingInfo;
    private RelativeLayout mapLayout;
    private boolean isFirstIn=true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shangquan_detail);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        join=findViewById(R.id.join);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ShangShi_Detail.this,"点击加入行程",Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        String titles[] = new String[]{"商圈简介", "商店布局图", "全局视频","商铺商圈布局图"};
        for (int i = 0; i < titles.length; i++) {
            HashMap<String, String> map = new HashMap<>();
            map.put("title", titles[i]);

            list.add(map);
        }
        adapter = new ExpandbleAdapter(this, list);
        ImageView imgeview =new ImageView(this);
        imgeview.setImageResource(R.drawable.test_guanggao);
        adapter.addFooterView(imgeview);
        adapter.addFooterView(ICardView.getNewInstance(this));

        recyclerView.setAdapter(adapter);

        mapLayout = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.shinei_map, null);
        mapView1 = mapLayout.findViewById(R.id.map);
        indoorFloorSwitchView = mapLayout.findViewById(R.id.indoor_switchview);
        recyclerView.setMapScrollView(indoorFloorSwitchView);

        mapView1.onCreate(savedInstanceState);




    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView1.onDestroy();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView1.onResume();
//        if(isFirstIn){
//            recyclerView.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    isFirstIn=false;
//                    for (int i = 0; i < adapter.getData().size(); i++) {
//                        RelativeLayout relativeLayout = (RelativeLayout) adapter.getViewByPosition(recyclerView, i, R.id.expandButton);
//                        if (!relativeLayout.isSelected()) {
//                            relativeLayout.performClick();
//                        }
//                    }
//
//                }
//            }, 300);
//        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView1.onPause();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView1.onSaveInstanceState(outState);

    }
    public class ExpandbleAdapter extends BaseQuickAdapter<HashMap<String, String>, BaseViewHolder> implements ExpandableLayout.OnExpansionUpdateListener {
        private Context context;
        private Marker marker1;

        public ExpandbleAdapter(Context context, @Nullable List<HashMap<String, String>> data) {
            super(R.layout.item_shagquan_detail_multiitem_1, data);
            this.context = context;
        }

        @Override
        protected void convert(final BaseViewHolder holder, final HashMap<String, String> item) {
            View view = holder.itemView;
            final RelativeLayout expandButton = view.findViewById(R.id.expandButton);
            TextView title = expandButton.findViewById(R.id.title);
            title.setText(item.get("title"));
            final ImageView tip = expandButton.findViewById(R.id.openOrClose);


            final ExpandableLayout expandableLayout = view.findViewById(R.id.expandLayout);
            expandableLayout.setOnExpansionUpdateListener(this);
            switch (holder.getAdapterPosition()){
                case 0:
                    int dp10= IUtils.dip2px(context,10);
                    TextView textView=new TextView(context);
                    textView.setTextSize(14);
                    textView.setPadding(dp10,dp10,dp10,dp10);
                    textView.setTextColor(Color.BLACK);
                    ViewGroup.LayoutParams vl=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    textView.setLayoutParams(vl);
                    textView.setLineSpacing(IUtils.dip2px(context,5),1);
                    textView.setText("江北区观音桥，是传统的商贸繁华区域，是重庆市人民政府确定的五大商圈之一，是江北区政治、经济、文化中心和交通枢纽。\n" +
                            "观音桥商圈以观音桥转盘为中心，建新东、南、西、北路为辐射方向，半径1000米内区域。该商圈作为北部城区商业发源地，凭借邻解放碑及新区优势，曾为上世纪90年代中期的第二大商圈，但随着交通改善，商业结构、布局不合理而出现“商业空心”现象，限制了一些大型、综合性商业物业在此发展。");
                    expandableLayout.addView(textView);
                    break;
                case 1:
                    int width=context.getResources().getDisplayMetrics().widthPixels;
                    int height= ViewGroup.LayoutParams.WRAP_CONTENT;
                    ViewGroup.LayoutParams vl1=new ViewGroup.LayoutParams(width,height);
                    ImageView imageView=new ImageView(context);
                    imageView.setAdjustViewBounds(true);
                    imageView.setImageResource(R.drawable.test_guangyinqiao);
                    imageView.setLayoutParams(vl1);
                    expandableLayout.addView(imageView);
                    break;
                case 2:
                    JZVideoPlayerStandard jzVideoPlayerStandard=new JZVideoPlayerStandard(context);
                    ViewGroup.LayoutParams vll=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,IUtils.dip2px(context,200));
                    jzVideoPlayerStandard.setLayoutParams(vll);
                    jzVideoPlayerStandard.setUp("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4"
                    , JZVideoPlayer.SCREEN_WINDOW_LIST);
                    Glide.with(context).load("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1514748505&di=120ca60c6a528acb0adc92daa659f700&src=http://imgsrc.baidu.com/imgad/pic/item/dc54564e9258d10936cd8a94db58ccbf6c814ded.jpg")
                            .apply(new RequestOptions().centerCrop())
                            .into(jzVideoPlayerStandard.thumbImageView);
                    expandableLayout.addView(jzVideoPlayerStandard);
                    break;
                case 3:
                    final AMap aMap = mapView1.getMap();

                    MapUtils.changeCamera(aMap, CameraUpdateFactory.newCameraPosition(new CameraPosition(LConstants.SHINEIDITU, 18, 0, 30))
                            , new AMap.CancelableCallback() {
                                @Override
                                public void onFinish() {
                                    LogUtil.e("地图动画完成");
                                }

                                @Override
                                public void onCancel() {
                                    LogUtil.e("地图动画取消");
                                }
                            });
                    aMap.showIndoorMap(true);
                    // 关闭SDK自带的室内地图控件
                    aMap.getUiSettings().setIndoorSwitchEnabled(false);
                    aMap.getUiSettings().setZoomControlsEnabled(false);
                    UiSettings uiSettings = aMap.getUiSettings();
//                    aMap.setLocationSource(this);//通过aMap对象设置定位数据源的监听
//
//                    uiSettings.setMyLocationButtonEnabled(true); //显示默认的定位按钮
//
//                    aMap.setMyLocationEnabled(true);// 可触发定位并显示当前位置

                    aMap.setOnIndoorBuildingActiveListener(new AMap.OnIndoorBuildingActiveListener() {
                        @Override
                        public void OnIndoorBuilding(final IndoorBuildingInfo indoorBuildingInfo) {
                            Log.e("注册室内地图信息", "indoor OnIndoorBuilding " + indoorBuildingInfo);
                            if (indoorBuildingInfo != null) {
                                indoorFloorSwitchView.setVisible(true);
                            } else {
                                indoorFloorSwitchView.setVisible(false);
                            }
                            if (indoorBuildingInfo != null) {
                                recyclerView.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        //相同室内图，不需要替换楼层总数，只需要设置选中的楼层即可
                                        if (mIndoorBuildingInfo == null || mIndoorBuildingInfo.poiid != indoorBuildingInfo.poiid) {

                                            indoorFloorSwitchView
                                                    .setItems(indoorBuildingInfo.floor_names);
                                        }
                                        indoorFloorSwitchView
                                                .setSeletion(indoorBuildingInfo.activeFloorName);

                                        mIndoorBuildingInfo = indoorBuildingInfo;
                                    }
                                });
                            }
                        }
                    });
                    indoorFloorSwitchView.setOnIndoorFloorSwitchListener(new IndoorFloorSwitchView.OnIndoorFloorSwitchListener() {
                        @Override
                        public void onSelected(int selectedIndex) {
                            Log.e("amap", "indoor onselected " + selectedIndex + "   mindorr:::::" + mIndoorBuildingInfo);
                            if (mIndoorBuildingInfo != null) {
                                mIndoorBuildingInfo.activeFloorIndex = mIndoorBuildingInfo.floor_indexs[selectedIndex];
                                mIndoorBuildingInfo.activeFloorName = mIndoorBuildingInfo.floor_names[selectedIndex];

                                aMap.setIndoorBuildingInfo(mIndoorBuildingInfo);
                                if (marker1 != null) marker1.remove();
                            }
                        }
                    });
                    aMap.setOnPOIClickListener(new AMap.OnPOIClickListener() {
                        @Override
                        public void onPOIClick(Poi poi) {
                            if (marker1 != null) {
                                marker1.remove();
                            }
                            final MarkerOptions markerOptions = new MarkerOptions();
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
                            markerOptions.position(poi.getCoordinate()).title(poi.getName())
//                                    .snippet(poi.getPoiId())
                            ;
                            marker1 = aMap.addMarker(markerOptions);
                            marker1.showInfoWindow();

                        }
                    });
                    aMap.setOnInfoWindowClickListener(new AMap.OnInfoWindowClickListener() {
                        @Override
                        public void onInfoWindowClick(Marker marker) {
                            ToastUtil.showToastShort("跳转商铺详情");
                            context.startActivity(new Intent(context,Classfy_ShangPu.class));
                        }
                    });



//                    aMap.setOnMapLoadedListener(new AMap.OnMapLoadedListener() {
//                        @Override
//                        public void onMapLoaded() {
//                            ToastUtil.showToastShort("地图加载完毕");
//
//                        }
//                    });
                    expandableLayout.addView(mapLayout);
                    break;
            }
            expandButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getAdapterPosition();
                    if (expandButton.isSelected()) {
                        expandableLayout.collapse(true);
                        expandButton.setSelected(false);
                        tip.animate().rotation(tip.getRotation()-180).setDuration(300).start();
                    } else {
                        expandButton.setSelected(true);
                        expandableLayout.expand(true);
                        tip.animate().rotation(tip.getRotation()-180).setDuration(300).start();
                    }
                }
            });
        }

        @Override
        public void onExpansionUpdate(float expansionFraction, int state) {

        }
    }
}
