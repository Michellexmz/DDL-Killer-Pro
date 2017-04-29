package ddlkillerpro.json;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import ddlkillerpro.bean.UserSet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by MichelleZhang on 2017/4/22.
 */

public class JsonUtil {
    public List<UserSet> StringFromJson (String jsondata)
    {
        Type listType = new TypeToken<List<UserSet>>(){}.getType();
        Gson gson=new Gson();
        ArrayList<UserSet> list=gson.fromJson(jsondata, listType);
        return list;
    }

}