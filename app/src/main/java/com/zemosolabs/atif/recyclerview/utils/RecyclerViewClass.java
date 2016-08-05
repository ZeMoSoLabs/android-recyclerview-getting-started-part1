package com.zemosolabs.atif.recyclerview.utils;

import android.graphics.drawable.Drawable;

public class RecyclerViewClass {
    private String mMsg1, mMsg2;
    private Drawable mImage_url;
    private boolean mIsChecked;

    /**
     * @param mMsg1 Main string to be displayed in each row
     * @param mMsg2 Supporting string to be displayed in each row
     * @param mImage_url Image to be displayed as avatar
     */
    public RecyclerViewClass(String mMsg1, String mMsg2, Drawable mImage_url){
        this(mMsg1, mMsg2, mImage_url, false);
    }

    /**
     * @param mMsg1 Main string to be displayed in each row
     * @param mMsg2 Supporting string to be displayed in each row
     * @param mImage_url Image to be displayed in each row
     * @param mIsChecked Status of the checkbox in each row
     */
    public RecyclerViewClass(String mMsg1, String mMsg2, Drawable mImage_url, boolean mIsChecked){
        this.mMsg1 = mMsg1;
        this.mMsg2 = mMsg2;
        this.mImage_url = mImage_url;
        this.mIsChecked = mIsChecked;
    }

    //setters

    /**
     * Sets the main string to be displayed in the row
     * @param mMsg1 String which is displayed as main message
     */
    public void setMessage1(String mMsg1){
        this.mMsg1 = mMsg1;
    }

    /**
     * Sets the supporting string to be displayed in the row
     * @param mMsg2 String which is displayed as supporting message
     */
    public void setMessage2(String mMsg2){
        this.mMsg2 = mMsg2;
    }

    /**
     * Sets the data of the image to be displayed as avatar
     * @param mImage_url image drawable object
     */
    public void setmImage_url(Drawable mImage_url){
        this.mImage_url = mImage_url;
    }

    /**
     * Sets the checkbox status of the row
     * @param mIsChecked status of the checkbox
     */
    public void setmIsChecked(boolean mIsChecked){
        this.mIsChecked = mIsChecked;
    }

    //getters

    /**
     * @return String containing Main message of the row
     */
    public String getMessage1(){
        return mMsg1;
    }

    /**
     * @return String containing Supporting message of the row
     */
    public String getMessage2(){
        return mMsg2;
    }

    /**
     * @return Drawable of avatar if the row
     */
    public Drawable getmImage_url(){
        return mImage_url;
    }

    /**
     * @return current status of the checkbox
     */
    public boolean getmIsChecked(){
        return mIsChecked;
    }

}
