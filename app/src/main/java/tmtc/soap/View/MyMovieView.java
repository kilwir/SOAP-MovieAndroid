package tmtc.soap.View;

import android.view.View;

import java.util.List;

import tmtc.soap.Model.Movie;

/**
 * Bad Boys Team
 * Created by remyjallan on 27/03/2016.
 */
public interface MyMovieView extends BaseView {
    void showMyMovies(List<Movie> movies);
    void navigateToMovie(View view, Movie movie);
}
