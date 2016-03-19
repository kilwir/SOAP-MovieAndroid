package tmtc.soap.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmtc.soap.Listener.CommentsListener;
import tmtc.soap.Listener.ItemCommentListener;
import tmtc.soap.Model.Comment;
import tmtc.soap.R;

/**
 * Bad Boys Team
 * Created by remyjallan on 19/03/2016.
 */
public class CommentsUserAdapter extends RecyclerView.Adapter<CommentsUserAdapterViewHolder> implements ItemCommentListener.IPosition {

    private Context mContext;
    private List<Comment> mComments;
    private ItemCommentListener.IComment mListener;

    public CommentsUserAdapter(Context context,List<Comment> comments) {
        mContext = context;
        mComments = comments;
    }

    public void setListener(ItemCommentListener.IComment listener) {
        mListener = listener;
    }

    @Override
    public CommentsUserAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comment_user, parent, false);

        return new CommentsUserAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CommentsUserAdapterViewHolder holder, int position) {
        Comment comment = mComments.get(position);
        holder.setClickListener(this);
        holder.TextContent.setText(comment.getContent());
        Picasso.with(mContext)
                .load(comment.getMovie().getPoster())
                .into(holder.ImageCover);
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }

    @Override
    public void ItemCommentClickListener(View view, int position) {
        if(mListener != null) {
            mListener.ItemCommentClickListener(view,mComments.get(position));
        }
    }
}

class CommentsUserAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @Bind(R.id.text_content)
    protected TextView TextContent;
    @Bind(R.id.image_cover)
    protected ImageView ImageCover;

    private ItemCommentListener.IPosition mClickListener;

    public void setClickListener(ItemCommentListener.IPosition listener) {
        mClickListener = listener;
    }

    public CommentsUserAdapterViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(mClickListener != null) {
            mClickListener.ItemCommentClickListener(view,getAdapterPosition());
        }
    }
}
