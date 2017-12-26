package com.quzhao.Adapter;

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

    public CyclePageAdapter(@Nullable List<HashMap<String, String>> data) {
        super(R.layout.item_gallery, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HashMap<String, String> item) {

    }
}
