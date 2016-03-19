package tmtc.soap.Presenter.Implementation;

import android.content.Intent;

import org.parceler.Parcels;

import tmtc.soap.Model.User;
import tmtc.soap.Presenter.UserPresenter;
import tmtc.soap.View.UserView;

/**
 * Bad Boys Team
 * Created by remyjallan on 19/03/2016.
 */
public class UserPresenterImpl implements UserPresenter {

    private UserView mView;
    private User mUser;

    public UserPresenterImpl(UserView view) {
        mView = view;
    }

    @Override
    public void init(Intent intent) {
        if(intent  == null) {
            mView.navigateToMain();
        } else {
            mUser = Parcels.unwrap(intent.getParcelableExtra("user"));
            if(mUser == null) {
                mView.navigateToMain();
            }
            mView.showUser(mUser);
        }
    }
}
