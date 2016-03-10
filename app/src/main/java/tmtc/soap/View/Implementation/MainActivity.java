package tmtc.soap.View.Implementation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.ButterKnife;
import tmtc.soap.Fragment.FragmentMovies;
import tmtc.soap.Listener.ItemMovieListener;
import tmtc.soap.Model.Movie;
import tmtc.soap.Presenter.Implementation.MainPresenterImpl;
import tmtc.soap.Presenter.MainPresenter;
import tmtc.soap.R;
import tmtc.soap.View.Template.DrawerAppCompatActivity;
import tmtc.soap.View.MainView;

public class MainActivity extends DrawerAppCompatActivity implements MainView, NavigationView.OnNavigationItemSelectedListener, ItemMovieListener.IMovie {

    private MainPresenter mPresenter;

    private ProgressDialog mProgressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.init();

        mPresenter = new MainPresenterImpl(this);

        mPresenter.loadLastMovies();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress(String message) {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    @Override
    public void hideProgress() {
        if(mProgressDialog != null) {
            mProgressDialog.hide();
            mProgressDialog = null;
        }
    }

    @Override
    public void setupWindowAnimations() {

    }

    @Override
    public void showLastMovies(List<Movie> movies) {
        FragmentMovies fragmentMovies = (FragmentMovies) getFragmentManager().findFragmentById(R.id.fragment_movies);
        if(fragmentMovies != null) {
            fragmentMovies.setListenerMovie(this);
            fragmentMovies.loadMovies(movies);
        }
    }

    @Override
    public void navigateToLogin() {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void actionNavigationItemSelected(int id) {
        switch (id) {
            case R.id.logout:
                mPresenter.logout();
                break;
        }
    }

    @Override
    public void ItemMovieListenerOnClick(Movie movie) {
        mPresenter.onMovieClick(movie);
    }
}
