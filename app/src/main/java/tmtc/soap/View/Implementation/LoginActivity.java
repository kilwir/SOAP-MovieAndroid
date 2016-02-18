package tmtc.soap.View.Implementation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import tmtc.soap.Presenter.Implementation.LoginPresenterImpl;
import tmtc.soap.Presenter.LoginPresenter;
import tmtc.soap.R;
import tmtc.soap.View.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mPresenter = new LoginPresenterImpl(this);
    }
}
