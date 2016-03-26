package tmtc.soap.DataManager;

import com.loopj.android.http.AsyncHttpClient;

/**
 * Bad Boys Team
 * Created by remyjallan on 25/03/2016.
 */
public abstract class DataManager {
    private AsyncHttpClient mClient;

    public DataManager() {
        mClient = new AsyncHttpClient();
    }

    public AsyncHttpClient getClient() {
        mClient.addHeader("tmtc-token",AuthDataManager.getInstance().getToken());
        mClient.setConnectTimeout(100000);
        return mClient;
    }

    public AsyncHttpClient getClientWithoutHeader() {
        return mClient;
    }
}
