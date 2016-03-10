package tmtc.soap.View.Implementation;

import android.os.Bundle;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmtc.soap.CustomView.TopCropImageView;
import tmtc.soap.Model.Movie;
import tmtc.soap.Presenter.Implementation.MoviePresenterImpl;
import tmtc.soap.Presenter.MoviePresenter;
import tmtc.soap.R;
import tmtc.soap.View.MovieView;
import tmtc.soap.View.Template.BaseAppCompatActivity;

/**
 * Bad Boys Team
 * Created by remyjallan on 10/03/2016.
 */
public class MovieActivity extends BaseAppCompatActivity implements MovieView, View.OnClickListener {

    private MoviePresenter mPresenter;

    @Bind(R.id.image_poster)
    public TopCropImageView ImagePoster;
    @Bind(R.id.text_name_movie)
    public TextView TextNameMovie;
    @Bind(R.id.text_synopsis)
    public TextView TextSynopsis;
    @Bind(R.id.toolbar)
    public Toolbar Toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);

        initToolbar();
        Logger.init("MovieActivity");
        mPresenter = new MoviePresenterImpl(this);
        mPresenter.init(getIntent());

    }

    @Override
    public void setupWindowAnimations() {
    }

    @Override
    public void onClick(View view) {
        this.navigateToMain();
    }

    public void initToolbar() {
        if(Toolbar != null) {
            setSupportActionBar(Toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            Toolbar.setNavigationOnClickListener(this);
        }
    }

    @Override
    public void init(Movie movie) {
        Picasso.with(this)
                .load(movie.getPoster())
                .into(ImagePoster);
        TextNameMovie.setText(movie.getName());
        TextSynopsis.setText(movie.getSynopsis());
    }

    @Override
    public void navigateToMain() {
        super.onBackPressed();
    }
}
