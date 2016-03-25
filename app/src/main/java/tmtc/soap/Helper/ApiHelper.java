package tmtc.soap.Helper;

import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Bad Boys Team
 * Created by remyjallan on 24/03/2016.
 */
public class ApiHelper {
    public static String BASE = "http://10.31.2.89:3000/";

    public static String AUTH = ApiHelper.BASE + "auth/";

    public static String SIGN_UP = ApiHelper.AUTH + "signup";

    protected static String LOGIN = ApiHelper.AUTH + "{username}/{password}";


    public static String login(String username,String password) {
        HashMap<String,String> params = new HashMap<>();
        params.put("username",username);
        params.put("password",password);

        return ApiHelper.getUrl(ApiHelper.LOGIN, params);
    }

    protected static String getUrl(String url,HashMap<String,String> params) {
        for (Map.Entry<String, String> stringEntry : params.entrySet()) {
            String key = stringEntry.getKey();
            String value = stringEntry.getValue();

            url = url.replace("{" + key + "}", value);
        }

        return url;
    }


}
