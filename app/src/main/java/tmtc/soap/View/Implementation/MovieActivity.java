package tmtc.soap.View.Implementation;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.TitlePageIndicator;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tmtc.soap.Adapter.MovieContentPagerAdapter;
import tmtc.soap.CustomView.TopCropImageView;
import tmtc.soap.Listener.ItemCommentListener;
import tmtc.soap.Listener.ItemPersonListener;
import tmtc.soap.Model.Comment;
import tmtc.soap.Model.Movie;
import tmtc.soap.Model.MoviePerson;
import tmtc.soap.Presenter.Implementation.MoviePresenterImpl;
import tmtc.soap.Presenter.MoviePresenter;
import tmtc.soap.R;
import tmtc.soap.View.MovieView;
import tmtc.soap.View.Template.DrawerAppCompatActivity;

/**
 * Bad Boys Team
 * Created by remyjallan on 10/03/2016.
 */

public class MovieActivity extends DrawerAppCompatActivity implements MovieView, ItemPersonListener.IPerson, ItemCommentListener.IComment {

    private MoviePresenter mPresenter;
    private FragmentPagerAdapter mPagerAdapter;

    @Bind(R.id.image_poster)
    public TopCropImageView ImagePoster;
    @Bind(R.id.text_name_movie)
    public TextView TextNameMovie;
    @Bind(R.id.view_pager_content)
    public ViewPager ViewPagerContent;
    @Bind(R.id.page_indicator_content)
    public TitlePageIndicator PageIndicatorContent;

    private void initViewPager(Movie movie) {
        mPagerAdapter = new MovieContentPagerAdapter(getFragmentManager(),movie,this,this);
        ViewPagerContent.setAdapter(mPagerAdapter);
        PageIndicatorContent.setViewPager(ViewPagerContent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);
        this.init();
        Logger.init("MovieActivity");
        mPresenter = new MoviePresenterImpl(this);
        mPresenter.init(getIntent());

    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void setupWindowAnimations() {
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

    @Override
    public void ItemPersonListenerOnClick(View view, MoviePerson person) {
        this.navigateToPerson(view, person);
    }

    @Override
    public void navigateToPerson(View view,MoviePerson person) {
        Intent intent = new Intent(this,PersonActivity.class);
        intent.putExtra("person", Parcels.wrap(person));
        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(MovieActivity.this,view,"picture_person");
        startActivity(intent, activityOptions.toBundle());
    }

    @Override
    public void navigateToLogin() {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.fab_share)
    public void shareMovie() {
        this.shareText("Super film sur tmtc://movie?id="+mPresenter.getMovie().getId());
    }

    @Override
    public void ItemCommentClickListener(View view, Comment comment) {
        Logger.d("Comment clicked ! -> "+comment.getId() );
    }
}
