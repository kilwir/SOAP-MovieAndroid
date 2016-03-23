package tmtc.soap.Listener;

import tmtc.soap.Model.ErrorContainer;

/**
 * Bad Boys Team
 * Created by remyjallan on 23/03/2016.
 */
public interface SearchListener<T> {
    void OnSearchSuccess(T movie);
    void OnSearchError(ErrorContainer error);
}
