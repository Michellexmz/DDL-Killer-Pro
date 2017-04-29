package com.example.michellezhang.ddlkillerpro;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.michellezhang.ddlkillerpro.Operation.Operation;
import com.example.michellezhang.ddlkillerpro.database.ItemInfo;
import com.example.michellezhang.ddlkillerpro.database.impl.ItemImpl;

import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ItemDetails extends AppCompatActivity {

    private Button btnEdit;
    private Button btnDelete;
    private ImageButton btnReturn;
    private Button btnCompele;
    private TextView itemNameView;
    private ImageView itemLabelView;
    private TextView itemLeftTimeView;
    private TextView itemDDLView;
    private ImageView logoView;
    private ItemInfo itemInfo;
    private ProgressDialog dialog;
    private ProgressDialog dialogCompleted;
    private Date itemCompleted;
    public static int DELETE_OK = 22;
    public static int EDIT_REQ = 33;
    public static int EDIT_OK = 44;
    public static int COMPELTED_OK = 44;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnReturn = (ImageButton) findViewById(R.id.btnreturn);
        btnCompele = (Button) findViewById(R.id.btnCompele);
        itemNameView = (TextView) findViewById(R.id.textName);
        itemLabelView = (ImageView) findViewById(R.id.itemLabel);
        itemLeftTimeView = (TextView) findViewById(R.id.textLeft);
        itemDDLView = (TextView) findViewById(R.id.textDDL);
        logoView = (ImageView) findViewById(R.id.showComplete);
        dialog = new ProgressDialog(ItemDetails.this);
        dialogCompleted = new ProgressDialog(ItemDetails.this);
        dialogCompleted.setTitle("上传数据中");
        dialogCompleted.setMessage("请稍等...");
        Intent intent = getIntent();
        itemInfo = (ItemInfo) intent.getSerializableExtra("ItemInfo");
        if (itemInfo != null){
            Integer type = intent.getIntExtra("Type", 0);
            itemShow(itemInfo, type);
        }

        btnCompele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ItemDetails.this)
                        .setTitle("您已完成该事项？")
                        .setPositiveButton("是的",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialogCompleted.show();
                                        new Thread(new Runnable() {
                                            public void run() {
                                                Date date = new Date();
                                                itemCompleted = date;
                                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                                                //ItemImpl itemImpl = new ItemImpl();
                                                //itemImpl.itemCompleted(itemInfo.getId(), date);
                                                //itemInfo.setItemComplected(date);
                                                Integer itemID = itemInfo.getItemid();
                                                String url = "itemFunction/FinishItem";
                                                Operation operation = new Operation();
                                                System.out.println("ItemID is "+itemID);
                                                String result = operation.finishItem(url, itemID.toString(), formatter.format(date));
                                                Message msg=new Message();
                                                System.out.println("result---->"+result);
                                                msg.obj=result;
                                                handler.sendMessage(msg);
                                            }
                                        }).start();
                                    }
                                }).setNegativeButton("并没有", null).create()
                        .show();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*String dateString1 = "2017/05/30 23:59:59";
                Date newTime = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date deadline1 = new Date();
                Date completed1 = new Date();
                try{
                    deadline1 = formatter.parse(dateString1); //deadline
                    completed1 = formatter.parse(dateString1); //deadline
                } catch(Exception e){
                    e.printStackTrace();
                }

                ItemInfo itemInfo = new ItemInfo("DDL killer pro", 40170501, newTime, deadline1, completed1,
                        1, 0, 0, 20170503, 30170121);*/
                Intent intent = new Intent(ItemDetails.this, Adding.class);
                intent.putExtra("ItemInfo", itemInfo);
                startActivityForResult(intent, EDIT_REQ);
                Intent intent1 = new Intent();
                setResult(EDIT_OK, intent1);
                finish();
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ItemDetails.this)
                        .setTitle("您确定删除该事项？")
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        new Thread(new Runnable() {
                                            public void run() {
                                                Operation operation = new Operation();
                                                String url = "itemFunction/DeleteItemServlet";
                                                Integer retID = itemInfo.getItemid();
                                                String result = operation.deleteItem(url, retID.toString());
                                                Message msg=new Message();
                                                System.out.println("result---->"+result);
                                                msg.obj=result;
                                                handler1.sendMessage(msg);
                                            }
                                        }).start();


                                        Intent intent = new Intent();
                                        setResult(DELETE_OK, intent);
                                        finish();
                                        //delete the item
                                    }
                                })
                        .setNegativeButton("取消", null).create()
                        .show();
            }
        });
    }

    @Override
    public void onActivityResult(int request, int result, Intent data) {
        super.onActivityResult(request, result, data);
        if(request == EDIT_REQ && result == Adding.EDIT_RES){
            ItemInfo itemInfo = (ItemInfo) data.getSerializableExtra("ItemInfo");
            itemShow(itemInfo, 1);
        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            dialogCompleted.dismiss();
            String msgobj=msg.obj.toString();
            if(msgobj.equals("failed"))
            {
                Toast.makeText(ItemDetails.this, "修改事项失败", Toast.LENGTH_LONG).show();
                finish();
            }
            else {
                Toast.makeText(ItemDetails.this, "修改事项成功", Toast.LENGTH_LONG).show();
                JSONObject jsonObject = JSONObject.fromObject(msgobj);
                Integer itemID = (Integer) jsonObject.get("itemID");
               /* SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                try{
                    itemCompleted = formatter.parse(strDDL);
                } catch (ParseException e){
                    e.printStackTrace();
                }*/
                ItemImpl itemImpl = new ItemImpl();
                itemImpl.itemCompleted(itemID, itemCompleted);
                ItemInfo item = ItemImpl.getItemInfo(itemID);
                itemShow(item, 2);
                Intent intent = new Intent();
                setResult(COMPELTED_OK, intent);
                finish();
            }
            super.handleMessage(msg);
        }
    };

    Handler handler1 = new Handler(){
        @Override
        public void handleMessage(Message msg){
            dialogCompleted.dismiss();
            String msgobj=msg.obj.toString();
            if(msgobj.equals("failed"))
            {
                Toast.makeText(ItemDetails.this, "删除事项失败", Toast.LENGTH_LONG).show();
                finish();
            }
            else {
                Toast.makeText(ItemDetails.this, "删除事项成功", Toast.LENGTH_LONG).show();
                Integer itemID = Integer.valueOf(msgobj);
                ItemImpl itemImpl = new ItemImpl();
                itemImpl.deleteItem(itemID);
                Intent intent = new Intent();
                setResult(DELETE_OK, intent);
                finish();
            }
            super.handleMessage(msg);
        }
    };

    private void itemShow(ItemInfo itemInfo, Integer type){
        itemNameView.setText(itemInfo.getItemName());
        ItemImpl itemImpl = new ItemImpl();
        Integer labelLogo = 0;
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        //hashMap.put("labelLogo", R.drawable.battery);
        //hashMap.put("labelName", "Battery");

        hashMap = itemImpl.getItemLogo(itemInfo.getItemid());
        Set<Map.Entry<String, Object>> set = hashMap.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext()){
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
            if(entry.getKey().equals("labelLogo")){
                labelLogo = (Integer) entry.getValue();
                System.out.println("labelLogo is "+labelLogo);
            }
            if(entry.getKey().equals("labelName")){
                String labelName = (String) entry.getValue();
                System.out.println("labelName is "+labelName);
            }
        }
        try{
            itemLabelView.setImageResource(labelLogo);
            System.out.println("labelLogo is " + labelLogo);
        } catch (Exception e){
            e.printStackTrace();
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        if (type == 1){
            long interval = itemInfo.getItemDeadline().getTime() - itemInfo.getItemNewTime().getTime();
            itemLeftTimeView.setText(interval / (24 * 60 * 60 * 1000) + "天");
            logoView.setVisibility(View.INVISIBLE);
        }
        else {
            TextView showView = (TextView) findViewById(R.id.textViewNext);
            TextView useless = (TextView) findViewById(R.id.textView2);
            useless.setVisibility(View.INVISIBLE);
            itemLeftTimeView.setVisibility(View.INVISIBLE);
            showView.setText("创建时间： " + formatter.format(itemInfo.getItemNewTime()) +
                    "\n完成时间： " + formatter.format(itemInfo.getItemComplected()));
            btnCompele.setVisibility(View.INVISIBLE);
            btnDelete.setVisibility(View.INVISIBLE);
            btnEdit.setVisibility(View.INVISIBLE);
        }
        itemDDLView.setText("截止时间： " + formatter.format(itemInfo.getItemDeadline()));
    }
}
