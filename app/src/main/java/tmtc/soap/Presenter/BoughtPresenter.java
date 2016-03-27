package tmtc.soap.Presenter;

import android.content.Intent;

import com.vinaygaba.creditcardview.CreditCardView;

/**
 * Bad Boys Team
 * Created by remyjallan on 26/03/2016.
 */
public interface BoughtPresenter {
    void init(Intent intent);
    void buy();
    void selectCard(CreditCardView card);
}
