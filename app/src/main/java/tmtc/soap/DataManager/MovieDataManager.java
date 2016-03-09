package tmtc.soap.DataManager;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tmtc.soap.Listener.MoviesListener;
import tmtc.soap.Model.Movie;

/**
 * Bad Boys Team
 * Created by remyjallan on 09/03/2016.
 */
public class MovieDataManager {
    private static MovieDataManager ourInstance = new MovieDataManager();

    public static MovieDataManager getInstance() {
        return ourInstance;
    }

    private MovieDataManager() {
    }

    public void getLastMovies(MoviesListener listener) {
        List<Movie> movies = new ArrayList<>();
        for(int i = 0; i< 10; i++){
            Movie movie = new Movie("Movie "+i,"Action");
            movie.setReleaseDate(new Date().toString());
            movies.add(movie);
            if(i%2 == 0)
                movie.setPoster("http://fr.web.img4.acsta.net/pictures/16/01/19/16/49/249124.jpg");
            else
                movie.setPoster("http://fr.web.img6.acsta.net/pictures/16/01/26/13/56/487595.jpg");
        }

        listener.OnMoviesSuccess(movies);
    }
}
