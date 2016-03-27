package tmtc.soap.View;

import android.view.View;

import java.util.List;

import tmtc.soap.Model.Movie;

/**
 * Bad Boys Team
 * Created by remyjallan on 27/03/2016.
 */
public interface RecommendationView extends BaseView {
    void showRecommendation(List<Movie> movies);
    void navigateToMovie(View view, Movie movie);
}
