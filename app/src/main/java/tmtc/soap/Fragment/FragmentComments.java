package tmtc.soap.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmtc.soap.Adapter.CommentsAdapter;
import tmtc.soap.DataManager.CommentDataManager;
import tmtc.soap.Listener.CommentsListener;
import tmtc.soap.Model.Comment;
import tmtc.soap.Model.ErrorContainer;
import tmtc.soap.Model.Movie;
import tmtc.soap.R;
import tmtc.soap.View.Template.BaseAppCompatActivity;

/**
 * Bad Boys Team
 * Created by remyjallan on 17/03/2016.
 */
public class FragmentComments extends Fragment implements CommentsListener {

    @Bind(R.id.recycler_comments)
    public RecyclerView RecyclerComments;

    private List<Comment> mComments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comments,container,false);
        ButterKnife.bind(this, view);
        Logger.init("Comments Fragment");
        this.initRecyclerView();
        this.loadContent();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void loadContent() {
        if(RecyclerComments != null && mComments != null) {
            CommentsAdapter adapter = new CommentsAdapter(getActivity(),mComments);
            RecyclerComments.setAdapter(adapter);
        }
    }

    private void initRecyclerView() {
        RecyclerComments.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        RecyclerComments.setItemAnimator(new DefaultItemAnimator());
        RecyclerComments.setLayoutManager(layoutManager);
    }

    public void loadComments(Movie movie) {
        CommentDataManager.getInstance().getMovieComment(movie,this);
    }

    @Override
    public void OnCommentsSuccess(List<Comment> comments) {
        this.mComments = comments;
    }

    @Override
    public void OnCommentsError(ErrorContainer error) {
        this.mComments = new ArrayList<>();
        BaseAppCompatActivity activity = (BaseAppCompatActivity) getActivity();
        activity.showMessage(error.Message);
    }
}
