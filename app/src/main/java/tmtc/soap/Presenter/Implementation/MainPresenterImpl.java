package tmtc.soap.Presenter.Implementation;

import tmtc.soap.Presenter.MainPresenter;
import tmtc.soap.View.MainView;

/**
 * Bad Boys Team
 * Created by remyjallan on 06/03/2016.
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView mView;

    public MainPresenterImpl(MainView view) {
        mView = view;
    }
}
