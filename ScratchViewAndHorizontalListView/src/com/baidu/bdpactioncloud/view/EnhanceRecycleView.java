package com.baidu.bdpactioncloud.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import timber.log.Timber;

/**
 * EnhanceRecycleView
 *
 * @author linjunwu
 * @since 2016/5/23
 */
public class EnhanceRecycleView extends RecyclerView{

    private static final String TAG = "EnhanceRecycleView";
    private static final boolean DEBUG = true;

    private OnItemClickListener mOnItemClickListener;

    private int mEnhanceTouchSlop;
    private int mEnhanceScrollPointerId;
    private int mEnhanceInitialTouchX;
    private int mEnhanceInitialTouchY;


    public EnhanceRecycleView(Context context) {
        super(context);
        init(context);
    }

    public EnhanceRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public EnhanceRecycleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (itemClick(e)) {
            return true;
        } else {
            return super.onTouchEvent(e);
        }

    }

    private void init (Context context) {
        final ViewConfiguration vc = ViewConfiguration.get(context);
        mEnhanceTouchSlop = vc.getScaledTouchSlop();
    }

    private boolean itemClick(MotionEvent e) {

        final int action = MotionEventCompat.getActionMasked(e);
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                mEnhanceScrollPointerId = MotionEventCompat.getPointerId(e, 0);
                mEnhanceInitialTouchX = (int) (e.getX() + 0.5f);
                mEnhanceInitialTouchY = (int) (e.getY() + 0.5f);
            }
                break;
            case MotionEvent.ACTION_UP: {
                final int index = MotionEventCompat.findPointerIndex(e, mEnhanceScrollPointerId);
                if (index < 0) {
                    if (DEBUG) {
                        Timber.tag(TAG);
                        Timber.e("Error processing scroll; pointer index for id " +
                                mEnhanceScrollPointerId + " not found. Did any MotionEvents get skipped?");
                    }
                    return false;
                }

                final int x = (int) (MotionEventCompat.getX(e, index) + 0.5f);
                final int y = (int) (MotionEventCompat.getY(e, index) + 0.5f);
                final int dx = x - mEnhanceInitialTouchX;
                final int dy = y - mEnhanceInitialTouchY;
                if ((Math.abs(dx) < mEnhanceTouchSlop) && (Math.abs(dy) < mEnhanceTouchSlop)) {
                    View view = getChildViewByPosition(x, y);
                    int pos = getChildPosition(view);
                    if (mOnItemClickListener != null && view != null) {
                        mOnItemClickListener.OnItemClick(this, view, pos);
                        return true;
                    }

                }

            }
                break;
        }
        return false;
    }

    private View getChildViewByPosition(int x, int y) {
        int count = getChildCount();
        Rect frame = new Rect();
        for (int i=0; i<count; i++) {
            View child = getChildAt(i);
            if ( child.getVisibility() == View.VISIBLE ) {
                child.getHitRect( frame );
                if ( frame.contains( x, y ) ) {
                    return child;
                }
            }

        }
        return null;
    }


    public interface OnItemClickListener{
        public void OnItemClick(View viewgroup, View view, int position);
    }
}
