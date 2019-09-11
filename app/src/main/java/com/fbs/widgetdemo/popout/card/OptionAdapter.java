package com.fbs.widgetdemo.popout.card;

import android.content.Context;


import com.fbs.widgetdemo.R;
import com.fbs.widgetdemo.utils.universaladapter.UniversalAdapter;

import java.util.List;

public class OptionAdapter extends UniversalAdapter<Step1Bean.CardTypeBean> {
    public OptionAdapter(List<Step1Bean.CardTypeBean> src, Context context) {
        super(src, context);
    }

    @Override
    public int getLayoutId(int type) {
        return R.layout.layout_option_item_tv;
    }

    @Override
    public void convert(UniversalVH vh, Step1Bean.CardTypeBean data, int pos) {
        vh.setText(R.id.tv_text,data.getValueName());
    }
}
