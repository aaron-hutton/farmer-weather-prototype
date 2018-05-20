package uk.ac.cam.cl.interaction_design.group19.app.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MetOfficeAPI {

    public static final String KEY = "6d150446-6caf-4009-8d9e-91f67c5aa7d0";
    public static final String BASE_URL = "http://datapoint.metoffice.gov.uk/public/data/";
    public static final String LOCATION_LIST = "val/wxobs/all/json/sitelist";
    public static final String HOURLY_DATA = "val/wxobs/all/json/"; // + location_id
    public static final String DAILY_DATA = "val/wxfcs/all/json"; // + location_id
    public static final String HOURLY_LOCATION_LIST = "val/wxobs/all/json/sitelist";
    public static final String IMAGE_PATH = "layer/wxobs/all/json/capabilities";

    private static String addParam(String base_url, String key, Object value) {
        String url = base_url;
        if (url.indexOf('?') < 0) {
            url += "?";
        } else {
            url += "&";
        }

        url += key;
        url += "=";
        url += URLEncoder.encode(value.toString(), StandardCharsets.UTF_8);

        return url;
    }

    public static JsonObject jsonFromUrl(URL url) {
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
        if (root == null) {
            return null;
        } else {
            return root.getAsJsonObject();
        }
    }

    public static URL makeURL(String resource_part) {
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
        if (url == null) {
            return result;
        }
        JsonObject obj = jsonFromUrl(url);
        System.out.println(obj.toString());
        return result;
    }

    public static DayData getDayData(LocalDateTime date, int location_id) {
        //TODO: make nicer
        if (date.toLocalDate().equals(LocalDate.now())) {
            return todaySummary(location_id);
        } else if (date.toLocalDate().equals(LocalDate.now().plusDays(1))) {
            return tomorrowSummary();
        } else {
            return null;
        }
    }

    public static DayData todaySummary(int location_id) {
        try {
            URL url = makeURL(addParam(DAILY_DATA + Integer.toString(location_id), "res", "daily"));
            JsonObject obj = jsonFromUrl(url);
            JsonObject day = obj.getAsJsonObject("SiteRep").getAsJsonObject("DV")
                    .getAsJsonObject("Location").getAsJsonArray("Period")
                    .get(0).getAsJsonObject().getAsJsonArray("Rep")
                    .get(0).getAsJsonObject();

            return new DayData(13.5, 9.7, 26.5, 23, 37);
        } catch (NullPointerException e) {
            return new DayData(11.2, 5.8, 46.5, 99, 3);
        }
    }

    public static DayData tomorrowSummary() {
        return new DayData(13.5, 9.7, 26.5, 23, 37);
    }

    public List<MetOfficeLocation> hourlyLocationList() {
        List<MetOfficeLocation> result = new ArrayList<>();
        URL url = makeURL(HOURLY_LOCATION_LIST);
        if (url == null) {
            return result;
        }
        JsonObject root = jsonFromUrl(url);
        if (root == null) {
            return result;
        }
        JsonArray locations = root.getAsJsonObject("Locations").getAsJsonArray("Location");
        for (JsonElement location : locations) {
            JsonObject obj = location.getAsJsonObject();
            result.add(new MetOfficeLocation(obj.get("id").getAsInt(),
                    obj.get("latitude").getAsDouble(),
                    obj.get("longitude").getAsDouble()));
        }

        return result;
    }

    public List<List<HourlyData>> fiveDayForecast(int location_id) {
        /*
        Returns a list with items for (up to) five days,
        each of which is a list of HourlyDatas
        representing weather data about some random hour.

        For testing, can use location_id = 3066
        */
        return null;
    }

    public static void main(String[] args) {
        MetOfficeAPI api = new MetOfficeAPI();
        System.out.println(api.fiveDayForecast(3066).get(0).get(4).time);
        System.out.println(Location.fromAddress("Homerton College, Cambridge").latitude);
    }

    public ArrayList<Double> gddForecast(int location, double base) {
        URL u = makeURL(addParam(DAILY_DATA + Integer.toString(location), "res", "daily"));

        JsonObject weekly = jsonFromUrl(u);

        JsonArray weekJsonArr = weekly.getAsJsonObject("SiteRep").getAsJsonObject("DV")
                .getAsJsonObject("Location").getAsJsonArray("Period");

        ArrayList toReturn = new ArrayList();

        for (JsonElement j : weekJsonArr) {
            JsonArray dayNight = j.getAsJsonObject().getAsJsonArray("Rep");
            int max = dayNight.get(0).getAsJsonObject().get("Dm").getAsInt();
            int min = dayNight.get(1).getAsJsonObject().get("Nm").getAsInt();
            toReturn.add(Math.max(((double) max + min) / 2 - base, 0));
        }

        return toReturn;
    }
}