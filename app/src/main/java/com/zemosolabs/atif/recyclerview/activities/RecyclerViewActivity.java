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

package com.zemosolabs.atif.recyclerview.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zemosolabs.atif.recyclerview.R;
import com.zemosolabs.atif.recyclerview.adapters.CustomAdapter;
import com.zemosolabs.atif.recyclerview.utils.RecyclerViewClass;
import com.zemosolabs.atif.recyclerview.utils.RecyclerViewDecorator;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity implements CustomAdapter.UpdateMainClass {

    private RecyclerView mRecyclerView;
    private CustomAdapter mAdapter;
    private List<RecyclerViewClass> mItems;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new RecyclerViewDecorator(this));
        Bitmap bitmap = getRoundedShape(BitmapFactory.decodeResource(getResources(),R.drawable.ironman));
        Drawable d = new BitmapDrawable(getResources(), bitmap);
        mItems = new ArrayList<>();
        //adding test items to the list
        for(int i=0; i<30; i++){
            mItems.add(i, new RecyclerViewClass(i+" string1", i+" string2", d));

        }
        mAdapter = new CustomAdapter(this, mItems);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onBackPressed() {
        if(mAdapter.getLongPressStatus()){
            mAdapter.setOnLongPressStatus(false);
        }else{
            super.onBackPressed();
        }
    }

    public Bitmap getRoundedShape(Bitmap scaleBitmapImage) {
        int targetWidth = (int)getResources().getDimension(R.dimen.profile_pic_size);
        int targetHeight = (int)getResources().getDimension(R.dimen.profile_pic_size);;
        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                targetHeight,Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);
        Path path = new Path();
        path.addCircle(((float) targetWidth - 1) / 2,
                ((float) targetHeight - 1) / 2,
                (Math.min(((float) targetWidth),
                        ((float) targetHeight)) / 2),
                Path.Direction.CCW);

        canvas.clipPath(path);
        canvas.drawBitmap(scaleBitmapImage,
                new Rect(0, 0, scaleBitmapImage.getWidth(),
                        scaleBitmapImage.getHeight()),
                new Rect(0, 0, targetWidth, targetHeight), null);
        return targetBitmap;
    }

    @Override
    public void updateItemList(int position) {
        mItems.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    @Override
    public void updateListBackground(int position, boolean isChecked) {
        try {
            mItems.get(position).setmIsChecked(isChecked);
            mAdapter.notifyItemChanged(position);
        }catch(IllegalStateException e){
            //do nothing
        }
    }
}
