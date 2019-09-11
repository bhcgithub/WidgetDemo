package com.fbs.widgetdemo.popout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fbs.widgetdemo.R;
import com.fbs.widgetdemo.popout.card.CardIdDialog;
import com.fbs.widgetdemo.popout.card.PopOutAdapter;
import com.fbs.widgetdemo.popout.card.PopOutBean;
import com.fbs.widgetdemo.popout.card.Step1Bean;

import java.util.ArrayList;
import java.util.List;


/**
 * 弹框样式列表
 */
public class PopOutListActivity extends AppCompatActivity {

    private ImageView brack;
    private TextView title;
    private RecyclerView mLv;
    private List<PopOutBean> mdata;
    private PopOutAdapter popOutAdapter;
    private Step1Bean step1Bean;

    public static void access(Context context){
        Intent intent=new Intent(context,PopOutListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_out_list);
        brack = (ImageView) findViewById(R.id.brack);
        title = (TextView) findViewById(R.id.title);
        mLv = (RecyclerView) findViewById(R.id.pop_list);
        addData();
        setData();
        setListener();
    }

    private void setListener() {
        title.setText("弹框样式列表");
        brack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //身份证输入校验
        if (popOutAdapter != null)
            popOutAdapter.setOnPopOutItemListener(new PopOutAdapter.PopOutItemListener() {

                @Override
                public void onItemClick(PopOutBean outBean) {
                    if ("Cardld".equals(outBean.getmActivity()))
                        CardIdDialog.showCardIdDialog(PopOutListActivity.this, step1Bean, new CardIdDialog.RentDialogListener() {
                            @Override
                            public void save(String cardCode, String cardName, String cardNo) {

                            }
                        });
                }
            });
    }

    private void addData() {
        if (mdata == null)
            mdata = new ArrayList<>();
        mdata.add(new PopOutBean(getResources().getDrawable(R.mipmap.pop_card), "Cardld"));
        mdata.add(new PopOutBean(getResources().getDrawable(R.mipmap.pop_card_data), ""));
        mdata.add(new PopOutBean(getResources().getDrawable(R.mipmap.pop_card), ""));
        mdata.add(new PopOutBean(getResources().getDrawable(R.mipmap.pop_card_data), ""));
        mdata.add(new PopOutBean(getResources().getDrawable(R.mipmap.pop_card), ""));
        mdata.add(new PopOutBean(getResources().getDrawable(R.mipmap.pop_card_data), ""));
        mdata.add(new PopOutBean(getResources().getDrawable(R.mipmap.pop_card), ""));
        mdata.add(new PopOutBean(getResources().getDrawable(R.mipmap.pop_card_data), ""));
        mdata.add(new PopOutBean(getResources().getDrawable(R.mipmap.pop_card), ""));
        mdata.add(new PopOutBean(getResources().getDrawable(R.mipmap.pop_card_data), ""));

        //身份证输入建议弹框数据
        step1Bean = new Step1Bean();
        List<Step1Bean.CardTypeBean> cardType = step1Bean.getCardType();
        for (int i = 0; i < 4; i++) {
            Step1Bean.CardTypeBean cardTypeBean = new Step1Bean.CardTypeBean();
            cardTypeBean.setValueCode("" + (i + 1));
            cardTypeBean.setValueName("身份证" + (i + 1));
            cardType.add(cardTypeBean);
        }
        step1Bean.setCardType(cardType);
    }

    @SuppressLint("WrongConstant")
    private void setData() {
        popOutAdapter = new PopOutAdapter(mdata);
        LinearLayoutManager manager = new LinearLayoutManager(this);

        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mLv.setLayoutManager(manager);
        mLv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mLv.setItemAnimator(new DefaultItemAnimator());
        mLv.setAdapter(popOutAdapter);
    }
}
