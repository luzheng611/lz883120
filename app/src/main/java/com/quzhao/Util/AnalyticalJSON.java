package com.quzhao.Util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AnalyticalJSON {
    private static final String TAG = "AnalyticalJSON";


        /**把数据源HashMap转换成json
         * @param map
         */
        public static String hashMapToJson(HashMap map) {
            String string = "{";
            for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
                Map.Entry e = (Map.Entry) it.next();
                string += "'" + e.getKey() + "':";
                string += "'" + e.getValue() + "',";
            }
            string = string.substring(0, string.lastIndexOf(","));
            string += "}";
            return string;
        }

        /**
     * 解析得到HashMap。{"":"","":""}
     */
    public static HashMap<String, String> getHashMap(String json) {
        HashMap<String, String> item = new HashMap<String, String>();
        try {
            JSONObject json_data = new JSONObject(json);
            Iterator<String> keysIterator = json_data.keys();
            while (keysIterator.hasNext()) {
                String key = keysIterator.next();
                item.put(key, json_data.getString(key));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(TAG, "e==" + e.getMessage());
            return null;
        }
        return item;
    }

    /**
     * 解析得到List。{[{"":""},{"":""}]}
     */
    public static ArrayList<HashMap<String, String>> getList(String json, String type) {
        ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
        try {
            JSONObject jsonObj = new JSONObject(json);
            JSONArray result = jsonObj.getJSONArray(type);

            if (result == null) {
                return null;
            }
            for (int i = 0; i < result.length(); i++) {
                HashMap<String, String> map = new HashMap<String, String>();
                JSONObject json_data = result.getJSONObject(i);
                Iterator<String> keysIterator = json_data.keys();
                while (keysIterator.hasNext()) {
                    String key = keysIterator.next();
                    map.put(key, json_data.getString(key));
                }

                data.add(map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(TAG ,"e==" + e.getMessage());
            return null;
        }
        return data;
    }

//    /**
//     * 解析得到List。{[[{"":""},{"":""}]]}
//     */
//    public static ArrayList<HashMap<String, String>> getList_DoubleMiddle(String json, String type) {
//        ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
//        Set<String> time_set = new HashSet<>();
//        ArrayList<JSONObject> objList = new ArrayList<>();
//        ArrayList<String> time_List = new ArrayList<>();
//
//        try {
//            JSONObject jsonObj = new JSONObject(json);
//            JSONArray result = jsonObj.getJSONArray(type);
//            if (result == null) {
//                return null;
//            }
//            JSONArray array = result.getJSONArray(0);
//            for (int i = 0; i < array.length(); i++) {
//                HashMap<String, String> map = new HashMap<String, String>();
//                JSONObject json_data = array.getJSONObject(i);
//                String time_0 = json_data.getString("time");//20160802
//                JSONObject id_obj = json_data.getJSONObject("id");//id之后的数据
//                time_set.add(time_0);
//                objList.add(id_obj);
//            }
//            Log.w(TAG, "getList_DoubleMiddle: set.size" + time_set.size());
//            Iterator it = time_set.iterator();
//            int  [] arrays = new int  [time_set.size()];
//            int num = 0;
//            while (it.hasNext()) {
//                int   a = Integer.valueOf((String)(it.next())) ;
//                arrays[num] = a;
//                num = num + 1;
//                System.out.println(num);
//            }
//            //对结果进行排序
//            Arrays.sort(arrays);
//            for(int in=0;in<arrays.length;in++){
//                if(arrays.length-in-1-in==1){
//                    if(arrays[in]<arrays[arrays.length-in-1]){
//                        arrays[in]=arrays[arrays.length-in-1];
//                        arrays[arrays.length-in-1]=arrays[in];
//                    }
//                }
//                arrays[in]=arrays[arrays.length-in-1];
//                arrays[arrays.length-in-1]=arrays[in];
//            }
//            JSONObject endObj=new JSONObject();
//            JSONArray
//            for (int  time : arrays) {
//                time_List.add(time+"");
//                for(JSONObject obj:objList){
//                    String t=TimeUtils.getStrTime_spe(String .valueOf(obj.get("ls_time")));
//                    if(t.equals(time+"")){
//
//                    }
//
//                }
//
//
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//            Log.d("zhou", "e==" + e.getMessage());
//            return null;
//        }
//        return data;
//    }


    /**
     * 解析得到List。[{"":""},{"":""}]
     */
    public static ArrayList<HashMap<String, String>> getList_zj(String json) {
        ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
        try {
            JSONArray result = new JSONArray(json);
            if (result == null) {
                return null;
            }
            for (int i = 0; i < result.length(); i++) {
                HashMap<String, String> map = new HashMap<String, String>();
                JSONObject json_data = result.getJSONObject(i);
                Iterator<String> keysIterator = json_data.keys();
                while (keysIterator.hasNext()) {
                    String key = keysIterator.next();
                    map.put(key, json_data.getString(key));
                }
                data.add(map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(TAG, "e==" + e.getMessage());
            return null;
        }
        return data;
    }

    /**
     * 解析得到List。["",""]
     */
    public static ArrayList<String> getList_string(String json) {
        ArrayList<String> data = new ArrayList<String>();
        try {
            JSONArray result = new JSONArray(json);
            if (result == null) {
                return null;
            }
            for (int i = 0; i < result.length(); i++) {
                data.add(result.getString(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "e==" + e.getMessage());
            return null;
        }
        return data;
    }

    /**
     * 解析得到List。["",""]
     */
    public static ArrayList<Integer> getList_int(String json) {
        ArrayList<Integer> data = new ArrayList<Integer>();
        try {
            JSONArray result = new JSONArray(json);
            if (result == null) {
                return null;
            }
            for (int i = 0; i < result.length(); i++) {
                data.add(result.getInt(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(TAG, "e==" + e.getMessage());
            return null;
        }
        return data;
    }

    //解析{{"":""},{"":""}}
    public static ArrayList<HashMap<String, String>> getList4JsonObject2(String json) {
        ArrayList<HashMap<String, String>> datalist = new ArrayList<HashMap<String, String>>();
        JSONObject js = null;
        try {
            js = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (js == null) {
            return null;
        }
        Iterator<String> keysIterator = js.keys();
        while (keysIterator.hasNext()) {
            String key = keysIterator.next();
            JSONObject jmap = null;
            try {
                jmap = (JSONObject) js.get(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            HashMap<String, String> map = getHashMap(jmap.toString());
            datalist.add(map);
        }

        return datalist;
    }

    //解析{{[{"":""},{"":""}}]}
    public static ArrayList<HashMap<String, String>> getList4JsonObject4(String json, String type) {
        ArrayList<HashMap<String, String>> datalist = new ArrayList<HashMap<String, String>>();
        JSONObject js = null;
        try {
            js = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (js == null) {
            return null;
        }
        JSONObject j = null;
        try {
            j = (JSONObject) js.get(type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (j == null) {
            return null;
        }
        Iterator<String> keysIterator = j.keys();
        while (keysIterator.hasNext()) {
            String key = keysIterator.next();
            JSONArray jsonArray = null;
            try {
                jsonArray = (JSONArray) j.get(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            datalist = getList_zj(jsonArray.toString());
        }

        return datalist;
    }
}
