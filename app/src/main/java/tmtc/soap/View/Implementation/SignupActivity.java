package tmtc.soap.View.Implementation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tmtc.soap.Presenter.Implementation.SignupPresenterImpl;
import tmtc.soap.Presenter.SignupPresenter;
import tmtc.soap.R;
import tmtc.soap.View.BaseAppCompatActivity;
import tmtc.soap.View.SignupView;

/**
 * Bad Boys Team
 * Created by remyjallan on 04/03/2016.
 */
public class SignupActivity extends BaseAppCompatActivity implements SignupView {

    private SignupPresenter mPresenter;

    private ProgressDialog mProgressDialog;

    @Bind(R.id.input_email)
    public EditText InputEmail;
    @Bind(R.id.input_username)
    public EditText InputUsername;
    @Bind(R.id.input_password)
    public EditText InputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        mPresenter = new SignupPresenterImpl(this);
        this.setupWindowAnimations();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress(String message) {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    @Override
    public void hideProgress() {
        if(mProgressDialog != null) {
            mProgressDialog.hide();
            mProgressDialog = null;
        }
    }

    @Override
    public void setupWindowAnimations() {
        Fade fade = (Fade) TransitionInflater.from(this).inflateTransition(R.transition.activity_fade);
        getWindow().setEnterTransition(fade);
    }

    @OnClick(R.id.btn_signup)
    public void OnClickSignup() {
        mPresenter.signup(InputEmail.getText().toString(), InputUsername.getText().toString(), InputPassword.getText().toString());
    }

    @Override
    @OnClick(R.id.link_login)
    public void navigateToLogin() {
        Intent intent = new Intent(this,LoginActivity.class);
        transitionTo(intent);
    }

    @Override
    public void navigateToMain() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
