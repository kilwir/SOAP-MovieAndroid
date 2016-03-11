package tmtc.soap.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmtc.soap.Adapter.MoviePersonAdapter;
import tmtc.soap.Model.Movie;
import tmtc.soap.Model.MoviePerson;
import tmtc.soap.R;

/**
 * Bad Boys Team
 * Created by remyjallan on 10/03/2016.
 */
public class FragmentMoviePersons extends Fragment{
    private Movie mMovie;

    @Bind(R.id.recycler_movie_persons)
    public RecyclerView RecyclerMoviePersons;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_persons,container,false);
        ButterKnife.bind(this, view);
        initRecyclerView();
        loadContent();
        return view;
    }

    private void initRecyclerView() {
        RecyclerMoviePersons.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        RecyclerMoviePersons.setItemAnimator(new DefaultItemAnimator());
        RecyclerMoviePersons.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).build());
        RecyclerMoviePersons.setLayoutManager(layoutManager);
    }

    public void loadContent() {
        if(RecyclerMoviePersons != null && mMovie != null) {
            MoviePersonAdapter adapter = new MoviePersonAdapter(getActivity(),mMovie.getPersons());
            RecyclerMoviePersons.setAdapter(adapter);
        }
    }

    public void setMovie(Movie movie) {
        this.mMovie = movie;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
