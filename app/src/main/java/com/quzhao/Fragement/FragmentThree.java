package com.quzhao.Fragement;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.quzhao.Adapter.CyclePageAdapter;
import com.quzhao.CustomUI.UPMarqueeView;
import com.quzhao.CustomUI.mItemDecoration;
import com.quzhao.R;
import com.quzhao.Util.IUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/12/29.
 * 消息
 */

public class FragmentThree extends Fragment {
    private LinearLayout headView ;
    private View view;
    private RecyclerView recyclerView;
    private HuaTiAdapter adapter;
    private TextView floatLine;//顶部通知
    private UPMarqueeView marqueeView;
    private RecyclerView horizonalView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_huati,null);
        headView= (LinearLayout) inflater.inflate(R.layout.headview_huati,null);
        recyclerView=view.findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new mItemDecoration(getActivity()));
         adapter=new HuaTiAdapter(getActivity(),initData());
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recyclerView.setAdapter(adapter);
        initHeadView();
        adapter.addHeaderView(headView);




        return view;

    }

    private void initHeadView() {
        floatLine=headView.findViewById(R.id.huati_headline);
        marqueeView=headView.findViewById(R.id.viewflipper);
        horizonalView=headView.findViewById(R.id.horizonal_view);
        ArrayList list=new ArrayList();


        ArrayList<String > data = new ArrayList<>();
        data.add("家人给2岁孩子喝这个，孩子智力倒退10岁!!!");
        data.add("iPhone8最感人变化成真，必须买买买买!!!!");
        data.add("简直是白菜价！日本玩家33万甩卖15万张游戏王卡");
        data.add("iPhone7价格曝光了！看完感觉我的腰子有点疼...");
        data.add("主人内疚逃命时没带够，回废墟狂挖30小时！");
        setView(data);


        for(int i=0;i<20;i++){
            HashMap<String,String > map=new HashMap<>();
            list.add(map);
        }
        CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, true);
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());

        CyclePageAdapter adapter=new CyclePageAdapter(getActivity(),list);
        horizonalView.setLayoutManager(layoutManager);
        horizonalView.setHasFixedSize(true);
        horizonalView.setAdapter(adapter);
        horizonalView.addOnScrollListener(new CenterScrollListener());


    }
    /**
     * 初始化需要循环的View
     * 为了灵活的使用滚动的View，所以把滚动的内容让用户自定义
     * 假如滚动的是三条或者一条，或者是其他，只需要把对应的布局，和这个方法稍微改改就可以了，
     */
    private void setView(ArrayList<String> data) {
        ArrayList<View> views=new ArrayList<>();
        for (int i = 0; i < data.size(); i = i + 2) {
            //设置滚动的单个布局
            LinearLayout moreView = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.huati_line_item, null);
            //初始化布局的控件
            TextView tv1 = (TextView) moreView.findViewById(R.id.textview1);
            TextView tv2 = (TextView) moreView.findViewById(R.id.textview2);
            //进行对控件赋值
            tv1.setText(data.get(i).toString());
            if (data.size() > i + 1) {
                //因为淘宝那儿是两条数据，但是当数据是奇数时就不需要赋值第二个，所以加了一个判断，还应该把第二个布局给隐藏掉
                tv2.setText(data.get(i + 1).toString());
            }else {
                moreView.findViewById(R.id.ll2).setVisibility(View.GONE);
            }

            //添加到循环滚动数组里面去
            views.add(moreView);
        }
        marqueeView.setViews(views);
    }
    private List<MultiItemEntity> initData() {
        ArrayList<MultiItemEntity> data=new ArrayList<>();
        ArrayList<String > urls=new ArrayList<>();
        urls.add("http://img2.imgtn.bdimg.com/it/u=2698801140,2608849818&fm=27&gp=0.jpg");
        urls.add("data:image/jpeg;base64,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");
        urls.add("http://img5.imgtn.bdimg.com/it/u=3145931361,654738176&fm=27&gp=0.jpg");
        HuaTiAdapter.OrignalItem item1=new HuaTiAdapter.OrignalItem();
        item1.setTitle("三明6大商圈现状大对比!如今最火爆的是它？");
        item1.setTime("2017年9月20日 13:45:32");
        item1.setTag("旅游网");
        item1.setComment(new Random().nextInt(1000)+"");
        item1.setUrl("http://img2.imgtn.bdimg.com/it/u=2698801140,2608849818&fm=27&gp=0.jpg");
        HuaTiAdapter.OrignalItem item2=new HuaTiAdapter.OrignalItem();
        item2.setTitle("《变形金刚汽车人联盟巡展》全球首发落户重庆");
        item2.setTime("2018年1月2日 16:32:42");
        item2.setTag("旅游网");
        item2.setComment(new Random().nextInt(1000)+"");
        item2.setUrl("http://img5.imgtn.bdimg.com/it/u=3145931361,654738176&fm=27&gp=0.jpg");
        HuaTiAdapter.ThreeImageItem item3=new HuaTiAdapter.ThreeImageItem();
        item3.setTitle("2017的最后几天，快来龙湖u城天街");
        item3.setTime("2017年12月28日 12:32:42");
        item3.setTag("旅游网");
        item3.setComment(new Random().nextInt(1000)+"");
        item3.setUrls(urls);
        data.add(item1);
        data.add(item2);
        data.add(item3);
        return data;
    }


    private static class  HuaTiAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity,BaseViewHolder> {
        private Activity activity;
        private int dp120,dp90,dp20;
        private Drawable dra_comment;
        /**
         * Same as QuickAdapter#QuickAdapter(Context,int) but with
         * some initialization data.
         *
         * @param data A new list is created out of this one to avoid mutable list
         */
        public HuaTiAdapter(Activity activity,List<MultiItemEntity> data) {
            super(data);
            this.activity=activity;
            addItemType(0,R.layout.item_huati_original);
            addItemType(1,R.layout.item_huati_three_images);
            dp20=IUtils.dip2px(activity,15);
            dp120=IUtils.dip2px(activity,120);
            dp90=IUtils.dip2px(activity,90);
            dra_comment= ContextCompat.getDrawable(activity,R.drawable.pinglun_gray);
            dra_comment.setBounds(0,0,dp20,dp20*dra_comment.getIntrinsicHeight()/dra_comment.getIntrinsicWidth());
        }

        @Override
        protected void convert(BaseViewHolder holder, MultiItemEntity item) {
            switch (item.getItemType()){
                case 0:
                    OrignalItem o= (OrignalItem) item;
                    ((TextView) holder.getView(R.id.comment)).setCompoundDrawables(dra_comment,null,null,null);
                    holder.setText(R.id.title,o.getTitle())
                            .setText(R.id.time,o.getTime())
                            .setText(R.id.tag,o.getTag())
                            .setText(R.id.comment,o.getComment());
                    Glide.with(activity).load(o.getUrl()).apply(new RequestOptions().centerCrop()
                            .override(dp120,dp90))
                            .into((ImageView) holder.getView(R.id.image));
                    break;
                case 1:
                    ThreeImageItem t= (ThreeImageItem) item;
                    ((TextView) holder.getView(R.id.comment)).setCompoundDrawables(dra_comment,null,null,null);
                    holder.setText(R.id.title,t.getTitle())
                            .setText(R.id.time,t.getTime())
                            .setText(R.id.tag,t.getTag())
                            .setText(R.id.comment,t.getComment());
                    LinearLayout linearLayout=holder.getView(R.id.images);
                    int width=(activity.getResources().getDisplayMetrics().widthPixels
                            -IUtils.dip2px(activity,50))/3;
                    int height=width*3/4;
                    for(String url:t.getUrls()){
                        ImageView imageView=new ImageView(activity);
                        ViewGroup.MarginLayoutParams vl=new LinearLayout.LayoutParams(width,height);
                        vl.leftMargin=IUtils.dip2px(activity,5);
                        vl.rightMargin=IUtils.dip2px(activity,5);
                        imageView.setLayoutParams(vl);
                        Glide.with(activity).load(url).apply(new RequestOptions().centerCrop()
                                .override(width,height))
                                .into(imageView);
                        linearLayout.addView(imageView);
                    }


                    break;
            }
        }


        private static class OrignalItem implements MultiItemEntity{
            private String title;
            private String time;
            private String tag;
            private String comment;
            private String url;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            @Override
            public int getItemType() {
                return 0;
            }
        }
        private static class ThreeImageItem implements MultiItemEntity{
            private String title;
            private String time;
            private String tag;
            private String comment;
            private ArrayList<String> urls;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public ArrayList<String> getUrls() {
                return urls;
            }

            public void setUrls(ArrayList<String> urls) {
                this.urls = urls;
            }

            @Override
            public int getItemType() {
                return 1;
            }
        }
    }
}
