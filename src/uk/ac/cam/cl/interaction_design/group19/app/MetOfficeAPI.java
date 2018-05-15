package uk.ac.cam.cl.interaction_design.group19.app;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import uk.ac.cam.cl.interaction_design.group19.app.GDDs.MinMax;

public class MetOfficeAPI {
  
    public static final String KEY = "6d150446-6caf-4009-8d9e-91f67c5aa7d0";
    public static final String BASE_URL = "http://datapoint.metoffice.gov.uk/public/data/";
    public static final String LOCATION_LIST = "val/wxobs/all/json/sitelist";
    public static final String IMAGE_PATH = "layer/wxobs/all/json/capabilities";

    private String addParam(String base_url, String key, Object value) {
        String url = base_url;
        if (url.indexOf('?') < 0) {
            url += "?";
        } else {
            url += "&";
        }

        url += key;
        url += "=";
        try {
            url += URLEncoder.encode(value.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.err.println("UTF-8 doesn't exist");
        }

        return url;
    }

    public JsonObject jsonFromUrl(URL url) {
        JsonElement root = null;
        // Make the HTTP request
        try {
            URLConnection request = url.openConnection();
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

    public List<String> locationList() {
        List<String> result = null;
        URL url = null;
        try {
            url = new URL(addParam(BASE_URL + LOCATION_LIST, "key", KEY));
        } catch (MalformedURLException e) {
            System.err.println("Malformed URL error");
            return result;
        }
        JsonObject obj = jsonFromUrl(url);
        System.out.println(obj.toString());
        return result;
    }

    public static void main(String[] args) {
        MetOfficeAPI api = new MetOfficeAPI();
        api.locationList();
    }

    public ArrayList<MinMax> minmax(Integer location) throws MalformedURLException {
        URL u = new URL(BASE_URL + "val/wxfcs/all/json/" + Integer.toString(location) + "?res=daily&key=" + KEY);

        JsonObject weekly = jsonFromUrl(u);

        JsonArray weekJsonArr = weekly.getAsJsonObject("SiteRep").getAsJsonObject("DV").getAsJsonObject("Location").getAsJsonArray("Period");

        for(JsonElement j : weekJsonArr) {

        }

        return null;
    }
}