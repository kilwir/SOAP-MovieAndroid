package tmtc.soap.View.Implementation;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.orhanobut.logger.Logger;
import com.vinaygaba.creditcardview.CreditCardView;

import butterknife.Bind;
import butterknife.OnClick;
import tmtc.soap.Fragment.FragmentCreditCard;
import tmtc.soap.Model.Movie;
import tmtc.soap.Presenter.BoughtPresenter;
import tmtc.soap.Presenter.Implementation.BoughtPresenterImpl;
import tmtc.soap.R;
import tmtc.soap.View.BoughtView;
import tmtc.soap.View.Template.DrawerAppCompatActivity;

/**
 * Bad Boys Team
 * Created by remyjallan on 26/03/2016.
 */
public class BoughtActivity extends DrawerAppCompatActivity implements BoughtView, View.OnClickListener {

    @Bind(R.id.text_adver)
    public TextView TextAdvert;

    private BoughtPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bought);
        this.init();
    }

    @Override
    protected void init() {
        super.init();
        mPresenter = new BoughtPresenterImpl(this);
        mPresenter.init(getIntent());
        this.setCreditCardFragment();
    }

    private void setCreditCardFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentCreditCard creditCard = new FragmentCreditCard();
        creditCard.setOnClickListener(this);
        fragmentTransaction.add(R.id.fragment_container, creditCard, "creditCard");
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View view) {
        mPresenter.selectCard((CreditCardView) view);
    }

    @Override
    public void setMovieName(String name) {
        TextAdvert.setText("Location de " + name + " pour 1000€");
    }

    @Override
    public void showConfirmBought(Movie movie, CreditCardView card) {
        new MaterialDialog.Builder(this)
                .title("Confirmer la location")
                .content("Confirmer la location de " + movie.getName() + " pour 1000€ avec la carte " + card.getCardName() + " - " + card.getCardNumber())
                .positiveText("Confirmer")
                .negativeText("Annuler")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        mPresenter.buy();
                    }
                }).show();
    }

    @Override
    public void backToMovie(String message, boolean bought) {
        Intent intent = new Intent();
        intent.putExtra("message",message);
        intent.putExtra("bought",bought);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }
}
