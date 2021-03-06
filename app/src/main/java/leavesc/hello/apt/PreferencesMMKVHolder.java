package leavesc.hello.apt;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.tencent.mmkv.MMKV;

import leavesc.hello.apt_annotation.preferences.IPreferencesHolder;

/**
 * 作者：leavesC
 * 时间：2019/1/5 1:05
 * 描述：
 * GitHub：https://github.com/leavesC
 * Blog：https://www.jianshu.com/u/9df45b87cfdf
 */
public class PreferencesMMKVHolder implements IPreferencesHolder {

    @Override
    public String serialize(String key, Object src) {
        String json = new Gson().toJson(src);
        MMKV kv = MMKV.defaultMMKV();
        kv.putString(key, json);
        return json;
    }

    @Override
    public <T> T deserialize(String key, Class<T> classOfT) {
        MMKV kv = MMKV.defaultMMKV();
        String json = kv.decodeString(key, "");
        if (!TextUtils.isEmpty(json)) {
            return new Gson().fromJson(json, classOfT);
        }
        return null;
    }

    @Override
    public void remove(String key) {
        MMKV kv = MMKV.defaultMMKV();
        kv.remove(key);
    }

}