package tmtc.soap.Presenter.Implementation;

import android.app.Activity;
import android.content.Context;

import tmtc.soap.DataManager.AuthDataManager;
import tmtc.soap.Listener.LoginListener;
import tmtc.soap.Model.ErrorContainer;
import tmtc.soap.Model.User;
import tmtc.soap.Presenter.LoginPresenter;
import tmtc.soap.R;
import tmtc.soap.View.LoginView;

/**
 * Bad Boys Team
 * Created by remyjallan on 18/02/2016.
 */
public class LoginPresenterImpl implements LoginPresenter,LoginListener{

    private Context mContext;
    private LoginView mView;

    public LoginPresenterImpl(LoginView view, Context context) {
        this.mView = view;
        this.mContext = context;
        AuthDataManager.getInstance().init((Activity)mView);
    }

    @Override
    public void login(String username, String password) {
        mView.showProgress(mContext.getString(R.string.connection));
        if(username.isEmpty()) {
            mView.hideProgress();
            mView.showMessage(mContext.getString(R.string.username_empty));
        }
        if(password.isEmpty() || password.length() < 4 ) {
            mView.hideProgress();
            mView.showMessage(mContext.getString(R.string.password_empty));
        }

        User user = new User(username,password);

        AuthDataManager.getInstance().login(user,this);
    }

    @Override
    public void checkIsConnected() {
        if(AuthDataManager.getInstance().isConnected()) {
            mView.navigateToMain();
        }
    }

    @Override
    public void onLoginSuccess(User user) {
        mView.hideProgress();
        mView.navigateToMain();
    }

    @Override
    public void onLoginError(ErrorContainer error) {
        mView.hideProgress();
        mView.showMessage(error.Message);
    }
}
