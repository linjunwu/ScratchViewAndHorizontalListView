package com.baidu.bdpactioncloud.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.baidu.bdpactioncloud.R;

import timber.log.Timber;

/**
 * ScratchView
 *
 * @author linjunwu
 * @since 2016/5/20
 */
public class ScratchView extends View{

    private static final String TAG = "ScratchView";
    private static final boolean DEBUG = true;

    private static final float DEFAULT_STROKE_WIDTH = 20;
    private static final float AREA_THRESHOLD = 0.5f;

    private Paint mScratchPaint;

    private Path mPath;
    private float mLastX;
    private float mLastY;

    private float mStrokeWidth = DEFAULT_STROKE_WIDTH;
    private float mAreaThreshold = AREA_THRESHOLD;

    public ScratchView(Context context) {
        this(context, null);
    }

    public ScratchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScratchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        final Resources.Theme theme = context.getTheme();
        TypedArray array = theme.obtainStyledAttributes( attrs, R.styleable.ScratchView, defStyleAttr, 0 );
        if( null != array ) {
            float strokeWidth = array.getFloat(R.styleable.ScratchView_StrokeWidth, 0);
            float areaThreshold = array.getFloat(R.styleable.ScratchView_AreaThreshold, 0);
            if (DEBUG) {
                Timber.tag(TAG);
                Timber.d("strokeWidth:" + strokeWidth + ",areaThreshold:" + areaThreshold);
            }
            if (strokeWidth > 0) {
               mStrokeWidth = strokeWidth;
            }
            if (areaThreshold > 0) {
               mAreaThreshold = areaThreshold;
            }

        }


        mScratchPaint = new Paint();
        mScratchPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        // 看注释可以知道setAntiAlias(true)与setFlags(Paint.ANTI_ALIAS_FLAG)效果是一样的
        // mScratchPaint.setAntiAlias(true);
        mScratchPaint.setDither(true);
        mScratchPaint.setFilterBitmap(false);
        mScratchPaint.setStyle(Paint.Style.STROKE);
        mScratchPaint.setStrokeWidth(mStrokeWidth);
        mScratchPaint.setStrokeCap(Paint.Cap.ROUND);
        mScratchPaint.setStrokeJoin(Paint.Join.ROUND);
        mScratchPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));

        mPath = new Path();
        mPath.reset();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //将绘制操作保存到新的图层（更官方的说法应该是离屏缓存）
        // 加入这个由于混合绘图，抠掉的那部分不是透明的，会是黑色的
        int sc = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

        canvas.drawPath(mPath, mScratchPaint);
        canvas.drawColor(0xFF0000ff, PorterDuff.Mode.SRC_OUT);
//        canvas.drawBitmap(mMask, 0f, 0f, mScratchPaint);

        // TODO: 2016/5/20  为什么在这里设置Xfermode为null,就没有效果
//        mScratchPaint.setXfermode(null);

        canvas.restoreToCount(sc);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float x = event.getX();
        float y = event.getY();
        if (DEBUG) {
            Timber.tag(TAG);
            Timber.d("action:" + action + ",x:" + x + ",y:" + y);
        }
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.quadTo(mLastX, mLastY, x, y);
                postInvalidate();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        mLastX = x;
        mLastY = y;

        if (isCanGoneThisByAreaPercent()) {
            setVisibility(View.GONE);
        }

        return true;
    }

    private boolean isCanGoneThisByAreaPercent() {
        RectF pathRectF = new RectF(0, 0, 0, 0);
        mPath.computeBounds(pathRectF, true);

        float rawArea = getWidth() * getHeight();
        float scratchArea = (pathRectF.right - pathRectF.left) * (pathRectF.bottom - pathRectF.top);

        float percent = scratchArea / rawArea;

        if (DEBUG) {
            Timber.tag(TAG);
            Timber.d("mPath RectF left:" + pathRectF.left +
                    ",right:" + pathRectF.right +
                    ",top:" + pathRectF.top +
                    ",bottom:" + pathRectF.bottom);
            Timber.tag(TAG);
            Timber.d("rawArea:" + rawArea + ",scratchArea:" + scratchArea +
                        ",percent:" + percent);
        }

        if (percent > mAreaThreshold) {
            return true;
        } else {
            return false;
        }

    }
}
