package com.quzhao.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.quzhao.Base.BaseActivity;
import com.quzhao.Fragement.FragmentFour;
import com.quzhao.Fragement.FragmentOne;
import com.quzhao.Fragement.FragmentThree;
import com.quzhao.Fragement.FragmentTwo;
import com.quzhao.R;
import com.quzhao.Util.IUtils;
import com.zaaach.citypicker.CityPickerActivity;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements AMapLocationListener {
    /*
    tabs
     */
    private ViewPager pager;
    private TabLayout tabLayout;
    private mHomePageAdapter adapter;
    private ArrayList<Fragment> list;
    private boolean needExit = false;
    /*
    头部
     */
    private TextView location;//定位
    private TextView title;//标题
    private ImageView search;//搜索
    private static final int REQUEST_CODE_PICK_CITY = 0;//启动城市选择页面

    /*
    定位相关
     */
    private AMapLocationClientOption mLocationOption;//声明mLocationOption对象
    private AMapLocationClient mlocationClient;
    private int getLocationDelay=5000;//定位请求间隔时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initMain();


    }

    public void initMain() {
        initTabs();
        initHead();
        getLoaction();
    }

    //定位
    private void getLoaction() {


        mlocationClient = new AMapLocationClient(this);
//初始化定位参数
        mLocationOption = new AMapLocationClientOption();
//设置返回地址信息，默认为true
        mLocationOption.setNeedAddress(true);
//设置定位监听
        mlocationClient.setLocationListener(this);
//设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//设置定位间隔,单位毫秒,默认为2000ms
//        mLocationOption.setInterval(getLocationDelay);
        mLocationOption.setOnceLocationLatest(true);
//设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
// 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
// 在定位结束后，在合适的生命周期调用onDestroy()方法
// 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
//启动定位
        mlocationClient.startLocation();

    }

    private void initHead() {
        location = findViewById(R.id.location);
        title = findViewById(R.id.title);
        search = findViewById(R.id.search);
        Drawable loc = ContextCompat.getDrawable(this, R.drawable.location);
        int dp16 = IUtils.dip2px(this, 16);
        int dp10 = IUtils.dip2px(this, 12);
        loc.setBounds(0, 0, dp16, dp16 * loc.getIntrinsicHeight() / loc.getIntrinsicWidth());
        Drawable down = ContextCompat.getDrawable(this, R.drawable.down_white);
        down.setBounds(0, 0, dp10, dp10 * down.getIntrinsicHeight() / down.getIntrinsicWidth());
        location.setCompoundDrawables(loc, null, down, null);
        location.setCompoundDrawablePadding(IUtils.dip2px(this, 5));
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //启动城市选择页面
                startActivityForResult(new Intent(MainActivity.this, CityPickerActivity.class),
                        REQUEST_CODE_PICK_CITY);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, Search.class));
                startActivity(new Intent(MainActivity.this, MapTest.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK) {
            if (data != null) {
                String city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                location.setText(city);
            }
        }
    }

    private void initTabs() {
        pager = (ViewPager) findViewById(R.id.home_viewpager);
        tabLayout = (TabLayout) findViewById(R.id.home_bottom_tablayout);
        FragmentOne f1 = new FragmentOne();
        FragmentTwo f2 = new FragmentTwo();
        FragmentThree f3 = new FragmentThree();
        FragmentFour f4 = new FragmentFour();
        list = new ArrayList<>();
        list.add(f1);
        list.add(f2);
        list.add(f3);
        list.add(f4);
        adapter = new mHomePageAdapter(this, getSupportFragmentManager(), list);
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(list.size());
        tabLayout.setupWithViewPager(pager);
        //设置自定义tab
        for (int i = 0; i < list.size(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null)
                tab.setCustomView(adapter.getTabView(i));
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                ((TextView) tab.getCustomView().findViewById(R.id.home_tab_text)).setTextColor(getResources().getColor(R.color.main_color));
                pager.setCurrentItem(tab.getPosition());
                title.setText(adapter.getPageTitle(tab.getPosition()));
                tab.getCustomView().setSelected(true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ((TextView) tab.getCustomView().findViewById(R.id.home_tab_text)).setTextColor(getResources().getColor(R.color.gray));
                tab.getCustomView().setSelected(false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        pager.requestDisallowInterceptTouchEvent(true);
        pager.setCurrentItem(0);
        tabLayout.getTabAt(0).getCustomView().setSelected(true);
        ((TextView) tabLayout.getTabAt(0).getCustomView().findViewById(R.id.home_tab_text)).setTextColor(getResources().getColor(R.color.main_color));
    }

    @Override
    public void onBackPressed() {
        if (needExit) {
            finish();
            return;
        }
        Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
        needExit = true;
        tabLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                needExit = false;
            }
        }, 2000);
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
               // amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                //amapLocation.getLatitude();//获取纬度
              //  amapLocation.getLongitude();//获取经度
              //  amapLocation.getAccuracy();//获取精度信息
               // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
              //  Date date = new Date(amapLocation.getTime());
               // df.format(date);//定位时间
                //amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
               // amapLocation.getCountry();//国家信息
                //amapLocation.getProvince();//省信息
                amapLocation.getCity();//城市信息
                //amapLocation.getDistrict();//城区信息
                //amapLocation.getStreet();//街道信息
               // amapLocation.getStreetNum();//街道门牌号信息
                amapLocation.getCityCode();//城市编码
                //amapLocation.getAdCode();//地区编码
                //amapLocation.getAOIName();//获取当前定位点的AOI信息
                final String city=amapLocation.getCity();
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setMessage("您定位到："+city+",确定切换到"+city+"吗").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        location.setText(city);//确认定位，设置显示当前定位
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //定位不符合，启动城市选择页面
                        startActivityForResult(new Intent(MainActivity.this, CityPickerActivity.class),
                                REQUEST_CODE_PICK_CITY);
                    }
                }).create().show();
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }

    // TODO: 2017/4/25 首页 页面适配器
    public static class mHomePageAdapter extends FragmentPagerAdapter {
        private int images[] = {R.drawable.sel_tab1, R.drawable.sel_tab2,
                R.drawable.sel_tab3, R.drawable.sel_tab4};
        private ArrayList<Fragment> list;
        private String titls[];
        private Context context;

        public mHomePageAdapter(Context context, FragmentManager fm, ArrayList<Fragment> list) {
            super(fm);
            this.list = list;
            this.context = context;
            titls = context.getResources().getStringArray(R.array.bottom_title);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list == null ? 0 : list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titls[position];
        }

        public View getTabView(int position) {
            View view = LayoutInflater.from(context).inflate(R.layout.home_tab_customview, null);
            ImageView img = (ImageView) view.findViewById(R.id.home_tab_image);
            TextView text = (TextView) view.findViewById(R.id.home_tab_text);
            text.setText(getPageTitle(position));
            img.setImageResource(images[position]);
            return view;
        }

    }
}
