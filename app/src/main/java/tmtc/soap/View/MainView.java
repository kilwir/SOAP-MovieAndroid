package tmtc.soap.View;

import java.util.List;

import tmtc.soap.Model.Movie;

/**
 * Bad Boys Team
 * Created by remyjallan on 06/03/2016.
 */
public interface MainView extends View {
    void showLastMovies(List<Movie> movies);
}
