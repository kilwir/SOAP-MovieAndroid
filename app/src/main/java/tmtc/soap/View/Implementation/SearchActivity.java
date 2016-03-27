package tmtc.soap.View.Implementation;

import android.app.ActivityOptions;
import android.content.Intent;
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
import android.view.View;
import android.widget.ProgressBar;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.orhanobut.logger.Logger;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;

import org.parceler.Parcels;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmtc.soap.Adapter.SearchAdapter;
import tmtc.soap.Listener.SearchItemClickListener;
import tmtc.soap.Model.Movie;
import tmtc.soap.Model.MoviePerson;
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
public class SearchActivity extends DrawerAppCompatActivity implements SearchView, SearchBox.MenuListener, SearchBox.SearchListener, SearchItemClickListener.ISearchItem {
    @Bind(R.id.searchbox)
    SearchBox SearchInput;
    @Bind(R.id.recycler_search)
    RecyclerView RecyclerSearch;
    @Bind(R.id.loader)
    ProgressBar Loader;

    private SearchPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        this.init();
        mPresenter = new SearchPresenterImpl(this,this);
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
            adapter.setListener(this);
            RecyclerSearch.setAdapter(adapter);
        }
    }

    @Override
    public void navigateToMovie(View view, Movie movie) {
        Intent intent = new Intent(this,MovieActivity.class);
        intent.putExtra("movie", Parcels.wrap(movie));
        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(SearchActivity.this, view, "poster_movie");
        startActivity(intent, activityOptions.toBundle());
    }

    @Override
    public void navigateToPerson(View view, MoviePerson person) {
        Intent intent = new Intent(this,PersonActivity.class);
        intent.putExtra("person", Parcels.wrap(person));
        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(SearchActivity.this,view, "picture_person");
        startActivity(intent, activityOptions.toBundle());
    }

    @Override
    public void onSearchItemClick(View view, SearchItem item) {
        if(item.getIdType() == Movie.ID) {
            this.navigateToMovie(view,(Movie)item);
        } else if( item.getIdType() == MoviePerson.ID) {
            this.navigateToPerson(view,(MoviePerson)item);
        }
    }

    @Override
    public void showProgress(String message) {
        Loader.setVisibility(ProgressBar.VISIBLE);
        RecyclerSearch.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        Loader.setVisibility(ProgressBar.INVISIBLE);
        RecyclerSearch.setVisibility(View.VISIBLE);
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
        mPresenter.search(s);
    }

    @Override
    public void onSearch(String s) {
        mPresenter.search(s);
    }

    @Override
    public void onResultClick(SearchResult searchResult) {
    }
}
