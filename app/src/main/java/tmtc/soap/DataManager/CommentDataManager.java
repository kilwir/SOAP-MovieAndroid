package tmtc.soap.DataManager;

import android.os.Handler;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import tmtc.soap.Helper.ApiHelper;
import tmtc.soap.Helper.ErrorHelper;
import tmtc.soap.Listener.CommentsListener;
import tmtc.soap.Model.Comment;
import tmtc.soap.Model.Movie;
import tmtc.soap.Model.MoviePerson;
import tmtc.soap.Model.User;

/**
 * Bad Boys Team
 * Created by remyjallan on 17/03/2016.
 */
public class CommentDataManager extends DataManager{
    private static CommentDataManager ourInstance = new CommentDataManager();

    public static CommentDataManager getInstance() {
        return ourInstance;
    }

    private CommentDataManager() {
        Logger.init("CommentDataManager");
    }

    public void getMovieComment(final Movie movie, final CommentsListener<List<Comment>> listener) {
        getClient().get(ApiHelper.commentMovie(movie.getId()), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                if (statusCode == 200) {
                    List<Comment> list = new ArrayList<>();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject commentJSon = response.getJSONObject(i);
                            JSONObject userJson = commentJSon.getJSONObject("User");
                            User user = new User();
                            user.setId(userJson.getInt("id"));
                            user.setEmail(userJson.getString("email"));
                            user.setUsername(userJson.getString("username"));
                            list.add(new Comment(commentJSon.getInt("id"),
                                    commentJSon.getString("content"),
                                    commentJSon.getDouble("rating"),
                                    user,
                                    movie));
                        } catch (JSONException e) {
                            listener.OnCommentsError(ErrorHelper.ErrorParsingJson());
                        }
                    }
                    listener.OnCommentsSuccess(list);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                listener.OnCommentsError(ErrorHelper.ErrorParsingJson());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.OnCommentsError(ErrorHelper.ErrorParsingJson());
            }
        });
    }

    public void getUserComment(final User user,final CommentsListener<List<Comment>> listener ) {
        Handler handler = new Handler();
        handler.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        Movie movie = new Movie(96, "Borat");
                        movie.setReleaseDate("2016/12/12");
                        movie.setPoster("http://fr.web.img6.acsta.net/r_1280_720/pictures/16/02/03/11/17/130929.jpg");
                        movie.setSynopsis("La suite des nouvelles aventures de Superman, confronté pour la première fois au chevalier noir de Gotham City, Batman.");
                        List<Comment> comments = new ArrayList<Comment>();
                        ArrayList<MoviePerson> persons = new ArrayList<MoviePerson>();

                        for (int i = 0; i < 5; i++) {
                            persons.add(new MoviePerson(i, "Rocky la poutre", "Producteur", "http://henrycavill.org/images/Events/2015-07-23-1/Henry-Cavill-UNCLE-press-031.jpg"));
                        }

                        movie.setPersons(persons);

                        for (int i = 0; i < 8; i++) {
                            Comment comment = new Comment(i, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean pretium libero a ligula tincidunt, porta malesuada mauris semper. Vivamus eu nisl nec augue egestas congue et id leo. Phasellus eget.", 3.5, user, movie);
                            comment.setCreateDate("2016/03/12");
                            comments.add(comment);
                        }
                        listener.OnCommentsSuccess(comments);
                    }
                }
                , 1000);
    }

    public void saveComment(Comment comment, CommentsListener<Boolean> listener) {
        if(comment.getId() < 0) {
            this.createComment(comment,listener);
        } else {
            this.updateComment(comment,listener);
        }
    }

    private void createComment(Comment comment, final CommentsListener<Boolean> listener) {
        RequestParams params = new RequestParams();
        params.put("movie_id",comment.getMovie().getId());
        params.put("user_id",comment.getUser().getId());
        params.put("content",comment.getContent());
        params.put("rating",comment.getRating());
        getClient().post(ApiHelper.COMMENT, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if(statusCode == 200) {
                    listener.OnCommentsSuccess(true);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                listener.OnCommentsError(ErrorHelper.ErrorParsingJson());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.OnCommentsError(ErrorHelper.ErrorParsingJson());
            }
        });
    }

    private void updateComment(Comment comment, final CommentsListener<Boolean> listener) {
        RequestParams params = new RequestParams();
        params.put("content",comment.getContent());
        params.put("rating",comment.getRating());
        getClient().put(ApiHelper.putComment(comment.getId()),params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if(statusCode == 200) {
                    listener.OnCommentsSuccess(true);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                listener.OnCommentsError(ErrorHelper.ErrorParsingJson());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.OnCommentsError(ErrorHelper.ErrorParsingJson());
            }
        });
    }
}
