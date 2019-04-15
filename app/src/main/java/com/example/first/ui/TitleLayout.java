package com.example.first.ui;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.first.R;

import androidx.annotation.Nullable;

/**
 * @ClassName: TitleLayout
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/4/3 13:10
 * @Version: 1.0
 */
public class TitleLayout extends LinearLayout implements View.OnClickListener {

    public TitleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_title, this);
        Button btnTitle = findViewById(R.id.title_back);
        btnTitle.setOnClickListener(this);
        Button btnEdit = findViewById(R.id.title_edit);
        btnEdit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back:
                ((Activity)getContext()).finish();
            case R.id.title_edit:
                Toast.makeText(getContext(), "You clicked Edit button", Toast.LENGTH_SHORT).show();
            default:
                break;
        }
    }
}
