package tmtc.soap.View;

import android.view.View;

import tmtc.soap.Model.Movie;
import tmtc.soap.Model.MoviePerson;
import tmtc.soap.Model.User;

/**
 * Bad Boys Team
 * Created by remyjallan on 10/03/2016.
 */
public interface MovieView extends BaseView {
    void init(Movie movie);
    void navigateToMain();
    void navigateToPerson(View view,MoviePerson person);
    void navigateToLogin();
    void navigateToUser(User user);
}
