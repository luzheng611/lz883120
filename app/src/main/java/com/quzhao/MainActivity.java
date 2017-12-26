package com.quzhao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.quzhao.Adapter.CyclePageAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList<HashMap<String,String >> list=new ArrayList<>();
        for(int i=0;i<20;i++){
            HashMap<String,String > map=new HashMap<>();
            list.add(map);
        }
        RecyclerView recyclerView=findViewById(R.id.recycler);
        final CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, true);
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());


        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new CyclePageAdapter(list));
        recyclerView.addOnScrollListener(new CenterScrollListener());




    }

    public void  initMain(){

    }
}
