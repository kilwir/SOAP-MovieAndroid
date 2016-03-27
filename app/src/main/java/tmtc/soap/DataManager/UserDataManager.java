package tmtc.soap.DataManager;

import android.os.Handler;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import tmtc.soap.Helper.ApiHelper;
import tmtc.soap.Helper.ErrorHelper;
import tmtc.soap.Listener.UserListener;
import tmtc.soap.Model.User;

/**
 * Bad Boys Team
 * Created by remyjallan on 04/03/2016.
 */
public class UserDataManager extends DataManager{

    private static UserDataManager ourInstance = new UserDataManager();

    public static UserDataManager getInstance() {
        return ourInstance;
    }

    private UserDataManager() {}

    public void isMyFriend(User user, final UserListener<Boolean> listener) {
        getClient().get(ApiHelper.isMyFriend(user.getId()), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (statusCode == 200) {
                    try {
                        if (response.has("IsMyFriend") && response.getBoolean("IsMyFriend")) {
                            listener.OnUserSuccess(true);
                        } else {
                            listener.OnUserSuccess(false);
                        }
                    } catch (JSONException e) {
                        listener.OnUserError(ErrorHelper.ErrorParsingJson());
                    }
                } else {
                    listener.OnUserError(ErrorHelper.WrongStatusCode());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                listener.OnUserError(ErrorHelper.ErrorParsingJson());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.OnUserError(ErrorHelper.ErrorParsingJson());
            }
        });
    }

    public void addFriend(User user ,final UserListener<Boolean> listener) {
        getClient().post(ApiHelper.postFriend(user.getId()),new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if(statusCode == 200) {
                    listener.OnUserSuccess(true);
                } else {
                    listener.OnUserError(ErrorHelper.WrongStatusCode());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                listener.OnUserError(ErrorHelper.ErrorParsingJson());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.OnUserError(ErrorHelper.ErrorParsingJson());
            }
        });
    }

    public void deleteFriend(User user, final UserListener<Boolean> listener) {
        getClient().delete(ApiHelper.deleteFriend(AuthDataManager.getInstance().getCurrentUser().getId(),user.getId()), new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if(statusCode == 200) {
                    listener.OnUserSuccess(true);
                } else {
                    listener.OnUserError(ErrorHelper.WrongStatusCode());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                listener.OnUserError(ErrorHelper.ErrorParsingJson());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.OnUserError(ErrorHelper.ErrorParsingJson());
            }
        });
    }
}
