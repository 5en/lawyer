package com.yanglaoClient.mvp.helper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 类的一些方法
 */
public class GenericHelper {

    /**
     * 返回
     *
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> Class<T> getViewClass(Class<?> clz) {
        Type type = clz.getGenericSuperclass();
        if (type == null || !(type instanceof ParameterizedType)) return null;
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] types = parameterizedType.getActualTypeArguments();
        if (types == null || types.length == 0) return null;
        return (Class<T>) types[0];
    }
}
