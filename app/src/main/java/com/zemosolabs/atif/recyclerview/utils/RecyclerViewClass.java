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

import android.graphics.drawable.Drawable;

public class RecyclerViewClass {
    private String mMsg1, mMsg2;
    private Drawable mImage_url;
    private boolean mIsChecked;

    public RecyclerViewClass(String mMsg1, String mMsg2, Drawable mImage_url){
        this(mMsg1, mMsg2, mImage_url, false);
    }

    public RecyclerViewClass(String mMsg1, String mMsg2, Drawable mImage_url, boolean mIsChecked){
        this.mMsg1 = mMsg1;
        this.mMsg2 = mMsg2;
        this.mImage_url = mImage_url;
        this.mIsChecked = mIsChecked;
    }

    //setters
    public void setMessage1(String mMsg1){
        this.mMsg1 = mMsg1;
    }
    public void setMessage2(String mMsg2){
        this.mMsg2 = mMsg2;
    }
    public void setmImage_url(Drawable mImage_url){
        this.mImage_url = mImage_url;
    }
    public void setmIsChecked(boolean mIsChecked){
        this.mIsChecked = mIsChecked;
    }

    //getters
    public String getMessage1(){
        return mMsg1;
    }
    public String getMessage2(){
        return mMsg2;
    }
    public Drawable getmImage_url(){
        return mImage_url;
    }
    public boolean getmIsChecked(){
        return mIsChecked;
    }

}
