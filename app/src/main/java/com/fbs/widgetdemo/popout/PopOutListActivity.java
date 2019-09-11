package com.fbs.widgetdemo.popout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fbs.widgetdemo.R;

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
        if (popOutAdapter!=null)
            popOutAdapter.setOnPopOutItemListener(new PopOutAdapter.PopOutItemListener() {

                @Override
                public void onItemClick(PopOutBean outBean) {

                }
            });
    }

    private void addData() {
        if (mdata == null)
            mdata = new ArrayList<>();
        mdata.add(new PopOutBean(getResources().getDrawable(R.mipmap.pop_card), ""));
        mdata.add(new PopOutBean(getResources().getDrawable(R.mipmap.pop_card_data), ""));
        mdata.add(new PopOutBean(getResources().getDrawable(R.mipmap.pop_card), ""));
        mdata.add(new PopOutBean(getResources().getDrawable(R.mipmap.pop_card_data), ""));
        mdata.add(new PopOutBean(getResources().getDrawable(R.mipmap.pop_card), ""));
        mdata.add(new PopOutBean(getResources().getDrawable(R.mipmap.pop_card_data), ""));
        mdata.add(new PopOutBean(getResources().getDrawable(R.mipmap.pop_card), ""));
        mdata.add(new PopOutBean(getResources().getDrawable(R.mipmap.pop_card_data), ""));
        mdata.add(new PopOutBean(getResources().getDrawable(R.mipmap.pop_card), ""));
        mdata.add(new PopOutBean(getResources().getDrawable(R.mipmap.pop_card_data), ""));
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
