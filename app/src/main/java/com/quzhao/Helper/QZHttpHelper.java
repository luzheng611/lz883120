package com.quzhao.Helper;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.quzhao.Util.AnalyticalJSON;
import com.quzhao.Util.ApisSeUtil;
import com.quzhao.Util.LogUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/26.
 */

public class QZHttpHelper {
    public interface DataListener {
        void onSuccess(HashMap<String,String > map, String api);

        void onError(String response, String api);

        void onStart(String api);

        void onFinish(String api);
    }

    private DataListener mDataListener;

    public QZHttpHelper(DataListener dataListener) {
        mDataListener = dataListener;
    }

    public void get() {
    }

    public void post(Map<String, String> params, final String api) {
        JSONObject js = new JSONObject(params);
        LogUtil.e("API::  " + api + "    参数：：" + js);
        ApisSeUtil.M m = ApisSeUtil.i(js);
        OkGo.<String>post(api).tag(api).params("key", m.K())
                .params("msg", m.M())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(response.body()!=null&&!response.body().equals("")){
                            mDataListener.onSuccess(AnalyticalJSON.getHashMap(response.body()), api);
                        }else{
                            mDataListener.onError(response.body(),api);
                        }

                    }

                    @Override
                    public void onStart(Request<String, ? extends Request> request) {
                        super.onStart(request);
                        mDataListener.onStart(api);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        mDataListener.onFinish(api);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        mDataListener.onError(response.body(), api);
                    }
                });

    }



}
