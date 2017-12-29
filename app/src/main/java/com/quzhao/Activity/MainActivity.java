package com.quzhao.Activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.quzhao.Base.BaseActivity;
import com.quzhao.Fragement.FragmentFour;
import com.quzhao.Fragement.FragmentOne;
import com.quzhao.Fragement.FragmentThree;
import com.quzhao.Fragement.FragmentTwo;
import com.quzhao.R;
import com.quzhao.Util.IUtils;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    /*
    tabs
     */
    private ViewPager pager;
    private TabLayout tabLayout;
    private mHomePageAdapter adapter;
    private ArrayList<Fragment> list;
    private boolean needExit=false;
    /*
    头部
     */
    private TextView location;//定位
    private TextView title;//标题
    private ImageView search;//搜索

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




            initMain();


    }

    public void  initMain(){
        initTabs();
        initHead();
    }

    private void initHead() {
        location =findViewById(R.id.location);
        title=findViewById(R.id.title);
        search=findViewById(R.id.search);
        Drawable loc= ContextCompat.getDrawable(this,R.drawable.location);
        int dp16 =IUtils.dip2px(this,16);
        int dp10 =IUtils.dip2px(this,12);
        loc.setBounds(0,0, dp16, dp16 *loc.getIntrinsicHeight()/loc.getIntrinsicWidth());
        Drawable down=ContextCompat.getDrawable(this,R.drawable.down_white);
        down.setBounds(0,0, dp10, dp10 *down.getIntrinsicHeight()/down.getIntrinsicWidth());
        location.setCompoundDrawables(loc,null,down,null);
        location.setCompoundDrawablePadding(IUtils.dip2px(this,5));
    }

    private void initTabs() {
        pager = (ViewPager) findViewById(R.id.home_viewpager);
        tabLayout = (TabLayout) findViewById(R.id.home_bottom_tablayout);
        FragmentOne f1=new FragmentOne();
        FragmentTwo f2=new FragmentTwo();
        FragmentThree f3=new FragmentThree();
        FragmentFour f4=new FragmentFour();
        list=new ArrayList<>();
        list.add(f1);
        list.add(f2);
        list.add(f3);
        list.add(f4);
        adapter=new mHomePageAdapter(this,getSupportFragmentManager(),list);
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
        pager.setCurrentItem(0);
        tabLayout.getTabAt(0).getCustomView().setSelected(true);
        ((TextView) tabLayout.getTabAt(0).getCustomView().findViewById(R.id.home_tab_text)).setTextColor(getResources().getColor(R.color.main_color));
    }
    @Override
    public void onBackPressed() {
        if (needExit) {
            finish();
            return ;
        }
        Toast.makeText(this,"再按一次退出应用", Toast.LENGTH_SHORT).show();
        needExit = true;
        tabLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                needExit = false;
            }
        }, 2000);
    }

    // TODO: 2017/4/25 首页 页面适配器
    public  static class mHomePageAdapter extends FragmentPagerAdapter {
        private int images[]={R.drawable.sel_tab1,R.drawable.sel_tab2,
                R.drawable.sel_tab3,R.drawable.sel_tab4};
        private ArrayList<Fragment> list;
        private String titls[];
        private Context context;
        public mHomePageAdapter(Context context,FragmentManager fm, ArrayList<Fragment> list) {
            super(fm);
            this.list = list;
            this.context=context;
            titls= context.getResources().getStringArray(R.array.bottom_title);
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

        public View getTabView(int position){
            View view = LayoutInflater.from(context).inflate(R.layout.home_tab_customview, null);
            ImageView img = (ImageView) view.findViewById(R.id.home_tab_image);
            TextView text = (TextView) view.findViewById(R.id.home_tab_text);
            text.setText(getPageTitle(position));
            img.setImageResource(images[position]);
            return view;
        }

    }
}
