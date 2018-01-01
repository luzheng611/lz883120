package com.quzhao.Activity.Detail;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quzhao.Base.ICardView;
import com.quzhao.R;
import com.quzhao.Util.IUtils;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by Administrator on 2018/1/1.
 */

public class Classfy_ShangQuan  extends AppCompatActivity {
    private TextView join;
    private ImageView back;
    private RecyclerView recyclerView;
    private ExpandbleAdapter adapter;
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
                Toast.makeText(Classfy_ShangQuan.this,"点击加入行程",Toast.LENGTH_SHORT).show();
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





    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    public class ExpandbleAdapter extends BaseQuickAdapter<HashMap<String, String>, BaseViewHolder> implements ExpandableLayout.OnExpansionUpdateListener {
        private Context context;


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
                    int width2=context.getResources().getDisplayMetrics().widthPixels;
                    int height2= ViewGroup.LayoutParams.WRAP_CONTENT;
                    ViewGroup.LayoutParams vl2=new ViewGroup.LayoutParams(width2,height2);
                    ImageView imageView2=new ImageView(context);
                    imageView2.setAdjustViewBounds(true);
                    imageView2.setImageResource(R.drawable.test_mall);
                    imageView2.setLayoutParams(vl2);
                    expandableLayout.addView(imageView2);
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
