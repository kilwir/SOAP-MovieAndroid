package tmtc.soap.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmtc.soap.Model.Movie;
import tmtc.soap.R;

/**
 * Bad Boys Team
 * Created by remyjallan on 09/03/2016.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapterViewHolder> {

    private Context mContext;
    private List<Movie> mMovies;

    public MoviesAdapter(Context context,List<Movie> movies) {
        this.mContext = context;
        this.mMovies = movies;
        Logger.init("MoviesAdapter");
    }

    @Override
    public MoviesAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie,parent,false);

        return new MoviesAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MoviesAdapterViewHolder holder, int position) {
        Movie movie = mMovies.get(position);

        Picasso.with(mContext)
                .load(movie.getPoster())
                .into(holder.mPoster);
        holder.mTitle.setText(movie.getName());

    }

    @Override
    public int getItemCount() {
        return this.mMovies.size();
    }
}

class MoviesAdapterViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.item_movie_poster)
    protected ImageView mPoster;
    @Bind(R.id.item_movie_title)
    protected TextView  mTitle;

    public MoviesAdapterViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}