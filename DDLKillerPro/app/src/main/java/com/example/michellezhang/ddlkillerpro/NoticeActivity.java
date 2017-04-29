package com.example.michellezhang.ddlkillerpro;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.michellezhang.ddlkillerpro.database.ItemInfo;
import com.example.michellezhang.ddlkillerpro.database.SetInfo;
import com.example.michellezhang.ddlkillerpro.database.impl.ItemImpl;
import com.example.michellezhang.ddlkillerpro.database.impl.SetImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;

import static com.hss01248.notifyutil.NotifyUtil.buildProgress;
import static com.hss01248.notifyutil.NotifyUtil.init;

/**
 * Created by MichelleZhang on 2017/4/23.
 */


public class NoticeActivity extends AppCompatActivity {

    public SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd"); //construct a time format
    public String date = sDateFormat.format(new java.util.Date());
    public List<SetInfo> setInfoList;
    public List<ItemInfo> itemInfoList;
    public static Date curDate;
    public ItemInfo itemInfo;
    int widgetNum;
    int id;
    int widgetFlag;
    public static long a;
    public static long b;
    public static double e;
    public static long f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init(getApplicationContext());

        setInfoList = SetImpl.getAllSet(1);
        widgetFlag=setInfoList.get(0).getWidgetFlag();
        widgetNum=setInfoList.get(0).getWidgetNum();

        if(widgetFlag == 1) {
            itemInfoList= ItemImpl.getWidgetItem(widgetNum);
            for (int i=0; i<itemInfoList.size(); i++) {
                showProgress(itemInfoList.get(i));
            }

        }
    }

    public static void showProgress(final ItemInfo itemInfo) {

        new Handler().postDelayed(new Runnable() {
            @Override

            public void run() {
                long progresses = 0;
                if(progresses >=100){
                    return;
                }
                //init(getApplicationContext());

                //curDate = new Date(System.currentTimeMillis());
                Date date = new Date();
                a=date.getTime()-itemInfo.getItemNewTime().getTime();
                b=itemInfo.getItemDeadline().getTime()-itemInfo.getItemNewTime().getTime();
                e=a*1.0/b;
                f=(long)(e*100);
                System.out.println(f);

                buildProgress((int)f,R.drawable.logo2,itemInfo.getItemName()+"的进度为", (int)f, 100).show();
                //showProgress(itemInfo);
            }
        },500);

    }

}