package tmtc.soap.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmtc.soap.Model.Movie;
import tmtc.soap.R;

/**
 * Bad Boys Team
 * Created by remyjallan on 10/03/2016.
 */
public class FragmentMovieInformation extends Fragment {
    private Movie mMovie;

    @Bind(R.id.text_synopsis)
    public TextView TextSynopsis;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_information,container,false);
        ButterKnife.bind(this,view);
        loadContent();
        return view;
    }

    public void loadContent() {
        if(TextSynopsis != null && mMovie != null) {
            TextSynopsis.setText(mMovie.getSynopsis());
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
