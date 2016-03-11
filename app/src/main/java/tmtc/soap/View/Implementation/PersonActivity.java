package tmtc.soap.View.Implementation;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmtc.soap.Adapter.TinyMoviesAdapter;
import tmtc.soap.Model.Movie;
import tmtc.soap.Model.MoviePerson;
import tmtc.soap.Presenter.Implementation.PersonPresenterImpl;
import tmtc.soap.Presenter.PersonPresenter;
import tmtc.soap.R;
import tmtc.soap.View.PersonView;
import tmtc.soap.View.Template.BaseAppCompatActivity;
import tmtc.soap.View.Template.DrawerAppCompatActivity;

/**
 * Bad Boys Team
 * Created by remyjallan on 11/03/2016.
 */
public class PersonActivity extends DrawerAppCompatActivity implements PersonView {

    PersonPresenter mPresenter;

    @Bind(R.id.image_profile)
    ImageView ImageProfil;
    @Bind(R.id.text_name)
    TextView TextName;
    @Bind(R.id.recycler_movies)
    RecyclerView RecyclerMovies;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        ButterKnife.bind(this);

        mPresenter = new PersonPresenterImpl(this);
        init();
    }

    @Override
    protected void init() {
        super.init();
        initRecyclerView();
        mPresenter.init(getIntent());
    }

    @Override
    public void setupWindowAnimations() {

    }

    @Override
    protected void actionNavigationItemSelected(int id) {

    }

    @Override
    public void init(MoviePerson person) {
        TextName.setText(person.getName());
        Picasso.with(this).load(person.getPicture()).into(ImageProfil);
    }

    @Override
    public void showMovies(List<Movie> movies) {
        TinyMoviesAdapter adapter = new TinyMoviesAdapter(this,movies);
        RecyclerMovies.setAdapter(adapter);
    }

    private void initRecyclerView() {
        RecyclerMovies.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerMovies.setItemAnimator(new DefaultItemAnimator());
        RecyclerMovies.setLayoutManager(layoutManager);
    }
}
