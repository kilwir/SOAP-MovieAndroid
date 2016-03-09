package tmtc.soap.View;

/**
 * Bad Boys Team
 * Created by remyjallan on 04/03/2016.
 */
public interface View {
    void showMessage(String message);
    void showProgress(String message);
    void hideProgress();
    void setupWindowAnimations();
}
