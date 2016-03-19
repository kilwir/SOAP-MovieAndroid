package tmtc.soap.Listener;

import android.view.View;

import tmtc.soap.Model.Comment;

/**
 * Bad Boys Team
 * Created by remyjallan on 19/03/2016.
 */
public abstract class ItemCommentListener {
    public interface IPosition {
        void ItemCommentCLickListener(View view, int position);
    }
    public interface IComment {
        void ItemCommentClickListener(View view, Comment comment);
    }

}
