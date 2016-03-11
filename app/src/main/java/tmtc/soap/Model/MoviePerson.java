package tmtc.soap.Model;

import org.parceler.Parcel;

/**
 * Bad Boys Team
 * Created by remyjallan on 10/03/2016.
 */
@Parcel
public class MoviePerson {
    private String mName;
    private String mPicture;
    private String mRole;

    public MoviePerson() {}

    public MoviePerson(String name,String role) {
        mName = name;
        mRole = role;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPicture() {
        return mPicture;
    }

    public void setPicture(String picture) {
        mPicture = picture;
    }

    public String getRole() {
        return mRole;
    }

    public void setRole(String role) {
        mRole = role;
    }
}
