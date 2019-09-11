package com.fbs.widgetdemo.utils.universaladapter;

import android.view.View;

/**
 * Created by LiXiaoSong on 2016/3/21.
 *
 * @Describe recycle的item点击事件监听
 */
public interface OnItemClickListener<T> {
     void onItemClick(T info, View v, int position);
}
