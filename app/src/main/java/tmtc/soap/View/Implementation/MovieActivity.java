package tmtc.soap.View.Implementation;

import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmtc.soap.Adapter.MovieContentPagerAdapter;
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
    private FragmentPagerAdapter mPagerAdapter;

    @Bind(R.id.image_poster)
    public TopCropImageView ImagePoster;
    @Bind(R.id.text_name_movie)
    public TextView TextNameMovie;
    @Bind(R.id.toolbar)
    public Toolbar Toolbar;
    @Bind(R.id.view_pager_content)
    public ViewPager ViewPagerContent;

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

    private void initViewPager(Movie movie) {
        mPagerAdapter = new MovieContentPagerAdapter(getFragmentManager(),movie);
        ViewPagerContent.setAdapter(mPagerAdapter);
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
        initViewPager(movie);
    }

    @Override
    public void navigateToMain() {
        super.onBackPressed();
    }
}
