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
    public static String BASE = "http://192.168.1.96:3000/";

    public static String AUTH = ApiHelper.BASE + "auth/";

    public static String SIGN_UP = ApiHelper.AUTH + "signup";

    public static String MOVIE = ApiHelper.BASE + "movies/";

    public static String COMMENT = ApiHelper.BASE + "comments/";

    public static String PERSON = ApiHelper.BASE + "persons/";

    public static String SEARCH = ApiHelper.BASE + "search/";

    public static String FRIENDSHIP = ApiHelper.BASE + "friendship/";

    protected static String LOGIN = ApiHelper.AUTH + "{username}/{password}";

    protected static String BOUGHT = ApiHelper.MOVIE + "bought?idUser={idUser}&idMovie={idMovie}";

    protected static String LAST_MOVIE = ApiHelper.MOVIE + "last?count={count}";

    protected static String COMMENT_MOVIE = ApiHelper.COMMENT+"movie/{idMovie}";

    protected static String PERSON_GET = ApiHelper.PERSON + "{idPerson}";

    protected static String COMMENT_PUT = ApiHelper.COMMENT + "{idComment}";

    protected static String GET_MOVIE = ApiHelper.MOVIE + "{idMovie}";

    protected static String SEARCH_GET = ApiHelper.SEARCH + "{query}";

    protected static String COMMENT_USER = ApiHelper.COMMENT + "user/{idUser}";

    protected static String IS_MY_FRIEND = ApiHelper.FRIENDSHIP + "{idUser}";

    protected static String POST_FRIEND = ApiHelper.FRIENDSHIP + "{idUser}";

    protected static String DELETE_FRIEND = ApiHelper.FRIENDSHIP + "?idUser1={idUser1}&idUser2={idUser2}";

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

    public static String lastMovie(int count) {
        HashMap<String,String> params = new HashMap<>();
        params.put("count",String.valueOf(count));

        return ApiHelper.getUrl(ApiHelper.LAST_MOVIE, params);
    }

    public static String commentMovie(int idMovie) {
        HashMap<String,String> params = new HashMap<>();
        params.put("idMovie",String.valueOf(idMovie));

        return ApiHelper.getUrl(ApiHelper.COMMENT_MOVIE, params);
    }

    public static String getPerson(int idPerson) {
        HashMap<String,String> params = new HashMap<>();
        params.put("idPerson",String.valueOf(idPerson));

        return ApiHelper.getUrl(ApiHelper.PERSON_GET, params);
    }

    public static String putComment(int idComment) {
        HashMap<String,String> params = new HashMap<>();
        params.put("idComment",String.valueOf(idComment));

        return ApiHelper.getUrl(ApiHelper.COMMENT_PUT, params);
    }

    public static String getMovie(int idMovie) {
        HashMap<String,String> params = new HashMap<>();
        params.put("idMovie",String.valueOf(idMovie));

        return ApiHelper.getUrl(ApiHelper.GET_MOVIE, params);
    }

    public static String getSearch(String query) {
        HashMap<String,String> params = new HashMap<>();
        params.put("query",query);

        return ApiHelper.getUrl(ApiHelper.SEARCH_GET,params);
    }

    public static String commentUser(int idUser) {
        HashMap<String,String> params = new HashMap<>();
        params.put("idUser",String.valueOf(idUser));

        return ApiHelper.getUrl(ApiHelper.COMMENT_USER, params);
    }

    public static String isMyFriend(int idUser) {
        HashMap<String,String> params = new HashMap<>();
        params.put("idUser",String.valueOf(idUser));

        return ApiHelper.getUrl(ApiHelper.IS_MY_FRIEND, params);
    }

    public static String postFriend(int idUser) {
        HashMap<String,String> params = new HashMap<>();
        params.put("idUser",String.valueOf(idUser));

        return ApiHelper.getUrl(ApiHelper.POST_FRIEND, params);
    }

    public static String deleteFriend(int idUser1, int idUser2) {
        HashMap<String,String> params = new HashMap<>();
        params.put("idUser1",String.valueOf(idUser1));
        params.put("idUser2",String.valueOf(idUser2));

        return ApiHelper.getUrl(ApiHelper.DELETE_FRIEND, params);
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
