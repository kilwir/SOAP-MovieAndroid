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
import tmtc.soap.Helper.JSONHelper;
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
                            list.add(new Comment(commentJSon.getInt("id"),
                                    commentJSon.getString("content"),
                                    commentJSon.getDouble("rating"),
                                    JSONHelper.JSONToUser(userJson),
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
        getClient().get(ApiHelper.commentUser(user.getId()),new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                if (statusCode == 200) {
                    List<Comment> list = new ArrayList<>();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject commentJSon = response.getJSONObject(i);
                            JSONObject movieJson = commentJSon.getJSONObject("Movie");
                            list.add(new Comment(commentJSon.getInt("id"),
                                    commentJSon.getString("content"),
                                    commentJSon.getDouble("rating"),
                                    AuthDataManager.getInstance().getCurrentUser(),
                                    JSONHelper.JSONToMovie(movieJson)));
                        } catch (JSONException e) {
                            listener.OnCommentsError(ErrorHelper.ErrorParsingJson());
                        }
                    }
                    listener.OnCommentsSuccess(list);
                } else {
                    listener.OnCommentsError(ErrorHelper.WrongStatusCode());
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
                } else {
                    listener.OnCommentsError(ErrorHelper.WrongStatusCode());
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
                } else {
                    listener.OnCommentsError(ErrorHelper.WrongStatusCode());
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
