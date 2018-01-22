package com.quzhao.CustomUI.InDoor.Interface;

import android.graphics.Bitmap;

import com.quzhao.CustomUI.InDoor.Adapter.BitAdapter;
import com.quzhao.CustomUI.InDoor.Unit.PathUnit;

import java.util.List;


/**
 * Created by karonl on 16/4/1.
 * 为绘制canvas供应getBitBuffer和路径
 */
public interface BitBuffer {

    List<PathUnit> getPathUnit();

    Bitmap getBitBuffer();

    void setOnAdapterListener(BitAdapter.AttrListener listener);
}
