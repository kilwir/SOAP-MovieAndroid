package tmtc.soap.View;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import tmtc.soap.Helper.TransitionHelper;

/**
 * Bad Boys Team
 * Created by remyjallan on 09/03/2016.
 */
public class BaseAppCompatActivity extends AppCompatActivity {
    @SuppressWarnings("unchecked") protected void transitionTo(Intent i) {
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(this, true);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs);
        startActivity(i, transitionActivityOptions.toBundle());
    }
}
