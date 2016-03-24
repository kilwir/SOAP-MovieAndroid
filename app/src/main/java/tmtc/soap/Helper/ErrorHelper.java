package tmtc.soap.Helper;

import tmtc.soap.Model.ErrorContainer;

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
}
