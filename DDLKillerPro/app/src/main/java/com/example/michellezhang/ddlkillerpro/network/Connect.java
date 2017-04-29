package com.example.michellezhang.ddlkillerpro.network;

import org.apache.http.client.methods.HttpPost;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by sjtuf on 2017/4/18.
 */
public class Connect {

    private static final String baserUrl="http://10.188.127.113:8080/";

    public HttpURLConnection getConn(String urlpath)
    {
        String finalUrl=baserUrl+urlpath;
        HttpURLConnection connection = null;
        try {
            URL url=new URL(finalUrl);
            connection=(HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return connection;

    }
    public HttpPost gethttPost(String urlpath)
    {
        HttpPost httpPost=new HttpPost(baserUrl+urlpath);

       // System.out.println(baserUrl+urlpath);
        return httpPost;
    }
}
