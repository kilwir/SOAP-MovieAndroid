package tmtc.soap.Presenter.Implementation;

import java.util.List;

import tmtc.soap.DataManager.MovieDataManager;
import tmtc.soap.Listener.MoviesListener;
import tmtc.soap.Model.ErrorContainer;
import tmtc.soap.Model.Movie;
import tmtc.soap.Presenter.MainPresenter;
import tmtc.soap.View.MainView;

/**
 * Bad Boys Team
 * Created by remyjallan on 06/03/2016.
 */
public class MainPresenterImpl implements MainPresenter, MoviesListener {

    private MainView mView;

    public MainPresenterImpl(MainView view) {
        mView = view;
    }

    @Override
    public void loadLastMovies() {
        mView.showProgress("Loading...");
        MovieDataManager.getInstance().getLastMovies(this);
    }

    @Override
    public void OnMoviesSuccess(List<Movie> movies) {
        mView.hideProgress();
        mView.showLastMovies(movies);
    }

    @Override
    public void OnMoviesError(ErrorContainer error) {
        mView.hideProgress();
        mView.showMessage(error.toString());
    }
}
