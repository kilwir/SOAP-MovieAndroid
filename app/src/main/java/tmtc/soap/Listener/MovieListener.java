package tmtc.soap.Listener;

import java.util.List;

import tmtc.soap.Model.ErrorContainer;
import tmtc.soap.Model.Movie;

/**
 * Bad Boys Team
 * Created by remyjallan on 18/03/2016.
 */
public interface MovieListener<T> {
    void OnMovieSuccess(T movie);
    void OnMovieError(ErrorContainer error);
}
