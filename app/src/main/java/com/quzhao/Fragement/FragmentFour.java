package com.quzhao.Fragement;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quzhao.R;
import com.quzhao.Util.IUtils;

/**
 * Created by Administrator on 2017/12/29.
 * 我的
 */

public class FragmentFour extends Fragment {
    private View view;
    private Drawable next;
    private TextView invite;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragmeny_mine,null);
        invite=view.findViewById(R.id.invite);
        next= ContextCompat.getDrawable(getActivity(),R.drawable.next);
        int dp= IUtils.dip2px(getActivity(),10);
        next.setBounds(0,0,dp,dp*next.getIntrinsicHeight()/next.getIntrinsicWidth());
        invite.setCompoundDrawables(null,null,next,null);
        return view;

    }
}
