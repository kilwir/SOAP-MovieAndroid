package tmtc.soap.DataManager;

import tmtc.soap.Listener.LoginListener;
import tmtc.soap.Listener.SignupListener;
import tmtc.soap.Model.User;

/**
 * Bad Boys Team
 * Created by remyjallan on 04/03/2016.
 */
public class UserDataManager {

    private User mCurrentUser;

    private static UserDataManager ourInstance = new UserDataManager();

    public static UserDataManager getInstance() {
        return ourInstance;
    }

    private UserDataManager() {
    }

    public void login(User user,LoginListener listener) {
        this.mCurrentUser = user;
        listener.onLoginSuccess(user);
    }

    public void signup(User user, SignupListener listener) {
        this.mCurrentUser = user;
        listener.onSignupSuccess(user);
    }

    public User getCurrentUser() {
        return mCurrentUser;
    }
}
