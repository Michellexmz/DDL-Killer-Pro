package com.example.michellezhang.ddlkillerpro;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.michellezhang.ddlkillerpro.database.LabelInfo;
import com.example.michellezhang.ddlkillerpro.database.impl.LabelImpl;

import java.util.ArrayList;
import java.util.List;

public class LabelDialog extends ListActivity {

    private final int POPUP_REQUEST_CODE = 2;
    private final int RESULT_POPUP = 1;

    static String RESULT = "result";
    private String names[] ={"Life","Homework","Sports","Sugar"};
    private int imgs[] = {R.drawable.life, R.drawable.school, R.drawable.football, R.drawable.lollipol};
    private List<LabelInfo> labelList;
    String labelID = "";
    String labelName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        populateLabelList();
        ArrayAdapter<LabelInfo> adapter = new LabelListArrayAdapter(this, labelList);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LabelInfo c = labelList.get(position);
                Intent returnIntent = new Intent();
                returnIntent.putExtra("labelRes", c.getLabelLogo().toString());
                returnIntent.putExtra("labelName", c.getLabelName());
                returnIntent.putExtra(RESULT, c.getLabelName());
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        System.out.println("Execute onActivityResult");
        if (resultCode == POPUP_REQUEST_CODE && intent != null){
            System.out.println("Intent is not null");
            if (resultCode == RESULT_POPUP){
                System.out.println("Result Code is RESULT_OK");
                labelID = intent.getStringExtra("labelID");
                labelName = intent.getStringExtra("labelName");
                finish();
            }
        }
    }

    private void populateLabelList() {
        labelList = new ArrayList<LabelInfo>();
        List<LabelInfo> lists = LabelImpl.getAllLabel();
        for(LabelInfo labelInfo : lists){
            labelList.add(labelInfo);
        }
    }

}
