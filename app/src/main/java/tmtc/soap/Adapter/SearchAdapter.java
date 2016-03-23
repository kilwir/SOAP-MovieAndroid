package tmtc.soap.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmtc.soap.CustomView.TopCropImageView;
import tmtc.soap.Model.Movie;
import tmtc.soap.Model.MoviePerson;
import tmtc.soap.Model.SearchItem;
import tmtc.soap.R;

/**
 * Bad Boys Team
 * Created by remyjallan on 23/03/2016.
 */
public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<SearchItem> mSearchItems;

    public SearchAdapter(Context context, List<SearchItem> searchItems) {
        mContext = context;
        mSearchItems = searchItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if(viewType == Movie.ID)
        {
            View view = inflater.inflate(R.layout.item_search_movie,parent,false);
            viewHolder = new SearchAdapterMovieViewHolder(view);
        } else if( viewType == MoviePerson.ID)
        {
            View view = inflater.inflate(R.layout.item_search_person,parent,false);
            viewHolder = new SearchAdapterPersonViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(mSearchItems.get(position).getIdType() == MoviePerson.ID) {
            this.onBindPersonViewHolder((SearchAdapterPersonViewHolder)holder,(MoviePerson) mSearchItems.get(position));
        } else if(mSearchItems.get(position).getIdType() == Movie.ID) {
            this.onBindMovieViewHolder((SearchAdapterMovieViewHolder)holder,(Movie) mSearchItems.get(position));
        }
    }

    private void onBindPersonViewHolder(SearchAdapterPersonViewHolder holder,MoviePerson person) {
        holder.TextName.setText(person.getName());
        Picasso.with(mContext)
                .load(person.getPicture())
                .into(holder.ImagePicture);
    }

    private void onBindMovieViewHolder(SearchAdapterMovieViewHolder holder,Movie movie) {
        holder.TextTitle.setText(movie.getName());
        Picasso.with(mContext)
                .load(movie.getPoster())
                .into(holder.ImageCover);
    }

    @Override
    public int getItemViewType(int position) {
        return mSearchItems.get(position).getIdType();
    }

    @Override
    public int getItemCount() {
        return mSearchItems.size();
    }
}

class SearchAdapterMovieViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.image_cover)
    TopCropImageView ImageCover;
    @Bind(R.id.text_title)
    TextView TextTitle;

    public SearchAdapterMovieViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}

class SearchAdapterPersonViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.image_picture)
    TopCropImageView ImagePicture;
    @Bind(R.id.text_name)
    TextView TextName;

    public SearchAdapterPersonViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}