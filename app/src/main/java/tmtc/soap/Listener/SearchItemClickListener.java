package tmtc.soap.Listener;

import android.view.View;

import tmtc.soap.Model.SearchItem;

/**
 * Bad Boys Team
 * Created by remyjallan on 23/03/2016.
 */
public abstract class SearchItemClickListener {
    public interface ISearchItem {
        void onSearchItemClick(View view,SearchItem item);
    }

    public interface  IPosition {
        void onSearchItemClick(View view,int position);
    }
}
