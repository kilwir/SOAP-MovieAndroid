package tmtc.soap.DataManager;

import android.os.Handler;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;
import tmtc.soap.Helper.ApiHelper;
import tmtc.soap.Helper.ErrorHelper;
import tmtc.soap.Helper.JSONHelper;
import tmtc.soap.Listener.MovieListener;
import tmtc.soap.Listener.PersonListener;
import tmtc.soap.Model.ErrorContainer;
import tmtc.soap.Model.Movie;
import tmtc.soap.Model.MoviePerson;
import tmtc.soap.Model.User;

/**
 * Bad Boys Team
 * Created by remyjallan on 09/03/2016.
 */
public class MovieDataManager extends DataManager{
    private static MovieDataManager ourInstance = new MovieDataManager();

    public static MovieDataManager getInstance() {
        return ourInstance;
    }

    private MovieDataManager() {
    }

    public void getLastMovies(final MovieListener<List<Movie>> listener) {
        getClient().get(ApiHelper.lastMovie(25), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                if (statusCode == 200) {
                    List<Movie> movies = new ArrayList<>();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject movieJson = response.getJSONObject(i);
                            Movie movie = JSONHelper.JSONToMovie(movieJson);
                            JSONArray persons = movieJson.getJSONArray("persons");
                            for (int j = 0; j < persons.length(); j++) {
                                JSONObject personJson = persons.getJSONObject(j);
                                MoviePerson person = JSONHelper.JSONToPerson(personJson);
                                movie.addPerson(person);
                            }
                            movies.add(movie);
                        } catch (JSONException e) {
                            listener.OnMovieError(ErrorHelper.ErrorParsingJson());
                            return;
                        }
                    }
                    listener.OnMovieSuccess(movies);
                } else {
                    listener.OnMovieError(ErrorHelper.WrongStatusCode());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                listener.OnMovieError(ErrorHelper.ErrorParsingJson());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.OnMovieError(ErrorHelper.ErrorParsingJson());
            }
        });

    }

    public void getMovieById(final int id, final MovieListener<Movie> listener) {
        getClient().get(ApiHelper.getMovie(id),new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (statusCode == 200) {
                    try {
                        Movie movie = JSONHelper.JSONToMovie(response);
                        if(response.has("persons")) {
                            JSONArray persons = response.getJSONArray("persons");
                            for(int i = 0; i < persons.length(); i++) {
                                movie.addPerson(JSONHelper.JSONToPerson((JSONObject) persons.get(i)));
                            }
                        }
                        listener.OnMovieSuccess(movie);
                    } catch (JSONException e) {
                        listener.OnMovieError(ErrorHelper.ErrorParsingJson());
                    }
                } else {
                    listener.OnMovieError(ErrorHelper.WrongStatusCode());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                listener.OnMovieError(ErrorHelper.ErrorParsingJson());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.OnMovieError(ErrorHelper.ErrorParsingJson());
            }
        });
    }

    public void getMovieForPerson(MoviePerson person,final MovieListener<List<Movie>> listener) {
        getClient().get(ApiHelper.getPerson(person.getId()),new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                List<Movie> list = new ArrayList<Movie>();
                try {
                    JSONArray moviesArray = response.getJSONArray("movies");
                    for(int i = 0;i < moviesArray.length(); i++) {
                        list.add(JSONHelper.JSONToMovie(moviesArray.getJSONObject(i)));
                    }
                } catch (JSONException e) {
                    listener.OnMovieError(ErrorHelper.ErrorParsingJson());
                }
                listener.OnMovieSuccess(list);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                listener.OnMovieError(ErrorHelper.ErrorParsingJson());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.OnMovieError(ErrorHelper.ErrorParsingJson());
            }
        });
    }

    public void getPersonById(int id, final PersonListener listener) {
        getClient().get(ApiHelper.getPerson(id),new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    MoviePerson person = JSONHelper.JSONToPerson(response);
                    listener.onPersonSuccess(person);
                } catch (JSONException e) {
                    listener.onPersonError(ErrorHelper.ErrorParsingJson());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                listener.onPersonError(ErrorHelper.ErrorParsingJson());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onPersonError(ErrorHelper.ErrorParsingJson());
            }
        });
    }

    public void boughtMovie(Movie movie, User user,final MovieListener<Boolean> listener) {
        RequestParams params = new RequestParams();

        getClient().get(ApiHelper.bought(user.getId(), movie.getId()),params,new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if(statusCode == 200) {
                    try {
                        Boolean bought = response.getBoolean("bought");
                        listener.OnMovieSuccess(bought);

                    } catch (JSONException e) {
                        listener.OnMovieError(ErrorHelper.ErrorParsingJson());
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                listener.OnMovieError(ErrorHelper.ErrorParsingJson());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.OnMovieError(ErrorHelper.ErrorParsingJson());
            }
        });
    }

    public void buyMovie(Movie movie, User user, final MovieListener<Boolean> listener) {
        RequestParams params = new RequestParams();
        params.put("idUser",user.getId());
        params.put("idMovie",movie.getId());
        getClient().post(ApiHelper.BUY, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (statusCode == 200) {
                    listener.OnMovieSuccess(true);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                listener.OnMovieError(ErrorHelper.ErrorParsingJson());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.OnMovieError(ErrorHelper.ErrorParsingJson());
            }
        });
    }

    public void myMovie(User user, final MovieListener<List<Movie>> listener) {
        getClient().get(ApiHelper.myMovie(user.getId()), new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (statusCode == 200) {
                    List<Movie> movies = new ArrayList<Movie>();
                    if(response.has("movies")) {
                        try {
                            JSONArray moviesJson = response.getJSONArray("movies");
                            for(int i = 0; i< moviesJson.length(); i++) {
                                movies.add(JSONHelper.JSONToMovie((JSONObject) moviesJson.get(i)));
                            }
                        } catch (JSONException e) {
                            listener.OnMovieError(ErrorHelper.ErrorParsingJson());
                        }
                    }
                    listener.OnMovieSuccess(movies);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                listener.OnMovieError(ErrorHelper.ErrorParsingJson());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.OnMovieError(ErrorHelper.ErrorParsingJson());
            }
        });
    }
}
