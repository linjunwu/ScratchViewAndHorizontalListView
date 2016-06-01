package com.baidu.bdpactioncloud.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * CommonView
 *
 * @author linjunwu
 * @since 2016/5/30
 */
public class CommonView extends View{
    public CommonView(Context context) {
        super(context);
    }

    public CommonView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CommonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // 该方式不是原创。
    protected  <T> T findView(int id) {
        return (T) findViewById(id);
    }

}
