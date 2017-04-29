package com.example.michellezhang.ddlkillerpro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.michellezhang.ddlkillerpro.database.LabelInfo;
import com.example.michellezhang.ddlkillerpro.database.impl.LabelImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Classification extends AppCompatActivity {
    public static int RESULT_CLASS_OK = 11;


    String names[] ={"Life","Homework","Sports","Lollipo", "Battery", "Book"};
    int imgs[] = {R.drawable.life, R.drawable.school, R.drawable.football, R.drawable.lollipol, R.drawable.battery, R.drawable.book};
    int labelIDs[] = {40123432, 40123433, 40123434, 40123435, 40123436, 40123437};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classification);
        final ArrayList<HashMap<String, Object>> labels = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> label1 = new HashMap<String, Object>();
        label1.put("labelLogo", R.drawable.all);
        label1.put("labelName", "All");
        label1.put("labelID", 123);
        LabelImpl labelImpl = new LabelImpl();
        List<LabelInfo> labelList = LabelImpl.getAllLabel();
        for(LabelInfo labelInfo : labelList){
            HashMap<String, Object> label = new HashMap<String, Object>();
            System.out.println(labelInfo.getLabelName());
            label.put("labelID", labelInfo.getLabelid());
            label.put("labelName", labelInfo.getLabelName());
            label.put("labelLogo", labelInfo.getLabelLogo());
            labels.add(label);
        }
        //for (int i = 0; i < names.length; i++) {

       // }
        SimpleAdapter saImageItems = new SimpleAdapter(this,
                labels,// 数据来源
                R.layout.content_classification,//每一个user xml 相当ListView的一个组件
                new String[] { "labelLogo", "labelName"},
                // 分别对应view 的id
                new int[] { R.id.labelLogo, R.id.labelName});
        // 获取listview
        ListView listView = (ListView) findViewById(R.id.sort);
        listView.setAdapter(saImageItems);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, Object> label = labels.get(position);
                Intent intent = new Intent();
                intent.putExtra("LabelID", (Integer) label.get("labelID"));
                setResult(RESULT_CLASS_OK, intent);
                finish();
            }
        });

    }

}
