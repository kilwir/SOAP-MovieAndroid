package tmtc.soap.Adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmtc.soap.Listener.ItemMovieListener;
import tmtc.soap.Model.Movie;
import tmtc.soap.R;

/**
 * Bad Boys Team
 * Created by remyjallan on 11/03/2016.
 */
public class TinyMoviesAdapter extends RecyclerView.Adapter<TinyMoviesAdapterViewHolder> implements ItemMovieListener.IPosition {

    private Context mContext;
    private List<Movie> mMovies;
    private ItemMovieListener.IMovie mListener;

    public TinyMoviesAdapter(Context context,List<Movie> movies,ItemMovieListener.IMovie listener) {
        this.mContext = context;
        this.mMovies = movies;
        this.mListener = listener;
    }

    @Override
    public TinyMoviesAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tiny_movie,parent,false);
        Logger.init("TinyMoviesAdapter");
        return new TinyMoviesAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TinyMoviesAdapterViewHolder holder, int position) {
        Movie movie = mMovies.get(position);
        holder.setItemMovieListener(this);
        holder.mName.setText(movie.getName());
        Picasso.with(mContext).load(movie.getPoster()).into(holder.mPoster);
        holder.mRelease.setText(movie.getReleaseDate());
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    @Override
    public void ItemMovieListenerOnClick(View view, int position) {
        mListener.ItemMovieListenerOnClick(view,mMovies.get(position));
    }
}

class TinyMoviesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @Bind(R.id.image_poster)
    protected ImageView mPoster;
    @Bind(R.id.text_name)
    protected TextView mName;
    @Bind(R.id.text_release)
    protected TextView mRelease;

    private ItemMovieListener.IPosition mListener;

    public TinyMoviesAdapterViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    public void setItemMovieListener(ItemMovieListener.IPosition listener) {
        mListener = listener;
    }

    @Override
    public void onClick(View view) {
        if(mListener != null) {
            mListener.ItemMovieListenerOnClick(view,getAdapterPosition());
        }
    }
}
