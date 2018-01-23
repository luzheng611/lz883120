package com.quzhao.Util;



import com.quzhao.Base.mApplication;

import org.json.JSONObject;

/**
 * 作者：因陀罗网 on 2017/5/24 10:51
 * 公司：成都因陀罗网络科技有限公司
 * 接口加密
 */

public class ApisSeUtil {


    public static  M i(JSONObject js){
         M i=new M();
        String key=RandomUtil.generateString(6) + System.currentTimeMillis() / 1000;
        try {
            i.setK(Base64.encode(RSAUtils.encryptByPublicKey(key.getBytes(), mApplication.PK())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String msg=Base64.encode(DESUtil.encrypt(js.toString().getBytes(),key.substring(0,8)));
        i.setM(msg);

        return i;
    }
    public   static class M{
        public M() {
            super();
        }

        String k;
        String m;

        public String K() {
            return k;
        }

        public void setK(String k) {
            this.k = k;
        }

        public String M() {
            return m;
        }

        public void setM(String m) {
            this.m = m;
        }
    }
}
