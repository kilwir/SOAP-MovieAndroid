package tmtc.soap.Presenter.Implementation;

import android.content.Intent;

import org.parceler.Parcels;

import java.util.List;

import tmtc.soap.DataManager.CommentDataManager;
import tmtc.soap.Listener.CommentsListener;
import tmtc.soap.Model.Comment;
import tmtc.soap.Model.ErrorContainer;
import tmtc.soap.Model.User;
import tmtc.soap.Presenter.UserPresenter;
import tmtc.soap.View.UserView;

/**
 * Bad Boys Team
 * Created by remyjallan on 19/03/2016.
 */
public class UserPresenterImpl implements UserPresenter, CommentsListener {

    private UserView mView;
    private User mUser;

    public UserPresenterImpl(UserView view) {
        mView = view;
    }

    @Override
    public void init(Intent intent) {
        if(intent  == null) {
            mView.navigateToMain();
        } else {
            mUser = Parcels.unwrap(intent.getParcelableExtra("user"));
            if(mUser == null) {
                mView.navigateToMain();
            }
            mView.showUser(mUser);
            this.loadComments();
        }
    }

    @Override
    public void loadComments() {
        mView.showProgress(null);
        CommentDataManager.getInstance().getUserComment(mUser,this);
    }

    @Override
    public void OnCommentsSuccess(List<Comment> comments) {
        mView.hideProgress();
        mView.showComments(comments);
    }

    @Override
    public void OnCommentsError(ErrorContainer error) {
        mView.hideProgress();
        mView.showMessage(error.Message);
    }
}
