package tmtc.soap.View.Implementation;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.TitlePageIndicator;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tmtc.soap.Adapter.MovieContentPagerAdapter;
import tmtc.soap.CustomView.TopCropImageView;
import tmtc.soap.Fragment.FragmentMovieInformation;
import tmtc.soap.Listener.ItemCommentListener;
import tmtc.soap.Listener.ItemPersonListener;
import tmtc.soap.Listener.OnClickBoughtListener;
import tmtc.soap.Model.Comment;
import tmtc.soap.Model.Movie;
import tmtc.soap.Model.MoviePerson;
import tmtc.soap.Model.User;
import tmtc.soap.Presenter.Implementation.MoviePresenterImpl;
import tmtc.soap.Presenter.MoviePresenter;
import tmtc.soap.R;
import tmtc.soap.View.MovieView;
import tmtc.soap.View.Template.DrawerAppCompatActivity;

/**
 * Bad Boys Team
 * Created by remyjallan on 10/03/2016.
 */

public class MovieActivity extends DrawerAppCompatActivity implements MovieView, ItemPersonListener.IPerson, ItemCommentListener.IComment, OnClickBoughtListener {

    private static final int ID_BOUGHT = 69;

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
        mPagerAdapter = new MovieContentPagerAdapter(getFragmentManager(),movie,this,this,this,this);
        ViewPagerContent.setAdapter(mPagerAdapter);
        PageIndicatorContent.setViewPager(ViewPagerContent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        this.init();
        Logger.init("MovieActivity");
        mPresenter = new MoviePresenterImpl(this,this);
        mPresenter.init(getIntent());

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
    public void confirmBought(String title) {
        new MaterialDialog.Builder(this)
                .title(getString(R.string.confirm_locate))
                .content(getString(R.string.do_you_want_locate)+ " " + title + " " +getString(R.string.for_money))
                .positiveText(getString(R.string.locate))
                .negativeText(getString(R.string.cancel))
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        mPresenter.confirmBought();
                    }
                }).show();
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

    @Override
    public void navigateToUser(User user) {
        Intent intent = new Intent(this,UserActivity.class);
        intent.putExtra("user", Parcels.wrap(user));
        transitionTo(intent);
    }

    @Override
    public void navigateToBought(Movie movie) {
        Intent intent = new Intent(this, BoughtActivity.class);
        intent.putExtra("movie", Parcels.wrap(movie));
        transitionTo(intent, ID_BOUGHT);
    }

    @Override
    public void navigateToPlayer(Movie movie) {
        Intent intent = new Intent(this, PlayerActivity.class);
        transitionTo(intent);
    }

    @OnClick(R.id.fab_share)
    public void shareMovie() {
        this.shareText(getString(R.string.great_movie) + " tmtc://movie?id=" + mPresenter.getMovie().getId());
    }

    @Override
    public void ItemCommentClickListener(View view, Comment comment) {
        if(comment != null && comment.getUser() != null) {
            this.navigateToUser(comment.getUser());
        }
    }

    @Override
    public void OnClick(View view, boolean bought) {
        mPresenter.boughtMovie(bought);
    }

    @Override
    public void setupWindowAnimations() {
        Slide slide = (Slide) TransitionInflater.from(this).inflateTransition(R.transition.activity_slide);
        getWindow().setExitTransition(slide);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == ID_BOUGHT) {
            if(resultCode == Activity.RESULT_OK) {
                String message = data.getStringExtra("message");
                showMessage(message);
                if(data.getBooleanExtra("bought",false)) {
                    MovieContentPagerAdapter adapter = (MovieContentPagerAdapter) ViewPagerContent.getAdapter();
                    FragmentMovieInformation fragment = (FragmentMovieInformation) adapter.getFragment(0);
                    fragment.loadContent();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
