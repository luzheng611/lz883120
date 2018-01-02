package com.quzhao.Fragement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.quzhao.Activity.Detail.ShangShi_Detail;
import com.quzhao.Adapter.CyclePageAdapter;
import com.quzhao.Base.ICardView;
import com.quzhao.R;
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
        view=inflater.inflate(R.layout.fragement_one,null);
        cardView=view.findViewById(R.id.cardview);
        ICardView.initCardView(getActivity(),cardView);

        initBanner();
        initCuXiao();

        return view;

    }

    private void initCuXiao() {
        ArrayList<HashMap<String,String >> list=new ArrayList<>();
        for(int i=0;i<10;i++){
            HashMap<String,String > map=new HashMap<>();
            list.add(map);
        }
        RecyclerView recyclerView=view.findViewById(R.id.recycle);
        final CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, true);
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());

        adapter=new CyclePageAdapter(getActivity(),list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new CenterScrollListener());
    }

    private void initBanner() {
        banner=view.findViewById(R.id.banner);
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ArrayList images=new ArrayList();
        ArrayList titles=new ArrayList();
        images.add(R.drawable.test_xiezi);
        images.add(R.drawable.test_main_1);
        images.add(R.drawable.test_main_2);
        titles.add("标题1");
        titles.add("标题2");
        titles.add("标题3");
        banner.setBannerTitles(titles);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                imageView.setImageResource((Integer) path);
            }
        });
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                startActivity(new Intent(getActivity(), ShangShi_Detail.class));
                Toast.makeText(getActivity(), "点击商市广告页"+position, Toast.LENGTH_SHORT).show();
            }
        });
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setImages(images);
        banner.start();
    }
}
