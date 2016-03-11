package tmtc.soap.Presenter.Implementation;

import android.content.Intent;

import org.parceler.Parcels;

import java.util.List;

import tmtc.soap.DataManager.MovieDataManager;
import tmtc.soap.Listener.MoviesListener;
import tmtc.soap.Model.ErrorContainer;
import tmtc.soap.Model.Movie;
import tmtc.soap.Model.MoviePerson;
import tmtc.soap.Presenter.PersonPresenter;
import tmtc.soap.View.PersonView;

/**
 * Bad Boys Team
 * Created by remyjallan on 11/03/2016.
 */
public class PersonPresenterImpl implements PersonPresenter, MoviesListener {

    private MoviePerson mPerson;

    private PersonView mView;

    public PersonPresenterImpl(PersonView view) {
        this.mView = view;
    }

    @Override
    public void init(Intent intent) {
        if(intent != null) {
            mPerson = Parcels.unwrap(intent.getParcelableExtra("person"));
            mView.init(mPerson);
            this.loadMovies();
        }
    }

    @Override
    public void loadMovies() {
        if(mPerson != null) {
            mView.showProgress("Loading...");
            MovieDataManager.getInstance().getMovieForPerson(mPerson,this);
        }
    }

    @Override
    public void OnMoviesSuccess(List<Movie> movies) {
        mView.hideProgress();
        mView.showMovies(movies);
    }

    @Override
    public void OnMoviesError(ErrorContainer error) {
        mView.hideProgress();
        mView.showMessage(error.toString());
    }
}
