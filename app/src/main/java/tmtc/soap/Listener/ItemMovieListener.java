package tmtc.soap.Listener;

import android.view.View;

import tmtc.soap.Model.Movie;

/**
 * Bad Boys Team
 * Created by remyjallan on 09/03/2016.
 */
public abstract class ItemMovieListener {
    public interface IPosition {
        void ItemMovieListenerOnClick(View view,int position);
    }
    public interface IMovie {
        void ItemMovieListenerOnClick(View view,Movie movie);
    }
}
