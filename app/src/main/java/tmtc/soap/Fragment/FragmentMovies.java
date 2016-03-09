package tmtc.soap.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tmtc.soap.R;

/**
 * Bad Boys Team
 * Created by remyjallan on 06/03/2016.
 */
public class FragmentMovies extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies,container,false);
    }
}
