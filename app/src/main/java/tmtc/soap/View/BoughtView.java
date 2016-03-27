package tmtc.soap.View;

import com.vinaygaba.creditcardview.CreditCardView;

import tmtc.soap.Model.Movie;

/**
 * Bad Boys Team
 * Created by remyjallan on 26/03/2016.
 */
public interface BoughtView extends BaseView {
    void setMovieName(String name);
    void showConfirmBought(Movie movie,CreditCardView card);
    void backToMovie(String message, boolean bought);
}
