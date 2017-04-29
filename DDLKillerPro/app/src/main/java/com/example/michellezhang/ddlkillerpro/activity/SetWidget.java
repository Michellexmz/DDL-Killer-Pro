package com.example.michellezhang.ddlkillerpro.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.michellezhang.ddlkillerpro.NoticeActivity;
import com.example.michellezhang.ddlkillerpro.R;
import com.example.michellezhang.ddlkillerpro.database.ItemInfo;
import com.example.michellezhang.ddlkillerpro.database.SetInfo;
import com.example.michellezhang.ddlkillerpro.database.impl.ItemImpl;
import com.example.michellezhang.ddlkillerpro.database.impl.SetImpl;

import org.litepal.crud.DataSupport;

import java.util.List;

import static com.hss01248.notifyutil.NotifyUtil.init;
//import com.example.zdl.database.R;
//import com.example.zdl.database.impl.setimpl;

/**
 * Created by zdl on 2017/4/19.
 */

public class SetWidget extends AppCompatActivity {
    private long a;
    private long b;
    private int c;
    private int d;
    private double e;
    private List<ItemInfo> itemInfoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setwidget);
        final Button button8 = (Button) findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] number = {"显示1条事项", "显示2条事项", "显示3条事项", "显示4条事项", "显示5条事项"};
                AlertDialog itemnumber = new AlertDialog.Builder(SetWidget.this).setSingleChoiceItems(number,c, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        SetImpl.setwidgetnumber(1,which+1);
                        c = which;
                    }
                }).create();
                itemnumber.show();
            }
        });
        final Button button9 = (Button) findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] day = {"距离截止时间3天", "距离截止时间5天", "距离截止时间10天", "距离截止时间20天", "距离截止时间30天"};
                AlertDialog widgetdays = new AlertDialog.Builder(SetWidget.this).setSingleChoiceItems(day, d, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        d=which;
                        switch(which) {
                            case 0:
                                SetImpl.setwidgetdays(1, 3);
                                //SetInfo setinfos= DataSupport.findFirst(SetInfo.class);
                                //Log.d("SetWidget",""+setinfos.getWidgetDays());
                                break;
                            case 1:
                                SetImpl.setwidgetdays(1, 5);
                                break;
                            case 2:
                                SetImpl.setwidgetdays(1, 10);
                                break;
                            case 3:
                                SetImpl.setwidgetdays(1, 20);
                                break;
                            case 4:
                                SetImpl.setwidgetdays(1, 30);
                                break;

                        }
                    }
                }).create();
                widgetdays.show();
            }
        });

        Button button6=(Button)findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Switch switch1=(Switch)findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    SetInfo setinfo =new SetInfo();
                    setinfo= DataSupport.find(SetInfo.class,1);
                    itemInfoList = ItemImpl.getWidgetItem(setinfo.getWidgetNum());


                    //System.out.println();itemin
                    init(getApplicationContext());
                    for (int i = 0; i < itemInfoList.size(); i++) {
                        //System.out.println(itemInfoList.size());
                        //System.out.println(itemInfoList.get(i).getItemDeadline().getTime()-itemInfoList.get(i).getItemNewTime().getTime());

                        //Date date = new Date();
                        //System.out.println((date.getTime()-itemInfoList.get(i).getItemNewTime().getTime()));
                        //a=date.getTime()-itemInfoList.get(i).getItemNewTime().getTime();
                        //b=itemInfoList.get(i).getItemDeadline().getTime()-itemInfoList.get(i).getItemNewTime().getTime();
                        //e=a*1.0/b;
                        //System.out.println(e);
                        NoticeActivity.showProgress(itemInfoList.get(i));
                        button9.setVisibility(View.VISIBLE);
                        button8.setVisibility(View.VISIBLE);
                    }
                    //NoticeActivity.showProgress(itemInfoList.get(2));
                }
                else {
                    button9.setVisibility(View.INVISIBLE);
                    button8.setVisibility(View.INVISIBLE);
                    SetImpl.setwidgetflag(1,0);
                }
            }
        });
    }
}
