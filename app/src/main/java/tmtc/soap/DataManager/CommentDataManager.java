package tmtc.soap.DataManager;

import android.os.Handler;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import tmtc.soap.Listener.CommentsListener;
import tmtc.soap.Model.Comment;
import tmtc.soap.Model.Movie;
import tmtc.soap.Model.MoviePerson;
import tmtc.soap.Model.User;

/**
 * Bad Boys Team
 * Created by remyjallan on 17/03/2016.
 */
public class CommentDataManager {
    private static CommentDataManager ourInstance = new CommentDataManager();

    public static CommentDataManager getInstance() {
        return ourInstance;
    }

    private CommentDataManager() {
        Logger.init("CommentDataManager");
    }

    public void getMovieComment(final Movie movie, final CommentsListener listener) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<Comment> list = new ArrayList<>();
                User user = new User("Kiwi");
                for (int i = 0; i < 5; i++) {
                    Comment comment = new Comment(i, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean pretium libero a ligula tincidunt, porta malesuada mauris semper. Vivamus eu nisl nec augue egestas congue et id leo. Phasellus eget.", 3.5, user,movie);
                    comment.setCreateDate("2016/03/12");
                    list.add(comment);
                }
                if (movie.getId() % 2 == 0)
                    list.add(new Comment(69, "Super commentaire ma guele", 4, UserDataManager.getInstance().getCurrentUser(),movie));
                listener.OnCommentsSuccess(list);
            }
        }, 200);
    }

    public void getUserComment(final User user,final CommentsListener listener ) {
        Handler handler = new Handler();
        handler.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        Movie movie = new Movie(96,"Borat");
                        movie.setReleaseDate("2016/12/12");
                        movie.setPoster("http://fr.web.img6.acsta.net/r_1280_720/pictures/16/02/03/11/17/130929.jpg");
                        movie.setSynopsis("La suite des nouvelles aventures de Superman, confronté pour la première fois au chevalier noir de Gotham City, Batman.");
                        List<Comment> comments = new ArrayList<Comment>();
                        ArrayList<MoviePerson> persons = new ArrayList<MoviePerson>();

                        for(int i = 0; i < 5;i++) {
                            persons.add(new MoviePerson(i,"Rocky la poutre","Producteur"));
                        }

                        movie.setPersons(persons);

                        for(int i = 0; i< 8;i++) {
                            Comment comment = new Comment(i, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean pretium libero a ligula tincidunt, porta malesuada mauris semper. Vivamus eu nisl nec augue egestas congue et id leo. Phasellus eget.", 3.5, user, movie);
                            comment.setCreateDate("2016/03/12");
                            comments.add(comment);
                        }
                        listener.OnCommentsSuccess(comments);
                    }
                }
                , 1000);
    }

    public void saveComment(Movie movie,Comment comment) {
        Logger.d("Save comment !");
    }
}
