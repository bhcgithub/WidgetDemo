package com.fbs.widgetdemo.utils.addsubtract;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fbs.widgetdemo.R;


/**
 * @author: gh
 * @description:
 * @date: 2019-08-21.
 * @from:
 */
public class AmountView extends LinearLayout implements View.OnClickListener, TextWatcher {

    private static final String TAG = "AmountView";
    //购买数量/选中数量
    private int amount = 0;
    //最小数量
    private int minCount = 0;
    //最大数量
    private int maxCount = 100;
    //是否可编辑,默认可以
    private boolean isEditEnable = true;

    private OnAmountChangeListener mListener;

    private TextView etAmount;
    private Button btnDecrease;
    private Button btnIncrease;
    private LinearLayout ll_root;

    public AmountView(Context context) {
        this(context, null);
    }

    public AmountView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.layout_view_amount, this);
        etAmount = (TextView) findViewById(R.id.etAmount);
        btnDecrease = (Button) findViewById(R.id.btnDecrease);
        btnIncrease = (Button) findViewById(R.id.btnIncrease);
        ll_root = (LinearLayout) findViewById(R.id.ll_root);
        btnDecrease.setOnClickListener(this);
        btnIncrease.setOnClickListener(this);
        etAmount.addTextChangedListener(this);

        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.AmountView);
        int defaultCount = obtainStyledAttributes.getInteger(R.styleable.AmountView_defaultCount, 0);
        int maxCount = obtainStyledAttributes.getInteger(R.styleable.AmountView_maxCount, 100);
        int minCount = obtainStyledAttributes.getInteger(R.styleable.AmountView_minCount, 0);
        obtainStyledAttributes.recycle();
        this.amount = defaultCount;
        this.maxCount = maxCount;
        this.minCount = minCount;

        if (defaultCount == -1) {
            this.amount = 0;
            etAmount.setText("");
        } else if (minCount > amount) {
            etAmount.setText(minCount + "");
        } else if (maxCount < amount) {
            etAmount.setText(maxCount);
        } else {
            etAmount.setText(amount + "");
        }

    }

    @Override
    public void onClick(View v) {
        if (!isEditEnable) return;
        int i = v.getId();
        if (i == R.id.btnDecrease) {
            if (amount > minCount) {
                amount--;
                etAmount.setText(amount + "");
            } else {
                etAmount.setText(minCount + "");
            }
        } else if (i == R.id.btnIncrease) {
            if (amount < maxCount) {
                amount++;
                etAmount.setText(amount + "");
            } else {
                etAmount.setText(maxCount + "");
            }
        }

        etAmount.clearFocus();

        if (mListener != null) {
            mListener.onAmountChange(this, amount);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (TextUtils.isEmpty(s.toString()))
            return;
        int count = Integer.valueOf(s.toString());
        if (count > maxCount) {
            etAmount.setText(maxCount + "");
            return;
        }

        if (count < minCount) {
            etAmount.setText(minCount + "");
            return;
        }

        amount = count;

        if (mListener != null) {
            mListener.onAmountChange(this, amount);
        }
    }

    public void setEditEnable(boolean isEditEnable) {
        this.isEditEnable = isEditEnable;
        ll_root.setBackgroundResource(isEditEnable ? R.drawable.bg_amount_layout_editable : R.drawable.bg_amount_layout_noeditable);
    }

    public boolean isEditEnable() {
        return isEditEnable;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public int getMinCount() {
        return minCount;
    }

    public void setMinCount(int minCount) {
        this.minCount = minCount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int count) {
        this.amount = count;
        etAmount.setText(amount + "");
    }

    public void setOnAmountChangeListener(OnAmountChangeListener onAmountChangeListener) {
        this.mListener = onAmountChangeListener;
    }

    public interface OnAmountChangeListener {
        void onAmountChange(View view, int amount);
    }

}
