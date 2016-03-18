package tmtc.soap.DataManager;

import android.os.Handler;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import tmtc.soap.Listener.MovieListener;
import tmtc.soap.Listener.MoviesListener;
import tmtc.soap.Listener.PersonListener;
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

    public void getLastMovies(final MoviesListener listener) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<Movie> movies = new ArrayList<>();

                DateFormat outputFormatter1 = new SimpleDateFormat("dd MMM yyyy", Locale.FRANCE);
                String output = outputFormatter1.format(new Date());

                for (int i = 0; i < 10; i++) {
                    Movie movie = new Movie(i, "Deadpool");
                    movie.setReleaseDate(output);
                    movie.setSynopsis("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque molestie, tortor vitae luctus ullamcorper, ipsum ante laoreet dui, a congue ante libero quis orci. Ut convallis lectus felis, nec rutrum sapien viverra vitae. Suspendisse nec rutrum dui, ac venenatis lectus. Mauris semper lorem eu efficitur finibus. Vestibulum scelerisque posuere facilisis. Nullam commodo, est ac interdum sodales, quam ipsum viverra justo, at dapibus magna massa sed diam. Phasellus a pulvinar dolor. Nunc lacus erat, rutrum sed hendrerit convallis, sollicitudin et lacus. Aliquam euismod nisl at sollicitudin ultrices. Donec at purus sed justo accumsan ultricies.");
                    if (i % 2 == 0)
                        movie.setPoster("http://fr.web.img4.acsta.net/pictures/16/01/19/16/49/249124.jpg");
                    else
                        movie.setPoster("http://fr.web.img6.acsta.net/pictures/16/01/26/13/56/487595.jpg");

                    for (int j = 0; j < 8; j++) {
                        MoviePerson person = new MoviePerson(j, "Rémy Jallan", "Acteur");
                        person.setPicture("http://media4.popsugar-assets.com/files/2012/12/51/4/192/1922398/49a4cec24601ed4e_ryanreynolds.xxxlarge_2.jpg");
                        movie.addPerson(person);
                    }
                    movies.add(movie);
                }

                listener.OnMoviesSuccess(movies);
            }
        }, 3000);
    }

    public void getMovieById(final int id, final MovieListener listener) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Movie movie = new Movie(id, "Deadpool 2");
                DateFormat outputFormatter1 = new SimpleDateFormat("dd MMM yyyy", Locale.FRANCE);
                String output = outputFormatter1.format(new Date());
                movie.setSynopsis("Super synopsis ma guele");
                movie.setPoster("http://fr.web.img3.acsta.net/pictures/15/11/10/09/30/165611.jpg");
                movie.setReleaseDate(output);

                for (int j = 0; j < 8; j++) {
                    MoviePerson person = new MoviePerson(j, "Rémy Jallan", "Acteur");
                    person.setPicture("http://media4.popsugar-assets.com/files/2012/12/51/4/192/1922398/49a4cec24601ed4e_ryanreynolds.xxxlarge_2.jpg");
                    movie.addPerson(person);
                }

                listener.OnMovieSuccess(movie);
            }
        }, 1000);
    }

    public void getMovieForPerson(MoviePerson person,MoviesListener listener) {
        this.getLastMovies(listener);
    }

    public void getPersonById(int id, final PersonListener listener) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MoviePerson person = new MoviePerson();
                person.setId(69);
                person.setName("Jackie Michel");
                person.setRole("Réalisateur");
                person.setPicture("http://static1.purepeople.com/articles/9/13/20/59/@/1306777-leonardo-dicaprio-premiere-du-film-950x0-1.jpg");
                listener.onPersonSuccess(person);
            }
        }, 1000);
    }
}
