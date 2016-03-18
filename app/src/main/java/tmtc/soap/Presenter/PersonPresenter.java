package tmtc.soap.Presenter;

import android.content.Intent;

import tmtc.soap.Model.MoviePerson;

/**
 * Bad Boys Team
 * Created by remyjallan on 11/03/2016.
 */
public interface PersonPresenter {
    void init(Intent intent);
    void loadMovies();
    MoviePerson getPerson();
}
