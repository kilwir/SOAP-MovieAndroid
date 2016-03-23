package tmtc.soap.View;

import java.util.List;

import tmtc.soap.Model.SearchItem;

/**
 * Bad Boys Team
 * Created by remyjallan on 23/03/2016.
 */
public interface SearchView extends BaseView {
    void setSearchResult(List<SearchItem> searchResult);
}
