package tmtc.soap.Model;

import java.net.URI;

/**
 * Bad Boys Team
 * Created by remyjallan on 09/03/2016.
 */
public class Movie {
    private String mName;
    private String mReleaseDate;
    private String mType;
    private String mPoster;

    public Movie(String name, String type) {
        this.mName = name;
        this.mType = type;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getPoster() {
        return mPoster;
    }

    public void setPoster(String poster) {
        mPoster = poster;
    }
}