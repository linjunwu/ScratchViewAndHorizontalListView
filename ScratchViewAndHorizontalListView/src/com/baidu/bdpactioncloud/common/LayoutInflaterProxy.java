package com.baidu.bdpactioncloud.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xmlpull.v1.XmlPullParser;

/**
 * LayoutInflaterProxy
 * 注意继承RuntimeException与Exception的区别，继承RuntimeException的异常可以不进行处理，等运行
 * 的是否抛出，让线程去接收这个异常，继承Exception的异常，在代码实现上就要进行异常捕获处理。
 * @author linjunwu
 * @since 2016/5/30
 */
public abstract class LayoutInflaterProxy extends LayoutInflater{

    protected LayoutInflaterProxy(Context context) {
        super(context);
    }

    protected LayoutInflaterProxy(LayoutInflater original, Context newContext) {
        super(original, newContext);
    }

    public View inflate(int resource, ViewGroup root) {
        View view = super.inflate(resource, root);

        CommonView result = null;
        try {
            result = (CommonView) view;
        } catch (ClassCastException e) {
            throw new RootViewNotCommonViewException();
        }
        return result;
    }


    public View inflate(XmlPullParser parser, ViewGroup root) {
        View view = super.inflate(parser, root);

        CommonView result = null;
        try {
            result = (CommonView) view;
        } catch (ClassCastException e) {
            throw new RootViewNotCommonViewException();
        }
        return result;
    }


    public View inflate(int resource, ViewGroup root, boolean attachToRoot) {
        View view = super.inflate(resource, root, attachToRoot);

        CommonView result = null;
        try {
            result = (CommonView) view;
        } catch (ClassCastException e) {
            throw new RootViewNotCommonViewException();
        }
        return result;
    }


    public View inflate(XmlPullParser parser, ViewGroup root, boolean attachToRoot) {
        View view = super.inflate(parser, root, attachToRoot);

        CommonView result = null;
        try {
            result = (CommonView) view;
        } catch (ClassCastException e) {
            throw new RootViewNotCommonViewException();
        }
        return result;
    }


}
