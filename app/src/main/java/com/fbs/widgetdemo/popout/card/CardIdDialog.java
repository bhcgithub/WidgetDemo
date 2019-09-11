package com.fbs.widgetdemo.popout.card;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fbs.widgetdemo.R;
import com.fbs.widgetdemo.utils.pop.CustomPopWindow;
import com.fbs.widgetdemo.utils.RegularUtil;
import com.fbs.widgetdemo.utils.universaladapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/9/11
 */
public class CardIdDialog {
    public interface RentDialogListener {
        void save(String cardCode, String cardName, String cardNo);
    }

    private static void showToast(Context activity, String mContent) {
        Toast.makeText(activity, mContent, Toast.LENGTH_SHORT).show();
    }

    public static AlertDialog showCardIdDialog(final Context activity, final Step1Bean step1Bean, final RentDialogListener listener) {
        final AlertDialog dialog = new AlertDialog.Builder(activity)
                .create();

        View view = LayoutInflater.from(activity).inflate(R.layout.layout_rent_card_no_dialog, null);
        dialog.setView(view);
        final TextView tv_card_type = (TextView) view.findViewById(R.id.tv_card_type);
        LinearLayout ll_card_no = (LinearLayout) view.findViewById(R.id.ll_card_no);
        final EditText et_card_no = (EditText) view.findViewById(R.id.et_card_no);
        final TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        TextView tv_submit = (TextView) view.findViewById(R.id.tv_submit);

        if (step1Bean != null && step1Bean.getCardType() != null && step1Bean.getCardType().size() > 0) {
            tv_card_type.setText(step1Bean.getCardType().get(0).getValueName());
            tv_card_type.setTag(step1Bean.getCardType().get(0).getValueCode());
        }

        ll_card_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCardIdPop(activity, tv_card_type,step1Bean);
            }
        });

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(tv_card_type.getText().toString().trim())) {
                    showToast(activity, "证件类型不能为空");
                    return;
                }
                if (TextUtils.isEmpty(et_card_no.getText().toString().trim())) {
                    showToast(activity, "证件号码不能为空");
                    return;
                }
                if (et_card_no.getText().toString().trim().length() != 15 && et_card_no.getText().toString().trim().length() != 18) {
                    showToast(activity, "证件号码不正确");
                    return;
                }
                if (tv_card_type.getText().toString().contains("身份证") && !RegularUtil.checkIdCardRule(et_card_no.getText().toString().trim())) {
                    showToast(activity, "证件号码不正确");
                    return;
                }
                listener.save((String) tv_card_type.getTag(), tv_card_type.getText().toString().trim(), et_card_no.getText().toString().trim());
                dialog.dismiss();

            }
        });

        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(R.drawable.dialog_building_border);
        window.setGravity(Gravity.CENTER);  //此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.dialogWindowAnim);  //添加动画
        dialog.show();
        WindowManager windowManager = ((Activity) activity).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = (int) (display.getWidth() * 3 / 4); //设置宽度
        return dialog;
    }

    /**
     * 类型选择框
     */
    public static void showCardIdPop(Context context, final TextView textView, Step1Bean step1Bean) {
        List<Step1Bean.CardTypeBean> src = new ArrayList<>();
        if (step1Bean != null && step1Bean.getCardType() != null && step1Bean.getCardType().size() > 0) {
            src = step1Bean.getCardType();
        }
        View popView = LayoutInflater.from(context).inflate(R.layout.layout_rent_pop, null);
        RecyclerView rv_list = (RecyclerView) popView.findViewById(R.id.rv_list);

        OptionAdapter popAdapter = new OptionAdapter(src, context);
        rv_list.setLayoutManager(new LinearLayoutManager(context));
        rv_list.setAdapter(popAdapter);
        rv_list.setNestedScrollingEnabled(false);
        rv_list.setHasFixedSize(true);

        final int anchorLoc[] = new int[2];
        // 获取锚点View在屏幕上的左上角坐标位置
        textView.getLocationOnScreen(anchorLoc);
        final int anchorWidth = textView.getWidth();
        //创建并显示popWindow
        final CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder(context)
                .setView(popView)
                .size(anchorWidth, ViewGroup.LayoutParams.WRAP_CONTENT)
                .create();

        popAdapter.updateData(src);
        rv_list.scrollToPosition(0);

        //创建并显示popWindow
        popWindow.showAsDropDown(textView, 0, 0);
        popAdapter.setOnItemClickListener(new OnItemClickListener<Step1Bean.CardTypeBean>() {
            @Override
            public void onItemClick(Step1Bean.CardTypeBean info, View v, int position) {
                textView.setText(info.getValueName());
                textView.setTag(info.getValueCode());
                popWindow.dissmiss();
            }
        });

    }
}
