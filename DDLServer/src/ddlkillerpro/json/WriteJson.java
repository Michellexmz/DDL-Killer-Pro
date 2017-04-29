package ddlkillerpro.json;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by MichelleZhang on 2017/4/22.
 */

public class WriteJson {
    public String getJsonData(List<?> list)
    {
        Gson gson=new Gson();
        String jsonstring=gson.toJson(list);
        return jsonstring;
    }

    public String getJsonData(String string)
    {
        Gson gson=new Gson();
        String jsonstring=gson.toJson(string);
        return jsonstring;
    }
}