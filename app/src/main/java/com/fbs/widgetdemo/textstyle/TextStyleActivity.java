package com.fbs.widgetdemo.textstyle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.fbs.widgetdemo.R;
import com.fbs.widgetdemo.popout.PopOutListActivity;

public class TextStyleActivity extends AppCompatActivity {
    public static void access(Context context){
        Intent intent=new Intent(context, TextStyleActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_style);
    }
}
