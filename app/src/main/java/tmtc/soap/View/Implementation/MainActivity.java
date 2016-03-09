package tmtc.soap.View.Implementation;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmtc.soap.DataManager.UserDataManager;
import tmtc.soap.Fragment.FragmentMovies;
import tmtc.soap.Model.Movie;
import tmtc.soap.Presenter.Implementation.MainPresenterImpl;
import tmtc.soap.Presenter.MainPresenter;
import tmtc.soap.R;
import tmtc.soap.View.MainView;

public class MainActivity extends AppCompatActivity implements MainView{

    private MainPresenter mPresenter;

    @Bind(R.id.activity_movies_toolbar)
    public Toolbar mToolbar;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = new MainPresenterImpl(this);
        initializeToolbar();
        mPresenter.loadLastMovies();
    }

    private void initializeToolbar() {

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");

        getSupportActionBar().setHomeAsUpIndicator(
                R.mipmap.ic_launcher);
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
            fragmentMovies.loadMovies(movies);
        }
    }
}
