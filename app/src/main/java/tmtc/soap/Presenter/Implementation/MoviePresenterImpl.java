package tmtc.soap.Presenter.Implementation;

import android.content.Intent;
import android.net.Uri;

import com.orhanobut.logger.Logger;

import org.parceler.Parcels;

import tmtc.soap.DataManager.MovieDataManager;
import tmtc.soap.DataManager.UserDataManager;
import tmtc.soap.Listener.MovieListener;
import tmtc.soap.Model.ErrorContainer;
import tmtc.soap.Model.Movie;
import tmtc.soap.Presenter.MoviePresenter;
import tmtc.soap.View.MovieView;

/**
 * Bad Boys Team
 * Created by remyjallan on 10/03/2016.
 */
public class MoviePresenterImpl implements MoviePresenter, MovieListener {
    private MovieView mView;

    private Movie mMovie;

    public MoviePresenterImpl(MovieView view) {
        mView = view;
    }

    @Override
    public Movie getMovie() {
        return mMovie;
    }

    @Override
    public void init(Intent intent) {
        mMovie = Parcels.unwrap(intent.getParcelableExtra("movie"));
        if(mMovie == null) {
            if(!UserDataManager.getInstance().isConnected()) {
                mView.navigateToLogin();
                return;
            }
            mView.showProgress("Chargement ...");
            Uri data = intent.getData();
            String id = data.getQueryParameter("id");
            if(id != null) {
                MovieDataManager.getInstance().getMovieById(Integer.valueOf(id),this);
            } else {
                mView.navigateToMain();
            }
        } else {
            mView.init(mMovie);
        }
    }

    @Override
    public void OnMovieSuccess(Movie movie) {
        this.mMovie = movie;
        mView.hideProgress();
        mView.init(movie);
    }

    @Override
    public void OnMovieError(ErrorContainer error) {
        mView.hideProgress();
        mView.showMessage(error.Message);
        mView.navigateToMain();
    }
}
