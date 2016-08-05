package com.zemosolabs.atif.recyclerview.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zemosolabs.atif.recyclerview.R;

public class RecyclerViewDecorator extends RecyclerView.ItemDecoration {
    Context mContext;
    Drawable mDivider;

    /**
     * Constructor to initialise the context and drawable to display the dividers
     * @param mContext Context of the Activity
     */
    public RecyclerViewDecorator(Context mContext){
        this.mContext = mContext;
        mDivider = ContextCompat.getDrawable(mContext, R.drawable.divider_line);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int dividerLeft = (int)mContext.getResources().getDimension(R.dimen.list_left_margin);
        int dividerRight = parent.getWidth() - (int)mContext.getResources().getDimension(R.dimen.activity_horizontal_margin);

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int dividerTop = child.getBottom() + params.bottomMargin;
            int dividerBottom = dividerTop + mDivider.getIntrinsicHeight();

            mDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom);
            mDivider.draw(c);
        }
    }
}
