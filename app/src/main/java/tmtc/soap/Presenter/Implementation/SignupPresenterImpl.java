package tmtc.soap.Presenter.Implementation;

import android.content.Context;

import tmtc.soap.DataManager.AuthDataManager;
import tmtc.soap.Listener.SignupListener;
import tmtc.soap.Model.ErrorContainer;
import tmtc.soap.Model.User;
import tmtc.soap.Presenter.SignupPresenter;
import tmtc.soap.R;
import tmtc.soap.View.SignupView;

/**
 * Bad Boys Team
 * Created by remyjallan on 04/03/2016.
 */
public class SignupPresenterImpl implements SignupPresenter, SignupListener {

    private SignupView mView;
    private Context mContext;

    public SignupPresenterImpl(SignupView view, Context context) {
        mView = view;
        mContext = context;
    }

    @Override
    public void signup(String email, String username, String password) {
        mView.showProgress(mContext.getString(R.string.create_account___));
        if(email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mView.hideProgress();
            mView.showMessage(mContext.getString(R.string.email_empty));
        }
        if(username.isEmpty() || username.length() < 4) {
            mView.hideProgress();
            mView.showMessage(mContext.getString(R.string.username_empty));
        }
        if(password.isEmpty() || password.length() < 4) {
            mView.hideProgress();
            mView.showMessage(mContext.getString(R.string.password_empty));
        }

        User user = new User(username,password);
        user.setEmail(email);

        AuthDataManager.getInstance().signup(user,this);
    }

    @Override
    public void onSignupSuccess(User user) {
        mView.hideProgress();
        mView.navigateToMain();
    }

    @Override
    public void onSignupError(ErrorContainer error) {
        mView.hideProgress();
        mView.showMessage(error.Message);
    }
}
