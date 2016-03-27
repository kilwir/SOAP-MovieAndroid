package tmtc.soap.Adapter;


import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import tmtc.soap.Fragment.FragmentComments;
import tmtc.soap.Fragment.FragmentMovieInformation;
import tmtc.soap.Fragment.FragmentMoviePersons;
import tmtc.soap.Listener.ItemCommentListener;
import tmtc.soap.Listener.ItemPersonListener;
import tmtc.soap.Listener.OnClickBoughtListener;
import tmtc.soap.Model.Movie;

/**
 * Bad Boys Team
 * Created by remyjallan on 10/03/2016.
 */
public class MovieContentPagerAdapter extends FragmentPagerAdapter {

    private SparseArray<Fragment> mFragments = new SparseArray<>();
    private Movie mMovie;
    private ItemPersonListener.IPerson mListenerPerson;
    private ItemCommentListener.IComment mListenerComment;
    private OnClickBoughtListener mListenerRent;

    public MovieContentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public MovieContentPagerAdapter(FragmentManager fm, Movie movie,ItemPersonListener.IPerson listenerPerson,ItemCommentListener.IComment listenerComment,OnClickBoughtListener listenerRent) {
        super(fm);
        this.mMovie = movie;
        this.mListenerPerson = listenerPerson;
        this.mListenerComment = listenerComment;
        this.mListenerRent = listenerRent;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 :
                FragmentMovieInformation fragmentInformation = new FragmentMovieInformation();
                fragmentInformation.setMovie(mMovie);
                fragmentInformation.setClickListener(mListenerRent);
                return fragmentInformation;
            case 1 :
                FragmentMoviePersons fragmentPersons = new FragmentMoviePersons();
                fragmentPersons.setMovie(mMovie);
                fragmentPersons.setItemPersonListener(mListenerPerson);
                return fragmentPersons;
            case 2:
                FragmentComments fragmentComments = new FragmentComments();
                fragmentComments.setMovie(mMovie);
                fragmentComments.setItemListener(mListenerComment);
                return fragmentComments;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Information";
            case 1:
                return "Casting";
            case 2:
                return "Comments";
            default:
                return "";
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container,position);
        mFragments.put(position,fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        mFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getFragment(int position) {
        return mFragments.get(position);
    }
}
