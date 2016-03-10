package tmtc.soap.View.Implementation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tmtc.soap.Presenter.Implementation.LoginPresenterImpl;
import tmtc.soap.Presenter.LoginPresenter;
import tmtc.soap.R;
import tmtc.soap.View.Template.BaseAppCompatActivity;
import tmtc.soap.View.LoginView;

public class LoginActivity extends BaseAppCompatActivity implements LoginView {

    private LoginPresenter mPresenter;

    @Bind(R.id.input_username)
    public EditText InputUsername;

    @Bind(R.id.input_password)
    public EditText InputPassword;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mPresenter = new LoginPresenterImpl(this);
        setupWindowAnimations();
        mPresenter.checkIsConnected();
    }

    @Override
    public void setupWindowAnimations() {
        Slide slide = (Slide) TransitionInflater.from(this).inflateTransition(R.transition.activity_slide);
        getWindow().setExitTransition(slide);
    }

    @OnClick(R.id.btn_login)
    public void OnClickLogin() {
        mPresenter.login(InputUsername.getText().toString(),InputPassword.getText().toString());
    }

    @Override
    @OnClick(R.id.link_signup)
    public void navigateToSignup() {
        Intent intent = new Intent(this, SignupActivity.class);
        transitionTo(intent);
    }

    public void navigateToMain() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
