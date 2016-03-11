package tmtc.soap.View;

import android.view.View;

import java.util.List;

import tmtc.soap.Model.Movie;
import tmtc.soap.Model.MoviePerson;

/**
 * Bad Boys Team
 * Created by remyjallan on 11/03/2016.
 */
public interface PersonView extends BaseView {
    void init(MoviePerson person);
    void showMovies(List<Movie> movies);
    void navigateToMovie(View view,Movie movie);
}
