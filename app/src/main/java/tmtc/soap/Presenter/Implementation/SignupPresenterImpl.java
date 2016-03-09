package tmtc.soap.Presenter.Implementation;

import tmtc.soap.DataManager.UserDataManager;
import tmtc.soap.Listener.SignupListener;
import tmtc.soap.Model.ErrorContainer;
import tmtc.soap.Model.User;
import tmtc.soap.Presenter.SignupPresenter;
import tmtc.soap.View.SignupView;

/**
 * Bad Boys Team
 * Created by remyjallan on 04/03/2016.
 */
public class SignupPresenterImpl implements SignupPresenter, SignupListener {

    private SignupView mView;

    public SignupPresenterImpl(SignupView view) {
        mView = view;
    }

    @Override
    public void signup(String email, String username, String password) {
        mView.showProgress("Creating Account...");
        if(email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mView.hideProgress();
            mView.showMessage("Email as empty");
        }
        if(username.isEmpty() || username.length() < 4) {
            mView.hideProgress();
            mView.showMessage("Wrong Username");
        }
        if(password.isEmpty() || password.length() < 4) {
            mView.hideProgress();
            mView.showMessage("Wrong Password");
        }

        User user = new User(username,password);
        user.setEmail(email);

        UserDataManager.getInstance().signup(user,this);
    }

    @Override
    public void onSignupSuccess(User user) {
        mView.hideProgress();
        mView.navigateToMain();
    }

    @Override
    public void onSignupError(ErrorContainer error) {
        mView.hideProgress();
        mView.showMessage(error.toString());
    }
}
