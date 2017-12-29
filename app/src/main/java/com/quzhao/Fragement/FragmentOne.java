package com.quzhao.Fragement;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.quzhao.Adapter.CyclePageAdapter;
import com.quzhao.Base.ICardView;
import com.quzhao.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/12/29.
 * 商市
 */

public class FragmentOne extends Fragment {
    private View view;
    private CardView cardView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragement_one,null);
        cardView=view.findViewById(R.id.cardview);
        ICardView.initCardView(getActivity(),cardView);

        ArrayList<HashMap<String,String >> list=new ArrayList<>();
        for(int i=0;i<20;i++){
            HashMap<String,String > map=new HashMap<>();
            list.add(map);
        }
        RecyclerView recyclerView=view.findViewById(R.id.recycle);
        final CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, true);
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());


        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new CyclePageAdapter(list));
        recyclerView.addOnScrollListener(new CenterScrollListener());
        return view;

    }
}
