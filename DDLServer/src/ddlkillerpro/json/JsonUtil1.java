package ddlkillerpro.json;

import ddlkillerpro.bean.ItemSet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sjtuf on 2017/4/15.
 */
public class JsonUtil1 {

    public List<ItemSet> StringFromJson(String jsondata) {
        Type listType = new TypeToken<List<ItemSet>>() {
        }.getType();
        Gson gson = new Gson();
        ArrayList<ItemSet> list = gson.fromJson(jsondata, listType);
        return list;
    }
}