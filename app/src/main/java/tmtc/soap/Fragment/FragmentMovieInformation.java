package tmtc.soap.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tmtc.soap.DataManager.AuthDataManager;
import tmtc.soap.DataManager.MovieDataManager;
import tmtc.soap.Listener.MovieListener;
import tmtc.soap.Listener.OnClickBoughtListener;
import tmtc.soap.Model.ErrorContainer;
import tmtc.soap.Model.Movie;
import tmtc.soap.R;

/**
 * Bad Boys Team
 * Created by remyjallan on 10/03/2016.
 */
public class FragmentMovieInformation extends Fragment {
    private Movie mMovie;
    private Boolean mBought;
    private OnClickBoughtListener mClickListener;

    @Bind(R.id.text_synopsis)
    public TextView TextSynopsis;
    @Bind(R.id.button_rent_movie)
    public Button ButtonRent;

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
            MovieDataManager.getInstance().boughtMovie(mMovie, AuthDataManager.getInstance().getCurrentUser(), new MovieListener<Boolean>() {
                @Override
                public void OnMovieSuccess(Boolean movie) {
                    mBought = movie;
                    ButtonRent.setVisibility(View.VISIBLE);
                    if (movie) {
                        ButtonRent.setText(getActivity().getApplicationContext().getString(R.string.watch_video));
                    }
                }

                @Override
                public void OnMovieError(ErrorContainer error) {
                    ButtonRent.setVisibility(View.INVISIBLE);
                }
            });
        }
    }

    public void setClickListener(OnClickBoughtListener listener) {
        mClickListener = listener;
    }

    public void setMovie(Movie movie) {
        this.mMovie = movie;
    }

    @OnClick(R.id.button_rent_movie)
    public void RentClick(View view) {
        if(mClickListener != null) {
            mClickListener.OnClick(view,mBought);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
