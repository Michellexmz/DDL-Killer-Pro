package com.example.michellezhang.ddlkillerpro;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.michellezhang.ddlkillerpro.Operation.Operation;
import com.example.michellezhang.ddlkillerpro.database.ItemInfo;
import com.example.michellezhang.ddlkillerpro.database.impl.ItemImpl;
import com.example.michellezhang.ddlkillerpro.database.impl.LabelImpl;
import com.example.michellezhang.ddlkillerpro.database.impl.UserImpl;
import com.example.michellezhang.ddlkillerpro.pickerview.TimePopupWindow;

import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Adding extends AppCompatActivity {
    private TextView tvTime;
    private Integer type;
    private ListView labelOptions;
    private EditText itemNameView;
    private String itemName;
    private String chosedLabelRes;
    private String chosedLabelName;
    private Switch groupSW;
    private Integer groupCheck;
    private Switch friendSW;
    private Integer friendCheck;
    private Switch noticeSW;
    private Integer noticeCheck;
    TimePopupWindow pwTime;
    Button btnCancel;
    Button btnConfirm;
    private Date itemNewTime; //!Notice! itemNewTime is Date type
    private Date itemDeadline;
    ProgressDialog dialog;
    private ItemInfo itemInfo;
    private Integer labelLogo;
    private Integer labelID;
    private Integer newItemID;
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public static int ADDING_RES = 111;
    public static int EDIT_RES = 222;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);

        itemNameView = (EditText) findViewById(R.id.itemName);
        tvTime=(TextView) findViewById(R.id.canlendar);

        Intent intent = getIntent();
        if(intent.getSerializableExtra("ItemInfo") != null){
            type = 1;
            itemInfo = (ItemInfo) intent.getSerializableExtra("ItemInfo");
            newItemID = itemInfo.getItemid();
            boolean group = (itemInfo.getGroupFlag() == 1) ? true : false;
            boolean notice = (itemInfo.getNoticeFlag() == 1) ? true : false;
            boolean friend = (itemInfo.getShowable() == 1) ? true : false;
            groupCheck = itemInfo.getGroupFlag();
            noticeCheck = itemInfo.getNoticeFlag();
            friendCheck = itemInfo.getShowable();
            itemName = itemInfo.getItemName();
            itemNameView.setText(itemName);
            itemNewTime = itemInfo.getItemNewTime();
            initSW(group, friend, notice);
            ItemImpl itemImpl = new ItemImpl();
            HashMap<String, Object> hashMap = itemImpl.getItemLogo(itemInfo.getItemid());
            String labelName = "";
            //ItemImpl itemImpl = new ItemImpl();
            //HashMap<String, Object> hashMap = itemImpl.getItemLogo(itemInfo.getId());
            Set<Map.Entry<String, Object>> set = hashMap.entrySet();
            Iterator it = set.iterator();
            while (it.hasNext()){
                Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
                if(entry.getKey().equals("labelLogo")){
                    labelLogo = (Integer) entry.getValue();
                }
                if(entry.getKey().equals("labelName")){
                    labelName = (String) entry.getValue();
                }
            }
            initLabelTabel(labelLogo, labelName);
            chosedLabelName = labelName;
            chosedLabelRes = labelLogo.toString();
            itemDeadline = itemInfo.getItemDeadline();
            tvTime.setText(getTime(itemDeadline));
        }
        else {
            type = 0;
            initSW(true, true, true);
            groupCheck = 1;
            noticeCheck = 1;
            friendCheck = 1;
            initLabelTabel(R.drawable.school, "exam");
            Integer num = R.drawable.school;
            chosedLabelName = "exam";
            chosedLabelRes = num.toString();
        }

        labelOptions = (ListView) findViewById(R.id.chosedLabel);
        labelOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent labelIntent = new Intent(Adding.this, LabelDialog.class);
                startActivityForResult(labelIntent, 1);
            }
        });


        //时间选择器
        pwTime = new TimePopupWindow(this, TimePopupWindow.Type.ALL);
        //时间选择后回调
        pwTime.setOnTimeSelectListener(new TimePopupWindow.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                tvTime.setText(getTime(date));
                itemDeadline = date;
            }
        });
        //弹出时间选择器
        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwTime.showAtLocation(tvTime, Gravity.BOTTOM, 0, 0,new Date());
            }
        });

        dialog = new ProgressDialog(Adding.this);
        dialog.setTitle("上传数据中");
        dialog.setMessage("请稍等...");

        btnCancel = (Button) findViewById(R.id.cancel);
        btnConfirm = (Button) findViewById(R.id.confirm);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnConfirm.setOnClickListener(new SubmitOnClick());
    }

    @Override
    public void onActivityResult(int request, int result, Intent data) {
        super.onActivityResult(request, result, data);
        if(request == 1 && result == Activity.RESULT_OK){
            chosedLabelRes = data.getStringExtra("labelRes");
            chosedLabelName = data.getStringExtra("labelName");
            initLabelTabel(Integer.valueOf(chosedLabelRes), chosedLabelName);
        }
    }

    private class SubmitOnClick implements View.OnClickListener {
        public void onClick(View v) {
            itemName = itemNameView.getText().toString().trim();

            if (itemName == null || itemName.length() <= 0) {
                itemNameView.requestFocus();
                itemNameView.setError("事项名不能为空");
                return;
            }

            dialog.show();
            if(type == 0){
                new Thread(new Runnable() {
                    public void run() {
                        String url = "itemFunction/AddItemServlet";
                        System.out.println("Here");
                        Operation operation=new Operation();
                        UserImpl userImpl = new UserImpl();
                        String userID = userImpl.getUserID().toString();
                        LabelImpl labelImpl = new LabelImpl();
                        labelID = labelImpl.getLabelID(Integer.valueOf(chosedLabelRes));
                        itemNewTime = new Date();
                        String result=operation.addItem(url,itemName, userID, labelID.toString(), formatter.format(itemNewTime),
                                formatter.format(itemDeadline), friendCheck.toString(), noticeCheck.toString(), groupCheck.toString());
                        Message msg=new Message();
                        System.out.println("result---->"+result);
                        msg.obj=result;
                        handler.sendMessage(msg);
                    }
                }).start();
            }
            else {
                new Thread(new Runnable() {
                    public void run() {
                        String url = "itemFunction/SetItem";
                        Operation operation =new Operation();
                        LabelImpl labelImpl = new LabelImpl();
                        labelID = labelImpl.getLabelID(Integer.valueOf(chosedLabelRes));
                        String result=operation.setItem(url,itemName, labelID.toString(), formatter.format(itemDeadline),
                                friendCheck.toString(), noticeCheck.toString(), groupCheck.toString(), newItemID.toString());
                        Message msg=new Message();
                        System.out.println("result---->"+result);
                        msg.obj=result;
                        handler1.sendMessage(msg);
                    }
                }).start();
            }
        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            dialog.dismiss();
            String msgobj=msg.obj.toString();
            if(msgobj.equals("failed"))
            {
                Toast.makeText(Adding.this, "添加事项失败", Toast.LENGTH_LONG).show();
                finish();
            }
            else {
                Toast.makeText(Adding.this, "添加事项成功", Toast.LENGTH_LONG).show();
                JSONObject jsonObject = JSONObject.fromObject(msgobj);
                Integer itemID = (Integer) jsonObject.get("itemID");
                ItemImpl itemImpl = new ItemImpl();
                UserImpl userImpl = new UserImpl();
                Integer userID = userImpl.getUserID();
                ItemInfo itemInfo = new ItemInfo(itemName, labelID, itemNewTime, itemDeadline, null,
                        friendCheck, noticeCheck, groupCheck, userID, itemID);
                itemImpl.addItem(itemInfo);
                Intent intent = new Intent();
                setResult(ADDING_RES, intent);
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
                Toast.makeText(Adding.this, "修改事项失败", Toast.LENGTH_LONG).show();
                finish();
            }
            else {
                Toast.makeText(Adding.this, "修改事项成功", Toast.LENGTH_LONG).show();
                JSONObject jsonObject = JSONObject.fromObject(msgobj);
                Integer itemID = (Integer) jsonObject.get("itemID");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                ItemImpl itemImpl = new ItemImpl();
                itemImpl.setItem(newItemID, itemName, labelID, itemDeadline, friendCheck, noticeCheck, groupCheck);
                Intent intent = new Intent();
                ItemInfo itemInfo = new ItemInfo(itemName, labelID,  itemNewTime, itemDeadline, null, friendCheck, noticeCheck, groupCheck,
                        UserImpl.getUserID(), itemID);
                intent.putExtra("ItemInfo", itemInfo);
                setResult(EDIT_RES, intent);
                finish();
            }
            super.handleMessage(msg);
        }
    };

    public void initSW(boolean group, boolean friend, boolean notice){
        groupSW = (Switch) findViewById(R.id.group_switch);
        groupSW.setChecked(group);
        groupSW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    groupCheck = 1;
                }
                else {
                    groupCheck = 0;
                }
            }
        });

        friendSW = (Switch) findViewById(R.id.friend_switch);
        friendSW.setChecked(friend);
        friendSW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    friendCheck = 1;
                }
                else {
                    friendCheck = 0;
                }
            }
        });

        noticeSW = (Switch) findViewById(R.id.notice_switch);
        noticeSW.setChecked(notice);
        noticeSW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    noticeCheck = 1;
                }
                else {
                    noticeCheck = 0;
                }
            }
        });
    }

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

    public void initLabelTabel(int labelRes, String labelName){
        ArrayList<HashMap<String, Object>> labels = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> label = new HashMap<String, Object>();
        label.put("thisLogo", labelRes);
        label.put("thisName", labelName);
        labels.add(label);
        SimpleAdapter saImageItems = new SimpleAdapter(this,
                labels,// 数据来源
                R.layout.content_label_row,//每一个user xml 相当ListView的一个组件
                new String[] { "thisLogo", "thisName"},
                // 分别对应view 的id
                new int[] { R.id.thisLogo, R.id.thisName});
        ((ListView) findViewById(R.id.chosedLabel)).setAdapter(saImageItems);
    }
}
