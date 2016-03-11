package tmtc.soap.Adapter;


import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import tmtc.soap.Fragment.FragmentMovieInformation;
import tmtc.soap.Fragment.FragmentMoviePersons;
import tmtc.soap.Model.Movie;

/**
 * Bad Boys Team
 * Created by remyjallan on 10/03/2016.
 */
public class MovieContentPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEM = 2;
    private Movie mMovie;

    public MovieContentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public MovieContentPagerAdapter(FragmentManager fm, Movie movie) {
        super(fm);
        this.mMovie = movie;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 :
                FragmentMovieInformation fragment = new FragmentMovieInformation();
                fragment.setMovie(mMovie);
                return fragment;
            case 1 :
                FragmentMoviePersons fragment2 = new FragmentMoviePersons();
                fragment2.setMovie(mMovie);
                return fragment2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEM;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Title " + position;
    }
}
