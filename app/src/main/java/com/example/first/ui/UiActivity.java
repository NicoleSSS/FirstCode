package com.example.first.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.first.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @ClassName: UiActivity
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/4/2 21:36
 * @Version: 1.0
 */
public class UiActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
        btn = findViewById(R.id.btn_show);
        btn.setOnClickListener(this);
        progressBar = findViewById(R.id. progress_bar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_show:

                break;
            default:
                 break;
        }
    }

    public void showAlertDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(UiActivity.this);
        dialog.setTitle("This is a Dialog");
        dialog.setMessage("Do you want delete the picture?");
        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.show();
    }


}
