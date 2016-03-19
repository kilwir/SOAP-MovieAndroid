package tmtc.soap.View.Implementation;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.parceler.Parcels;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmtc.soap.Adapter.CommentsUserAdapter;
import tmtc.soap.CustomView.TopCropImageView;
import tmtc.soap.Listener.ItemCommentListener;
import tmtc.soap.Model.Comment;
import tmtc.soap.Model.Movie;
import tmtc.soap.Model.User;
import tmtc.soap.Presenter.Implementation.UserPresenterImpl;
import tmtc.soap.Presenter.UserPresenter;
import tmtc.soap.R;
import tmtc.soap.View.Template.DrawerAppCompatActivity;
import tmtc.soap.View.UserView;

/**
 * Bad Boys Team
 * Created by remyjallan on 19/03/2016.
 */
public class UserActivity extends DrawerAppCompatActivity implements UserView, ItemCommentListener.IComment {

    @Bind(R.id.image_picture)
    ImageView ImagePicture;
    @Bind(R.id.text_name_user)
    TextView TextName;
    @Bind(R.id.recycler_comments)
    RecyclerView RecyclerComments;
    @Bind(R.id.loader_comments)
    ProgressBar ProgressComments;
    @Bind(R.id.fab_user)
    ImageButton FabUser;

    UserPresenter mPresenter;

    private void initRecyclerView() {
        RecyclerComments.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerComments.setItemAnimator(new DefaultItemAnimator());
        RecyclerComments.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).build());
        RecyclerComments.setLayoutManager(layoutManager);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        mPresenter = new UserPresenterImpl(this);
        this.init();
    }

    @Override
    protected void init() {
        super.init();
        this.initRecyclerView();
        mPresenter.init(getIntent());
    }

    @Override
    public void navigateToMain() {
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void showUser(User user) {
        TextName.setText(user.getUsername());
        Picasso.with(getApplicationContext())
                .load("http://smartyvet.com/site/wp-content/uploads/2014/05/red-panda-5.jpg")
                .into(ImagePicture);
    }

    @Override
    public void showComments(List<Comment> comments) {
        CommentsUserAdapter adapter = new CommentsUserAdapter(getApplicationContext(),comments);
        adapter.setListener(this);
        RecyclerComments.setAdapter(adapter);
    }

    @Override
    public void navigateToMovie(View view, Movie movie) {
        Intent intent = new Intent(this,MovieActivity.class);
        intent.putExtra("movie", Parcels.wrap(movie));
        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(UserActivity.this,view,"poster_movie");
        startActivity(intent, activityOptions.toBundle());
    }

    @Override
    public void hideFab() {
        FabUser.setVisibility(View.INVISIBLE);
    }

    @Override
    public void fabAlreadyFriend() {
        FabUser.setImageDrawable(getDrawable(R.drawable.ic_person_24dp));
    }

    @Override
    public void fabNotFriend() {
        FabUser.setImageDrawable(getDrawable(R.drawable.ic_person_add_24dp));
    }

    @Override
    public void showProgress(String message) {
        ProgressComments.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    public void hideProgress() {
        ProgressComments.setVisibility(ProgressBar.INVISIBLE);
    }

    @Override
    public void ItemCommentClickListener(View view, Comment comment) {
        this.navigateToMovie(view,comment.getMovie());
    }
}
