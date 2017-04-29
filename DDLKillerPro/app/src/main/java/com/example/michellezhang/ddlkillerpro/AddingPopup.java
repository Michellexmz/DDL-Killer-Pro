package com.example.michellezhang.ddlkillerpro;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.michellezhang.ddlkillerpro.activity.SetLabel;
import com.example.michellezhang.ddlkillerpro.database.LabelInfo;

import java.util.ArrayList;
import java.util.List;

public class AddingPopup extends ListActivity {
    private String names[] ={"添加事项","添加分类"};
    private int imgs[] = {R.drawable.itemnew, R.drawable.label};
    private List<LabelInfo> labelList;
    String labelID = "";
    String labelName = "";
    public static int LABEL_ADD = 99;

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
                if (c.getLabelLogo() == R.drawable.itemnew){
                    Intent intent = new Intent(AddingPopup.this, Adding.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(AddingPopup.this, SetLabel.class);
                    startActivityForResult(intent, LABEL_ADD);
                }
            }
        });
    }
    private void populateLabelList() {
        labelList = new ArrayList<LabelInfo>();
        for(int i = 0; i < names.length; i++){
            labelList.add(new LabelInfo(names[i], imgs[i]));
        }
    }

}
