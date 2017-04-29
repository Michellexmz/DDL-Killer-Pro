package com.example.michellezhang.ddlkillerpro.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.michellezhang.ddlkillerpro.R;
import com.example.michellezhang.ddlkillerpro.database.LabelInfo;
import com.example.michellezhang.ddlkillerpro.database.impl.LabelImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//import com.example.zdl.database.R;
//import com.example.zdl.database.impl.labelimpl;
//import com.example.zdl.database.labelinfo;

/**
 * Created by zdl on 2017/4/21.
 */

public class labe extends AppCompatActivity {
    private static final String ACTIVITY_TAG="LogDemo";
    private Context context;
    private SimpleAdapter adapter;
    private ArrayList<HashMap<String,Object>> data;
    private boolean a;
    private SwipeMenuListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        boolean c;
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.label_2);
        Button button12 = (Button) findViewById(R.id.button12);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final List<ApplicationInfo> mAPPList = getPackageManager().getInstalledApplications(0);

        SwipeMenuCreator creator =new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem editItem=new SwipeMenuItem(getApplicationContext());
                editItem.setBackground(new ColorDrawable(Color.GREEN));
                editItem.setWidth(dp2px(80));
                //editItem.setTitle("edit");
                //editItem.setTitleSize(18);
                //editItem.setTitleColor(Color.WHITE);
                editItem.setIcon(R.drawable.icon_pencil_edit);
                menu.addMenuItem(editItem);

                SwipeMenuItem deleteItem=new SwipeMenuItem(getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.LTGRAY));
                deleteItem.setWidth(dp2px(80));
                deleteItem.setIcon(R.drawable.battery);
                menu.addMenuItem(deleteItem);
            }
        };
        listView=(SwipeMenuListView)findViewById(R.id.listView_2);
        listView.setMenuCreator(creator);

        //LabelImpl labelImpl = new LabelImpl(this);
        labelShow();

        /*String[] data = new String[30];
        for (int i = 0; i < data.length; i++) {
            data[i] = "测试数据:" + i;
        }
        final ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,R.layout.layout_2,data);*/
        /*data = new ArrayList<HashMap<String, Object>>();
        for(int i = 0; i < 20; i++) {
            HashMap<String, Object>hashMap = new HashMap<String, Object>();
            hashMap.put("labellogo", R.mipmap.ic_launcher);
            hashMap.put("labelname", "项目" + i);
            data.add(hashMap);
        }*/

        //adapter=new SimpleAdapter(this,data,R.layout.label_3,new String[]{"labellogo","labelname"},new int[]{R.id.imageview2,R.id.textview2});
        //adapter.notifyDataSetChanged();
      //  listView.setAdapter((adapter));

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch(index){
                    case 0:
                        Toast.makeText(context, "编辑:"+position,Toast.LENGTH_SHORT).show();
                        LabelInfo labelInfo = new LabelInfo();
                        labelInfo.setLabelName((String )data.get(position).get("labelname"));
                        labelInfo.setLabelLogo((Integer) data.get(position).get("labellogo"));
                        labelInfo.setLabelid((Integer) data.get(position).get("labelID"));
                        Intent intent=new Intent(labe.this,SetLabel.class);
                        intent.putExtra("LabelInfo", labelInfo);
                        startActivityForResult(intent,0);
                        break;
                    case 1:
                        Toast.makeText(context, "删除:"+position,Toast.LENGTH_SHORT).show();
                        //Tips();
                        //Log.d(labe.ACTIVITY_TAG,"4");
                        //if(a) {
                            //Log.d(labe.ACTIVITY_TAG,"1");
                            data.remove(position);
                            adapter.notifyDataSetChanged();
                            adapter=new SimpleAdapter(context,data,R.layout.label_3,new String[]{"labellogo","labelname"},new int[]{R.id.imageview2,R.id.textview2});
                            listView.setAdapter((adapter));
                            //Log.d(labe.ACTIVITY_TAG,"2");
                            //a=false;
                            break;
                        //}
                        //else {
                            //Log.d(labe.ACTIVITY_TAG, "3");
                        //}

                }
                return false;
            }
        });

    }
    public int dp2px(float dipValue) {
        final float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    @Override
    public void onActivityResult(int request, int result, Intent data) {
        super.onActivityResult(request, result, data);
        if (request == 0 && result == SetLabel.LABEL_OK) {
            if (data != null) {
                labelShow();
            }
        }
    }

    private void labelShow(){
        List<LabelInfo> labelinfos = LabelImpl.getAllLabel();
        data = new ArrayList<HashMap<String, Object>>();
        for (LabelInfo labelInfo : labelinfos) {
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("labelID", labelInfo.getLabelid());
            hashMap.put("labelname", labelInfo.getLabelName());
            hashMap.put("labellogo", labelInfo.getLabelLogo());
            data.add(hashMap);
        }
        adapter=new SimpleAdapter(this,data,R.layout.label_3,new String[]{"labellogo","labelname"},new int[]{R.id.imageview2,R.id.textview2});
        listView.setAdapter(adapter);
    }

    /*ppublic void Tips() {
        boolean b;
        AlertDialog isDelete = new AlertDialog.Builder(this).create();
        isDelete.setTitle("系统提示");
        /*new AlertDialog.Builder(labe.this)
                .setTitle("您已完成该事项？")
                .setPositiveButton("确定",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                a=true;
            }
        }).setNegativeButton("并没有", null).create()
                .show();
        isDelete.setMessage("确定要删除么");
        isDelete.setButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                a=true;
                Log.d(labe.ACTIVITY_TAG,"5");

            }
        });
        isDelete.setButton2("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                a=true;
                Log.d(labe.ACTIVITY_TAG,"6");
            }
        });
        isDelete.show();*/
        //b=a;
        //a=false;
        //return b;*/
    }

