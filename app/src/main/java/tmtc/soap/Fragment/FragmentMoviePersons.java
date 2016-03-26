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
import android.widget.Toast;

import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmtc.soap.Adapter.MoviePersonAdapter;
import tmtc.soap.DataManager.MovieDataManager;
import tmtc.soap.Listener.ItemPersonListener;
import tmtc.soap.Listener.MovieListener;
import tmtc.soap.Model.ErrorContainer;
import tmtc.soap.Model.Movie;
import tmtc.soap.Model.MoviePerson;
import tmtc.soap.R;

/**
 * Bad Boys Team
 * Created by remyjallan on 10/03/2016.
 */
public class FragmentMoviePersons extends Fragment implements ItemPersonListener.IPosition, MovieListener<Movie> {
    private Movie mMovie;
    private ItemPersonListener.IPerson mListener;

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
        if(mMovie != null){
           if(mMovie.getPersons() != null && mMovie.getPersons().size() > 0) {
               setContent();
           } else {
               MovieDataManager.getInstance().getMovieById(mMovie.getId(),this);
           }
        }

    }

    private void setContent() {
        if(RecyclerMoviePersons != null && mMovie != null) {
            MoviePersonAdapter adapter = new MoviePersonAdapter(getActivity(),mMovie.getPersons());
            adapter.setItemPersonListener(this);
            RecyclerMoviePersons.setAdapter(adapter);
        }
    }

    public void setMovie(Movie movie) {
        this.mMovie = movie;
    }

    public void setItemPersonListener(ItemPersonListener.IPerson listener) {
        mListener = listener;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void ItemPersonListenerOnClick(View view, int position) {
        if(mListener != null) {
            mListener.ItemPersonListenerOnClick(view,mMovie.getPersons().get(position));
        }
    }

    @Override
    public void OnMovieSuccess(Movie movie) {
        mMovie = movie;
        setContent();
    }

    @Override
    public void OnMovieError(ErrorContainer error) {
        Toast.makeText(getActivity(),error.Message,Toast.LENGTH_LONG).show();
    }
}
