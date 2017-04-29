package com.example.michellezhang.ddlkillerpro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ant.liao.GifView;

import org.litepal.tablemanager.Connector;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private Date dateNew;
    private GifView gif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateNew = new Date();
        Connector.getDatabase();
        findViewById(R.id.item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DaysMatter.class));
            }
        });
        findViewById(R.id.user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });
        findViewById(R.id.calendar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DaysCalendar.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_time_line, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //ItemInfo itemInfo1 = new ItemInfo();
            //itemInfo1 = DataSupport.find(ItemInfo.class, 1);
            //System.out.println(itemInfo1.getItemName());
            //ItemImpl.addItem(20170518,dateNew,"asd",1,dateNew,dateNew,1,1,1,20170503);
            //ItemInfo itemInfo = ItemImpl.getItemInfo(1);
            //System.out.println("id:"+itemInfo.getItemComplected() + "\n" + itemInfo.getItemDeadline());
            startActivity(new Intent(MainActivity.this, set.class));


        }

        return super.onOptionsItemSelected(item);
    }
}
