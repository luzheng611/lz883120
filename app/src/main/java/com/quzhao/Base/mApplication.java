package com.quzhao.Base;

import android.app.Activity;
import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.quzhao.Util.Base64;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

/**
 * 作者：因陀罗网 on 2018/1/5 18:33
 * 公司：成都因陀罗网络科技有限公司
 */

public class mApplication extends Application {
    private static mApplication instance;
    public ArrayList<Activity> registerList = new ArrayList<>();
    private static boolean isTest=true;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        initOkgo();

    }

    private void initOkgo() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
//log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(isTest?HttpLoggingInterceptor.Level.BODY: HttpLoggingInterceptor.Level.NONE);
//log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.SEVERE);
        builder.addInterceptor(loggingInterceptor);


//全局的读取超时时间
        builder.readTimeout(10000, TimeUnit.MILLISECONDS);
//全局的写入超时时间
        builder.writeTimeout(10000, TimeUnit.MILLISECONDS);
//全局的连接超时时间
        builder.connectTimeout(10000, TimeUnit.MILLISECONDS);
        OkGo.getInstance().init(this).setOkHttpClient(builder.build()).setRetryCount(3);
    }

    public static mApplication getInstance() {
        return instance;
    }



    public static byte[] PK(){
        return Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCiwo1VXQ0myY7dM62/iQ0MPSgZ\n" +
                "zE491DnLkEwv6Qdvcd7EXPaXJluONEPy7cCMgZrCyeduWlKmRAW8YEMZexXupoTx\n" +
                "V3kZxtxR8ozI3/ItNZf8wAMpeRpF5Fv/cifXWjimOYrX2tOB+7nviSTb19XgbAln\n" +
                "NbN3d7blGFmuFJNNxQIDAQAB");
    }
}
