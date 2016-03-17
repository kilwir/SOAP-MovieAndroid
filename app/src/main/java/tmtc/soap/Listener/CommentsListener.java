package tmtc.soap.Listener;

import java.util.List;

import tmtc.soap.Model.Comment;
import tmtc.soap.Model.ErrorContainer;

/**
 * Bad Boys Team
 * Created by remyjallan on 17/03/2016.
 */
public interface CommentsListener {
    void OnCommentsSuccess(List<Comment> comments);
    void OnCommentsError(ErrorContainer error);
}
