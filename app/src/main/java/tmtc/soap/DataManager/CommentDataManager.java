package tmtc.soap.DataManager;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import tmtc.soap.Listener.CommentsListener;
import tmtc.soap.Model.Comment;
import tmtc.soap.Model.Movie;
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

    public void getMovieComment(Movie movie,CommentsListener listener) {
        List<Comment> list  = new ArrayList<>();
        User user = new User("Kiwi");
        for(int i = 0; i < 5; i++) {
            Comment comment = new Comment(i,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean pretium libero a ligula tincidunt, porta malesuada mauris semper. Vivamus eu nisl nec augue egestas congue et id leo. Phasellus eget.",3.5,user);
            comment.setCreateDate("2016/03/12");
            list.add(comment);
        }
        if(movie.getId() % 2 == 0)
            list.add( new Comment(69,"Super commentaire ma guele",4,UserDataManager.getInstance().getCurrentUser()) );
        listener.OnCommentsSuccess(list);
    }

    public void saveComment(Movie movie,Comment comment) {
        Logger.d("Save comment !");
    }
}
