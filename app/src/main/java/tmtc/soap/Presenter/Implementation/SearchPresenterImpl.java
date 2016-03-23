package tmtc.soap.Presenter.Implementation;

import android.content.Intent;

import java.util.List;

import tmtc.soap.DataManager.SearchDataManager;
import tmtc.soap.Listener.SearchListener;
import tmtc.soap.Model.ErrorContainer;
import tmtc.soap.Model.SearchItem;
import tmtc.soap.Presenter.SearchPresenter;
import tmtc.soap.View.SearchView;

/**
 * Bad Boys Team
 * Created by remyjallan on 23/03/2016.
 */
public class SearchPresenterImpl implements SearchPresenter, SearchListener<List<SearchItem>> {

    private SearchView mView;
    private String mCurrentQuery;

    public SearchPresenterImpl(SearchView view) {
        mView = view;
    }

    @Override
    public void init(Intent intent) {
        if(intent != null) {
            String query = intent.getStringExtra("query");
            if(query != null && !query.equals("")) {
                this.search(query);
            }
        }
    }

    @Override
    public void search(String query) {
        mView.showProgress("Loading ...");
        this.mCurrentQuery = query;
        SearchDataManager.getInstance().search(query,this);
    }

    @Override
    public void OnSearchSuccess(List<SearchItem> movie) {
        mView.hideProgress();
        mView.setSearchResult(movie);
    }

    @Override
    public void OnSearchError(ErrorContainer error) {
        mView.hideProgress();
        mView.showMessage(error.Message);
    }
}
