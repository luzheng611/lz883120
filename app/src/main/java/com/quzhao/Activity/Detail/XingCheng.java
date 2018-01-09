package com.quzhao.Activity.Detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps.TextureMapView;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusPath;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkRouteResult;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quzhao.Base.BaseActivity;
import com.quzhao.CustomUI.NoScrollRecyclerView;
import com.quzhao.Helper.MapOverlay.BusRouteOverlay;
import com.quzhao.Helper.MapOverlay.DrivingRouteOverlay;
import com.quzhao.Helper.MapOverlay.WalkRouteOverlay;
import com.quzhao.R;
import com.quzhao.Util.AMapUtil;
import com.quzhao.Util.LConstants;
import com.quzhao.Util.LogUtil;
import com.quzhao.Util.ToastUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2018/1/6.
 */

public class XingCheng extends BaseActivity implements View.OnClickListener {
    private ImageView back;
    private NoScrollRecyclerView recyclerView;
    private TextView tuijian;
    private TextureMapView mapView1, mapView2;
//    private CardView cardView;
    private XingChengAdapter adapter;
    private ArrayList<String> titles = new ArrayList<String>();


    private String time, distance, lights = "";
    private TextView info;
    private TextView car, bus, walk;
    private static final int CAR = 1;
    private static final int BUS = 2;
    private static final int WALK = 3;
    private static final int RIDE = 4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xingcheng);
//        cardView = findViewById(R.id.cardview);
//        ICardView.initCardView(this, cardView);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        info = findViewById(R.id.msg);
        car = findViewById(R.id.car);
        bus = findViewById(R.id.bus);
        walk = findViewById(R.id.walk);
        car.setOnClickListener(this);
        bus.setOnClickListener(this);
        walk.setOnClickListener(this);

        recyclerView = findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tuijian = findViewById(R.id.tuijian);
        mapView1 = findViewById(R.id.map_nvi);
        mapView1.onCreate(savedInstanceState);
        mapView2 = findViewById(R.id.allMap);
        mapView2.onCreate(savedInstanceState);
        tuijian.setText("王府井百货-推荐行程路线");
        titles.add("王府井");
        titles.add("金牛万达广场");
        titles.add("石桥铺");
        adapter = new XingChengAdapter(titles);
        recyclerView.setAdapter(adapter);

