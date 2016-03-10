package tmtc.soap.View.Template;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import tmtc.soap.Helper.TransitionHelper;
import tmtc.soap.View.BaseView;

/**
 * Bad Boys Team
 * Created by remyjallan on 09/03/2016.
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity implements BaseView {
    protected ProgressDialog mProgressDialog;

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
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

    @SuppressWarnings("unchecked")
    protected void transitionTo(Intent i) {
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(this, true);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs);
        startActivity(i, transitionActivityOptions.toBundle());
    }
}
