package com.fbs.widgetdemo.utils.pop;

import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.widget.PopupWindow;

/**
 * @author: gh
 * @description:
 * @date: 2019-07-31.
 * @from:
 */
public class PopWindowSelectCondition extends PopupWindow {

    public PopWindowSelectCondition(View contentView, int width, int height) {
        super(contentView,width,height);
    }

    @Override
    public void showAsDropDown(View anchor) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect rect = new Rect();
            anchor.getGlobalVisibleRect(rect);
            int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
            setHeight(h);
        }
        super.showAsDropDown(anchor);
    }

}
