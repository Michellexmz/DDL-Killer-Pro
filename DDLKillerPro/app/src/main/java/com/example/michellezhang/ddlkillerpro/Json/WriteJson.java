package com.example.michellezhang.ddlkillerpro.Json;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by sjtuf on 2017/4/18.
 */
public class WriteJson {

    public String getJsonData(List<?> list)
    {
        Gson gson=new Gson();
        String jsonstring=gson.toJson(list);
        return jsonstring;
    }
}
