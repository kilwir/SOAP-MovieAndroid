package tmtc.soap.Helper;

import tmtc.soap.Model.ErrorContainer;

/**
 * Bad Boys Team
 * Created by remyjallan on 06/03/2016.
 */
public class ErrorHelper {
    public static ErrorContainer ContextIsEmpty() {
        return new ErrorContainer(1,"The context is empty");
    }
}
