package tmtc.soap.DataManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

import com.orhanobut.logger.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tmtc.soap.Helper.ErrorHelper;
import tmtc.soap.Listener.LoginListener;
import tmtc.soap.Listener.SignupListener;
import tmtc.soap.Listener.UserListener;
import tmtc.soap.Model.ErrorContainer;
import tmtc.soap.Model.User;

/**
 * Bad Boys Team
 * Created by remyjallan on 04/03/2016.
 */
public class UserDataManager {

    private static final String PREFS_NAME_USER = "USER_TMTC";

    private User mCurrentUser;
    private Context mContext;

    private static UserDataManager ourInstance = new UserDataManager();

    public static UserDataManager getInstance() {
        return ourInstance;
    }

    private UserDataManager() {}

    public void init(Context context) {
        this.mContext = context;
        this.mCurrentUser = this.getUserOnLocal();
    }

    public boolean isConnected() {
        if(mCurrentUser != null && mCurrentUser.getUsername() != null && mCurrentUser.getPassword() != null) {
            return true;
        }

        return false;
    }

    public void login(User user, LoginListener listener) {
        if(mContext == null) {
            listener.onLoginError(ErrorHelper.ContextIsEmpty());
            return;
        }
        user.setEmail("injectEmail@email.fr");
        saveUserOnLocal(user);
        listener.onLoginSuccess(user);
    }

    public void signup(User user, SignupListener listener) {
        if( mContext == null) {
            listener.onSignupError(ErrorHelper.ContextIsEmpty());
            return;
        }
        saveUserOnLocal(user);
        listener.onSignupSuccess(user);
    }

    public void logout() {
        this.deleteUserOnLocal();
    }

    public User getCurrentUser() {
        return mCurrentUser;
    }

    public void isMyFriend(User user, final UserListener<Boolean> listener) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(Math.random() %2 == 0)
                    listener.OnUserSuccess(true);
                else
                    listener.OnUserSuccess(false);
            }
        },300);
    }

    public void addFriend(User user ,final UserListener<Boolean> listener) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.OnUserSuccess(true);
            }
        },300);
    }

    public void deleteFriend(User user, final UserListener<Boolean> listener) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.OnUserSuccess(true);
            }
        },300);
    }

    private void deleteUserOnLocal() {
        if(mContext == null){
            return;
        }
        SharedPreferences preferences = mContext.getSharedPreferences(PREFS_NAME_USER,0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username",null);
        editor.putString("password",null);
        editor.putString("email",null);
        editor.apply();
    }

    private void saveUserOnLocal(User user) {
        if(mContext == null){
            return;
        }
        SharedPreferences preferences = mContext.getSharedPreferences(PREFS_NAME_USER,0);
        SharedPreferences.Editor editor = preferences.edit();
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
        user.setEmail(preferences.getString("email",null));

        if(user.getUsername() == null || user.getPassword() == null) {
            return null;
        } else {
            return user;
        }
    }
}
