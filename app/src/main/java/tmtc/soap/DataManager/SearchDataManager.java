package tmtc.soap.DataManager;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import tmtc.soap.Listener.SearchListener;
import tmtc.soap.Model.Movie;
import tmtc.soap.Model.MoviePerson;
import tmtc.soap.Model.SearchItem;

/**
 * Bad Boys Team
 * Created by remyjallan on 23/03/2016.
 */
public class SearchDataManager {
    private static SearchDataManager ourInstance = new SearchDataManager();

    public static SearchDataManager getInstance() {
        return ourInstance;
    }

    private SearchDataManager() {
    }

    public void search(String query, final SearchListener<List<SearchItem>> listener) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<SearchItem> list = new ArrayList<>();

                Movie movie = new Movie(169,"Borat");
                movie.setSynopsis("Borat, reporter kazakh, est envoyé aux Etats-Unis par la télévision de son pays pour y tourner un reportage sur le mode de vie de cette nation vénérée comme un modèle. Au cours de son périple, il rencontre de vraies personnes dans des situations authentiques, avec les conséquences les plus incroyables. Son comportement à contre-courant provoque les réactions les plus diverses, et révèle les préjugés et les dessous de la société américaine. Aucun sujet n'échappera à sa soif d'apprendre, même les plus extrêmes. Un vrai choc des cultures...");
                movie.setReleaseDate("15/11/2006");
                movie.setPoster("http://fr.web.img6.acsta.net/r_1280_720/medias/nmedia/18/36/29/57/18682308.jpg");
                for (int i = 0; i < 4; i++) {
                    movie.addPerson(new MoviePerson(134,"Sacha Barons Cohen","Acteur","http://fr.web.img5.acsta.net/r_1280_720/medias/nmedia/18/36/29/57/18682097.jpg"));
                }

                MoviePerson person= new MoviePerson(135,"Pamela Anderson","Acteur","http://fr.web.img1.acsta.net/r_640_600/b_1_d6d6d6/medias/nmedia/18/66/09/25/18910251.jpg");

                list.add(movie);
                list.add(person);
                list.add(movie);
                list.add(person);

                listener.OnSearchSuccess(list);
            }
        },500);
    }
}
