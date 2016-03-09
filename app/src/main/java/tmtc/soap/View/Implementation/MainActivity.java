package tmtc.soap.View.Implementation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.orhanobut.logger.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmtc.soap.DataManager.UserDataManager;
import tmtc.soap.Presenter.Implementation.MainPresenterImpl;
import tmtc.soap.Presenter.MainPresenter;
import tmtc.soap.R;
import tmtc.soap.View.MainView;

public class MainActivity extends AppCompatActivity implements MainView{

    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = new MainPresenterImpl(this);
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showProgress(String message) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setupWindowAnimations() {

    }
}
