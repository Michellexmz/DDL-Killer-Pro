package com.example.michellezhang.ddlkillerpro.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.michellezhang.ddlkillerpro.R;

//import com.example.zdl.database.R;
//import com.example.zdl.database.impl.setimpl;

/**
 * Created by zdl on 2017/4/19.
 */

public class SetNotice extends AppCompatActivity {
    private int e;
    private int f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setnotice);
        Button button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button button10 = (Button) findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] time = {"0:00","6:00","8:00","12:00","15:00","18:00","20:00","22:00",};
                AlertDialog timenotice = new AlertDialog.Builder(SetNotice.this).setSingleChoiceItems(time, f, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        f=which;
                        dialog.dismiss();
                    }
                }).create();
                timenotice.show();
                //Intent intent2 =new Intent(SetNotice.this,timepicker.class);
                //startActivityForResult(intent2,0);

            }
        });
        Button button11 = (Button) findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] frequency = {"1小时提醒一次","3小时提醒一次","6小时提醒一次","12小时提醒一次","24小时提醒一次","48小时提醒一次","5天提醒一次"};
                AlertDialog frequencynotice = new AlertDialog.Builder(SetNotice.this).setSingleChoiceItems(frequency, e, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        e=which;
                        dialog.dismiss();
                    }
                }).create();
                frequencynotice.show();
            }
        });
    }
}
