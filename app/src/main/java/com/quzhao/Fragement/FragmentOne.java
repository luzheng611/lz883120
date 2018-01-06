package com.quzhao.Fragement;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.quzhao.Activity.Detail.ShangShi_Detail;
import com.quzhao.Activity.Detail.ShangShi_Detail_Search;
import com.quzhao.Adapter.CyclePageAdapter;
import com.quzhao.Base.ICardView;
import com.quzhao.R;
import com.quzhao.Util.IUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/12/29.
 * 商市
 */

public class FragmentOne extends Fragment {
    private View view;
    private CardView cardView;
    private Banner banner;
    private CyclePageAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragement_one, null);
        cardView = view.findViewById(R.id.cardview);
        ICardView.initCardView(getActivity(), cardView);

        initBanner();
        initCuXiao();
        view.findViewById(R.id.shangshi_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ShangShi_Detail_Search.class));
            }
        });
        return view;

    }

    private void initCuXiao() {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, String> map = new HashMap<>();
            list.add(map);
        }
        RecyclerView recyclerView = view.findViewById(R.id.recycle);
        final CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, true);
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());

        adapter = new CyclePageAdapter(getActivity(), list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new CenterScrollListener());
    }

    private void initBanner() {
        final ArrayList<HashMap<String, String>> maps = new ArrayList<>();

        HashMap<String, String> map = new HashMap<>();
        map.put("location", "武侯区天府三街");
        map.put("distance", "0.8km");
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("location", "高新区桐梓林");
        map1.put("distance", "4.8km");
        HashMap<String, String> map3 = new HashMap<>();
        map3.put("location", "锦江区天府广场");
        map3.put("distance", "10.8km");
        maps.add(map);
        maps.add(map1);
        maps.add(map3);

        banner = view.findViewById(R.id.banner);
        final TextView loaction = banner.findViewById(R.id.m_location);
        final TextView distance = banner.findViewById(R.id.m_distance);
        Drawable drawable= ContextCompat.getDrawable(getActivity(),R.drawable.location_white);
        drawable.setBounds(0,0,IUtils.dip2px(getActivity(),12),IUtils.dip2px(getActivity(),12)
        *drawable.getIntrinsicHeight()/drawable.getIntrinsicWidth());
        loaction.setCompoundDrawables(drawable,null,null,null);
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                loaction.setText(maps.get(position).get("location"));
                distance.setText(maps.get(position).get("distance"));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        final ArrayList images = new ArrayList();
        ArrayList titles = new ArrayList();
        images.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3427925100,1586540657&fm=27&gp=0.jpg");
        images.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1528842545,3940220255&fm=200&gp=0.jpg");
        images.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1567535791,3145216400&fm=200&gp=0.jpg");
        titles.add("武侯区沃尔玛超市");
        titles.add("高新区家乐福超市");
        titles.add("锦江区欧尚超市");
        banner.setBannerTitles(titles);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, final ImageView imageView) {
                Glide.with(getActivity()).asBitmap().load(path)
                        .apply(new RequestOptions().centerCrop()
                                .override(getResources().getDisplayMetrics().widthPixels - IUtils.dip2px(getActivity(), 20)
                                        , IUtils.dip2px(getActivity(), 180)))
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                                RoundedBitmapDrawable rbd = RoundedBitmapDrawableFactory.create(getResources(), resource);
                                rbd.setCornerRadius(IUtils.dip2px(getActivity(), 3));
                                imageView.setImageDrawable(rbd);
                            }
                        });

            }
        });
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                startActivity(new Intent(getActivity(), ShangShi_Detail.class));
            }
        });
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setImages(images);
        banner.start();
    }
}
