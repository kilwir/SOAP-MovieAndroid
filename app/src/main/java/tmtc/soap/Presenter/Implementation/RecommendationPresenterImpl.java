package tmtc.soap.Presenter.Implementation;

import java.util.List;

import tmtc.soap.DataManager.MovieDataManager;
import tmtc.soap.Listener.MovieListener;
import tmtc.soap.Model.ErrorContainer;
import tmtc.soap.Model.Movie;
import tmtc.soap.Presenter.RecommendationPresenter;
import tmtc.soap.View.RecommendationView;

/**
 * Bad Boys Team
 * Created by remyjallan on 27/03/2016.
 */
public class RecommendationPresenterImpl implements RecommendationPresenter, MovieListener<List<Movie>> {
    private RecommendationView mView;

    public RecommendationPresenterImpl(RecommendationView view) {
        mView = view;
    }

    @Override
    public void loadRecommendation() {
        mView.showProgress("Chargement ...");
        MovieDataManager.getInstance().recommended(this);
    }

    @Override
    public void OnMovieSuccess(List<Movie> movie) {
        mView.hideProgress();
        mView.showRecommendation(movie);
    }

    @Override
    public void OnMovieError(ErrorContainer error) {
        mView.hideProgress();
        mView.showMessage(error.Message);
    }
}
