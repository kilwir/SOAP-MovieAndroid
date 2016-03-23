package tmtc.soap.Model;

import org.parceler.Parcel;

/**
 * Bad Boys Team
 * Created by remyjallan on 10/03/2016.
 */
@Parcel
public class MoviePerson implements SearchItem {

    public final static int ID = 1;

    private int mId;
    private String mName;
    private String mPicture;
    private String mRole;

    public MoviePerson() {}

    public MoviePerson(int id,String name,String role) {
        mId = id;
        mName = name;
        mRole = role;
    }

    public MoviePerson(int id, String name, String role, String picture) {
        mId = id;
        mName = name;
        mRole = role;
        mPicture = picture;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
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

    @Override
    public int getIdType() {
        return ID;
    }
}
