package tmtc.soap.Presenter.Implementation;

import tmtc.soap.Presenter.LoginPresenter;
import tmtc.soap.View.LoginView;

/**
 * Bad Boys Team
 * Created by remyjallan on 18/02/2016.
 */
public class LoginPresenterImpl implements LoginPresenter{

    private LoginView mView;

    public LoginPresenterImpl(LoginView view) {
        this.mView = view;
    }
}
