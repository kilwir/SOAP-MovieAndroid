package tmtc.soap.Model;

/**
 * Bad Boys Team
 * Created by remyjallan on 17/03/2016.
 */
public class Comment {
    private int mId;
    private String mContent;
    private double mRating;
    private User mUser;
    private Movie mMovie;
    private String mCreateDate;

    public Comment(int id, String content, double rating, User user, Movie movie) {
        mId = id;
        mContent = content;
        mRating = rating;
        mUser = user;
        mMovie = movie;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public double getRating() {
        return mRating;
    }

    public void setRating(double rating) {
        mRating = rating;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public Movie getMovie() {
        return mMovie;
    }

    public void setMovie(Movie movie) {
        mMovie = movie;
    }

    public String getCreateDate() {
        return mCreateDate;
    }

    public void setCreateDate(String createDate) {
        mCreateDate = createDate;
    }
}
