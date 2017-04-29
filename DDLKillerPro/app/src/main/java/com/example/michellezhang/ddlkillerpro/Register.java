package com.example.michellezhang.ddlkillerpro;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.michellezhang.ddlkillerpro.Operation.Operation;

import net.sf.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class Register extends AppCompatActivity {
    String str;
    String filePath=null;
    String jsonString=null;
    private static final int REQUEST_EX = 1;
    String userName=null;
    String password=null;
    Button btnCancel;
    Button btnRegister;
    ImageView userImg;
    EditText userNameView;
    EditText userPwdView;
    ProgressDialog dialog;
    Button btnImage;
    private final static int REQUEST_CODE_CHOOSE_IMAGE = 111;
    private final static int REQUEST_CODE_TAKE_PHOTO = 222;
    private final static int REQUEST_CODE_CROP_IMAGE = 333;
    /**request Code 从相册选择照片并裁切**/
    private final static int SELECT_PIC=123;
    /**request Code 裁切照片**/
    private final static int CROP_PIC=125;
    private Uri imgUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnCancel = (Button) findViewById(R.id.register_cancel);
        btnRegister = (Button) findViewById(R.id.register_submit);
        userNameView = (EditText) findViewById(R.id.userName);
        userPwdView = (EditText) findViewById(R.id.new_password);
        dialog = new ProgressDialog(Register.this);
        btnImage = (Button) findViewById(R.id.img_select);
        userImg = (ImageView) findViewById(R.id.userImg);
        imgUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "test.jpg"));
        dialog.setTitle("上传数据中");
        dialog.setMessage("请稍等...");

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnImage.setOnClickListener(new SelectOnClick());
        btnRegister.setOnClickListener(new SubmitOnClick());
    }

    private class SelectOnClick implements View.OnClickListener {
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_PICK, null);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intent, REQUEST_CODE_CHOOSE_IMAGE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(intent != null) {
            switch (requestCode) {
                // 将拍摄的照片进行裁剪(注意，这里需要传递的是照片的路径，而不是intent.getData(), 因为intent.getData()返回的是缩略图的数据)
                case REQUEST_CODE_TAKE_PHOTO:
                    startCropImage(imgUri);
                    break;
                // 将选择的图片进行裁剪
                case REQUEST_CODE_CHOOSE_IMAGE:
                    if (intent.getData() != null) {
                        ContentResolver resolver = getContentResolver();
                        //照片的原始资源地址
                        Uri originalUri = intent.getData();
                        try{
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(resolver, originalUri);
                            System.out.println(originalUri.toString().substring(6));
                            userImg.setImageBitmap(bitmap);
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    break;
                // 将裁剪后的图片进行上传
                case REQUEST_CODE_CROP_IMAGE:
                    try {
                        Uri selectedImage = intent.getData(); //获取系统返回的照片的Uri
                        String[] filePathColumn = { MediaStore.Images.Media.DATA };
                        Cursor cursor = getContentResolver().query(selectedImage,
                                filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String picturePath = cursor.getString(columnIndex);  //获取照片路径
                        cursor.close();
                        Bitmap bitmap= BitmapFactory.decodeFile(picturePath);
                        userImg.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    break;
                default:
                    break;

            }
        } else {
            // 解决某些手机intent为空的情况
            if (requestCode == REQUEST_CODE_TAKE_PHOTO && imgUri != null) {
                startCropImage(imgUri);
            }
        }
    }

    private void startCropImage(Uri uri) {
        Intent intent=new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 使图片处于可裁剪状态
        intent.putExtra("crop", "true");
        // 裁剪框的比例（根据需要显示的图片比例进行设置）
        intent.putExtra("aspectX", 3);
        intent.putExtra("aspectY", 2);
        // 让裁剪框支持缩放
        intent.putExtra("scale", true);
        // 裁剪后图片的大小（注意和上面的裁剪比例保持一致）
        intent.putExtra("outputX", 1);
        intent.putExtra("outputY", 1);
        // 传递原图路径
        //File cropFile = new File(Environment.getExternalStorageDirectory() + "/crop_image.jpg");
        //Uri cropImageUri = Uri.fromFile(cropFile);
        //intent.putExtra(MediaStore.EXTRA_OUTPUT, cropImageUri);
        intent.putExtra("outputFormat", "JPEG");
        // return-data=true传递的为缩略图，小米手机默认传递大图，所以会导致onActivityResult调用失败
        intent.putExtra("return-data", false);
        // 是否需要人脸识别
        intent.putExtra("noFaceDetection", true);
        System.out.println(intent.getData());
        startActivityForResult(intent, REQUEST_CODE_CROP_IMAGE);
    }

    private class SubmitOnClick implements View.OnClickListener
    {
        public void onClick(View v) {
            userName=userNameView.getText().toString().trim();
            password=userPwdView.getText().toString().trim();

            if (userName==null||userName.length()<=0)
            {
                userNameView.requestFocus();
                userNameView.setError("用户名不能为空");
                return ;
            }

            dialog.show();
            new Thread(new Runnable() {
                public void run() {
                    String url="logreg/RegisterServlet";
                    Operation operation=new Operation();
                    //File file = new File(imgUri);
                    String result=operation.register(url,userName,password, "");
                    Message msg=new Message();
                    System.out.println("result---->"+result);
                    msg.obj=result;
                    handler.sendMessage(msg);

                    /*Operaton operaton=new Operaton();
                    File file=new File(filePath);
                    String photo=operaton.uploadFile(file, "ImgReciver");
                    //先进行图片上传的操作，然后服务器返回图片保存在服务器的路径，
                    System.out.println("photo---->"+photo);
                    UserSet user=new UserSet(username, password, photo);
                    //构造一个user对象
                    List<UserSet> list=new ArrayList<UserSet>();
                    list.add(user);
                    WriteJson writeJson=new WriteJson();
                    //将user对象写出json形式字符串
                    jsonString= writeJson.getJsonData(list);
                    System.out.println(jsonString);
                    String result= operaton.UpData("Register", jsonString);
                    Message msg=new Message();
                    System.out.println("result---->"+result);
                    msg.obj=result;
                    handler1.sendMessage(msg);*/
                }
            }).start();

        }
    }

    private void alert() {
        Dialog dialog = new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("您选择的不是有效的图片")
                .setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                filePath = null;
                            }
                        })
                .create();
        dialog.show();
    }

    Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            dialog.dismiss();
            String msgobj=msg.obj.toString();
            if(msgobj.equals("Login Failed"))
            {
                Toast.makeText(Register.this, "注册失败", Toast.LENGTH_LONG).show();
            }
            else {
                JSONObject jsonObject = JSONObject.fromObject(msgobj);
                Integer userID = (Integer) jsonObject.get("userID");
                Toast.makeText(Register.this, "注册成功\n你的ID为：" + userID, Toast.LENGTH_LONG).show();
                finish();
            }
            super.handleMessage(msg);
        }
    };
}
