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
import tmtc.soap.Model.Movie;
import tmtc.soap.R;

/**
 * Bad Boys Team
 * Created by remyjallan on 11/03/2016.
 */
public class TinyMoviesAdapter extends RecyclerView.Adapter<TinyMoviesAdapterViewHolder> {

    private Context mContext;
    private List<Movie> mMovies;

    public TinyMoviesAdapter(Context context,List<Movie> movies) {
        this.mContext = context;
        this.mMovies = movies;
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
        holder.mName.setText(movie.getName());
        Picasso.with(mContext).load(movie.getPoster()).into(holder.mPoster);

        holder.mRelease.setText(movie.getReleaseDate());
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }
}

class TinyMoviesAdapterViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.image_poster)
    protected ImageView mPoster;
    @Bind(R.id.text_name)
    protected TextView mName;
    @Bind(R.id.text_release)
    protected TextView mRelease;

    public TinyMoviesAdapterViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
