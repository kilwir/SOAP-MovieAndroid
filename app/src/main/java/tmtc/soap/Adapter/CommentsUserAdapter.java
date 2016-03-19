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
import tmtc.soap.Model.Comment;
import tmtc.soap.R;

/**
 * Bad Boys Team
 * Created by remyjallan on 19/03/2016.
 */
public class CommentsUserAdapter extends RecyclerView.Adapter<CommentsUserAdapterViewHolder> {

    private Context mContext;
    private List<Comment> mComments;

    public CommentsUserAdapter(Context context,List<Comment> comments) {
        mContext = context;
        mComments = comments;
    }

    @Override
    public CommentsUserAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comment_user,parent,false);

        return new CommentsUserAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CommentsUserAdapterViewHolder holder, int position) {
        Comment comment = mComments.get(position);
        holder.TextContent.setText(comment.getContent());
        Picasso.with(mContext)
                .load(comment.getMovie().getPoster())
                .into(holder.ImageCover);
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }
}

class CommentsUserAdapterViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.text_content)
    protected TextView TextContent;
    @Bind(R.id.image_cover)
    protected ImageView ImageCover;

    public CommentsUserAdapterViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
