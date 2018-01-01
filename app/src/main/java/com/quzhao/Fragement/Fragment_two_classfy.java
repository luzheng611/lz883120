package com.quzhao.Fragement;

import android.content.Intent;
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
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quzhao.Activity.Detail.Classfy_ShangQuan;
import com.quzhao.Activity.Detail.Classfy_ShangQuan2;
import com.quzhao.R;
import com.quzhao.Util.IUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/12/31.
 */

public class Fragment_two_classfy extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private ClassfyAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_two_classfy,null);
        recyclerView=view.findViewById(R.id.recycle);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<HashMap<String,String>> list=new ArrayList<>();
        for(int i=0;i<20;i++){
            HashMap<String,String > map=new HashMap<>();
            list.add(map);
        }
        adapter=new ClassfyAdapter(list);
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(getArguments()!=null){
                    startActivity(new Intent(getActivity(), Classfy_ShangQuan.class));
                }else{
                    Intent intent=new Intent(getActivity(), Classfy_ShangQuan2.class);
                    startActivity(intent);
                }

            }
        });

        return view;
    }


    private class ClassfyAdapter extends BaseQuickAdapter<HashMap<String,String > ,BaseViewHolder> {
        private Drawable zhe ,song;
        public ClassfyAdapter(@Nullable List<HashMap<String, String>> data) {
            super(R.layout.item_fragment_two_classfy, data);
            zhe= ContextCompat.getDrawable(getActivity(),R.drawable.text_zhe);
            song= ContextCompat.getDrawable(getActivity(),R.drawable.test_song);
            int dp= IUtils.dip2px(getActivity(),16);
            zhe.setBounds(0,0,dp,dp);
            song.setBounds(0,0,dp,dp);
        }

        @Override
        protected void convert(BaseViewHolder helper, HashMap<String, String> item) {
            ((TextView) helper.getView(R.id.cuxiao)).setCompoundDrawables(zhe,null,null,null);
            ((TextView) helper.getView(R.id.cuxiao)).setCompoundDrawablePadding(IUtils.dip2px(getActivity(),8));
            ((TextView) helper.getView(R.id.cuxiao2)).setCompoundDrawables(song,null,null,null);
            ((TextView) helper.getView(R.id.cuxiao2)).setCompoundDrawablePadding(IUtils.dip2px(getActivity(),8));
        }
    }
}
