package com.quzhao.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quzhao.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Administrator on 2017/12/26.
 */

public class ICustomViewPagerAdapter extends PagerAdapter  {
    private LinkedList<View> caches;
    private ArrayList<HashMap<String,String >> list;
    private Context context;
    private LayoutInflater inflater;
    public ICustomViewPagerAdapter(Context context, ArrayList<HashMap<String,String >> list) {
        super();
        this.context=context;
        this.list=list;
        caches=new LinkedList<>();
        inflater=LayoutInflater.from(context);
    }



    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView;
        if(caches.size()>0){
            itemView=caches.getFirst();
            caches.removeFirst();
        }else{
            itemView= inflater.inflate(R.layout.item_gallery,null);
        }
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
        if(object!=null){
            caches.addLast((View) object);
        }
    }



}
