package com.quzhao.Helper.SpanHelpers;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.quzhao.R;

public class WeiBoContentClickableSpan extends ClickableSpan {

private Context mContext;

public WeiBoContentClickableSpan(Context context) {
    mContext = context;
}

@Override
public void onClick(View widget) {

}

@Override
public void updateDrawState(TextPaint ds) {
    super.updateDrawState(ds);
    ds.setColor(ContextCompat.getColor(mContext, R.color.main_color));
    ds.setUnderlineText(false);
}
}