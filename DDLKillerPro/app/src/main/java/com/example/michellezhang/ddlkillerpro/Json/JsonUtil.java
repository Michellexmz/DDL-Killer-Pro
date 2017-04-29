package com.example.michellezhang.ddlkillerpro.Json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sjtuf on 2017/4/18.
 */
public class JsonUtil {
    public List<?> StringFromJson (String jsondata)
    {
        Type listType = new TypeToken<List<?>>(){}.getType();
        Gson gson=new Gson();
        ArrayList<?> list=gson.fromJson(jsondata, listType);
        return list;

    }
}

