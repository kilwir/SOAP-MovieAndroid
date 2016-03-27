package tmtc.soap.Presenter.Implementation;

import android.content.Intent;

import com.orhanobut.logger.Logger;

import org.parceler.Parcels;

import java.util.List;
import java.util.Objects;

import tmtc.soap.DataManager.AuthDataManager;
import tmtc.soap.DataManager.CommentDataManager;
import tmtc.soap.DataManager.UserDataManager;
import tmtc.soap.Listener.CommentsListener;
import tmtc.soap.Listener.UserListener;
import tmtc.soap.Model.Comment;
import tmtc.soap.Model.ErrorContainer;
import tmtc.soap.Model.User;
import tmtc.soap.Presenter.UserPresenter;
import tmtc.soap.View.UserView;

/**
 * Bad Boys Team
 * Created by remyjallan on 19/03/2016.
 */
public class UserPresenterImpl implements UserPresenter, CommentsListener<List<Comment>>,UserListener<Boolean> {

    private UserView mView;
    private User mUser;
    private boolean mIsFriend;

    public UserPresenterImpl(UserView view) {
        mView = view;
        Logger.init("UserPresenterImpl");
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
            this.checkStateFriend();
            this.loadComments();
        }
    }

    @Override
    public void loadComments() {
        mView.showProgress(null);
        CommentDataManager.getInstance().getUserComment(mUser,this);
    }

    @Override
    public void checkStateFriend() {
        if(Objects.equals(mUser.getId(), AuthDataManager.getInstance().getCurrentUser().getId())) {
            mView.hideFab();
        } else {
            UserDataManager.getInstance().isMyFriend(mUser,this);
        }
    }

    @Override
    public void performFabFriend() {
        if(mIsFriend) {
            UserDataManager.getInstance().deleteFriend(mUser, new UserListener<Boolean>() {
                @Override
                public void OnUserSuccess(Boolean response) {
                    mIsFriend = false;
                    mView.fabNotFriend();
                }

                @Override
                public void OnUserError(ErrorContainer error) {
                    mView.showMessage(error.Message);
                }
            });
        } else {
            UserDataManager.getInstance().addFriend(mUser, new UserListener<Boolean>() {
                @Override
                public void OnUserSuccess(Boolean response) {
                    if(response) {
                        mIsFriend = true;
                        mView.fabAlreadyFriend();
                    }
                }

                @Override
                public void OnUserError(ErrorContainer error) {
                    mView.showMessage(error.Message);
                }
            });
        }
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

    @Override
    public void OnUserSuccess(Boolean response) {
        mIsFriend = response;
        if(response) {
            mView.fabAlreadyFriend();
        } else {
            mView.fabNotFriend();
        }
    }

    @Override
    public void OnUserError(ErrorContainer error) {
        mView.showMessage(error.Message);
    }
}
