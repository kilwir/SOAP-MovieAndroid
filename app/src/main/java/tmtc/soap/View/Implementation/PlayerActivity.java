package tmtc.soap.View.Implementation;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import java.net.URI;
import java.net.URISyntaxException;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmtc.soap.R;
import tmtc.soap.View.Template.BaseAppCompatActivity;

/**
 * Bad Boys Team
 * Created by remyjallan on 27/03/2016.
 */
public class PlayerActivity extends BaseAppCompatActivity {

    @Bind(R.id.imageview)
    ImageView ImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        ButterKnife.bind(this);
        Picasso.with(this)
                .load("http://images.mentalfloss.com/sites/default/files/styles/insert_main_wide_image/public/istock_000049345960_small.jpg")
                .into(ImageView);
    }

    @Override
    public void setupWindowAnimations() {
        return;
    }
}
