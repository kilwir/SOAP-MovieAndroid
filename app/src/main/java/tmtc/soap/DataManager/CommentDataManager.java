package tmtc.soap.DataManager;

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
    }

    public void getMovieComment(Movie movie,CommentsListener listener) {
        List<Comment> list  = new ArrayList<>();
        User user = new User("Kiwi");
        for(int i = 0; i < 5; i++) {
            list.add(new Comment(i,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean pretium libero a ligula tincidunt, porta malesuada mauris semper. Vivamus eu nisl nec augue egestas congue et id leo. Phasellus eget.",3.5,user));
        }
        listener.OnCommentsSuccess(list);
    }
}
