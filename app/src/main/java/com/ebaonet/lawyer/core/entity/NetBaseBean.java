package com.ebaonet.lawyer.core.entity;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by dong on 2015/11/9.
 */
public class NetBaseBean extends Object{

    public static final int SERVER_ERROR_CODE = -11;//连接服务器异常
    public static final int OK = 0;//获取数据正常

    /**
     * code 为返回值
     * msg 为返回信息
     * response 为返回的数据
     */
    public int code;
    public String message;
    private String cart_num;
    private String msg_num;
    private JSONObject resultData;


    public String getMsg_num() {
        return msg_num;
    }

    public void setMsg_num(String msg_num) {
        this.msg_num = msg_num;
    }

    public String getCart_num() {
        return cart_num;
    }

    public void setCart_num(String cart_num) {
        this.cart_num = cart_num;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JSONObject getResultData() {
        return resultData;
    }

    public void setResultData(JSONObject resultData) {
        this.resultData = resultData;
    }

    public boolean isSuccess() {
        if (code == OK) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder(code).append(message).append(resultData.toString());
        return stringBuilder.toString();
    }

    public static NetBaseBean getObjectFromJson(String str) {
        NetBaseBean netBaseBean = new NetBaseBean();
        try {
            JSONObject jsonObject = new JSONObject(str);
            netBaseBean.setCode(jsonObject.getInt("code"));
            netBaseBean.setMessage(jsonObject.getString("message"));
            netBaseBean.setMsg_num("msg_num");
            netBaseBean.setResultData(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return netBaseBean;
    }

    /**
     * 获取传输数据时单个数据内容对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getObjectData(Class<T> clazz) throws JSONException {
        Gson gson = buildGson();
        T t = (T) gson.fromJson(resultData.toString(), clazz);
        return t;
    }

    /**
     * 获取传输数据时数组数据内容
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> ArrayList<T> getArrayData(String keyList, Class<T> clazz) throws JSONException{
        Gson gson =  buildGson();
        JSONArray array = null;
        try {
            array = resultData.getJSONArray(keyList);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayList<T> ps = new ArrayList<>();
        if(array==null){
              return ps;
        }
        for (int i = 0; i < array.length(); i++) {
            T t = null;
            try {
                t = (T) gson.fromJson(array.get(i).toString(), clazz);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            ps.add(t);
        }
        return ps;
    }

    public  Gson buildGson() {
        Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .registerTypeAdapter(Integer.class, new IntegerDefault0Adapter())
                    .registerTypeAdapter(int.class, new IntegerDefault0Adapter())
                    .registerTypeAdapter(double.class,new DoubleDefault0Adapter())
                    .create();

        return gson;
    }
    public class IntegerDefault0Adapter implements JsonSerializer<Integer>, JsonDeserializer<Integer> {
        @Override
        public Integer deserialize(JsonElement json, Type typeOfT,
                                   JsonDeserializationContext context)
                throws JsonParseException {
            try {
                if (json.getAsString().equals("")){
                    return 0;
                }
            } catch (Exception ignore){
            }
            try {
                return json.getAsInt();
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }


        @Override
        public JsonElement serialize(Integer src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src);
        }


        }
    public class DoubleDefault0Adapter implements JsonSerializer<Double>, JsonDeserializer<Double> {
        @Override
        public Double deserialize(JsonElement json, Type typeOfT,
                                  JsonDeserializationContext context)
                throws JsonParseException {
            try {
                if (json.getAsString().equals("")) {
                    return Double.parseDouble("0");
                }
            } catch (Exception ignore) {
            }
            try {
                return json.getAsDouble();
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override
        public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src);
        }


    }
}
