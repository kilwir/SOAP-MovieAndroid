package tmtc.soap.Presenter.Implementation;

import android.app.Activity;

import tmtc.soap.DataManager.AuthDataManager;
import tmtc.soap.Listener.LoginListener;
import tmtc.soap.Model.ErrorContainer;
import tmtc.soap.Model.User;
import tmtc.soap.Presenter.LoginPresenter;
import tmtc.soap.View.LoginView;

/**
 * Bad Boys Team
 * Created by remyjallan on 18/02/2016.
 */
public class LoginPresenterImpl implements LoginPresenter,LoginListener{

    private LoginView mView;

    public LoginPresenterImpl(LoginView view) {
        this.mView = view;
        AuthDataManager.getInstance().init((Activity)mView);
    }

    @Override
    public void login(String username, String password) {
        mView.showProgress("Connection...");
        if(username.isEmpty()) {
            mView.hideProgress();
            mView.showMessage("Username as empty");
        }
        if(password.isEmpty() || password.length() < 4 ) {
            mView.hideProgress();
            mView.showMessage("Password as wrong");
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
