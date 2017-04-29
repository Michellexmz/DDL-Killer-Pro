package com.example.michellezhang.ddlkillerpro;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.michellezhang.ddlkillerpro.Operation.Operation;
import com.example.michellezhang.ddlkillerpro.database.ItemInfo;
import com.example.michellezhang.ddlkillerpro.database.LabelInfo;
import com.example.michellezhang.ddlkillerpro.database.UserInfo;
import com.example.michellezhang.ddlkillerpro.database.impl.ItemImpl;
import com.example.michellezhang.ddlkillerpro.database.impl.LabelImpl;
import com.example.michellezhang.ddlkillerpro.database.impl.UserImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.litepal.crud.DataSupport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {
    // UI references.
    private EditText mUserIDView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private String userID;
    private String password;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dialog = new ProgressDialog(Login.this);
        mUserIDView = (EditText) findViewById(R.id.userID);
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {

                    return true;
                }
                return false;
            }
        });


        //mLoginFormView = findViewById(R.id.login_form);
        //mProgressView = findViewById(R.id.login_progress);
        dialog.setTitle("上传数据中");
        dialog.setMessage("请稍等...");
        Button btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignIn.setOnClickListener(new SubmitOnClick());
        Button btnSignUp = (Button) findViewById(R.id.sign_up_button);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }

    private class SubmitOnClick implements View.OnClickListener
    {
        public void onClick(View v) {
            userID=mUserIDView.getText().toString().trim();
            password=mPasswordView.getText().toString().trim();
            System.out.println(R.drawable.school);
            System.out.println(R.drawable.life);

            if (userID==null||userID.length()<=0)
            {
                mUserIDView.requestFocus();
                mUserIDView.setError("用户名不能为空");
                return ;
            }
            if (!isUserIDValid(userID)){
                mUserIDView.requestFocus();
                mUserIDView.setError(getString(R.string.error_invalid_userID));
                return ;
            }
            if (password == null || !isPasswordValid(password)){
                mPasswordView.requestFocus();
                mPasswordView.setError(getString(R.string.error_invalid_password));
                return;
            }

            dialog.show();
            new Thread(new Runnable() {
                public void run() {
                    String url="logreg/LoginServlet";
                    Operation operation=new Operation();
                    String result=operation.login(url,userID,password);
                    Message msg=new Message();
                    System.out.println("result---->"+result);

                    if(result.equals("Login Failed")){
                        msg.obj=result;
                    }
                    else {
                        JSONObject jsonObject = JSONObject.fromObject(result);
                        Integer userID = (Integer) jsonObject.get("userID");
                        String userName = (String) jsonObject.get("userName");
                        String userPwd = (String) jsonObject.get("userPwd");
                        String userImg = (String) jsonObject.get("userImg");
                        UserInfo userInfo = new UserInfo(userID, userName, userPwd, userImg);
                        userInfo.setId(2);
                        UserImpl userImpl = new UserImpl();
                        userImpl.addUser(userInfo);

                        DataSupport.deleteAll(ItemInfo.class);
                        ItemImpl itemImpl = new ItemImpl();
                        String url2="itemFunction/ShowAllItems";
                        String items = operation.getAllItem(url2, userID.toString());
                        JSONArray jsonArray=JSONArray.fromObject(items);

                        for(Object object : jsonArray){
                            JSONObject json = JSONObject.fromObject(object);
                            System.out.println(json.toString());
                            SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy h:m:s aaa");
                            String itemName = (String) json.get("itemName");
                            Integer itemId = (Integer) json.get("itemID");
                            Integer userId = (Integer) json.get("userID");
                            Integer labelId = (Integer) json.get("labelID");
                            Integer showable = (Integer) json.get("showable");
                            Integer groupFlag = (Integer) json.get("groupFlag");
                            Integer noticeFlag = (Integer) json.get("noticeFlag");
                            String newStr = (String) json.get("itemNewTime");
                            String ddlStr = (String) json.get("itemDeadline");
                            String comStr = (String) json.get("itemCompleted");
                            Date itemNewTime = new Date();
                            Date itemDeadline = new Date();
                            Date itemCompleted = new Date();
                            try{
                                itemNewTime = formatter.parse(newStr);
                                itemDeadline = formatter.parse(ddlStr);
                                if (comStr != null){
                                    itemCompleted = formatter.parse(comStr);
                                }
                                else{
                                    itemCompleted = null;
                                }
                            } catch (ParseException e){
                                e.printStackTrace();
                            }

                            ItemInfo itemInfo = new ItemInfo(itemName, labelId, itemNewTime, itemDeadline, itemCompleted,
                                    showable, noticeFlag, groupFlag, userId, itemId);
                            itemImpl.addItem(itemInfo);
                        }

                        DataSupport.deleteAll(LabelInfo.class);
                        String urlLabel = "labelFunction/ShowAllLabels";
                        String labels = operation.getAllLabel(urlLabel, userID.toString());
                        System.out.println("here!"+ labels.toString());
                        JSONArray labelArray=JSONArray.fromObject(labels);
                        for(Object object : labelArray){
                            JSONObject json = JSONObject.fromObject(object);
                            String labelName = (String) json.get("labelName");
                            Integer labelID = (Integer) json.get("labelID");
                            Integer labelLogo= (Integer) json.get("labelLogo");
                            String labelColor = (String) json.get("labelColor");
                            LabelInfo labelInfo = new LabelInfo(labelID, labelName, labelLogo, labelColor);
                            LabelImpl labelImpl = new LabelImpl();
                            labelImpl.addLabel(labelInfo);
                        }
                        msg.obj=result;
                    }
                    handler.sendMessage(msg);
                }
            }).start();

        }
    }

    Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            dialog.dismiss();
            String msgobj=msg.obj.toString();
            System.out.println("msgobj is:" + msgobj);
            if(msgobj.equals("Login Failed"))
            {
                Toast.makeText(Login.this, "登录失败", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(Login.this, "登陆成功", Toast.LENGTH_LONG).show();
                try {


                    finish();
                    //将信息保存进本地数据库
                } catch (JSONException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            super.handleMessage(msg);
        }
    };


    private boolean isUserIDValid(String userID) {
        Pattern pattern = Pattern.compile("[0-9]{8}");
        Matcher matcher = pattern.matcher(userID);
        return matcher.matches();
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6 && password.length() <= 16;
    }

}
