package tmtc.soap.View;

import android.view.View;

import java.util.List;

import tmtc.soap.Model.Movie;

/**
 * Bad Boys Team
 * Created by remyjallan on 06/03/2016.
 */
public interface MainView extends BaseView {
    void showLastMovies(List<Movie> movies);
    void navigateToLogin();
    void navigateToMovie(View movieView,Movie movie);
    void navigateToSearch(String query);
}
