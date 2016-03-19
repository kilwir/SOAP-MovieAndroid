package tmtc.soap.Listener;

import tmtc.soap.Model.ErrorContainer;
import tmtc.soap.Model.User;

/**
 * Bad Boys Team
 * Created by remyjallan on 19/03/2016.
 */
public interface UserListener<T> {
    void OnUserSuccess(T response);
    void OnUserError(ErrorContainer error);
}
