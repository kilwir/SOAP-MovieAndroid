package tmtc.soap.Listener;

import android.view.View;

import tmtc.soap.Model.MoviePerson;

/**
 * Bad Boys Team
 * Created by remyjallan on 11/03/2016.
 */
public abstract class ItemPersonListener {
    public interface IPosition {
        void ItemPersonListenerOnClick(View view,int position);
    }
    public interface IPerson {
        void ItemPersonListenerOnClick(View view,MoviePerson person);
    }
}
