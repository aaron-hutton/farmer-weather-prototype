package uk.ac.cam.cl.interaction_design.group19.app;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MetOfficeAPI {
    private static final String KEY = "6d150446-6caf-4009-8d9e-91f67c5aa7d0";
    private static final String BASE_URL = "http://datapoint.metoffice.gov.uk/public/data/";
    private static final String LOCATION_LIST = "val/wxobs/all/json/sitelist";

    public JsonObject jsonFromUrl(String url) {
        JsonElement root = null;
        // Make the HTTP request
        try {
            URL url_object = new URL(url);
            URLConnection request = url_object.openConnection();
            request.connect();

            // Get a JSON object
            JsonParser jp = new JsonParser();
            root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        } catch (MalformedURLException e) {
            System.err.println("Malformed URL in JSON request");
        } catch (IOException e) {
            System.err.println("Error in opening connection in JSON request");
        }
        return root.getAsJsonObject();
    }
}
