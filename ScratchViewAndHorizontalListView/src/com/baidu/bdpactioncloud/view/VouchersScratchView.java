package com.baidu.bdpactioncloud.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.bdpactioncloud.R;

/**
 * VouchersScratchView
 *
 * @author linjunwu
 * @since 2016/5/20
 */
public class VouchersScratchView extends AbstractBaseRelativeLayout implements View.OnClickListener{
    private static final String TAG = "ScratchView";
    private static final boolean DEBUG = true;

    private LinearLayout mLlScratchPreview;
    private TextView mScratchPreviewText;
    private Button mScratchPreviewAction;

    public VouchersScratchView(Context context) {
        super(context);
    }

    public VouchersScratchView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public VouchersScratchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.bt_scratch_preview_action:
                goneLlScratchPreview();
                break;
        }

    }

    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater.from(context).inflate(R.layout.scratch_area,
                this, true);
        mLlScratchPreview = $(R.id.ll_scratch_preview);
        mScratchPreviewText = $(R.id.tv_scratch_preview_text);
        mScratchPreviewAction = $(R.id.bt_scratch_preview_action);

        mScratchPreviewAction.setOnClickListener(this);
    }

    private void goneLlScratchPreview() {
        mLlScratchPreview.setVisibility(View.GONE);
    }
}
