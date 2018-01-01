package com.quzhao.Activity.Detail;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quzhao.Base.BaseActivity;
import com.quzhao.Base.ICardView;
import com.quzhao.R;
import com.quzhao.Util.IUtils;
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

public class Classfy_ShangQuan2 extends BaseActivity {
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Banner banner;
    private ImageView back;
    private TextView openOrClose;
    private RecyclerView recyclerView;

    private TextView cuxiao, join;
    private ExpandbleAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shangquan_detail2);
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
                Toast.makeText(Classfy_ShangQuan2.this, "点击加入行程", Toast.LENGTH_SHORT).show();
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
        banner.setBannerStyle(BannerConfig.RIGHT);
        final ArrayList images = new ArrayList();
        images.add(R.drawable.test_detail);
        images.add(R.drawable.test_detail_2);
        images.add(R.drawable.test_detail);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                imageView.setImageResource((Integer) path);
            }
        });
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(Classfy_ShangQuan2.this, "点击详情广告页" + position, Toast.LENGTH_SHORT).show();
            }
        });
        banner.setImages(images);
        banner.start();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        String titles[] = new String[]{"促销信息", "商店布局图", "附近信息"};
        for (int i = 0; i < titles.length; i++) {
            HashMap<String, String> map = new HashMap<>();
            map.put("title", titles[i]);

            list.add(map);
        }
        adapter = new ExpandbleAdapter(this, list);
        adapter.setFooterView(ICardView.getNewInstance(this));

        recyclerView.setAdapter(adapter);



        openOrClose = findViewById(R.id.openOrClose);
        openOrClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<adapter.getData().size();i++){
                    RelativeLayout relativeLayout= (RelativeLayout) adapter.getViewByPosition(recyclerView,i,R.id.expandButton);
                    if(!openOrClose.isSelected()&&!relativeLayout.isSelected()){
                        relativeLayout.performClick();
                    }else if(openOrClose.isSelected()&&relativeLayout.isSelected()){
                        relativeLayout.performClick();
                    }
                }
                if(openOrClose.isSelected()){
                    openOrClose.setText("展开");
                    openOrClose.setSelected(false);
                }else{
                    openOrClose.setText("收起");
                    openOrClose.setSelected(true);
                }

            }
        });
    }


    public class ExpandbleAdapter extends BaseQuickAdapter<HashMap<String, String>, BaseViewHolder> implements ExpandableLayout.OnExpansionUpdateListener {
        private Context context;
        private int [] image=new int[]{R.drawable.test_recycle1,R.drawable.test_recycle2,R.drawable.test_recycle3};

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
                    HorizontalScrollView horizontalScrollView=new HorizontalScrollView(context);
                    horizontalScrollView.setHorizontalScrollBarEnabled(false);
                    LinearLayout linearLayout=new LinearLayout(context);
                    linearLayout.setPadding(0,IUtils.dip2px(context,10),0,IUtils.dip2px(context,10));
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    horizontalScrollView.addView(linearLayout);
                    for(int i=0;i<10;i++){
                        LinearLayout.LayoutParams ll=new LinearLayout.LayoutParams(IUtils.dip2px(context,60),IUtils.dip2px(context,60));
                        ImageView imageView=new ImageView(context);
                        ll.leftMargin=IUtils.dip2px(context,10);
                        ll.rightMargin=IUtils.dip2px(context,10);
                        imageView.setLayoutParams(ll);


                        imageView.setImageResource(image[i%image.length]);
                        linearLayout.addView(imageView);
                    }
                    expandableLayout.addView(horizontalScrollView);
                    break;
                case 1:
                    int width=context.getResources().getDisplayMetrics().widthPixels;
                    int height= ViewGroup.LayoutParams.WRAP_CONTENT;
                    ViewGroup.LayoutParams vl=new ViewGroup.LayoutParams(width,height);
                    ImageView imageView=new ImageView(context);
                    imageView.setAdjustViewBounds(true);
                    imageView.setImageResource(R.drawable.test_mall);
                    imageView.setLayoutParams(vl);
                    expandableLayout.addView(imageView);
                    break;
                case 2:
                    int width1=context.getResources().getDisplayMetrics().widthPixels;
                    int height1= ViewGroup.LayoutParams.WRAP_CONTENT;
                    ViewGroup.LayoutParams v1=new ViewGroup.LayoutParams(width1,height1);
                    ImageView imageView1=new ImageView(context);
                    imageView1.setAdjustViewBounds(true);
                    imageView1.setImageResource(R.drawable.test_mall);
                    imageView1.setLayoutParams(v1);
                    expandableLayout.addView(imageView1);

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
