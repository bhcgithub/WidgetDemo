package com.fbs.widgetdemo.popout.card;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/9/11
 */
public class PopOutBean implements Serializable {
    private Drawable drawable;
    private String mActivity;

    public PopOutBean(Drawable drawable, String mActivity) {
        this.drawable = drawable;
        this.mActivity = mActivity;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public String getmActivity() {
        return mActivity == null ? "" : mActivity;
    }

    public void setmActivity(String mActivity) {
        this.mActivity = mActivity;
    }
}
