package tmtc.soap.View.Template;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.orhanobut.logger.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmtc.soap.R;

/**
 * Bad Boys Team
 * Created by remyjallan on 09/03/2016.
 */
public abstract class DrawerAppCompatActivity extends BaseAppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @Bind(R.id.drawer_main)
    public DrawerLayout mDrawer;
    @Bind(R.id.nav_drawer)
    public NavigationView mNavigationDrawer;

    protected void init() {
        initNavigationView();
    }

    protected void initNavigationView() {
        mNavigationDrawer.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        this.actionNavigationItemSelected(id);

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected abstract void actionNavigationItemSelected(int id);
}
