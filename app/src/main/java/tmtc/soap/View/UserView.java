package tmtc.soap.View;

import java.util.List;

import tmtc.soap.Model.Comment;
import tmtc.soap.Model.User;

/**
 * Bad Boys Team
 * Created by remyjallan on 19/03/2016.
 */
public interface UserView extends BaseView {
    void navigateToMain();
    void showUser(User user);
    void showComments(List<Comment> comments);
}
