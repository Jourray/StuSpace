package com.example.team.plugutils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.Key;
import java.util.List;
import java.util.Map;

/**
 * author : ZhiG
 * e-mail : 1120121044@163.com
 * date   : 2019/9/2615:09
 * desc   :
 * package: StuSpace:
 */
public class SharedPrefrenceUtils {
    private static SharedPreferences sp;

    public static void saveBoolean(Context context, String key, boolean value) {
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key, defValue);
    }

    public static boolean getBoolean(Context context, String key) {
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key, false);
    }

    public static void saveString(Context context, String key, String value) {
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sp.edit().putString(key, value).commit();
    }

    public static String getString(Context context, String key, String value) {
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sp.getString(key, value);
    }

    public static String getString(Context context, String key) {
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sp.getString(key, "");
    }

    /*保存对象
     * @param context 上下文
     * @param key 键
     * @param obj 要保存的对象（Serializable的子类）
     * @param <T> 泛型
     */
    public static <T extends Serializable> void putObject(Context context, String key, T obj) {
        try {
            put(context, key, obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void put(Context context, String key, Object obj) throws IOException {
        if (obj == null) {//判断对象为空
            return;
        }
        //字节数组输出流
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //操作对象的流，序列化与反序列化
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        //吧文件写入数组并进行Base64编码
        objectOutputStream.writeObject(obj);
        String s = new String(Base64.encode(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
        objectOutputStream.close();
        byteArrayOutputStream.close();
        saveString(context, key, s);
    }

    /**
     * 获取对象
     *
     * @param context 上下文
     * @param key     键
     * @param <T>     指定泛型
     * @return 泛型对象
     */
    public static <T extends Serializable> T getObject(Context context, String key) {
        try {
            return (T) get(context, key);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Object get(Context context, String key) throws IOException, ClassNotFoundException {
        String base64 = getString(context, key);
        if (TextUtils.isEmpty(base64)) {
            return null;
        }
        //将base64转化成byte数组
        byte[] decode = Base64.decode(base64.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decode);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Object object = objectInputStream.readObject();
        byteArrayInputStream.close();
        objectInputStream.close();
        return object;
    }

    public static List<String> getStringList(Context context, String key) throws IOException, ClassNotFoundException {
        return (List<String>) get(context, key);
    }

    public static void putStringList(Context context, String key, List<String> list) {
        try {
            put(context, key, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 存储List集合
     *
     * @param context 上下文
     * @param key     存储的键
     * @param list    存储的集合
     */
    public static void putSerializableList(Context context, String key, List<? extends Serializable> list) {
        try {
            put(context, key, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <E extends Serializable> List<E> getSerializableList(Context context, String key) throws IOException, ClassNotFoundException {
        return (List<E>) get(context, key);
    }

    public static <K extends Serializable, V extends Serializable> void putMap(Context context, String key, Map<K, V> map) {
        try {
            put(context, key, map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <E extends Serializable, K extends Serializable> Map<E, K> getMap(Context context, String key) throws IOException, ClassNotFoundException {
        return (Map<E, K>) get(context, key);
    }


}
