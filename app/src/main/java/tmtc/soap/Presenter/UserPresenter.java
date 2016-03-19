package tmtc.soap.Presenter;

import android.content.Intent;

import tmtc.soap.Model.Movie;

/**
 * Bad Boys Team
 * Created by remyjallan on 19/03/2016.
 */
public interface UserPresenter {
    void init(Intent intent);
    void loadComments();
    void checkStateFriend();
    void performFabFriend();
}
