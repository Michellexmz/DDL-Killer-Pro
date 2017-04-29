package com.example.michellezhang.ddlkillerpro;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.michellezhang.ddlkillerpro.database.ItemInfo;
import com.example.michellezhang.ddlkillerpro.database.LabelInfo;
import com.example.michellezhang.ddlkillerpro.database.impl.ItemImpl;
import com.example.michellezhang.ddlkillerpro.database.impl.LabelImpl;
import com.example.michellezhang.ddlkillerpro.database.impl.UserImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class DaysMatter extends AppCompatActivity implements AbsListView.OnScrollListener {
    private ItemInfo itemChanged;
    private TabLayout mTabLayout;
    private int types = 1;
    private int curPosition = 0;
    private ListView listView;
    private SimpleAdapter saImageItems;
    private ArrayList<HashMap<String, Object>> items;
    private final int ITEM_DETAILS = 1;
    private final int CLASSIFICATION = 2;
    private final int ADDING_ITEM = 3;
    private final int ADDING_LABEL = 4;
    private final int ADDING = 5;
    final ArrayList<ItemInfo> itemInfosUndo = new ArrayList<ItemInfo>();
    final ArrayList<ItemInfo> itemInfosDone = new ArrayList<ItemInfo>();

    public static final int []sTitle ={R.drawable.itemundo, R.drawable.itemdone};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days_matter);
        items = new ArrayList<HashMap<String, Object>>();

        findViewById(R.id.add1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(DaysMatter.this, AddingPopup.class);
                //intent.setClass(DaysMatter.this, Adding.class);
                startActivityForResult(intent, ADDING);
            }
        });
        findViewById(R.id.label).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(DaysMatter.this, Classification.class), CLASSIFICATION);

            }
        });
        getItem();
        itemShow(itemInfosUndo, 1);

        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.addTab(mTabLayout.newTab().setIcon(sTitle[0]));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(sTitle[1]));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab == mTabLayout.getTabAt(0)){
                    types = 1;
                    itemShow(itemInfosUndo, types);
                } else{
                    types = 2;
                    itemShow(itemInfosDone, types);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if(tab == mTabLayout.getTabAt(0)){
                    types = 2;
                    itemShow(itemInfosDone, types);
                } else{
                    types = 1;
                    itemShow(itemInfosUndo, types);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if(tab == mTabLayout.getTabAt(0)){
                    types = 1;
                    itemShow(itemInfosUndo, types);
                } else{
                    types = 2;
                    itemShow(itemInfosDone, types);
                }
            }
        });

        itemShow(itemInfosUndo, types);
    }

    @Override
    public void onActivityResult(int request, int result, Intent data) {
        super.onActivityResult(request, result, data);
        if (request == ITEM_DETAILS && result == ItemDetails.EDIT_OK){
            if (data != null){
                /*ItemInfo tmp = (ItemInfo) data.getSerializableExtra("ItemInfo");
                itemChanged = tmp;
                itemInfosDone.add(itemChanged);
                Iterator<ItemInfo> iteraoter = itemInfosUndo.iterator();
                while(iteraoter.hasNext()){
                    ItemInfo item = iteraoter.next();
                    if (item.getId() == itemChanged.getId()){
                        iteraoter.remove();
                    }
                }*/
                getItem();
                modifyList(itemInfosUndo, 1);
                saImageItems.notifyDataSetChanged();
            }
        }
        else if(request == ITEM_DETAILS && result == ItemDetails.DELETE_OK){
            items.remove(curPosition);
            saImageItems.notifyDataSetChanged();
            //getItem();
        }
        else if(request == ITEM_DETAILS && result == ItemDetails.COMPELTED_OK){
            getItem();
            modifyList(itemInfosUndo, 1);
            saImageItems.notifyDataSetChanged();
        }
        else if(request == CLASSIFICATION && result == Classification.RESULT_CLASS_OK){
            if (data != null){
                int labelID = data.getIntExtra("LabelID", 0);
                ItemImpl itemImpl = new ItemImpl();
                itemInfosUndo.clear();
                itemInfosDone.clear();
                List<ItemInfo> items;
                if(labelID == 123){
                    items = itemImpl.getAllItem(UserImpl.getUserID());
                }
                else{
                    items = itemImpl.getLabelAllItem(labelID);
                }
                for(ItemInfo item : items){
                    if(item.getItemComplected() == null){
                        itemInfosUndo.add(item);
                    }
                    else {
                        itemInfosDone.add(item);
                    }
                }
                if(types == 1){
                    modifyList(itemInfosUndo, 1);
                    saImageItems.notifyDataSetChanged();
                } else{
                    modifyList(itemInfosDone, 2);
                    saImageItems.notifyDataSetChanged();
                }
            }
        }
        else if (request == ADDING){
            getItem();
            modifyList(itemInfosUndo, 1);
            saImageItems.notifyDataSetChanged();
        }
    }

    private void getItem(){
        //UserImpl userImpl = new UserImpl();
        //Integer userID = userImpl.getUserID();
        ItemImpl itemImpl = new ItemImpl();
        List<ItemInfo> itemList = itemImpl.getAllItem(UserImpl.getUserID());
        Date date = new Date();
        itemInfosDone.clear();
        itemInfosUndo.clear();
        for(ItemInfo item : itemList){
            if(item.getItemComplected() == null){
                itemInfosUndo.add(item);
            }
            else {
                itemInfosDone.add(item);
            }
        }
    }

    private void modifyList(ArrayList<ItemInfo> itemInfos, final Integer type){
        items.clear();
        for (ItemInfo itemInfo:
                itemInfos) {
            HashMap<String, Object> item = new HashMap<String, Object>();
            Integer labelID = itemInfo.getLabelID();
            LabelImpl labelImpl = new LabelImpl();
            LabelInfo labelInfo = labelImpl.getLabel(labelID);
            item.put("itemLabel", labelInfo.getLabelLogo());
            item.put("itemName", itemInfo.getItemName());
            item.put("itemDescription", getDescription(itemInfo, type));
            items.add(item);
        }
    }

    private void itemShow(final ArrayList<ItemInfo> itemInfos, final Integer type){
        //int imgs[] = {R.drawable.battery, R.drawable.school, R.drawable.battery, R.drawable.school, R.drawable.battery, R.drawable.school,
         //       R.drawable.battery, R.drawable.school, R.drawable.battery, R.drawable.school, R.drawable.battery, R.drawable.school};
        int i = 0;
        items.clear();
        for (ItemInfo itemInfo:
             itemInfos) {
            HashMap<String, Object> item = new HashMap<String, Object>();
            Integer labelID = itemInfo.getLabelID();
            LabelImpl labelImpl = new LabelImpl();
            LabelInfo labelInfo = labelImpl.getLabel(labelID);
            //item.put("itemLabel", imgs[i]);
            i += 1;
            item.put("itemLabel", labelInfo.getLabelLogo());
            item.put("itemName", itemInfo.getItemName());
            item.put("itemDescription", getDescription(itemInfo, type));
            items.add(item);
        }
        saImageItems = new SimpleAdapter(this,
                items,// 数据来源
                R.layout.content_item_row,//每一个user xml 相当ListView的一个组件
                new String[] { "itemLabel", "itemName", "itemDescription"},
                // 分别对应view 的id
                new int[] { R.id.itemLogo, R.id.itemName, R.id.itemDescription});
        // 获取listview
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(saImageItems);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(DaysMatter.this, ItemDetails.class);
                ItemInfo singleItem = itemInfos.get(position);
                itemChanged = singleItem;
                intent.putExtra("ItemInfo", singleItem);
                intent.putExtra("Type", type);
                curPosition = position;
                startActivityForResult(intent, ITEM_DETAILS);

            }
        });
        listView.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch(scrollState) {
            // 用户在手指离开屏幕之前，由于手指用力滑了一下，视图仍然依靠惯性继续滑动
            case SCROLL_STATE_FLING:
                // 通过滑动增加5行数据
                for(int i = 0; i < 5; i++) {
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("pic", R.mipmap.ic_launcher);
                    map.put("title", "增加项" + i);
                    items.add(map);
                }

                // 通知UI线程重新刷新页面，更新动态数据
                saImageItems.notifyDataSetChanged();
                break;

            // 视图已经停止滑动
            case SCROLL_STATE_IDLE:
                break;

            // 手指没有离开屏幕，视图正在滑动
            case SCROLL_STATE_TOUCH_SCROLL:
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    private String getDescription(ItemInfo itemInfo, Integer desType){
        Date newTime = itemInfo.getItemNewTime();
        Date Deadline = itemInfo.getItemDeadline();
        if(desType == 1){
            long interval = Deadline.getTime() - newTime.getTime();
            long days = interval / (24 * 60 * 60 * 1000);
            long hours = interval / (60 * 60 * 1000) % 24;
            long minutes = interval / (60 * 1000) % 60;
            return "剩余时间 " + days + " 天 " + hours + " 时 " + minutes + " 分 ";
        }
        else{
            Date Completed = itemInfo.getItemComplected();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            return "完成时间 " + formatter.format(Completed);
        }
    }

}
