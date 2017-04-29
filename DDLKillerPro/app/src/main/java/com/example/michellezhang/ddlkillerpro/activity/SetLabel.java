package com.example.michellezhang.ddlkillerpro.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.michellezhang.ddlkillerpro.Operation.Operation;
import com.example.michellezhang.ddlkillerpro.R;
import com.example.michellezhang.ddlkillerpro.database.LabelInfo;
import com.example.michellezhang.ddlkillerpro.database.impl.LabelImpl;
import com.goodiebag.horizontalpicker.HorizontalPicker;

import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//import com.example.zdl.database.R;
//import com.example.zdl.database.impl.labelimpl;
//import com.example.zdl.database.impl.setimpl;

/**
 * Created by zdl on 2017/4/19.
 */

public class SetLabel extends AppCompatActivity {
    private Integer labelLogo;
    private String string;
    private ProgressDialog dialog;
    public static int LABEL_OK = 111;
    public static int ADD_LABEL_OK = 98;
    private LabelInfo curLabel = null;
    private EditText edittext;
    private int type = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setlabel);
        edittext=(EditText)findViewById(R.id.labelName);
        dialog = new ProgressDialog(SetLabel.this);
        dialog.setTitle("上传数据中");
        dialog.setMessage("请稍等...");
        final HorizontalPicker hpImage = (HorizontalPicker) findViewById(R.id.hpImage1);
        List<HorizontalPicker.PickerItem> imageItems = new ArrayList<>();
        imageItems.add(new HorizontalPicker.DrawableItem(R.drawable.calendar));
        imageItems.add(new HorizontalPicker.DrawableItem(R.drawable.groupflag));
        imageItems.add(new HorizontalPicker.DrawableItem(R.drawable.itemnew));
        imageItems.add(new HorizontalPicker.DrawableItem(R.drawable.clock));
        imageItems.add(new HorizontalPicker.DrawableItem(R.drawable.star));
        imageItems.add(new HorizontalPicker.DrawableItem(R.drawable.football));
        imageItems.add(new HorizontalPicker.DrawableItem(R.drawable.library));
        imageItems.add(new HorizontalPicker.DrawableItem(R.drawable.life));
        imageItems.add(new HorizontalPicker.DrawableItem(R.drawable.school));

        Intent intent = getIntent();
        if(intent.getSerializableExtra("LabelInfo") != null){
            curLabel = (LabelInfo) intent.getSerializableExtra("LabelInfo");
            edittext.setText(curLabel.getLabelName());
        }
        else{
            type = 2;

        }
        hpImage.setItems(imageItems);

        Button button15=(Button)findViewById(R.id.button15);
        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                string = edittext.getText().toString();

                if (type == 1){
                    dialog.show();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Operation operation = new Operation();
                            String url="labelFunction/SetLabel";
                            LabelInfo labelInfo = new LabelInfo();
                            labelInfo.setLabelLogo(labelLogo);
                            labelInfo.setLabelName(string);
                            LabelImpl labelImpl = new LabelImpl();
                            Integer labelID = curLabel.getLabelid();
                            String result=operation.setLabel(url,string, labelLogo.toString(),"color",labelID.toString());
                            Message msg=new Message();
                            System.out.println("result---->"+result);
                            msg.obj=result;
                            handler.sendMessage(msg);
                        }
                    }).start();
                }
                else {
                    dialog.show();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Operation operation = new Operation();
                            String url="labelFunction/AddLabel";
                            String result=operation.addLabel(url,string,labelLogo.toString(),"color1");
                            Message msg=new Message();
                            System.out.println("result---->"+result);
                            msg.obj=result;
                            handler1.sendMessage(msg);
                        }
                    }).start();
                }

            }
        });


        HorizontalPicker.OnSelectionChangeListener listener=new HorizontalPicker.OnSelectionChangeListener() {
            @Override
            public void onItemSelect(HorizontalPicker picker, int i) {
                HorizontalPicker.PickerItem selected = picker.getSelectedItem();
                labelLogo=selected.getDrawable();
                System.out.println("Drawable is " + labelLogo);

                //Toast.makeText(SetLabel.this, "Item at" + (horizontalPicker.getSelectedIndex() + 1) + " is selected", Toast.LENGTH_SHORT).show();
            }
        };
        //Log.d("SetLabel","a");
        hpImage.setChangeListener(listener);

        Button button16=(Button)findViewById(R.id.button16);
        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            dialog.dismiss();
            String msgobj=msg.obj.toString();
            if(msgobj.equals("failed"))
            {
                Toast.makeText(SetLabel.this, "修改分类失败", Toast.LENGTH_LONG).show();
                finish();
            }
            else {
                Toast.makeText(SetLabel.this, "修改分类成功", Toast.LENGTH_LONG).show();
                JSONObject jsonObject = JSONObject.fromObject(msgobj);
                Integer labelId = (Integer) jsonObject.get("labelID");
                String labelName = (String) jsonObject.get("labelName");
                Integer labellogo = (Integer) jsonObject.get("labelLogo");
                LabelImpl.setLabel(labelId,labelName,labellogo);
                Intent intent = new Intent();
                setResult(LABEL_OK, intent);
                finish();
            }
            super.handleMessage(msg);
        }
    };

    Handler handler1 = new Handler(){
        @Override
        public void handleMessage(Message msg){
            dialog.dismiss();
            String msgobj=msg.obj.toString();
            if(msgobj.equals("failed"))
            {
                Toast.makeText(SetLabel.this, "添加分类失败", Toast.LENGTH_LONG).show();
                finish();
            }
            else {
                Toast.makeText(SetLabel.this, "添加分类成功", Toast.LENGTH_LONG).show();
                JSONObject jsonObject = JSONObject.fromObject(msgobj);
                Integer labelId = (Integer) jsonObject.get("labelID");
                String labelName = (String) jsonObject.get("labelName");
                Integer labellogo = (Integer) jsonObject.get("labelLogo");
                LabelInfo labelInfo = new LabelInfo(labelId, labelName, labelLogo, "color");
                LabelImpl label = new LabelImpl();
                label.addLabel(labelInfo);
                finish();
            }
            super.handleMessage(msg);
        }
    };
}
