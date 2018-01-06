package com.quzhao.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quzhao.R;
import com.quzhao.Util.IUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/12/27.
 */

public class CyclePageAdapter extends BaseQuickAdapter<HashMap<String,String >,BaseViewHolder> {
    private Context context;
    private String images[]=new String[]{
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3367563809,1871687962&fm=27&gp=0.jpg"
            ,"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=347269054,3267518575&fm=27&gp=0.jpg"
            ,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3768672931,1765052502&fm=27&gp=0.jpg"
            ,"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2951020257,3752145132&fm=27&gp=0.jpg"
            ,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=515049035,402137590&fm=27&gp=0.jpg"
    };
    public CyclePageAdapter(Context context,@Nullable List<HashMap<String, String>> data) {
        super(R.layout.item_gallery, data);
        this.context=context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, HashMap<String, String> item) {
        Glide.with(context)
                .load(images[helper.getAdapterPosition()%images.length])
                .apply(new RequestOptions().centerCrop().override(IUtils.dip2px(context,180),IUtils.dip2px(context,180)
                )).into((ImageView) helper.getView(R.id.image));
        helper.getView(R.id.image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "点击促销活动"+helper.getAdapterPosition(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
