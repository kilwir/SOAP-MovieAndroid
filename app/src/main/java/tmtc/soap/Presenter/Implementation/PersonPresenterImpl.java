package tmtc.soap.Presenter.Implementation;

import android.content.Intent;
import android.net.Uri;

import org.parceler.Parcels;

import java.util.List;

import tmtc.soap.DataManager.AuthDataManager;
import tmtc.soap.DataManager.MovieDataManager;
import tmtc.soap.Listener.MovieListener;
import tmtc.soap.Listener.PersonListener;
import tmtc.soap.Model.ErrorContainer;
import tmtc.soap.Model.Movie;
import tmtc.soap.Model.MoviePerson;
import tmtc.soap.Presenter.PersonPresenter;
import tmtc.soap.View.PersonView;

/**
 * Bad Boys Team
 * Created by remyjallan on 11/03/2016.
 */
public class PersonPresenterImpl implements PersonPresenter, MovieListener<List<Movie>>, PersonListener {

    private MoviePerson mPerson;

    private PersonView mView;

    public PersonPresenterImpl(PersonView view) {
        this.mView = view;
    }

    @Override
    public void init(Intent intent) {
        if(intent != null) {
            mPerson = Parcels.unwrap(intent.getParcelableExtra("person"));
            if(mPerson == null) {
                if(!AuthDataManager.getInstance().isConnected()) {
                    mView.navigateToLogin();
                    return;
                }
                mView.showProgress("Loading...");
                Uri data = intent.getData();
                String id = data.getQueryParameter("id");
                if(id != null) {
                    MovieDataManager.getInstance().getPersonById(Integer.valueOf(id),this);
                } else {
                    mView.navigateToMain();
                }
            } else {
                mView.init(mPerson);
                this.loadMovies();
            }
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
    public MoviePerson getPerson() {
        return this.mPerson;
    }

    @Override
    public void OnMovieSuccess(List<Movie> movies) {
        mView.hideProgress();
        mView.showMovies(movies);
    }

    @Override
    public void OnMovieError(ErrorContainer error) {
        mView.hideProgress();
        mView.showMessage(error.toString());
    }

    @Override
    public void onPersonSuccess(MoviePerson person) {
        mPerson = person;
        mView.hideProgress();
        mView.init(person);
        this.loadMovies();
    }

    @Override
    public void onPersonError(ErrorContainer error) {
        mView.hideProgress();
        mView.showMessage(error.Message);
        mView.navigateToMain();
    }
}
