package tmtc.soap.Helper;

import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import tmtc.soap.Model.Movie;

/**
 * Bad Boys Team
 * Created by remyjallan on 24/03/2016.
 */
public class ApiHelper {
    public static String BASE = "http://10.31.2.89:3000/";

    public static String AUTH = ApiHelper.BASE + "auth/";

    public static String SIGN_UP = ApiHelper.AUTH + "signup";

    public static String MOVIE = ApiHelper.BASE + "movies/";

    protected static String LOGIN = ApiHelper.AUTH + "{username}/{password}";

    protected static String BOUGHT = ApiHelper.MOVIE + "bought?idUser={idUser}&idMovie={idMovie}";

    public static String login(String username,String password) {
        HashMap<String,String> params = new HashMap<>();
        params.put("username",username);
        params.put("password", password);

        return ApiHelper.getUrl(ApiHelper.LOGIN, params);
    }

    public static String bought(int idUser,int idMovie){
        HashMap<String,String> params = new HashMap<>();
        params.put("idUser",String.valueOf(idUser));
        params.put("idMovie",String.valueOf(idMovie));

        return ApiHelper.getUrl(ApiHelper.BOUGHT, params);
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
