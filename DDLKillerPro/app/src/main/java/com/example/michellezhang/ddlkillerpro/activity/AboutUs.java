package com.example.michellezhang.ddlkillerpro.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.michellezhang.ddlkillerpro.R;

//import com.example.zdl.database.R;

/**
 * Created by zdl on 2017/4/19.
 */

public class AboutUs extends AppCompatActivity  {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.aboutus);
            Button button5 = (Button) findViewById(R.id.button5);
            button5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

        }
    }
