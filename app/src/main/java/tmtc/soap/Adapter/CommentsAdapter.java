package tmtc.soap.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmtc.soap.Listener.ItemMovieListener;
import tmtc.soap.Model.Comment;
import tmtc.soap.R;

/**
 * Bad Boys Team
 * Created by remyjallan on 17/03/2016.
 */
public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapterViewHolder>{

    private Context mContext;
    private List<Comment> mComments;

    public CommentsAdapter(Context context,List<Comment> comments) {
        this.mComments = comments;
        this.mContext = context;
    }

    @Override
    public CommentsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comment,parent,false);

        return new CommentsAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CommentsAdapterViewHolder holder, int position) {
        Comment comment = mComments.get(position);
        holder.mName.setText(comment.getUser().getUsername());
        holder.mRating.setText(comment.getRating() + "/5");
        holder.mContent.setText(comment.getContent());
        Picasso.with(mContext)
                .load("http://smartyvet.com/site/wp-content/uploads/2014/05/red-panda-5.jpg")
                .into(holder.mPicture);
    }

    @Override
    public int getItemCount() {
        return this.mComments.size();
    }
}

class CommentsAdapterViewHolder extends RecyclerView.ViewHolder{

    @Bind(R.id.image_profile)
    protected ImageView mPicture;
    @Bind(R.id.text_name)
    protected TextView mName;
    @Bind(R.id.text_rating)
    protected TextView mRating;
    @Bind(R.id.text_content)
    protected TextView mContent;

    public CommentsAdapterViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
