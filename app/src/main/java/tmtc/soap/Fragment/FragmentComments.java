package tmtc.soap.Fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.orhanobut.logger.Logger;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmtc.soap.Adapter.CommentsAdapter;
import tmtc.soap.DataManager.CommentDataManager;
import tmtc.soap.DataManager.UserDataManager;
import tmtc.soap.Listener.CommentsListener;
import tmtc.soap.Listener.ItemCommentListener;
import tmtc.soap.Model.Comment;
import tmtc.soap.Model.ErrorContainer;
import tmtc.soap.Model.Movie;
import tmtc.soap.R;
import tmtc.soap.View.Template.BaseAppCompatActivity;

/**
 * Bad Boys Team
 * Created by remyjallan on 17/03/2016.
 */
public class FragmentComments extends Fragment implements CommentsListener<List<Comment>>,View.OnClickListener, MaterialDialog.SingleButtonCallback, ItemCommentListener.IPosition {

    @Bind(R.id.recycler_comments)
    public RecyclerView RecyclerComments;
    @Bind(R.id.loader_comments)
    public ProgressBar LoaderComments;

    private Movie mMovie;
    private List<Comment> mComments;
    private Comment mPersonalComment;

    private ItemCommentListener.IComment mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comments,container,false);
        ButterKnife.bind(this, view);
        Logger.init("Comments Fragment");
        this.initRecyclerView();
        if(mMovie != null) {
            this.loadComments();
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void loadContent() {
        if(RecyclerComments != null && mComments != null) {
            boolean personalExsit = false;
            if(mPersonalComment != null)
                personalExsit = true;
            CommentsAdapter adapter = new CommentsAdapter(getActivity(),mComments,personalExsit);
            adapter.setButtonListener(this);
            adapter.setItemListener(this);
            RecyclerComments.setAdapter(adapter);
        }
    }

    private void initRecyclerView() {
        RecyclerComments.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        RecyclerComments.setItemAnimator(new DefaultItemAnimator());
        RecyclerComments.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).build());
        RecyclerComments.setLayoutManager(layoutManager);
    }

    public void setMovie(Movie movie) {
        mMovie = movie;
    }

    private void loadComments() {
        if(mComments == null) {
            showProgress();
            CommentDataManager.getInstance().getMovieComment(mMovie, this);
        } else {
            loadContent();
        }
    }

    public void setItemListener(ItemCommentListener.IComment listener) {
        mListener = listener;
    }

    public void showProgress() {
        LoaderComments.setVisibility(ProgressBar.VISIBLE);
    }

    public void hideProgress() {
        LoaderComments.setVisibility(ProgressBar.INVISIBLE);
    }

    @Override
    public void OnCommentsSuccess(List<Comment> comments) {
        hideProgress();
        this.mComments = comments;
        for (Comment comment :
                mComments) {
            if(comment.getUser() == UserDataManager.getInstance().getCurrentUser()) {
                mPersonalComment = comment;
            }
        }
        this.loadContent();
    }

    @Override
    public void OnCommentsError(ErrorContainer error) {
        hideProgress();
        this.mComments = new ArrayList<>();
        BaseAppCompatActivity activity = (BaseAppCompatActivity) getActivity();
        activity.showMessage(error.Message);
    }

    @Override
    public void onClick(View view) {
        MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                .title("Ajouter un commentaire")
                .customView(R.layout.dialog_comment, false)
                .positiveText("Poster")
                .negativeText("Annuler")
                .onPositive(this)
                .build();

        if(mPersonalComment != null) {
            EditText inputComment = (EditText) dialog.getView().findViewById(R.id.input_comment);
            RatingBar rating = (RatingBar) dialog.getView().findViewById(R.id.rating);
            inputComment.setText(mPersonalComment.getContent());
            rating.setRating((float) mPersonalComment.getRating());

            dialog.setTitle("Modifier mon commenaire");
        }

        dialog.show();
    }

    @Override
    public void onClick(@NonNull MaterialDialog dialog,@NonNull DialogAction which) {
        EditText inputComment = (EditText) dialog.getView().findViewById(R.id.input_comment);
        RatingBar rating = (RatingBar) dialog.getView().findViewById(R.id.rating);

        if(inputComment.getText().length() > 3) {
            if(mPersonalComment == null)
                mPersonalComment = new Comment(-1,inputComment.getText().toString(),rating.getRating(), UserDataManager.getInstance().getCurrentUser(),mMovie);
            else {
                mPersonalComment.setContent(inputComment.getText().toString());
                mPersonalComment.setRating(rating.getRating());
            }
            CommentDataManager.getInstance().saveComment(mMovie,mPersonalComment);
            Toast.makeText(getActivity(), "Votre commentaire a bien été enregistré", Toast.LENGTH_LONG).show();
            this.loadComments();
        } else {
            Toast.makeText(getActivity(),"Votre commentaire est trop court",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void ItemCommentClickListener(View view, int position) {
        if(mListener != null && mComments != null) {
            mListener.ItemCommentClickListener(view,mComments.get(position));
        }
    }
}
