package tmtc.soap.View.Template;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmtc.soap.DataManager.AuthDataManager;
import tmtc.soap.R;
import tmtc.soap.View.Implementation.LoginActivity;
import tmtc.soap.View.Implementation.MainActivity;

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
        AuthDataManager.getInstance().init(this);
        ButterKnife.bind(this);
        initNavigationView();
    }

    protected void initNavigationView() {
        mNavigationDrawer.setNavigationItemSelectedListener(this);
    }

    @Override
    public void setupWindowAnimations() {
        return;
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

    protected void actionNavigationItemSelected(int id) {
        switch (id) {
            case R.id.logout:
                AuthDataManager.getInstance().logout();
                Intent intentLogin = new Intent(this, LoginActivity.class);
                intentLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intentLogin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentLogin);
                finish();
                break;
            case R.id.last_exit:
                Intent intentMain = new Intent(this, MainActivity.class);
                intentMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intentMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentMain);
                finish();
                break;
        }
    }
}
