package tmtc.soap.DataManager;

import android.os.Handler;

import tmtc.soap.Listener.UserListener;
import tmtc.soap.Model.User;

/**
 * Bad Boys Team
 * Created by remyjallan on 04/03/2016.
 */
public class UserDataManager {

    private static UserDataManager ourInstance = new UserDataManager();

    public static UserDataManager getInstance() {
        return ourInstance;
    }

    private UserDataManager() {}

    public void isMyFriend(User user, final UserListener<Boolean> listener) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Math.random() % 2 == 0)
                    listener.OnUserSuccess(true);
                else
                    listener.OnUserSuccess(false);
            }
        }, 300);
    }

    public void addFriend(User user ,final UserListener<Boolean> listener) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.OnUserSuccess(true);
            }
        }, 300);
    }

    public void deleteFriend(User user, final UserListener<Boolean> listener) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.OnUserSuccess(true);
            }
        }, 300);
    }
}
