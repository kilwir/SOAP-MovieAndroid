package tmtc.soap.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.vinaygaba.creditcardview.CreditCardView;

import butterknife.ButterKnife;
import butterknife.OnClick;
import tmtc.soap.R;

/**
 * Bad Boys Team
 * Created by remyjallan on 27/03/2016.
 */
public class FragmentCreditCard extends Fragment{

    private View.OnClickListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_creditcard,container,false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.credit_card_rj,R.id.credit_card_am})
    public void cardClick(View view){
        if(mListener != null) {
            mListener.onClick(view);
        }
    }

    public void setOnClickListener(View.OnClickListener listener) {
        mListener = listener;
    }
}
