package tmtc.soap.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import tmtc.soap.Model.MoviePerson;
import tmtc.soap.R;

/**
 * Bad Boys Team
 * Created by remyjallan on 10/03/2016.
 */
public class MoviePersonAdapter extends RecyclerView.Adapter<MoviePersonAdapterHolder> {

    private Context mContext;
    private List<MoviePerson> mPersons;

    public MoviePersonAdapter(Context context, List<MoviePerson> persons) {
        this.mContext = context;
        this.mPersons = persons;
    }

    @Override
    public MoviePersonAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie_person,parent,false);

        return new MoviePersonAdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MoviePersonAdapterHolder holder, int position) {
        MoviePerson person = mPersons.get(position);
        holder.mTextName.setText(person.getName());
        holder.mTextRole.setText(person.getRole());
        Picasso.with(mContext).load(person.getPicture()).into(holder.mImageProfil);
    }

    @Override
    public int getItemCount() {
        return mPersons.size();
    }
}

class MoviePersonAdapterHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.text_name)
    protected TextView mTextName;
    @Bind(R.id.image_profile)
    protected CircleImageView mImageProfil;
    @Bind(R.id.text_role)
    protected TextView mTextRole;

    public MoviePersonAdapterHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
