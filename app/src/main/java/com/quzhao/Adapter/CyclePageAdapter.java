package com.quzhao.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quzhao.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/12/27.
 */

public class CyclePageAdapter extends BaseQuickAdapter<HashMap<String,String >,BaseViewHolder> {
    private Context context;
    public CyclePageAdapter(Context context,@Nullable List<HashMap<String, String>> data) {
        super(R.layout.item_gallery, data);
        this.context=context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, HashMap<String, String> item) {
//        helper.getView(R.id.image).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, "点击促销活动"+helper.getAdapterPosition(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
