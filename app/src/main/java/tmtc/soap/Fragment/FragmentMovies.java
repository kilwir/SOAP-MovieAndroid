package tmtc.soap.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmtc.soap.Adapter.MoviesAdapter;
import tmtc.soap.Listener.ItemMovieListener;
import tmtc.soap.Model.Movie;
import tmtc.soap.R;

/**
 * Bad Boys Team
 * Created by remyjallan on 06/03/2016.
 */
public class FragmentMovies extends Fragment implements ItemMovieListener.IPosition {

    @Bind(R.id.recycler_movies)
    public RecyclerView RecyclerMovies;
    @Bind(R.id.swipeContainer)
    public SwipeRefreshLayout SwipeRefresh;

    private List<Movie> mMovies;

    private ItemMovieListener.IMovie mListenerMovie;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies,container,false);
        ButterKnife.bind(this, view);
        Logger.init("Movies Fragment");
        this.initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        RecyclerMovies.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        RecyclerMovies.setItemAnimator(new DefaultItemAnimator());
        RecyclerMovies.setLayoutManager(layoutManager);
    }

    public void setListenerMovie(ItemMovieListener.IMovie listenerMovie) {
        mListenerMovie = listenerMovie;
    }

    public void setRefreshListener(SwipeRefreshLayout.OnRefreshListener listener) {
        SwipeRefresh.setOnRefreshListener(listener);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void loadMovies(List<Movie> movies) {
        this.mMovies = movies;
        MoviesAdapter adapter = new MoviesAdapter(getActivity(),movies);
        adapter.setItemMovieListener(this);
        RecyclerMovies.setAdapter(adapter);
    }

    public void isRefresh() {
        SwipeRefresh.setRefreshing(false);
    }

    @Override
    public void ItemMovieListenerOnClick(View view,int position) {
        if(mMovies != null) {
            if(mListenerMovie != null) {
                Movie movie = mMovies.get(position);
                mListenerMovie.ItemMovieListenerOnClick(view,movie);
            }
        }
    }
}

