package tmtc.soap.Presenter.Implementation;

import android.content.Context;
import android.content.Intent;

import com.orhanobut.logger.Logger;
import com.vinaygaba.creditcardview.CreditCardView;

import org.parceler.Parcels;

import tmtc.soap.DataManager.AuthDataManager;
import tmtc.soap.DataManager.MovieDataManager;
import tmtc.soap.Listener.MovieListener;
import tmtc.soap.Model.ErrorContainer;
import tmtc.soap.Model.Movie;
import tmtc.soap.Presenter.BoughtPresenter;
import tmtc.soap.R;
import tmtc.soap.View.BoughtView;

/**
 * Bad Boys Team
 * Created by remyjallan on 26/03/2016.
 */
public class BoughtPresenterImpl implements BoughtPresenter, MovieListener<Boolean> {

    private BoughtView mView;
    private Context mContext;
    private CreditCardView mCard;
    private Movie mMovie;

    public BoughtPresenterImpl(BoughtView view, Context context) {
        mView = view;
        mContext = context;
    }

    @Override
    public void init(Intent intent) {
        mMovie = Parcels.unwrap(intent.getParcelableExtra("movie"));
        if(mMovie == null) {
            Logger.d("MOVIE NULL");
        } else {
            mView.setMovieName(mMovie.getName());
        }
    }

    @Override
    public void buy() {
        mView.showProgress(mContext.getString(R.string.treatment___));
        MovieDataManager.getInstance().buyMovie(mMovie, AuthDataManager.getInstance().getCurrentUser(),this);
    }

    @Override
    public void selectCard(CreditCardView card) {
        mCard = card;
        mView.showConfirmBought(mMovie, mCard);
    }

    @Override
    public void OnMovieSuccess(Boolean movie) {
        mView.hideProgress();
        if(movie) {
            mView.backToMovie(mContext.getString(R.string.locate_has_effect),true);
        } else {
            mView.backToMovie(mContext.getString(R.string.locate_has_no_effect),false);
        }
    }

    @Override
    public void OnMovieError(ErrorContainer error) {
        mView.hideProgress();
        mView.backToMovie(error.Message,false);
    }
}
