package com.quzhao.Fragement;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.quzhao.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/12/29.
 * 活动
 */

public class FragmentTwo extends Fragment {
    private View view;
    private Banner banner;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private String titls[]= new String[]{"商圈","商铺","汽车","家居","服装"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragement_two,null);


        banner=view.findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        final ArrayList images=new ArrayList();
        images.add(R.drawable.test_two_banner);
        images.add(R.drawable.test_two_banner);
        images.add(R.drawable.test_two_banner);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                imageView.setImageResource((Integer) path);
            }
        });
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(getActivity(),"点击活动广告页"+position,Toast.LENGTH_SHORT).show();
            }
        });
        banner.setImages(images);
        banner.start();

        viewPager=view.findViewById(R.id.viewpager);
        ArrayList<Fragment> fragments=new ArrayList<>();
        Fragment_two_classfy fragment_two_classfy=new Fragment_two_classfy();
        Bundle bundle=new Bundle();
        bundle.putString("type","商圈");
        fragment_two_classfy.setArguments(bundle);
        fragments.add(fragment_two_classfy);
        fragments.add(new Fragment_two_classfy());
        fragments.add(new Fragment_two_classfy());
        fragments.add(new Fragment_two_classfy());
        fragments.add(new Fragment_two_classfy());
        viewPager.setOffscreenPageLimit(fragments.size());

        tabLayout=view.findViewById(R.id.tabs);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(new pagerAdapter(getActivity(),getChildFragmentManager(),fragments));

        return view;

    }

    // TODO: 2017/4/25 活动 分类页面适配器
    private class pagerAdapter extends FragmentPagerAdapter {


            private ArrayList<Fragment> list;

            private Context context;
            public pagerAdapter(Context context,FragmentManager fm, ArrayList<Fragment> list) {
                super(fm);
                this.list = list;
                this.context=context;

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


    }
}
