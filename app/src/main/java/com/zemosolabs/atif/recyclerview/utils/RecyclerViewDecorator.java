/**
 * Copyright 2016 Mohammed Atif
 *
 *Licensed under the Apache License, Version 2.0 (the "License");
 *you may not use this file except in compliance with the License.
 *You may obtain a copy of the License at
 *
 *http://www.apache.org/licenses/LICENSE-2.0
 *
 *Unless required by applicable law or agreed to in writing, software
 *distributed under the License is distributed on an "AS IS" BASIS,
 *WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *See the License for the specific language governing permissions and
 *limitations under the License.
 */

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
