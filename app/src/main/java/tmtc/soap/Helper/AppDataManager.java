package tmtc.soap.Helper;

/**
 * Bad Boys Team
 * Created by remyjallan on 18/02/2016.
 */
public class AppDataManager {
    private static AppDataManager ourInstance = new AppDataManager();

    public static AppDataManager getInstance() {
        return ourInstance;
    }

    private AppDataManager() {
    }
}
