package tmtc.soap.DataManager;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import tmtc.soap.Listener.MoviesListener;
import tmtc.soap.Model.Movie;
import tmtc.soap.Model.MoviePerson;

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

        DateFormat outputFormatter1 = new SimpleDateFormat("dd MMM yyyy", Locale.FRANCE);
        String output = outputFormatter1.format(new Date());

        for(int i = 0; i< 10; i++){
            Movie movie = new Movie(i,"Deadpool");
            movie.setReleaseDate(output);
            movie.setSynopsis("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque molestie, tortor vitae luctus ullamcorper, ipsum ante laoreet dui, a congue ante libero quis orci. Ut convallis lectus felis, nec rutrum sapien viverra vitae. Suspendisse nec rutrum dui, ac venenatis lectus. Mauris semper lorem eu efficitur finibus. Vestibulum scelerisque posuere facilisis. Nullam commodo, est ac interdum sodales, quam ipsum viverra justo, at dapibus magna massa sed diam. Phasellus a pulvinar dolor. Nunc lacus erat, rutrum sed hendrerit convallis, sollicitudin et lacus. Aliquam euismod nisl at sollicitudin ultrices. Donec at purus sed justo accumsan ultricies.");
            if(i%2 == 0)
                movie.setPoster("http://fr.web.img4.acsta.net/pictures/16/01/19/16/49/249124.jpg");
            else
                movie.setPoster("http://fr.web.img6.acsta.net/pictures/16/01/26/13/56/487595.jpg");

            for(int j = 0; j < 8; j++) {
                MoviePerson person = new MoviePerson(j,"Rémy Jallan","Acteur");
                person.setPicture("http://media4.popsugar-assets.com/files/2012/12/51/4/192/1922398/49a4cec24601ed4e_ryanreynolds.xxxlarge_2.jpg");
                movie.addPerson(person);
            }
            movies.add(movie);
        }

        listener.OnMoviesSuccess(movies);
    }

    public void getMovieForPerson(MoviePerson person,MoviesListener listener) {
        this.getLastMovies(listener);
    }
}
