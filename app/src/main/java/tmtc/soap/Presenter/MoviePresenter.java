package tmtc.soap.Presenter;

import android.content.Intent;

import tmtc.soap.Model.Movie;

/**
 * Bad Boys Team
 * Created by remyjallan on 10/03/2016.
 */
public interface MoviePresenter {
    void init(Intent intent);
    Movie getMovie();
    void boughtMovie(boolean bought);
}
