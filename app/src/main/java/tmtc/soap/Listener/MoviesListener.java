package tmtc.soap.Listener;

import java.util.List;

import tmtc.soap.Model.ErrorContainer;
import tmtc.soap.Model.Movie;

/**
 * Bad Boys Team
 * Created by remyjallan on 09/03/2016.
 */
public interface MoviesListener {
    void OnMoviesSuccess(List<Movie> movies);
    void OnMoviesError(ErrorContainer error);
}
