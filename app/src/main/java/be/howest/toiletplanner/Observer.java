package be.howest.toiletplanner;

import org.json.JSONException;

/**
 * Created by Dylan on 5/11/2014.
 */
public interface Observer {
    public void getData() throws JSONException;
}
