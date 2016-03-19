package tmtc.soap.View.Implementation;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmtc.soap.Adapter.CommentsUserAdapter;
import tmtc.soap.CustomView.TopCropImageView;
import tmtc.soap.Model.Comment;
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
public class UserActivity extends DrawerAppCompatActivity implements UserView {

    @Bind(R.id.image_picture)
    ImageView ImagePicture;
    @Bind(R.id.text_name_user)
    TextView TextName;
    @Bind(R.id.recycler_comments)
    RecyclerView RecyclerComments;
    @Bind(R.id.loader_comments)
    ProgressBar ProgressComments;

    UserPresenter mPresenter;

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
        RecyclerComments.setAdapter(adapter);
    }

    @Override
    public void showProgress(String message) {
        ProgressComments.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    public void hideProgress() {
        ProgressComments.setVisibility(ProgressBar.INVISIBLE);
    }

    private void initRecyclerView() {
        RecyclerComments.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerComments.setItemAnimator(new DefaultItemAnimator());
        RecyclerComments.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).build());
        RecyclerComments.setLayoutManager(layoutManager);
    }
}
