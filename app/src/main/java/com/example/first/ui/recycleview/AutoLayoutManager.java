package com.example.first.ui.recycleview;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class AutoLayoutManager extends RecyclerView.LayoutManager {

    @Override
    public boolean isAutoMeasureEnabled() {
        return true;
    }

   //控制每个item的layoutParams
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    /**
     * 调用时机：
     * 1.view第一次执行layout的时候
     * 2.adaper的数据集改变并通知观察者（也就是view）的时候
     * @param recycler
     * @param state
     */
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        //先将之前放置的无用的View放回recycler中
        detachAndScrapAttachedViews(recycler);

        //累加item布局时的x轴偏移
        int curLineWidth = 0;
        //累加item布局时的y轴偏移
        int curLineTop = 0;

        int lastLineMaxHeight = 0;
        for (int i = 0; i < getItemCount(); i++) {
            View view = recycler.getViewForPosition(i);

            //获取每个item的布局参数，计算每个item的占用位置(需要加上margin)
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
            addView(view);
            measureChildWithMargins(view, 0, 0);
            int width = getDecoratedMeasuredWidth(view) + params.leftMargin + params.rightMargin;
            int height = getDecoratedMeasuredHeight(view) + params.topMargin + params.bottomMargin;

            //累加当前行已有item的宽度
            curLineWidth += width;
            //如果累加的宽度小于等于RecyclerView的宽度，不需要换行
            if (curLineWidth <= getWidth()) {

                //布局item的真实位置
                layoutDecorated(view,
                        curLineWidth - width + params.leftMargin,
                        curLineTop + params.topMargin,
                        curLineWidth - params.rightMargin,
                        curLineTop + height - params.bottomMargin);
                //比较当前行item的最大高度，用于换行后计算item在y轴上的偏移量
                lastLineMaxHeight = Math.max(lastLineMaxHeight, height);
            } else {
                //换行
                curLineWidth = width;
                if (lastLineMaxHeight == 0) {
                    lastLineMaxHeight = height;
                }
                //记录当前行top
                curLineTop += lastLineMaxHeight;

                layoutDecorated(view,
                        params.leftMargin,
                        curLineTop + params.topMargin,
                        width - params.rightMargin,
                        curLineTop + height - params.bottomMargin);
                lastLineMaxHeight = height;
            }
        }
    }
}