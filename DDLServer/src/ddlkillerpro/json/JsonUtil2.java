package ddlkillerpro.json;

import ddlkillerpro.bean.LabelSet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sjtuf on 2017/4/15.
 */
public class JsonUtil2 {

    public List<LabelSet> StringFromJson(String jsondata) {
        Type listType = new TypeToken<List<LabelSet>>() {
        }.getType();
        Gson gson = new Gson();
        ArrayList<LabelSet> list = gson.fromJson(jsondata, listType);
        return list;
    }
}
