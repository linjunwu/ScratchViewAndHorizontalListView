package com.baidu.bdpactioncloud.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

/**
 * MockData
 *
 * @author linjunwu
 * @since 2016/6/1
 */
public class MockData {

    private static Object msync = new Object();
    private static MockData mInstance;

    private Context mContext;
    private AssetManager mAssetManager;
    private Gson mGson;

    private MockData(Context context) {
        mContext = context;
        mAssetManager = context.getAssets();
        mGson = new Gson();
    }

    public <T> T getMockData(String fileName, Class<T> type) throws IOException{
        InputStream inputStream = mAssetManager.open(fileName);
        byte[] data = new byte[inputStream.available()];
        inputStream.read(data);

        String mockData = new String(data);

        return mGson.fromJson(mockData, type);
    }

    public static MockData getInstance(Context context) {
        synchronized (msync) {
            if (mInstance == null) {
                mInstance = new MockData(context);
            }
            return mInstance;
        }
    }
}
