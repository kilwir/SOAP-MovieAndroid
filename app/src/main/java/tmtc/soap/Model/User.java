package tmtc.soap.Model;

import android.content.Intent;

import org.parceler.Parcel;

/**
 * Bad Boys Team
 * Created by remyjallan on 18/02/2016.
 */
@Parcel
public class User {
    private Integer mId;
    private String mUsername;
    private String mPassword;
    private String mEmail;

    public User(){

    }

    public User(String username) {
        mUsername = username;
    }

    public User(String username, String password) {
        mUsername = username;
        mPassword = password;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
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

    @Override
    public boolean equals(Object o) {
        if( o instanceof User) {
            User user = (User) o;
            if(this.mUsername.equals(user.getUsername()) && this.mEmail.equals(user.getEmail())){
                return true;
            }
        }
        return false;
    }
}