//        mapView1.getMap().getUiSettings().setZoomControlsEnabled(false);
        all();
        car.performClick();
    }

    private void tuijian(int type) {
        RouteSearch routeSearch = new RouteSearch(this);
        routeSearch.setRouteSearchListener(new RouteSearch.OnRouteSearchListener() {
            @Override
            public void onBusRouteSearched(BusRouteResult result, int i) {
                mapView1.getMap().clear();// 清理地图上的所有覆盖物
                if (i == AMapException.CODE_AMAP_SUCCESS) {
                    if (result != null && result.getPaths() != null) {
                        if (result.getPaths().size() > 0) {
                          BusRouteResult  mBusRouteResult = result;
                            BusPath path=mBusRouteResult.getPaths().get(0);
                            BusRouteOverlay drivingRouteOverlay = new BusRouteOverlay(
                                    XingCheng.this, mapView1.getMap(),path,
                                    mBusRouteResult.getStartPos(),
                                    mBusRouteResult.getTargetPos());
                            drivingRouteOverlay.setNodeIconVisibility(false);//设置节点marker是否显示
                            drivingRouteOverlay.removeFromMap();
                            drivingRouteOverlay.addToMap();
                            drivingRouteOverlay.zoomToSpan();
                            time = AMapUtil.getFriendlyTime(((int) path.getDuration()));
                            distance = AMapUtil.getFriendlyLength(((int) path.getDistance()));
                            LogUtil.e(path.getBusDistance()+"  公交  "+path.getWalkDistance());

                            info.setText(time + " | 公交路程:"+AMapUtil.getFriendlyLength(((int) path.getBusDistance())) +
                            " 需步行:"+AMapUtil.getFriendlyLength(((int) path.getWalkDistance()))
//                                    +" 总里程:"+distance
                            );


//                            BusResultListAdapter mBusResultListAdapter = new BusResultListAdapter(mContext, mBusRouteResult);
//                            mBusResultList.setAdapter(mBusResultListAdapter);
                        } else if (result != null && result.getPaths() == null) {
                            ToastUtil.showToastShort("未搜索到公交路线规划");
                        }
                    } else {
                        ToastUtil.showToastShort("未搜索到公交路线规划");
                    }
                } else {
                    ToastUtil.showToastShort("未搜索到公交路线规划");
                }
            }

            @Override
            public void onDriveRouteSearched(DriveRouteResult result, int i) {
                LogUtil.e("路线规划回调");
                if (i == 1000) {
                    if (result != null && result.getPaths() != null) {
                        if (result.getPaths().size() > 0) {
                            DriveRouteResult mDriveRouteResult = result;
                            final DrivePath drivePath = mDriveRouteResult.getPaths()
                                    .get(0);

                            DrivingRouteOverlay drivingRouteOverlay = new DrivingRouteOverlay(
                                    XingCheng.this, mapView1.getMap(), drivePath,
                                    mDriveRouteResult.getStartPos(),
                                    mDriveRouteResult.getTargetPos(), null, 0);
                            mapView1.getMap().clear();

                            drivingRouteOverlay.setNodeIconVisibility(false);//设置节点marker是否显示
                            drivingRouteOverlay.setIsColorfulline(true);//是否用颜色展示交通拥堵情况，默认true
                            drivingRouteOverlay.removeFromMap();
                            drivingRouteOverlay.addToMap();
                            drivingRouteOverlay.zoomToSpan();
                            time = AMapUtil.getFriendlyTime(((int) drivePath.getDuration()));
                            distance = AMapUtil.getFriendlyLength(((int) drivePath.getDistance()));
                            lights = String.valueOf(drivePath.getTotalTrafficlights());

                            info.setText(time + " | " + distance + " | " + lights + "个红绿灯");

                        } else if (result != null && result.getPaths() == null) {
                            ToastUtil.showToastShort("未搜索到驾车路线规划");
                        }

                    } else {
                        ToastUtil.showToastShort("未搜索到驾车路线规划");
                    }
                }
            }

            @Override
            public void onWalkRouteSearched(WalkRouteResult result, int i) {
                if (i == AMapException.CODE_AMAP_SUCCESS) {
                    if (result != null && result.getPaths() != null) {
                        if (result.getPaths().size() > 0) {
                            WalkRouteResult mWalkRouteResult = result;
                            final WalkPath walkPath = mWalkRouteResult.getPaths()
                                    .get(0);
                            WalkRouteOverlay walkRouteOverlay = new WalkRouteOverlay(
                                    XingCheng.this, mapView1.getMap(), walkPath,
                                    mWalkRouteResult.getStartPos(),
                                    mWalkRouteResult.getTargetPos());
                            mapView1.getMap().clear();
                            walkRouteOverlay.setNodeIconVisibility(false);
                            walkRouteOverlay.removeFromMap();
                            walkRouteOverlay.addToMap();
                            walkRouteOverlay.zoomToSpan();
                            time = AMapUtil.getFriendlyTime(((int) walkPath.getDuration()));
                            distance = AMapUtil.getFriendlyLength(((int) walkPath.getDistance()));
                            info.setText(time + " | " + distance);
                        } else if (result != null && result.getPaths() == null) {
                            ToastUtil.showToastShort("未搜索到步行路线规划");
                        }
                    } else {
                        ToastUtil.showToastShort("未搜索到步行路线规划");
                    }
                } else {
                    ToastUtil.showToastShort("未搜索到步行路线规划");
                }
            }

            @Override
            public void onRideRouteSearched(RideRouteResult result, int i) {

            }

        });
        if (CAR == type) {
            RouteSearch.DriveRouteQuery driveRouteQuery = new
                    RouteSearch.DriveRouteQuery(new RouteSearch.FromAndTo
                    (new LatLonPoint(LConstants.BIGUIYUAN.latitude, LConstants.BIGUIYUAN.longitude)
                            , new LatLonPoint(LConstants.WANFUJING.latitude, LConstants.WANFUJING.longitude)), 2, null, null, "");
            routeSearch.calculateDriveRouteAsyn(driveRouteQuery);
        } else if (WALK == type) {
            RouteSearch.WalkRouteQuery walkRouteQuery = new RouteSearch.WalkRouteQuery(new RouteSearch.FromAndTo
                    (new LatLonPoint(LConstants.BIGUIYUAN.latitude, LConstants.BIGUIYUAN.longitude)
                            , new LatLonPoint(LConstants.WANFUJING.latitude, LConstants.WANFUJING.longitude)));
            routeSearch.calculateWalkRouteAsyn(walkRouteQuery);
        } else if (BUS == type) {
            RouteSearch.BusRouteQuery busRouteQuery = new RouteSearch.BusRouteQuery(new RouteSearch.FromAndTo
                (new LatLonPoint(LConstants.BIGUIYUAN.latitude,LConstants.BIGUIYUAN.longitude)
                        ,new LatLonPoint(LConstants.WANFUJING.latitude,LConstants.WANFUJING.longitude)),1,"成都",0);
                    routeSearch.calculateBusRouteAsyn(busRouteQuery);
        }


    }

    private class AllSearchListenr implements RouteSearch.OnRouteSearchListener {
        private int pos = 1;

        @Override
        public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

        }

        @Override
        public void onDriveRouteSearched(DriveRouteResult result, int i) {
            LogUtil.e("路线规划回调");
            if (i == 1000) {
                if (result != null && result.getPaths() != null) {
                    if (result.getPaths().size() > 0) {
                        DriveRouteResult mDriveRouteResult = result;
                        final DrivePath drivePath = mDriveRouteResult.getPaths()
                                .get(0);

                        DrivingRouteOverlay drivingRouteOverlay = new DrivingRouteOverlay(
                                XingCheng.this, mapView2.getMap(), drivePath,
                                mDriveRouteResult.getStartPos(),
                                mDriveRouteResult.getTargetPos(), null, pos);

                        drivingRouteOverlay.setNodeIconVisibility(false);//设置节点marker是否显示
                        drivingRouteOverlay.setIsColorfulline(true);//是否用颜色展示交通拥堵情况，默认true
                        drivingRouteOverlay.removeFromMap();
                        drivingRouteOverlay.addToMap();
                        drivingRouteOverlay.zoomToSpan();
                        pos++;
                    } else if (result != null && result.getPaths() == null) {
                        ToastUtil.showToastShort("未搜索到路线规划");
                    }

                } else {
                    ToastUtil.showToastShort("未搜索到路线规划");
                }
            }
        }

        @Override
        public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

        }

        @Override
        public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

        }
    }


    private void all() {
        RouteSearch routeSearch = new RouteSearch(this);
        routeSearch.setRouteSearchListener(new AllSearchListenr());
        RouteSearch.DriveRouteQuery driveRouteQuery = new
                RouteSearch.DriveRouteQuery(new RouteSearch.FromAndTo
                (new LatLonPoint(LConstants.BIGUIYUAN.latitude, LConstants.BIGUIYUAN.longitude)
                        , new LatLonPoint(LConstants.WANFUJING.latitude, LConstants.WANFUJING.longitude)), 2, null, null, "");
        routeSearch.calculateDriveRouteAsyn(driveRouteQuery);
        RouteSearch.DriveRouteQuery driveRouteQuery2 = new
                RouteSearch.DriveRouteQuery(new RouteSearch.FromAndTo
                (new LatLonPoint(LConstants.BIGUIYUAN.latitude, LConstants.BIGUIYUAN.longitude)
                        , new LatLonPoint(LConstants.MEISHAN.latitude, LConstants.MEISHAN.longitude)), 2, null, null, "");
        routeSearch.calculateDriveRouteAsyn(driveRouteQuery2);
        RouteSearch.DriveRouteQuery driveRouteQuery3 = new
                RouteSearch.DriveRouteQuery(new RouteSearch.FromAndTo
                (new LatLonPoint(LConstants.BIGUIYUAN.latitude, LConstants.BIGUIYUAN.longitude)
                        , new LatLonPoint(LConstants.XILINGXUESHAN.latitude, LConstants.XILINGXUESHAN.longitude)), 2, null, null, "");
        routeSearch.calculateDriveRouteAsyn(driveRouteQuery3);
    }

    @Override
    public void onClick(View view) {
        car.setSelected(false);
        bus.setSelected(false);
        walk.setSelected(false);
        switch (view.getId()) {

            case R.id.car:
                car.setSelected(true);
                tuijian(CAR);
                break;
            case R.id.bus:
                bus.setSelected(true);
                tuijian(BUS);
                break;
            case R.id.walk:
                walk.setSelected(true);
                tuijian(WALK);
                break;
        }
    }

    private class XingChengAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public XingChengAdapter(@Nullable List<String> data) {
            super(R.layout.xingcheng_item, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final String item) {
            if (helper.getAdapterPosition() == 0) {
                helper.setGone(R.id.zhiding, false);
            } else {
                helper.setGone(R.id.zhiding, true);
            }
            helper.setText(R.id.title, (helper.getAdapterPosition() + 1) + "." + item);
            helper.getView(R.id.zhiding).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Collections.swap(titles, helper.getAdapterPosition(), 0);
                    notifyDataSetChanged();

                    LogUtil.e("变换之后的数组：：：" + titles);
                }
            });
            helper.getView(R.id.delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    titles.remove(item);
                    notifyDataSetChanged();
                    LogUtil.e("删除之后的数组：：：" + titles);
                }
            });
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
