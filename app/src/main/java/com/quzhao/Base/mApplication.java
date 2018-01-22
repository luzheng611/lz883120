package com.quzhao.Base;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;

/**
 * 作者：因陀罗网 on 2018/1/5 18:33
 * 公司：成都因陀罗网络科技有限公司
 */

public class mApplication extends Application {
    private static  mApplication instance;
    public ArrayList<Activity> registerList=new ArrayList<>();
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }

    public static mApplication getInstance() {
        return instance;
    }


}
