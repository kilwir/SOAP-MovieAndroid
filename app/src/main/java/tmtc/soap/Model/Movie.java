package tmtc.soap.Model;
import org.parceler.Parcel;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Bad Boys Team
 * Created by remyjallan on 09/03/2016.
 */
@Parcel
public class Movie implements SearchItem {

    public final static int ID = 0;

    private int mId;
    private String mName;
    private String mReleaseDate;
    private String mPoster;
    private String mSynopsis;

    private ArrayList<MoviePerson> mPersons;

    public Movie() {} //Parcel required

    public Movie(int id,String name) {
        mPersons = new ArrayList<>();
        this.mName = name;
        this.mId = id;
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

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public String getPoster() {
        return mPoster;
    }

    public void setPoster(String poster) {
        mPoster = poster;
    }

    public String getSynopsis() {
        return mSynopsis;
    }

    public void setSynopsis(String synopsis) {
        mSynopsis = synopsis;
    }

    public ArrayList<MoviePerson> getPersons() {
        return mPersons;
    }

    public void addPerson(MoviePerson person) {
        if(!this.mPersons.contains(person)) {
            this.mPersons.add(person);
        }
    }

    public void setPersons(ArrayList<MoviePerson> persons) {
        this.mPersons = persons;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "mId='" +mId +"\'"+
                ", mName='" + mName + '\'' +
                ", mReleaseDate='" + mReleaseDate + '\'' +
                ", mPoster='" + mPoster + '\'' +
                '}';
    }

    @Override
    public int getIdType() {
        return ID;
    }
}
