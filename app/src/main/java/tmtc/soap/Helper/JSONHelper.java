package tmtc.soap.Helper;

import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import tmtc.soap.Model.Movie;
import tmtc.soap.Model.MoviePerson;

/**
 * Bad Boys Team
 * Created by remyjallan on 25/03/2016.
 */
public class JSONHelper {
    public static Movie JSONToMovie(JSONObject json) throws JSONException {
        Movie movie = null;

        if(json != null) {
            if(json.has("id") && json.has("title")) {
                movie = new Movie(json.getInt("id"),json.getString("title"));
                if(json.has("cover") && !Objects.equals(json.getString("cover"), "null")) {
                    movie.setPoster(json.getString("cover"));
                } else {
                    movie.setPoster("http://www.parlonsbonsai.com/forums/uploads/post-82-1100815770.jpg");
                }
                if(json.has("synopsis")) {
                    movie.setSynopsis(json.getString("synopsis"));
                }
                if(json.has("release")) {
                    //2016-03-25T00:00:00.000Z
                    try {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.FRANCE);
                        Date date = format.parse(json.getString("release"));

                        format = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
                        movie.setReleaseDate(format.format(date));
                    } catch (ParseException e) {
                        Logger.d("exception parse -> " +e.getMessage());
                        movie.setReleaseDate(json.getString("release"));
                    }
                }
            }
        }

        return movie;
    }
    public static MoviePerson JSONToPerson(JSONObject json) throws JSONException {
        MoviePerson person = null;

        if(json != null) {
            person = new MoviePerson();
            if(json.has("id")) {
                person.setId(json.getInt("id"));
            }
            if(json.has("name")) {
                person.setName(json.getString("name"));
            }
            if(json.has("photo") && !Objects.equals(json.getString("photo"), "null")) {
                person.setPicture(json.getString("photo"));
            } else {
                person.setPicture("http://www.workman.com/blog/wp-content/uploads/2014/12/Hi-Red-Panda.jpg");
            }
            if(json.has("MoviePerson") && json.getJSONObject("MoviePerson").has("role")) {
                person.setRole(json.getJSONObject("MoviePerson").getString("role"));
            }
        }

        return person;
    }
}
