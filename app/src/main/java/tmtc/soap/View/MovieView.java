package tmtc.soap.View;

import tmtc.soap.Model.Movie;

/**
 * Bad Boys Team
 * Created by remyjallan on 10/03/2016.
 */
public interface MovieView extends BaseView {
    void init(Movie movie);
    void navigateToMain();
}
