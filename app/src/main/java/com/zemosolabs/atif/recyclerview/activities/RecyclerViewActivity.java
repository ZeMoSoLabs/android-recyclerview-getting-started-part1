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

    /**
     *<p>RecyclerView, Adapter and LayoutManagers are initialized within this method.</p>
     * <p>In this example, list items are also added in onCreate add passed as RecyclerViewClass object to the adapter.</p>
     * @param savedInstanceState will be used in next update to retain the state of the app on rotation of the screen.
     */
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
        //creating a rounded drawable for avatar
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

    /**
     * <p>This method is overridden to handle the back press made by the user,
     * It helps the user to control when to exit the app and when to come to normal state from long press state.</p>
     */
    @Override
    public void onBackPressed() {
        if(mAdapter.getLongPressStatus()){
            mAdapter.setOnLongPressStatus(false);
        }else{
            super.onBackPressed();
        }
    }

    /**
     * This method is used to create Whatsapp like round images to display as profile image or avatars.
     * @param scaleBitmapImage Bitmap of the image that is to be displayed as circle.
     * @return Bitmap of the same image cropped as circle.
     */
    public Bitmap getRoundedShape(Bitmap scaleBitmapImage) {
        //setting up height and width of the profile image
        int targetWidth = (int)getResources().getDimension(R.dimen.profile_pic_size);
        int targetHeight = (int)getResources().getDimension(R.dimen.profile_pic_size);
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

    /**
     * <p>This method is used to remove the item from speicified position</p>
     * @param position location of the item to be removed
     */
    @Override
    public void updateItemList(int position) {
        mItems.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    /**
     * <p>Updates the background checkbox status in POJO class and helps to set the background color on long press.</p>
     * <p>Illegal state is checked to prevent changing of checkbox status while list is being scrolled.</p>
     * @param position position of the item in list where checkbox status is changed.
     * @param isChecked current status of the checkbox.
     */
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
