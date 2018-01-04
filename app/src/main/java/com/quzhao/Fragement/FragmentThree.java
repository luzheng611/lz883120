package com.quzhao.Fragement;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.quzhao.R;

import java.util.List;

/**
 * Created by Administrator on 2017/12/29.
 * 消息
 */

public class FragmentThree extends Fragment {
    private LinearLayout headView ;
    private View view;
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_huati,null);
        recyclerView=view.findViewById(R.id.recyclerView);


        return super.onCreateView(inflater, container, savedInstanceState);

    }


    private class  HuaTiAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity,BaseViewHolder> {
        /**
         * Same as QuickAdapter#QuickAdapter(Context,int) but with
         * some initialization data.
         *
         * @param data A new list is created out of this one to avoid mutable list
         */
        public HuaTiAdapter(List<MultiItemEntity> data) {
            super(data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MultiItemEntity item) {

        }
    }
}
