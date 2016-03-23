package tmtc.soap.View.Implementation;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.orhanobut.logger.Logger;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmtc.soap.Adapter.SearchAdapter;
import tmtc.soap.Model.SearchItem;
import tmtc.soap.Presenter.Implementation.SearchPresenterImpl;
import tmtc.soap.Presenter.SearchPresenter;
import tmtc.soap.R;
import tmtc.soap.View.SearchView;
import tmtc.soap.View.Template.DrawerAppCompatActivity;

/**
 * Bad Boys Team
 * Created by remyjallan on 23/03/2016.
 */
public class SearchActivity extends DrawerAppCompatActivity implements SearchView, SearchBox.MenuListener, SearchBox.SearchListener {
    @Bind(R.id.searchbox)
    SearchBox SearchInput;
    @Bind(R.id.recycler_search)
    RecyclerView RecyclerSearch;

    private SearchPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        this.init();
        mPresenter = new SearchPresenterImpl(this);
        mPresenter.init(getIntent());
    }

    @Override
    protected void init() {
        super.init();
        this.initSearchInput();
        this.initRecyclerView();
        SearchInput.setSearchListener(this);
        Logger.init("SearchActivity");
    }

    private void initSearchInput() {
        SearchInput.setSearchWithoutSuggestions(true);
        SearchInput.setMenuListener(this);
    }

    private void initRecyclerView() {
        RecyclerSearch.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerSearch.setItemAnimator(new DefaultItemAnimator());
        RecyclerSearch.setLayoutManager(layoutManager);
    }

    @Override
    public void setSearchResult(List<SearchItem> searchResult) {
        if(searchResult != null) {
            SearchAdapter adapter = new SearchAdapter(this,searchResult);
            RecyclerSearch.setAdapter(adapter);
        }
    }

    @Override
    public void onMenuClick() {
        mDrawer.openDrawer(mNavigationDrawer);
    }

    @Override
    public void onSearchOpened() {

    }

    @Override
    public void onSearchCleared() {

    }

    @Override
    public void onSearchClosed() {

    }

    @Override
    public void onSearchTermChanged(String s) {

    }

    @Override
    public void onSearch(String s) {
        mPresenter.search(s);
    }

    @Override
    public void onResultClick(SearchResult searchResult) {
    }
}
