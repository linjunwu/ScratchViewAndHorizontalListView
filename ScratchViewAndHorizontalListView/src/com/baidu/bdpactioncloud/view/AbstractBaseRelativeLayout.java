package com.baidu.bdpactioncloud.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * BaseRelativeLayout
 *
 * @author linjunwu
 * @since 2016/5/23
 */
public abstract class AbstractBaseRelativeLayout extends RelativeLayout {
    public AbstractBaseRelativeLayout(Context context) {
        this(context, null);
    }

    public AbstractBaseRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AbstractBaseRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    // 该方式不是原创。
    protected  <T> T $(int id) {
        return (T)findViewById(id);
    }

    protected abstract void init(Context context, AttributeSet attrs, int defStyleAttr);
}
