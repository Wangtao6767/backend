package com.lensen.backend.utils;

import com.lensen.backend.flexjson.JSONSerializer;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

public class JsonUtils {

    public static String toString(Object obj) {
        return toString(null, obj);
    }

    public static String toString(String rootName, Object obj) {
        JSONSerializer serializer = new JSONSerializer();
        // serializer.addExclude("*.class");
        serializer.exclude(new String[]{"class"});
        if (rootName == null) {
            return serializer.deepSerialize(obj);
        }
        return serializer.deepSerialize(rootName, obj);
    }

    // edit by fjin,主要用于PaginationSupport toJson，去除很多不需要的属性，简化返回的数据，不处理map中含有多个pagination对象情况
    public static String toString(String rootName, Object obj, String[] fields) {
        JSONSerializer serializer = new JSONSerializer();
        // serializer.addExclude("*.class");
        serializer.exclude(new String[]{"class"});
        if (fields == null)
            return toString(rootName, obj);
        Map params = new HashMap();
        serializer.include(fields).exclude(new String[]{"*"});
        if (obj instanceof Map) {
            Map map = (Map) obj;
            Iterator iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                Object value = map.get(key);
                params.put(key, value);
            }
            if (rootName == null) {
                return serializer.deepSerialize(params);
            }
            return serializer.deepSerialize(rootName, params);
        }
        if (rootName == null) {
            return serializer.deepSerialize(obj);
        }
        return serializer.deepSerialize(rootName, obj);
    }

    /**
     * bean 转化为 json
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        HashMap<String, String> map = new HashMap<String, String>();
        Class c = obj.getClass();
        Field[] fields = c.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String name = fields[i].getName();
            try {
                fields[i].setAccessible(true);
                Object o = fields[i].get(obj);
                if (o == null || JSONUtils.isNull(o)) {
                    map.put("\"" + name + "\"", null);
                } else if (o instanceof Enum) {
                    throw new JSONException("'object' is an Enum. Use JSONArray instead");
                } else if (o instanceof Annotation || o.getClass().isAnnotation()) {
                    throw new JSONException("'object' is an Annotation.");
                } else if (o instanceof Number) {
                    map.put("\"" + name + "\"", o.toString());
                } else if (o instanceof String) {
                    map.put("\"" + name + "\"", "\"" + o.toString() + "\"");
                } else if (o instanceof Date) {
                    map.put("\"" + name + "\"", o.toString());
                } else if (o instanceof Boolean) {
                    map.put("\"" + name + "\"", o.toString());
                } else if (o instanceof List) {
                    throw new JSONException("'object' is an array. Use JSONArray instead");
                } else if (o instanceof Map) {
                    throw new JSONException("'object' is an map. Use JSONArray instead");
                } else {
                    JsonUtils.toJson(o);
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
        String s = map.toString();
        String str = s.replaceAll("\"=", "\":");
        return str;
    }

    public static HashMap<String, Object> toHashMap(Object object) {
        HashMap data = new HashMap();
        JSONObject jsonObject = toJSONObject(object);
        Iterator it = jsonObject.keys();

        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            Object value = jsonObject.get(key);
            data.put(key, value);
        }

        return data;
    }

    public static JSONObject toJSONObject(Object object) {
        return JSONObject.fromObject(object);
    }
}
