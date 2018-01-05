package com.quzhao.Util;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;

/**
 * 作者：因陀罗网 on 2018/1/5 18:25
 * 公司：成都因陀罗网络科技有限公司
 */

public class MapUtils {

    public static void changeCamera(AMap aMap,CameraUpdate update, AMap.CancelableCallback callback) {
        aMap.animateCamera(update, 1000, callback);

    }
}
