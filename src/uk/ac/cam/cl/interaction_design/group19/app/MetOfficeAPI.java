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
import uk.ac.cam.cl.interaction_design.group19.app.GDDs.Pair;

public class MetOfficeAPI {
  
    public static final String KEY = "6d150446-6caf-4009-8d9e-91f67c5aa7d0";
    public static final String BASE_URL = "http://datapoint.metoffice.gov.uk/public/data/";
    public static final String LOCATION_LIST = "val/wxobs/all/json/sitelist";
    public static final String HOURLY_DATA = "val/wxobs/all/json/"; // + location_id
    public static final String DAILY_DATA = "val/wxfcs/all/json/";
    public static final String HOURLY_LOCATION_LIST = "val/wxobs/all/json/sitelist";
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

    public URL makeURL(String resource_part) {
        URL url = null;
        try {
            url = new URL(addParam(BASE_URL + resource_part, "key", KEY));
        } catch (MalformedURLException e) {
            System.err.println("Malformed URL error");
            return null;
        }
        return url;
    }

    public List<String> locationList() {
        List<String> result = null;
        URL url = makeURL(LOCATION_LIST);
        if (url == null) return result;
        JsonObject obj = jsonFromUrl(url);
        System.out.println(obj.toString());
        return result;
    }

    public List<String> hourlyLocationList() {
        List<String> result = null;
        URL url = makeURL(HOURLY_LOCATION_LIST);
        if (url == null) return result;
        JsonObject obj = jsonFromUrl(url);
        System.out.println(obj.toString());
        return result;
    }

    public List<List<HourlyData>> fiveDayForecast(int location_id) {
        /*
        Returns a list with items for (up to) five days,
        each of which is a list of HourlyDatas
        representing weather data about some random hour.

        For testing, can use location_id = 3066
         */
        List<List<HourlyData>> days = new ArrayList<>();
        URL url = makeURL(addParam(HOURLY_DATA + Integer.toString(location_id), "res", "hourly"));
        if (url == null) return days;
        JsonObject obj = jsonFromUrl(url);
        JsonArray days_objects = obj.getAsJsonObject("SiteRep").getAsJsonObject("DV")
                               .getAsJsonObject("Location").getAsJsonArray("Period");

        for (JsonElement day : days_objects) {
            JsonArray hours = day.getAsJsonObject().getAsJsonArray("Rep");
            List<HourlyData> day_list = new ArrayList<>();
            for (JsonElement hour : hours) {
                JsonObject h = hour.getAsJsonObject();
                day_list.add(new HourlyData(h.get("T").getAsDouble(),
                        h.get("S").getAsDouble(),
                        h.get("D").getAsString(),
                        h.get("W").getAsString()));
            }
            days.add(day_list);
        }
        return days;
    }

    public static void main(String[] args) {
        MetOfficeAPI api = new MetOfficeAPI();
        System.out.println(api.gddForecast(3840, 10));
    }

    public ArrayList<Pair<String, Double>> gddForecast(int location, double base){
        URL u = makeURL(addParam(DAILY_DATA + Integer.toString(location), "res", "daily"));
        System.out.println(u.toString());

        JsonObject weekly = jsonFromUrl(u);

        JsonArray weekJsonArr = weekly.getAsJsonObject("SiteRep").getAsJsonObject("DV")
                .getAsJsonObject("Location").getAsJsonArray("Period");

        ArrayList toReturn = new ArrayList();

        for(JsonElement j : weekJsonArr) {
            JsonArray dayNight = j.getAsJsonObject().getAsJsonArray("Rep");
            String date = j.getAsJsonObject().get("value").getAsString();
            int max = dayNight.get(0).getAsJsonObject().get("Dm").getAsInt();
            int min = dayNight.get(1).getAsJsonObject().get("Nm").getAsInt();
            Pair<String, Double> p = new Pair<>(date, Math.max(((double) max+min)/2 - base, 0));
            toReturn.add(p);
        }

        return toReturn;
    }
}