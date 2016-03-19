package tmtc.soap.View.Implementation;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmtc.soap.CustomView.TopCropImageView;
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
        Picasso.with(getApplicationContext())
                .load("http://smartyvet.com/site/wp-content/uploads/2014/05/red-panda-5.jpg")
                .into(ImagePicture);
    }
}
