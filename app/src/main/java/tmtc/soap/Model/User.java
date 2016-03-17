package tmtc.soap.Model;

/**
 * Bad Boys Team
 * Created by remyjallan on 18/02/2016.
 */
public class User {
    private String mUsername;
    private String mPassword;
    private String mEmail;

    public User(String username) {
        mUsername = username;
    }

    public User(String username, String password) {
        mUsername = username;
        mPassword = password;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    @Override
    public String toString() {
        return "User("+mUsername+","+mPassword+","+mEmail+")";
    }
}
