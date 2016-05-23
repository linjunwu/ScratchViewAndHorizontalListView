package com.baidu.bdpactioncloud.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.bdpactioncloud.R;
import com.baidu.bdpactioncloud.model.GameActivity;

import java.util.List;

import timber.log.Timber;

/**
 * GameActivitysView
 *
 * @author linjunwu
 * @since 2016/5/23
 */
public class GameActivitysView extends AbstractBaseRelativeLayout{

    private static final String TAG = "GameActivitysView";
    private static final boolean DEBUG = true;

    private EnhanceRecycleView mGameActivityList;

    public GameActivitysView(Context context) {
        super(context);
    }

    public GameActivitysView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameActivitysView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater.from(context).inflate(R.layout.game_activity_view, this, true);
        mGameActivityList = $(R.id.rv_game_activity_list);
        mGameActivityList.setLayoutManager(new GameActivitysLinearLayoutManager(context));
        // 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mGameActivityList.setHasFixedSize(true);
        mGameActivityList.setOnItemClickListener(new EnhanceRecycleView.OnItemClickListener() {
            @Override
            public void OnItemClick(View viewgroup, View view, int position) {
                if (DEBUG) {
                    Timber.tag(TAG);
                    Timber.i("position:" +position);
                }
            }
        });

    }

    public void setGameActivityList(RecyclerView.Adapter<? extends RecyclerView.ViewHolder> gameActivityList) {
        mGameActivityList.setAdapter(gameActivityList);
    }


    public static class GameActivitysViewAdapter extends RecyclerView.Adapter<GameActivitysViewAdapter.ViewHolder> {

        private List<GameActivity> mGameActivitys;

        public GameActivitysViewAdapter(List<GameActivity> gameActivitys) {
            mGameActivitys = gameActivitys;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.game_activity_item, parent, false);
            ViewHolder vh = new ViewHolder(view);
            return vh;

        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            GameActivity gameActivity = mGameActivitys.get(position);
            holder.mGameActivityTitle.setText(gameActivity.mTitle);

        }

        @Override
        public int getItemCount() {
            if (mGameActivitys != null && mGameActivitys.size() > 0) {
                return mGameActivitys.size();
            }
            return 0;
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView mGameActivityIcon;
            public TextView mGameActivityTitle;
            public Button mGameActivityAction;

            public ViewHolder(View view){
                super(view);
                mGameActivityIcon = (ImageView) view.findViewById(R.id.iv_game_activity_icon);
                mGameActivityTitle = (TextView) view.findViewById(R.id.tv_game_activity_title);
                mGameActivityAction = (Button) view.findViewById(R.id.bt_game_activity_action);
            }
        }

    }

    public static class GameActivitysLinearLayoutManager extends LinearLayoutManager {

        public GameActivitysLinearLayoutManager(Context context) {
            super(context);
            super.setOrientation(LinearLayoutManager.HORIZONTAL);

        }

        public GameActivitysLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
            super.setOrientation(LinearLayoutManager.HORIZONTAL);
        }

        // TODO:怎么隐藏掉该方法--通过重载父类方法，并将方法体置为空。
        @Override
        public void setOrientation(int orientation) {
//            super.setOrientation(orientation);
        }

        @Override
        public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
            final int widthMode = MeasureSpec.getMode(widthSpec);
            final int heightMode = MeasureSpec.getMode(heightSpec);
            final int widthSize = MeasureSpec.getSize(widthSpec);
            final int heightSize = MeasureSpec.getSize(heightSpec);

            int childrenCount = getChildCount();
            // 如果没有子视图，RecycleView的大小有RecycleView的父类决定其大小
            if (childrenCount == 0) {
                setMeasuredDimension(widthSpec, heightSpec);
                return;
            }

            // 计算子视图总宽度,这里没有考虑，每个列表项修饰情况与Recycle的padding情况，后续会加入
            int sumWidthOfChildren = 0;
            for (int i=0; i<childrenCount; i++) {
                sumWidthOfChildren += getChildAt(i).getMeasuredWidth();
            }
            int heightOfChild = getChildAt(0).getMeasuredHeight();


            int width = 0;
            int height = 0;

            if (sumWidthOfChildren > widthSize) {

                switch (widthMode) {
                    case MeasureSpec.EXACTLY:
                    case MeasureSpec.AT_MOST:
                        width = widthSize;
                        break;
                    case MeasureSpec.UNSPECIFIED:
                    default:
                        width = getMinimumWidth();
                        break;
                }

            } else {
                switch (widthMode) {
                    case MeasureSpec.EXACTLY:
                    case MeasureSpec.AT_MOST:
                        width = sumWidthOfChildren;
                        break;
                    case MeasureSpec.UNSPECIFIED:
                    default:
                        width = getMinimumWidth();
                        break;
                }
            }


            switch (heightMode) {
                case MeasureSpec.EXACTLY:
                case MeasureSpec.AT_MOST:
                    height = heightOfChild;
                    break;
                case MeasureSpec.UNSPECIFIED:
                default:
                    height = getMinimumHeight();
                    break;
            }

            setMeasuredDimension(width, height);
        }
    }

}
