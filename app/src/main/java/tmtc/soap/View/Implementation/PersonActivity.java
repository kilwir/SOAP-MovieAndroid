package tmtc.soap.View.Implementation;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.parceler.Parcels;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tmtc.soap.Adapter.TinyMoviesAdapter;
import tmtc.soap.Listener.ItemMovieListener;
import tmtc.soap.Model.Movie;
import tmtc.soap.Model.MoviePerson;
import tmtc.soap.Presenter.Implementation.PersonPresenterImpl;
import tmtc.soap.Presenter.PersonPresenter;
import tmtc.soap.R;
import tmtc.soap.View.PersonView;
import tmtc.soap.View.Template.DrawerAppCompatActivity;

/**
 * Bad Boys Team
 * Created by remyjallan on 11/03/2016.
 */
public class PersonActivity extends DrawerAppCompatActivity implements PersonView, ItemMovieListener.IMovie {

    PersonPresenter mPresenter;

    @Bind(R.id.image_profile)
    ImageView ImageProfil;
    @Bind(R.id.text_name)
    TextView TextName;
    @Bind(R.id.recycler_movies)
    RecyclerView RecyclerMovies;
    @Bind(R.id.loader_movie)
    ProgressBar ProgressMovie;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        ButterKnife.bind(this);

        mPresenter = new PersonPresenterImpl(this);
        init();
    }

    @Override
    protected void init() {
        super.init();
        initRecyclerView();
        mPresenter.init(getIntent());
    }

    @Override
    public void init(MoviePerson person) {
        TextName.setText(person.getName());
        Picasso.with(this).load(person.getPicture()).into(ImageProfil);
    }

    @Override
    public void showMovies(List<Movie> movies) {
        TinyMoviesAdapter adapter = new TinyMoviesAdapter(this,movies,this);
        RecyclerMovies.setAdapter(adapter);
    }

    @Override
    public void navigateToMovie(View view, Movie movie) {
        Intent intent = new Intent(this,MovieActivity.class);
        intent.putExtra("movie", Parcels.wrap(movie));
        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(PersonActivity.this,view,"poster_movie");
        startActivity(intent, activityOptions.toBundle());
    }

    @Override
    public void navigateToLogin() {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void navigateToMain() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void ItemMovieListenerOnClick(View view, Movie movie) {
        this.navigateToMovie(view, movie);
    }

    @OnClick(R.id.fab_share)
    public void sharePerson() {
        this.shareText("Super film sur tmtc://person?id="+mPresenter.getPerson().getId());
    }

    @Override
    public void showProgress(String message) {
        ProgressMovie.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    public void hideProgress() {
        ProgressMovie.setVisibility(ProgressBar.INVISIBLE);
    }

    private void initRecyclerView() {
        RecyclerMovies.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerMovies.setItemAnimator(new DefaultItemAnimator());
        RecyclerMovies.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).build());
        RecyclerMovies.setLayoutManager(layoutManager);
    }
}
