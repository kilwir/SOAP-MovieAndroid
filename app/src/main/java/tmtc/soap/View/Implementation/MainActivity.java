package tmtc.soap.View.Implementation;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.parceler.Parcels;

import java.util.List;

import butterknife.Bind;
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

    @Bind(R.id.toolbar)
    public Toolbar mToolbar;

    private MainPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        this.init();

        mPresenter = new MainPresenterImpl(this);

        mPresenter.loadLastMovies();
    }

    @Override
    protected void init() {
        super.init();
        initializeToolbar();
        initializeDrawer();
    }

    protected void initializeDrawer() {

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar,
                R.string.open_nagivation_drawer, R.string.close_navigation_drawer);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    protected void initializeToolbar() {
        setSupportActionBar(mToolbar);
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
    public void navigateToMovie(View movieView,Movie movie) {
        Intent intent = new Intent(this,MovieActivity.class);
        intent.putExtra("movie", Parcels.wrap(movie));
        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,movieView,"poster_movie");
        startActivity(intent,activityOptions.toBundle());
    }

    @Override
    public void ItemMovieListenerOnClick(View view,Movie movie) {
        this.navigateToMovie(view,movie);
    }
}
