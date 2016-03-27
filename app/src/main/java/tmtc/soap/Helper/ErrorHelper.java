package tmtc.soap.Helper;

import android.content.Context;

import tmtc.soap.Model.ErrorContainer;
import tmtc.soap.R;

/**
 * Bad Boys Team
 * Created by remyjallan on 06/03/2016.
 */
public class ErrorHelper {
    public static ErrorContainer ContextIsEmpty() {
        return new ErrorContainer(691,"Une Erreur à été rencontré au sein de l'application");
    }

    public static ErrorContainer ErrorParsingJson() {
        return new ErrorContainer(692,"Une erreur à été rencontré avec le Serveur");
    }

    public static ErrorContainer WrongCredentials() {
        return new ErrorContainer(693,"Vos identifiants sont incorrects");
    }

    public static ErrorContainer WrongStatusCode() {
        return new ErrorContainer(694,"Une erreur à été rencontré avec le Serveur");
    }
}
