package tmtc.soap.DataManager;

import android.os.Handler;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import tmtc.soap.Helper.ApiHelper;
import tmtc.soap.Helper.ErrorHelper;
import tmtc.soap.Helper.JSONHelper;
import tmtc.soap.Listener.SearchListener;
import tmtc.soap.Model.Movie;
import tmtc.soap.Model.MoviePerson;
import tmtc.soap.Model.SearchItem;

/**
 * Bad Boys Team
 * Created by remyjallan on 23/03/2016.
 */
public class SearchDataManager extends DataManager{
    private static SearchDataManager ourInstance = new SearchDataManager();

    public static SearchDataManager getInstance() {
        return ourInstance;
    }

    private SearchDataManager() {
    }

    public void search(String query, final SearchListener<List<SearchItem>> listener) {
        getClient().get(ApiHelper.getSearch(query),new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if(statusCode == 200) {
                    List<SearchItem> list = new ArrayList<>();
                    try {
                        if(response.has("movie")) {
                            JSONArray movies = response.getJSONArray("movie");
                            for(int i = 0; i< movies.length(); i++) {
                                list.add(JSONHelper.JSONToMovie(movies.getJSONObject(i)));
                            }
                        }
                        if(response.has("person")) {
                            JSONArray persons = response.getJSONArray("person");
                            for(int i = 0; i< persons.length(); i++) {
                                list.add(JSONHelper.JSONToPerson(persons.getJSONObject(i)));
                            }
                        }

                        listener.OnSearchSuccess(list);
                    } catch (JSONException e) {
                        listener.OnSearchError(ErrorHelper.ErrorParsingJson());
                    }
                } else {
                    listener.OnSearchError(ErrorHelper.WrongStatusCode());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                listener.OnSearchError(ErrorHelper.ErrorParsingJson());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.OnSearchError(ErrorHelper.ErrorParsingJson());
            }
        });
    }
}
