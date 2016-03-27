package tmtc.soap.View.Implementation;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.parceler.Parcels;

import java.util.List;

import tmtc.soap.Fragment.FragmentMovies;
import tmtc.soap.Listener.ItemMovieListener;
import tmtc.soap.Model.Movie;
import tmtc.soap.Presenter.Implementation.RecommendationPresenterImpl;
import tmtc.soap.Presenter.RecommendationPresenter;
import tmtc.soap.R;
import tmtc.soap.View.RecommendationView;
import tmtc.soap.View.Template.DrawerAppCompatActivity;
import tmtc.soap.View.Template.ToolbarDrawerActivity;

/**
 * Bad Boys Team
 * Created by remyjallan on 27/03/2016.
 */
public class RecommendationActivity extends ToolbarDrawerActivity implements RecommendationView, ItemMovieListener.IMovie, SwipeRefreshLayout.OnRefreshListener {

    private RecommendationPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
        this.init();
    }

    @Override
    protected void init() {
        super.init();
        mToolbar.setTitle("Recommandations");
        mPresenter = new RecommendationPresenterImpl(this);
        mPresenter.loadRecommendation();
    }

    @Override
    public void showRecommendation(List<Movie> movies) {
        FragmentMovies fragmentMovies = (FragmentMovies) getFragmentManager().findFragmentById(R.id.fragment_movies);
        if(fragmentMovies != null) {
            fragmentMovies.setListenerMovie(this);
            fragmentMovies.setRefreshListener(this);
            fragmentMovies.loadMovies(movies);
            fragmentMovies.isRefresh();
        }
    }

    @Override
    public void navigateToMovie(View view, Movie movie) {
        Intent intent = new Intent(this,MovieActivity.class);
        intent.putExtra("movie", Parcels.wrap(movie));
        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(RecommendationActivity.this, view, "poster_movie");
        startActivity(intent, activityOptions.toBundle());
    }

    @Override
    public void ItemMovieListenerOnClick(View view, Movie movie) {
        this.navigateToMovie(view,movie);
    }

    @Override
    public void onRefresh() {
        mPresenter.loadRecommendation();
    }
}
