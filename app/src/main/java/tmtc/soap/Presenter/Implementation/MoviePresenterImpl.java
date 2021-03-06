package tmtc.soap.Presenter.Implementation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import org.parceler.Parcels;

import tmtc.soap.DataManager.AuthDataManager;
import tmtc.soap.DataManager.MovieDataManager;
import tmtc.soap.Listener.MovieListener;
import tmtc.soap.Model.ErrorContainer;
import tmtc.soap.Model.Movie;
import tmtc.soap.Presenter.MoviePresenter;
import tmtc.soap.R;
import tmtc.soap.View.MovieView;

/**
 * Bad Boys Team
 * Created by remyjallan on 10/03/2016.
 */
public class MoviePresenterImpl implements MoviePresenter {

    private Context mContext;
    private MovieView mView;
    private Movie mMovie;

    public MoviePresenterImpl(MovieView view, Context context) {
        mView = view;
        mContext = context;
    }

    @Override
    public Movie getMovie() {
        return mMovie;
    }

    @Override
    public void boughtMovie(boolean bought) {
        if(!bought) { //Location
            mView.confirmBought(mMovie.getName());
        } else { //Visionage
            mView.navigateToPlayer(mMovie);
        }
    }

    @Override
    public void confirmBought() {
        mView.navigateToBought(mMovie);
    }

    @Override
    public void init(Intent intent) {
        mMovie = Parcels.unwrap(intent.getParcelableExtra("movie"));
        if(mMovie == null) {
            if(!AuthDataManager.getInstance().isConnected()) {
                mView.navigateToLogin();
                return;
            }
            mView.showProgress(mContext.getString(R.string.loading___));
            Uri data = intent.getData();
            String id = data.getQueryParameter("id");
            if(id != null) {

                MovieDataManager.getInstance().getMovieById(Integer.valueOf(id), new MovieListener<Movie>() {
                    @Override
                    public void OnMovieSuccess(Movie movie) {
                        mMovie = movie;
                        mView.hideProgress();
                        mView.init(movie);
                    }

                    @Override
                    public void OnMovieError(ErrorContainer error) {
                        mView.hideProgress();
                        mView.showMessage(error.Message);
                        mView.navigateToMain();
                    }
                });
            } else {
                mView.navigateToMain();
            }
        } else {
            mView.init(mMovie);
        }
    }
}
