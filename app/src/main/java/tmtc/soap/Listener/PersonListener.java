package tmtc.soap.Listener;

import tmtc.soap.Model.ErrorContainer;
import tmtc.soap.Model.MoviePerson;
import tmtc.soap.Model.User;

/**
 * Bad Boys Team
 * Created by remyjallan on 18/03/2016.
 */
public interface PersonListener {
    void onPersonSuccess(MoviePerson person);
    void onPersonError(ErrorContainer error);
}
