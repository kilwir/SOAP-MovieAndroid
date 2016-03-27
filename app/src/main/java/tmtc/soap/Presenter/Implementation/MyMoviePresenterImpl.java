package tmtc.soap.Presenter.Implementation;

import android.content.Context;

import java.util.List;

import tmtc.soap.DataManager.AuthDataManager;
import tmtc.soap.DataManager.MovieDataManager;
import tmtc.soap.Listener.MovieListener;
import tmtc.soap.Model.ErrorContainer;
import tmtc.soap.Model.Movie;
import tmtc.soap.Presenter.MyMoviePresenter;
import tmtc.soap.R;
import tmtc.soap.View.MyMovieView;

/**
 * Bad Boys Team
 * Created by remyjallan on 27/03/2016.
 */
public class MyMoviePresenterImpl implements MyMoviePresenter, MovieListener<List<Movie>> {

    private MyMovieView mView;
    private Context mContext;

    public MyMoviePresenterImpl(MyMovieView view, Context context) {
        mView = view;
        mContext = context;
    }

    @Override
    public void loadMyMovie() {
        mView.showProgress(mContext.getString(R.string.loading___));
        MovieDataManager.getInstance().myMovie(AuthDataManager.getInstance().getCurrentUser(),this);
    }

    @Override
    public void OnMovieSuccess(List<Movie> movie) {
        mView.hideProgress();
        mView.showMyMovies(movie);
    }

    @Override
    public void OnMovieError(ErrorContainer error) {
        mView.hideProgress();
        mView.showMessage(error.Message);
    }
}
