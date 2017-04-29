package com.example.michellezhang.ddlkillerpro;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.michellezhang.ddlkillerpro.database.ItemInfo;
import com.example.michellezhang.ddlkillerpro.database.impl.ItemImpl;
import com.example.michellezhang.ddlkillerpro.database.impl.UserImpl;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class DaysCalendar extends AppCompatActivity {

    public List<ItemInfo> items;//linked to database
    public Date curDate;//today's Date information
    public long dateItv;//translate to event_time
    public SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd"); //construct a time format
    private ArrayList <Map <String, Object>> mItems=new ArrayList <Map <String, Object>>();

    public Button buttonW, buttonM;
    public MaterialCalendarView mcv;
    public ListView mListView;

    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days_calendar);

        //get current date
        curDate = new Date(System.currentTimeMillis());
        //define a MaterialCalendarView
        mcv = (MaterialCalendarView) findViewById(R.id.calendarView);
        //Set Selected Color in Calendar
        mcv.setSelectionColor(getResources().getColor(R.color.date_choose));
        //define a listview
        mListView = (ListView) findViewById(R.id.listView);

        //listen for click events

        //items:List<ItemInfo> type, local variable
        //mItems:HashMap type， for adapter
        //item:xml type, for listview layout
        mcv.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView mcv, @NonNull CalendarDay date, boolean selected) {
                items = new ArrayList<ItemInfo>();
                //translate date information to list view
                //get list<ItemInfo> from database selected by selected date
                ItemImpl item = new ItemImpl();
              //  List<ItemInfo> itemInfos = item.getItemByDate(date.getDate());
                List<ItemInfo> itemInfos = item.getAllItem(UserImpl.getUserID());
                System.out.println(itemInfos);
           //     System.out.println(date);
              //  System.out.println(date.getDate());
              //  refreshListData(itemInfos);
                for(ItemInfo singleItem : itemInfos){
                    Date tmp = singleItem.getItemDeadline();
                    System.out.println("DDL is " + tmp);
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                    String str = formatter.format(tmp);
                    System.out.println("Str is " + str);
                    String now = formatter.format(date.getDate());
                    System.out.println("Now is " + now);
                    if (str.equals(now)){
                        System.out.println(singleItem);
                        System.out.println(singleItem.getItemName());
                        items.add(singleItem);
                    }
                }
                System.out.println("items is " + items.toString());
                refreshListData(items);
                //items= ItemImpl.getAllItem(date.getDate());

                System.out.println(date.getDate());
                System.out.println(items.toString());

            }
            public void refreshListData(List<ItemInfo> items) {
                mItems.clear();

                for (int i=0; i < items.size(); i++) {
                    Map<String, Object> item = new HashMap<String, Object>();
                    item.put("event_name", items.get(i).getItemName());
                    item.put("event_time", sDateFormat.format(items.get(i).getItemDeadline()));
                    mItems.add(item);
                }

                SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), mItems, R.layout.item_layout,
                        new String[]{"event_name", "event_time"},
                        new int[]{R.id.event_name, R.id.event_time});
                mListView.setAdapter(adapter);
            }


        });


        buttonW = (Button) findViewById(R.id.buttonW);
        buttonM = (Button) findViewById(R.id.buttonM);

        buttonM.setBackgroundColor(getResources().getColor(R.color.date_bkg));
        buttonW.setBackgroundColor(getResources().getColor(R.color.date_bkg));

        buttonW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSetWeekMode();
            }

            public void onSetWeekMode() {

                mcv.state().edit()
                        .setCalendarDisplayMode(CalendarMode.WEEKS)
                        .commit();
            }
        });

        buttonM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSetMonthMode();
            }

            public void onSetMonthMode() {

                mcv.state().edit()
                        .setCalendarDisplayMode(CalendarMode.MONTHS)
                        .commit();
            }
        });
    };


}
