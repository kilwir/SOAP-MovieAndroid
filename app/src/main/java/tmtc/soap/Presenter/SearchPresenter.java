package tmtc.soap.Presenter;

import android.content.Intent;

/**
 * Bad Boys Team
 * Created by remyjallan on 23/03/2016.
 */
public interface SearchPresenter {
    void init(Intent intent);
    void search(String query);
}
