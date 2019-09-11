package com.fbs.widgetdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.fbs.widgetdemo.popout.PopOutListActivity;
import com.fbs.widgetdemo.textstyle.TextStyleActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnPop(View view) {
        PopOutListActivity.access(this);
    }

    public void btnTv(View view) {
        TextStyleActivity.access(this);
    }
}
