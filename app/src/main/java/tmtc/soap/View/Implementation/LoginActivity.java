package tmtc.soap.View.Implementation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tmtc.soap.Presenter.Implementation.LoginPresenterImpl;
import tmtc.soap.Presenter.LoginPresenter;
import tmtc.soap.R;
import tmtc.soap.View.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginView {

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
        mPresenter.checkIsConnected();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
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

    @OnClick(R.id.btn_login)
    public void OnClickLogin() {
        mPresenter.login(InputUsername.getText().toString(),InputPassword.getText().toString());
    }

    @Override
    @OnClick(R.id.link_signup)
    public void navigateToSignup() {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    public void navigateToMain() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
