package tmtc.soap.Presenter.Implementation;

import android.content.Intent;

import org.parceler.Parcels;

import tmtc.soap.Model.Movie;
import tmtc.soap.Presenter.MoviePresenter;
import tmtc.soap.View.MovieView;

/**
 * Bad Boys Team
 * Created by remyjallan on 10/03/2016.
 */
public class MoviePresenterImpl implements MoviePresenter {
    private MovieView mView;

    private Movie mMovie;

    public MoviePresenterImpl(MovieView view) {
        mView = view;
    }

    @Override
    public void init(Intent intent) {
        mMovie = Parcels.unwrap(intent.getParcelableExtra("movie"));
        if(mMovie == null) {
            mView.navigateToMain();
        } else {
            mView.init(mMovie);
        }
    }
}
