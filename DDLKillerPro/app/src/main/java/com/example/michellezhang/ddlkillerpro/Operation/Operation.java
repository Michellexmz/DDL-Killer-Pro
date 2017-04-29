package com.example.michellezhang.ddlkillerpro.Operation;

import com.example.michellezhang.ddlkillerpro.network.Connect;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
//import android.util.Log; //日志类，Android里面有
/**
 * Created by sjtuf on 2017/4/18.
 */
public class Operation
{

    public String login(String url,String userID,String userPwd)
    {
        String result = null;
        Connect connNet=new Connect();
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("userID", userID));
        params.add(new BasicNameValuePair("userPwd", userPwd));
        try {
            HttpEntity entity=new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost=connNet.gethttPost(url);
            System.out.println(httpPost.toString());
            httpPost.setEntity(entity);
            HttpClient client=new DefaultHttpClient();
            HttpResponse httpResponse=client.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK)
            {
                result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            }
            else
            {
                result="连接失败！";
            }
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        } catch (ClientProtocolException e) {

            e.printStackTrace();
        } catch (ParseException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return result;
    }

    public String checkusername(String url,String username)
    {
        String result=null;
        Connect connNet=new Connect();
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username", username));
        try {
            HttpEntity entity=new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost=connNet.gethttPost(url);
            System.out.println(httpPost.toString());
            httpPost.setEntity(entity);
            HttpClient client=new DefaultHttpClient();
            HttpResponse httpResponse=client.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode()==HttpStatus.SC_OK)
            {
                result=EntityUtils.toString(httpResponse.getEntity(), "utf-8");
                System.out.println("resu"+result);
            }
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        } catch (ClientProtocolException e) {

            e.printStackTrace();
        } catch (ParseException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return result;
    }

    public String register(String url,String userName,String userPwd,String userImg)
    {
        String result = null;
        Connect connNet=new Connect();
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("userName", userName));
        params.add(new BasicNameValuePair("userPwd", userPwd));
        params.add(new BasicNameValuePair("userImg",userImg));
        try {
            HttpEntity entity=new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost=connNet.gethttPost(url);
            System.out.println(httpPost.toString());
            httpPost.setEntity(entity);
            HttpClient client=new DefaultHttpClient();
            HttpResponse httpResponse=client.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK)
            {
                result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");

            }
            else
            {
                result="failed";
            }
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        } catch (ClientProtocolException e) {

            e.printStackTrace();
        } catch (ParseException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return result;
    }

    public String addItem(String url,String itemName,String userID,String labelID,String itemNewTime,String itemDeadline,String showable,String noticeFlag,String groupFlag)
    {
        String result = null;
        Connect connNet=new Connect();
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("itemName", itemName));
        params.add(new BasicNameValuePair("userID", userID));
        params.add(new BasicNameValuePair("labelID",labelID));
        params.add(new BasicNameValuePair("itemNewTime",itemNewTime));
        params.add(new BasicNameValuePair("itemDeadline",itemDeadline));
        params.add(new BasicNameValuePair("showable",showable));
        params.add(new BasicNameValuePair("noticeFlag",noticeFlag));
        params.add(new BasicNameValuePair("groupFlag",groupFlag));

        try {
            HttpEntity entity=new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost=connNet.gethttPost(url);
            System.out.println(httpPost.toString());
            httpPost.setEntity(entity);
            HttpClient client=new DefaultHttpClient();
            HttpResponse httpResponse=client.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK)
            {
                result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            }
            else
            {
                result="failed";
            }
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        } catch (ClientProtocolException e) {

            e.printStackTrace();
        } catch (ParseException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return result;
    }

    public String setItem(String url,String itemName,String labelID,String itemDeadline,String showable,String noticeFlag,String groupFlag,String itemID)
    {
        String result = null;
        Connect connNet=new Connect();
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("itemName", itemName));
        params.add(new BasicNameValuePair("labelID",labelID));
        params.add(new BasicNameValuePair("itemDeadline",itemDeadline));
        params.add(new BasicNameValuePair("showable",showable));
        params.add(new BasicNameValuePair("noticeFlag",noticeFlag));
        params.add(new BasicNameValuePair("groupFlag",groupFlag));
        params.add(new BasicNameValuePair("itemID",itemID));

        try {
            HttpEntity entity=new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost=connNet.gethttPost(url);
            System.out.println(httpPost.toString());
            httpPost.setEntity(entity);
            HttpClient client=new DefaultHttpClient();
            HttpResponse httpResponse=client.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK)
            {
                result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            }
            else
            {
                result="failed";
            }
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        } catch (ClientProtocolException e) {

            e.printStackTrace();
        } catch (ParseException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return result;
    }

    public String finishItem(String url,String itemID,String itemCompleted)
    {
        String result = null;
        Connect connNet=new Connect();
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("itemID", itemID));
        params.add(new BasicNameValuePair("itemCompleted",itemCompleted));

        try {
            HttpEntity entity=new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost=connNet.gethttPost(url);
            System.out.println(httpPost.toString());
            httpPost.setEntity(entity);
            HttpClient client=new DefaultHttpClient();
            HttpResponse httpResponse=client.execute(httpPost);
            System.out.println("code is "+httpResponse.getStatusLine().getStatusCode());
            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK)
            {
                result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            }
            else
            {
                result="failed";
            }
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        } catch (ClientProtocolException e) {

            e.printStackTrace();
        } catch (ParseException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return result;
    }

    public String deleteItem(String url,String itemID)
    {
        String result = null;
        Connect connNet=new Connect();
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("itemID", itemID));

        try {
            HttpEntity entity=new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost=connNet.gethttPost(url);
            System.out.println(httpPost.toString());
            httpPost.setEntity(entity);
            HttpClient client=new DefaultHttpClient();
            HttpResponse httpResponse=client.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK)
            {
                result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            }
            else
            {
                result="failed";
            }
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        } catch (ClientProtocolException e) {

            e.printStackTrace();
        } catch (ParseException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return result;
    }

    public String getAllItem(String url,String userID)
    {
        String result = null;
        Connect connNet=new Connect();
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("userID",userID));

        try {
            HttpEntity entity=new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost=connNet.gethttPost(url);
            System.out.println(httpPost.toString());
            httpPost.setEntity(entity);
            HttpClient client=new DefaultHttpClient();
            HttpResponse httpResponse=client.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK)
            {
                result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            }
            else
            {
                result="failed";
            }
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        } catch (ClientProtocolException e) {

            e.printStackTrace();
        } catch (ParseException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return result;
    }

    public String getAllLabel(String url,String userID)
    {
        String result = null;
        Connect connNet=new Connect();
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("userID",userID));

        try {
            HttpEntity entity=new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost=connNet.gethttPost(url);
            System.out.println(httpPost.toString());
            httpPost.setEntity(entity);
            HttpClient client=new DefaultHttpClient();
            HttpResponse httpResponse=client.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK)
            {
                result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            }
            else
            {
                result="failed";
            }
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        } catch (ClientProtocolException e) {

            e.printStackTrace();
        } catch (ParseException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return result;
    }

    public String addLabel(String url,String labelName,String labelLogo,String labelColor)
    {
        String result = null;
        Connect connNet=new Connect();
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("labelName",labelName));
        params.add(new BasicNameValuePair("labelLogo",labelLogo ));
        params.add(new BasicNameValuePair("labelColor",labelColor));

        try {
            HttpEntity entity=new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost=connNet.gethttPost(url);
            System.out.println(httpPost.toString());
            httpPost.setEntity(entity);
            HttpClient client=new DefaultHttpClient();
            HttpResponse httpResponse=client.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK)
            {
                result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            }
            else
            {
                result="failed";
            }
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        } catch (ClientProtocolException e) {

            e.printStackTrace();
        } catch (ParseException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return result;
    }

    public String setLabel(String url,String labelName,String labelLogo,String labelColor,String labelID)
    {
        String result = null;
        Connect connNet=new Connect();
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("labelName",labelName));
        params.add(new BasicNameValuePair("labelLogo",labelLogo ));
        params.add(new BasicNameValuePair("labelColor",labelColor));
        params.add(new BasicNameValuePair("labelID",labelID));

        try {
            HttpEntity entity=new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost=connNet.gethttPost(url);
            System.out.println(httpPost.toString());
            httpPost.setEntity(entity);
            HttpClient client=new DefaultHttpClient();
            HttpResponse httpResponse=client.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK)
            {
                result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            }
            else
            {
                result="failed";
            }
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        } catch (ClientProtocolException e) {

            e.printStackTrace();
        } catch (ParseException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return result;
    }

    public String deleteLabel(String url,String labelID)
    {
        String result = null;
        Connect connNet=new Connect();
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("labelID",labelID));

        try {
            HttpEntity entity=new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost=connNet.gethttPost(url);
            System.out.println(httpPost.toString());
            httpPost.setEntity(entity);
            HttpClient client=new DefaultHttpClient();
            HttpResponse httpResponse=client.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK)
            {
                result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            }
            else
            {
                result="failed";
            }
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        } catch (ClientProtocolException e) {

            e.printStackTrace();
        } catch (ParseException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return result;
    }

    public String setuser(String url,String userName,String userPwd,String userImg)
    {
        String result = null;
        Connect connNet=new Connect();
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("userName", userName));
        params.add(new BasicNameValuePair("userPwd", userPwd));
        params.add(new BasicNameValuePair("userImg",userImg));
        try {
            HttpEntity entity=new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost=connNet.gethttPost(url);
            System.out.println(httpPost.toString());
            httpPost.setEntity(entity);
            HttpClient client=new DefaultHttpClient();
            HttpResponse httpResponse=client.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK)
            {
                result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            }
            else
            {
                result="失败！";
            }
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        } catch (ClientProtocolException e) {

            e.printStackTrace();
        } catch (ParseException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return result;
    }


    public String UpData(String uripath,String jsonString)
    {
        String result = null;
        List<NameValuePair> list=new ArrayList<NameValuePair>();
        NameValuePair nvp=new BasicNameValuePair("jsonstring", jsonString);
        list.add(nvp);
        Connect connNet=new Connect();
        HttpPost httpPost=connNet.gethttPost(uripath);
        try {
            HttpEntity entity = new UrlEncodedFormEntity(list, HTTP.UTF_8);
            httpPost.setEntity(entity);
            HttpClient client=new DefaultHttpClient();
            HttpResponse httpResponse=client.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode()==200)
            {
                result=EntityUtils.toString(httpResponse.getEntity(), "utf-8");
                System.out.println("resu"+result);
            }
            else {
                result="上传失败！";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String uploadFile(File file, String urlString)
    {
        final String TAG = "uploadFile";
        final int TIME_OUT = 10*1000;
        final String CHARSET = "utf-8";
        String result = null;
        String  BOUNDARY =  UUID.randomUUID().toString();
        String PREFIX = "--" , LINE_END = "\r\n";
        String CONTENT_TYPE = "multipart/form-data";

        try {
            Connect connNet=new Connect();
            HttpURLConnection conn	=connNet.getConn(urlString);
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);//不允许使用缓存
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Charset", CHARSET); //设置编码
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);

            if(file!=null)
            {
                DataOutputStream dos = new DataOutputStream( conn.getOutputStream());
                StringBuffer sb = new StringBuffer();
                sb.append(PREFIX);
                sb.append(BOUNDARY);
                sb.append(LINE_END);


                sb.append("Content-Disposition: form-data; name=\"img\"; filename=\""+file.getName()+"\""+LINE_END);
                sb.append("Content-Type: application/octet-stream; charset="+CHARSET+LINE_END);
                sb.append(LINE_END);
                dos.write(sb.toString().getBytes());
                InputStream is = new FileInputStream(file);
                byte[] bytes = new byte[1024];
                int len = 0;
                while((len=is.read(bytes))!=-1)
                {
                    dos.write(bytes, 0, len);
                }
                is.close();
                dos.write(LINE_END.getBytes());
                byte[] end_data = (PREFIX+BOUNDARY+PREFIX+LINE_END).getBytes();
                dos.write(end_data);
                dos.flush();

                int res = conn.getResponseCode();
                //Log.e(TAG, "response code:"+res);
                //                if(res==200)
                //                {
                //Log.e(TAG, "request success");
                InputStream input =  conn.getInputStream();
                StringBuffer sb1= new StringBuffer();
                int ss ;
                while((ss=input.read())!=-1)
                {
                    sb1.append((char)ss);
                }
                result = sb1.toString();
                //Log.e(TAG, "result : "+ result);
                //                }
                //                else{
                //                    Log.e(TAG, "request error");
                //                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
