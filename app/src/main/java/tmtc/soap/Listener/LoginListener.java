package tmtc.soap.Listener;

import tmtc.soap.Model.ErrorContainer;
import tmtc.soap.Model.User;

/**
 * Bad Boys Team
 * Created by remyjallan on 04/03/2016.
 */
public interface LoginListener {
    void onLoginSuccess(User user);
    void onLoginError(ErrorContainer error);
}
