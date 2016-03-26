package tmtc.soap.DataManager;

import android.content.Context;
import android.content.SharedPreferences;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;
import tmtc.soap.Helper.ApiHelper;
import tmtc.soap.Helper.ErrorHelper;
import tmtc.soap.Listener.LoginListener;
import tmtc.soap.Listener.SignupListener;
import tmtc.soap.Model.ErrorContainer;
import tmtc.soap.Model.User;

/**
 * Bad Boys Team
 * Created by remyjallan on 24/03/2016.
 */
public class AuthDataManager extends DataManager {

    private static final String PREFS_NAME_USER = "USER_TMTC";
    private static final String PREFS_NAME_TOKEN = "TOKEN_TMTC";

    private String mToken;
    private User mCurrentUser;
    private Context mContext;
    private static AuthDataManager ourInstance = new AuthDataManager();

    public static AuthDataManager getInstance() {
        return ourInstance;
    }

    private AuthDataManager() {
    }

    public void init(Context context) {
        this.mContext = context;
        this.mCurrentUser = this.getUserOnLocal();
        Logger.init("AuthDataManager");
    }

    public boolean isConnected() {
        if(mCurrentUser != null && mCurrentUser.getUsername() != null && mCurrentUser.getPassword() != null) {
            if(mToken == null) {
                mToken = getTokenOnLocal();
                if(mToken != null) {
                    return true;
                }
            } else {
                return true;
            }
        }

        return false;
    }

    public String getToken() {
        return mToken;
    }

    public void login(final User user, final LoginListener listener) {
        if(mContext == null) {
            listener.onLoginError(ErrorHelper.ContextIsEmpty());
            return;
        }

        Logger.d(ApiHelper.login(user.getUsername(), user.getPassword()));

        getClientWithoutHeader().get(ApiHelper.login(user.getUsername(), user.getPassword()), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (statusCode == 200) {
                    try {
                        if (response.has("error")) {
                            JSONObject error = response.getJSONObject("error");
                            listener.onLoginError(new ErrorContainer(error.getInt("code"), error.getString("message")));
                        } else {
                            mToken = response.getString("token");
                            saveTokenOnLocal(mToken);
                            JSONObject userJson = response.getJSONObject("user");
                            user.setEmail(userJson.getString("email"));
                            user.setId(userJson.getInt("id"));
                            saveUserOnLocal(user);
                            listener.onLoginSuccess(user);
                        }

                    } catch (JSONException e) {
                        listener.onLoginError(ErrorHelper.ErrorParsingJson());
                    }
                } else {
                    listener.onLoginError(ErrorHelper.WrongStatusCode());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (statusCode == 401) {
                    listener.onLoginError(ErrorHelper.WrongCredentials());
                } else {
                    listener.onLoginError(ErrorHelper.ErrorParsingJson());
                }
            }
        });
    }

    public void signup(final User user, final SignupListener listener) {
        if( mContext == null) {
            listener.onSignupError(ErrorHelper.ContextIsEmpty());
            return;
        }

        RequestParams params = new RequestParams();

        params.put("username",user.getUsername());
        params.put("password",user.getPassword());
        params.put("email", user.getEmail());

        getClientWithoutHeader().post(ApiHelper.SIGN_UP, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (statusCode == 200) {
                    try {
                        if (response.has("error")) {
                            JSONObject error = response.getJSONObject("error");
                            listener.onSignupError(new ErrorContainer(error.getInt("code"), error.getString("message")));
                        } else {
                            mToken = response.getString("token");
                            saveUserOnLocal(user);
                            listener.onSignupSuccess(user);
                        }

                    } catch (JSONException e) {
                        listener.onSignupError(ErrorHelper.ErrorParsingJson());
                    }
                } else {
                    listener.onSignupError(ErrorHelper.WrongStatusCode());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                listener.onSignupError(ErrorHelper.ErrorParsingJson());
            }
        });
    }

    public void logout() {
        this.deleteUserOnLocal();
        deleteTokenOnLocal();
    }

    public User getCurrentUser() {
        return mCurrentUser;
    }

    private void deleteUserOnLocal() {
        if(mContext == null){
            return;
        }
        SharedPreferences preferences = mContext.getSharedPreferences(PREFS_NAME_USER,0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("id",0);
        editor.putString("username",null);
        editor.putString("password",null);
        editor.putString("email", null);
        editor.apply();
    }

    private void saveUserOnLocal(User user) {
        if(mContext == null){
            return;
        }
        SharedPreferences preferences = mContext.getSharedPreferences(PREFS_NAME_USER,0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("id",user.getId());
        editor.putString("username",user.getUsername());
        editor.putString("password",user.getPassword());
        editor.putString("email",user.getEmail());
        editor.apply();
    }

    private User getUserOnLocal(){
        if(mContext == null) {
            return null;
        }

        SharedPreferences preferences = mContext.getSharedPreferences(PREFS_NAME_USER,0);
        User user = new User(preferences.getString("username",null),preferences.getString("password",null));
        user.setId(preferences.getInt("id",-1));
        user.setEmail(preferences.getString("email", null));

        if(user.getUsername() == null || user.getPassword() == null || user.getId() == -1) {
            return null;
        } else {
            return user;
        }
    }

    private void saveTokenOnLocal(String token) {
        if(mContext == null) {
            return;
        }
        SharedPreferences preferences = mContext.getSharedPreferences(PREFS_NAME_TOKEN,0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("token",token);
        editor.apply();
    }

    private String getTokenOnLocal() {
        if(mContext == null) {
            return null;
        }

        SharedPreferences preferences = mContext.getSharedPreferences(PREFS_NAME_TOKEN,0);
        return preferences.getString("token",null);
    }

    private void deleteTokenOnLocal() {
        if(mContext == null) {
            return;
        }
        SharedPreferences preferences = mContext.getSharedPreferences(PREFS_NAME_TOKEN,0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("token",null);
        editor.apply();
    }
}
