package tmtc.soap.View.Implementation;

import android.app.ActivityOptions;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.speech.RecognizerIntent;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.orhanobut.logger.Logger;

import org.parceler.Parcels;

import java.util.ArrayList;
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
import tmtc.soap.View.Template.ToolbarDrawerActivity;

public class MainActivity extends ToolbarDrawerActivity implements MainView, NavigationView.OnNavigationItemSelectedListener, ItemMovieListener.IMovie, MaterialSearchView.OnQueryTextListener, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.search_view)
    public MaterialSearchView SearchView;
    private MainPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.init();

        mPresenter = new MainPresenterImpl(this,this);

        mPresenter.loadLastMovies();
    }

    @Override
    protected void init() {
        super.init();
        mToolbar.setTitle(getString(R.string.activity_title_last_exit));
        SearchView.setOnQueryTextListener(this);
    }

    @Override
    public void showLastMovies(List<Movie> movies) {
        FragmentMovies fragmentMovies = (FragmentMovies) getFragmentManager().findFragmentById(R.id.fragment_movies);
        if(fragmentMovies != null) {
            fragmentMovies.setListenerMovie(this);
            fragmentMovies.setRefreshListener(this);
            fragmentMovies.loadMovies(movies);
            fragmentMovies.isRefresh();
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
        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, movieView, "poster_movie");
        startActivity(intent, activityOptions.toBundle());
    }

    @Override
    public void navigateToSearch(String query) {
        Intent intent = new Intent(this,SearchActivity.class);
        intent.putExtra("query",query);
        transitionTo(intent);
    }

    @Override
    public void ItemMovieListenerOnClick(View view,Movie movie) {
        this.navigateToMovie(view, movie);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        item.getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        SearchView.setMenuItem(item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (SearchView.isSearchOpen()) {
            SearchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        this.navigateToSearch(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onRefresh() {
        mPresenter.loadLastMovies();
    }
}
