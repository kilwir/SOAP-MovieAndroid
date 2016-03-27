package tmtc.soap.View.Template;

import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import tmtc.soap.R;

/**
 * Bad Boys Team
 * Created by remyjallan on 27/03/2016.
 */
public abstract class ToolbarDrawerActivity extends DrawerAppCompatActivity {
    @Bind(R.id.toolbar)
    public Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        super.init();
        this.initializeToolbar();
        this.initializeDrawer();
    }

    protected void initializeDrawer() {

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar,
                R.string.open_nagivation_drawer, R.string.close_navigation_drawer);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    protected void initializeToolbar() {
        mToolbar.setTitle("Mes films");
        setSupportActionBar(mToolbar);
    }
}
