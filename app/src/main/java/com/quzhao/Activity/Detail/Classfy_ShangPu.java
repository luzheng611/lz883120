package com.quzhao.Activity.Detail;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.IndoorBuildingInfo;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
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
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/12/31.
 */

public class Classfy_ShangPu extends BaseActivity {
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Banner banner;
    private ImageView back;
    private TextView openOrClose;
    //    private RecyclerView recyclerView;
    private MapRecyclerView recyclerView;

    private TextView cuxiao, join;
    private ExpandbleAdapter adapter;
//    private MapView mapView1;
        private TextureMapView mapView1;
    private TextureMapView mapView2;
    private IndoorFloorSwitchView indoorFloorSwitchView;
    private IndoorBuildingInfo mIndoorBuildingInfo;
    private RelativeLayout mapLayout;
    private  boolean isFirstIn=true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shangpu_detail);
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        cuxiao = findViewById(R.id.cuxiao);
        Drawable zhe = ContextCompat.getDrawable(this, R.drawable.text_zhe);
        zhe.setBounds(0, 0, IUtils.dip2px(this, 16), IUtils.dip2px(this, 16));
        cuxiao.setCompoundDrawablePadding(IUtils.dip2px(this, 5));
        cuxiao.setCompoundDrawables(zhe, null, null, null);

        join = findViewById(R.id.join);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Classfy_ShangPu.this, "点击加入行程", Toast.LENGTH_SHORT).show();
            }
        });

        appBarLayout = findViewById(R.id.appBarLayout);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        banner = findViewById(R.id.banner);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        recyclerView = findViewById(R.id.recyclerView);

        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        final ArrayList images = new ArrayList();
        images.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1506373168,1639353157&fm=27&gp=0.jpg");
        images.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=351801828,2196497414&fm=27&gp=0.jpg");
        images.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3681508343,3972683519&fm=27&gp=0.jpg");
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, final ImageView imageView) {
                Glide.with(Classfy_ShangPu.this).asBitmap().load(path)
                        .apply(new RequestOptions().centerCrop()
                                .override(getResources().getDisplayMetrics().widthPixels
                                        , IUtils.dip2px(Classfy_ShangPu.this, 180)))
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                                imageView.setImageBitmap(resource);
                            }
                        });
            }
        });
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(Classfy_ShangPu.this, "点击详情广告页" + position, Toast.LENGTH_SHORT).show();
            }
        });
        banner.setImages(images);
        banner.start();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        String titles[] = new String[]{"周边促销", "周边促销店"};
        for (int i = 0; i < titles.length; i++) {
            HashMap<String, String> map = new HashMap<>();
            map.put("title", titles[i]);

            list.add(map);
        }
        adapter = new ExpandbleAdapter(this, list);
        adapter.setFooterView(ICardView.getNewInstance(this));
        adapter.setFooterViewAsFlow(false);
        recyclerView.setAdapter(adapter);


        openOrClose = findViewById(R.id.openOrClose);
        openOrClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < adapter.getData().size(); i++) {
                    RelativeLayout relativeLayout = (RelativeLayout) adapter.getViewByPosition(recyclerView, i, R.id.expandButton);
                    if (!openOrClose.isSelected() && !relativeLayout.isSelected()) {
                        relativeLayout.performClick();
                    } else if (openOrClose.isSelected() && relativeLayout.isSelected()) {
                        relativeLayout.performClick();
                    }
                }
                if (openOrClose.isSelected()) {
                    openOrClose.setText("展开");
                    openOrClose.setSelected(false);
                } else {
                    openOrClose.setText("收起");
                    openOrClose.setSelected(true);
                }

            }
        });
        mapLayout = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.shinei_map, null);
        mapView1 = mapLayout.findViewById(R.id.map);
        indoorFloorSwitchView = mapLayout.findViewById(R.id.indoor_switchview);
        recyclerView.setMapScrollView(indoorFloorSwitchView);
        mapView2 = new TextureMapView(this);
        mapView1.onCreate(savedInstanceState);
        mapView2.onCreate(savedInstanceState);


    }


    public class ExpandbleAdapter extends BaseQuickAdapter<HashMap<String, String>, BaseViewHolder> implements ExpandableLayout.OnExpansionUpdateListener, LocationSource {
        private Context context;
        private Marker marker1;
        private int[] image = new int[]{R.drawable.test_recycle1, R.drawable.test_recycle2, R.drawable.test_recycle3};
        private String[] titles = new String[]{"ZARA",  "琼斯","阿迪达斯"};
        private String[] texts = new String[]{"满200减50", "全场8折", "满100减20"};

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
            switch (holder.getAdapterPosition()) {
                case 0:
                    HorizontalScrollView horizontalScrollView = new HorizontalScrollView(context);
                    horizontalScrollView.setHorizontalScrollBarEnabled(false);
                    LinearLayout linearLayout = new LinearLayout(context);
                    linearLayout.setPadding(0, IUtils.dip2px(context, 10), 0, IUtils.dip2px(context, 10));
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    horizontalScrollView.addView(linearLayout);
                    for (int i = 0; i < 10; i++) {
                        View view1=LayoutInflater.from(context).inflate(R.layout.shangpu_item,null);
                        ImageView imageView=view1.findViewById(R.id.image);
                        imageView.setImageResource(image[i % image.length]);
                        ((TextView) view1.findViewById(R.id.title)).setText(titles[i%titles.length]);
                        ((TextView) view1.findViewById(R.id.msg)).setText(texts[i%texts.length]);
                        linearLayout.addView(view1);
                    }
                    expandableLayout.addView(horizontalScrollView);
                    break;

                case 1:
                    int width1 = context.getResources().getDisplayMetrics().widthPixels;
                    int height1 = IUtils.dip2px(Classfy_ShangPu.this, 300);
                    ViewGroup.LayoutParams v1 = new ViewGroup.LayoutParams(width1, height1);

                    mapView2.setLayoutParams(v1);
                    final AMap aMap2 = mapView2.getMap();
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
                            MarkerOptions markerOptions2=new MarkerOptions();
                            markerOptions2.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
                            markerOptions2.title("海昌极地公园").position(LConstants.HAICHANG);
                            Marker marker2=aMap2.addMarker(markerOptions2);
                            marker2.showInfoWindow();
                            MarkerOptions markerOptions3=new MarkerOptions();
                            markerOptions3.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
                            markerOptions3.title("银泰城").position(LConstants.YINGTAI);
                            Marker marker3=aMap2.addMarker(markerOptions3);
                            marker3.showInfoWindow();
                            LogUtil.e("设置marker");
                        }
                    });












                    expandableLayout.addView(mapView2);

                    break;
            }
            expandButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getAdapterPosition();
                    if (expandButton.isSelected()) {
                        expandableLayout.collapse(true);
                        expandButton.setSelected(false);
                        tip.animate().rotation(tip.getRotation() - 180).setDuration(300).start();
                    } else {
                        expandButton.setSelected(true);
                        expandableLayout.expand(true);
                        tip.animate().rotation(tip.getRotation() - 180).setDuration(300).start();
                    }
                }
            });
        }

        @Override
        public void onExpansionUpdate(float expansionFraction, int state) {

        }

        @Override
        public void activate(OnLocationChangedListener onLocationChangedListener) {
            LogUtil.e("activate");
        }

        @Override
        public void deactivate() {
            LogUtil.e("deactivate");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView1.onDestroy();
        mapView2.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView1.onResume();
        mapView2.onResume();
        if(isFirstIn){
            openOrClose.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isFirstIn=false;
                    openOrClose.performClick();
                }
            }, 300);
        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView1.onPause();
        mapView2.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView1.onSaveInstanceState(outState);
        mapView2.onSaveInstanceState(outState);
    }


}
