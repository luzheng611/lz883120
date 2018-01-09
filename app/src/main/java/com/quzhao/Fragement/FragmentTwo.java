package com.quzhao.Fragement;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.cpiz.android.bubbleview.BubblePopupWindow;
import com.cpiz.android.bubbleview.BubbleStyle;
import com.quzhao.R;
import com.quzhao.Util.IUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/12/29.
 * 活动
 */

public class FragmentTwo extends Fragment implements View.OnClickListener {
    private View view;
    private Banner banner;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView shaixuan;
    private String titls[]= new String[]{"商圈","商铺","汽车","家居","服装"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragement_two,null);

        shaixuan=view.findViewById(R.id.shaixuan);
        Drawable drawable= ContextCompat.getDrawable(getActivity(),R.drawable.shaixuan_gray);
        drawable.setBounds(0,0,IUtils.dip2px(getActivity(),10),IUtils.dip2px(getActivity(),10)
        *drawable.getIntrinsicHeight()/drawable.getIntrinsicWidth());
        shaixuan.setCompoundDrawables(null,null,drawable,null);
        shaixuan.setOnClickListener(this);
        banner=view.findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.RIGHT);

        final ArrayList images=new ArrayList();
        images.add(R.drawable.test_two_banner);
        images.add("http://img5.imgtn.bdimg.com/it/u=2951020257,3752145132&fm=27&gp=0.jpg");
        images.add("http://img5.imgtn.bdimg.com/it/u=1668560658,2152945151&fm=27&gp=0.jpg");
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, final ImageView imageView) {
                Glide.with(getActivity()).asBitmap().load(path)
                        .apply(new RequestOptions().centerCrop()
                                .override(getResources().getDisplayMetrics().widthPixels
                                        , IUtils.dip2px(getActivity(), 80)))
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.shaixuan:
                View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.down_popop, null);
                final TextView zuijin=rootView.findViewById(R.id.zuijin);
                final TextView zuire=rootView.findViewById(R.id.zuire);
                final TextView zonghe=rootView.findViewById(R.id.zonghe);

               final BubblePopupWindow window = new BubblePopupWindow(rootView, (BubbleStyle) rootView.findViewById(R.id.layout));
                window.setCancelOnTouch(true);
                window.setCancelOnTouchOutside(true);
                window.setCancelOnLater(0);
                window.showArrowTo(view, BubbleStyle.ArrowDirection.Up);
                zuijin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        window.dismiss();
                        shaixuan.setText(zuijin.getText().toString());
                    }
                });
                zuire.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        window.dismiss();
                        shaixuan.setText(zuire.getText().toString());
                    }
                });
                zonghe.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        window.dismiss();
                        shaixuan.setText(zonghe.getText().toString());
                    }
                });
                break;

        }
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
