package tmtc.soap.Listener;

import tmtc.soap.Model.Movie;

/**
 * Bad Boys Team
 * Created by remyjallan on 09/03/2016.
 */
public class ItemMovieListener {
    public interface Position {
        void ItemMovieListenerOnClick(int position);
    }
    public interface Movie {
        void ItemMovieListenerOnClick(Movie movie);
    }
}
