package tmtc.soap.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmtc.soap.Listener.ItemCommentListener;
import tmtc.soap.Model.Comment;
import tmtc.soap.R;

/**
 * Bad Boys Team
 * Created by remyjallan on 17/03/2016.
 */
public class CommentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private Context mContext;
    private List<Comment> mComments;
    private View.OnClickListener mClickListener;
    private boolean mPersonalCommentExist;
    private ItemCommentListener.IPosition mListener;


    public CommentsAdapter(Context context,List<Comment> comments,boolean personalCommentExist) {
        this.mComments = comments;
        this.mContext = context;
        this.mPersonalCommentExist = personalCommentExist;
    }

    public void setItemListener(ItemCommentListener.IPosition listener) {
        mListener = listener;
    }

    public void setButtonListener(View.OnClickListener clickListener) {
        mClickListener = clickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_HEADER) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_comment_header,parent,false);

            return new CommentsAdapterHeaderViewHolder(itemView);
        } else if( viewType == TYPE_ITEM) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_comment_item,parent,false);

            return new CommentsAdapterItemViewHolder(itemView);
        }
        throw new RuntimeException("Error type item comments");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof CommentsAdapterItemViewHolder) {
            Comment comment = getItem(position);
            CommentsAdapterItemViewHolder itemHolder = (CommentsAdapterItemViewHolder) holder;
            itemHolder.mName.setText(comment.getUser().getUsername());
            itemHolder.mRating.setText(comment.getRating() + "/5");
            itemHolder.mContent.setText(comment.getContent());
            itemHolder.setListener(mListener);
            Picasso.with(mContext)
                    .load("http://smartyvet.com/site/wp-content/uploads/2014/05/red-panda-5.jpg")
                    .into(itemHolder.mPicture);
        } else if(holder instanceof CommentsAdapterHeaderViewHolder) {
            CommentsAdapterHeaderViewHolder headerHolder = (CommentsAdapterHeaderViewHolder) holder;
            if(mClickListener != null)
                headerHolder.mAddComment.setOnClickListener(mClickListener);

            if(mPersonalCommentExist) {
                headerHolder.mAddComment.setText("Modifier mon commentaire");
            }
        }
    }

    @Override
    public int getItemCount() {
        return this.mComments.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    private Comment getItem(int position) {
        return mComments.get(position -1);
    }
}

class CommentsAdapterItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @Bind(R.id.image_profile)
    protected ImageView mPicture;
    @Bind(R.id.text_name)
    protected TextView mName;
    @Bind(R.id.text_rating)
    protected TextView mRating;
    @Bind(R.id.text_content)
    protected TextView mContent;

    protected ItemCommentListener.IPosition mListener;

    public void setListener(ItemCommentListener.IPosition listener) {
        mListener = listener;
    }

    public CommentsAdapterItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(mListener != null)
            mListener.ItemCommentClickListener(view,getAdapterPosition()-1);
    }
}

class CommentsAdapterHeaderViewHolder extends RecyclerView.ViewHolder{

    @Bind(R.id.button_add_comment)
    protected Button mAddComment;


    public CommentsAdapterHeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
