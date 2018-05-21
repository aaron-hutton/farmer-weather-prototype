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
import uk.ac.cam.cl.interaction_design.group19.app.weather.WeatherType;
import uk.ac.cam.cl.interaction_design.group19.app.weather.WindDir;

public class MetOfficeAPI {
    
    public static final String KEY                  = "6d150446-6caf-4009-8d9e-91f67c5aa7d0";
    public static final String BASE_URL             = "http://datapoint.metoffice.gov.uk/public/data/";
    public static final String LOCATION_LIST        = "val/wxobs/all/json/sitelist";
    public static final String HOURLY_DATA          = "val/wxobs/all/json/"; // + location_id
    public static final String DAILY_DATA           = "val/wxfcs/all/json/"; // + location_id
    public static final String HOURLY_LOCATION_LIST = "val/wxobs/all/json/sitelist";
    public static final String IMAGE_PATH           = "layer/wxobs/all/json/capabilities";
    
    public static DayData getDayData(LocalDateTime date, int location_id) {
        //TODO: make nicer
        if (date.toLocalDate().equals(LocalDate.now())) {
            return todaySummary(location_id);
        } else if (date.toLocalDate().equals(LocalDate.now().plusDays(1))) {
            return tomorrowSummary(location_id);
        } else {
            return null;
        }
    }
    
    public static DayData daySummary(int location_id, int day_number) {
        try {
            URL        url = makeURL(addParam(DAILY_DATA + Integer.toString(location_id), "res", "daily"));
            JsonObject obj = jsonFromUrl(url);
            JsonObject day = obj.getAsJsonObject("SiteRep").getAsJsonObject("DV")
                                .getAsJsonObject("Location").getAsJsonArray("Period")
                                .get(day_number).getAsJsonObject().getAsJsonArray("Rep")
                                .get(0).getAsJsonObject();
            JsonObject night = obj.getAsJsonObject("SiteRep").getAsJsonObject("DV")
                                  .getAsJsonObject("Location").getAsJsonArray("Period")
                                  .get(day_number).getAsJsonObject().getAsJsonArray("Rep")
                                  .get(1).getAsJsonObject();
            
            WeatherType weather            = WeatherData.getWeatherType(getAsStringOrDefault(day, "W", "0"));
            double      max_temp           = getAsDoubleOrDefault(day, "Dm", 20);
            double      min_temp           = getAsDoubleOrDefault(night, "Nm", 0);
            double      temperature        = 0.5 * (max_temp + min_temp);
            int         precipitation_prob = day.get("PPd").getAsInt();
            int         frost_prob;
            if (temperature > 5) {
                frost_prob = 1;
            } else if (temperature > 0) {
                frost_prob = 21;
            } else {
                frost_prob = 73;
            }
            WindDir dir        = WeatherData.getWindDir(getAsStringOrDefault(day, "D", "N"));
            int     wind_speed = day.get("S").getAsInt();
            return new DayData(LocalDate.now().plusDays(day_number),
                               weather,
                               temperature,
                               min_temp,
                               max_temp,
                               precipitation_prob,
                               frost_prob,
                               dir,
                               wind_speed);
        } catch (NullPointerException e) {
            return new DayData(LocalDate.now().plusDays(day_number),
                               WeatherType.DRIZZLE,
                               10,
                               8,
                               23,
                               72,
                               3,
                               WindDir.NW,
                               12);
        }
    }
    
    public static DayData todaySummary(int location_id) {
        return daySummary(location_id, 0);
    }
    
    public static DayData tomorrowSummary(int location_id) {
        return daySummary(location_id, 1);
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
    
    public static void main(String[] args) {
        MetOfficeAPI api = new MetOfficeAPI();
        System.out.println(api.fiveDayForecast(3066).get(0).get(4).time);
        System.out.println(Location.fromAddress("Homerton College, Cambridge").latitude);
    }
    
    public static List<List<HourlyData>> fiveDayForecast(int location_id) {
        /*
        Returns a list with items for (up to) five days,
        each of which is a list of HourlyDatas
        representing weather data about some random hour.

        For testing, can use location_id = 3066
        */
        List<List<HourlyData>> days = new ArrayList<>();
        URL                    url  = makeURL(addParam(HOURLY_DATA + Integer.toString(location_id), "res", "hourly"));
        if (url == null) {
            return days;
        }
        JsonObject obj = jsonFromUrl(url);
        if (obj == null) {
            for (int i = 0; i < 5; i++) {
                List<HourlyData> day = new ArrayList<>();
                for (int j = 0; j < 14; j++) {
                    day.add(new HourlyData(12.0, 4.2, "NW", "5", "09:00"));
                }
                days.add(day);
            }
            return days;
        }
        JsonArray days_objects = obj.getAsJsonObject("SiteRep").getAsJsonObject("DV")
                                    .getAsJsonObject("Location").getAsJsonArray("Period");
        
        for (JsonElement day : days_objects) {
            JsonArray        hours    = day.getAsJsonObject().getAsJsonArray("Rep");
            List<HourlyData> day_list = new ArrayList<>();
            int              time     = 9;
            for (JsonElement hour : hours) {
                String     timestamp = String.valueOf(time / 10) + String.valueOf(time % 10) + ":00";
                JsonObject h         = hour.getAsJsonObject();
                day_list.add(new HourlyData(getAsDoubleOrDefault(h, "T", -1),
                                            getAsDoubleOrDefault(h, "S", -1),
                                            getAsStringOrDefault(h, "D", "N"),
                                            getAsStringOrDefault(h, "W", "NA"),
                                            timestamp));
                time++;
            }
            days.add(day_list);
        }
        return days;
    }
    
    private static String getAsStringOrDefault(JsonObject h, String d, String defaultString) {
        var elem = h.get(d);
        return elem != null ? elem.getAsString() : defaultString;
    }
    
    private static double getAsDoubleOrDefault(JsonObject h, String t, double defaultDouble) {
        var elem = h.get(t);
        return elem != null ? elem.getAsDouble() : defaultDouble;
    }
    
    public List<String> locationList() {
        List<String> result = null;
        URL          url    = makeURL(LOCATION_LIST);
        if (url == null) {
            return result;
        }
        JsonObject obj = jsonFromUrl(url);
        System.out.println(obj.toString());
        return result;
    }
    
    public static List<MetOfficeLocation> hourlyLocationList() {
        List<MetOfficeLocation> result = new ArrayList<>();
        URL                     url    = makeURL(HOURLY_LOCATION_LIST);
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
                                             getAsDoubleOrDefault(obj, "latitude", 0),
                                             getAsDoubleOrDefault(obj, "longitude", 0)));
        }
        
        return result;
    }
    
    public static ArrayList<Double> gddForecast(int location, double base) {
        var toReturn = new ArrayList();
        
        URL u = makeURL(addParam(DAILY_DATA + Integer.toString(location), "res", "daily"));
        
        JsonObject weekly = jsonFromUrl(u);
        
        if (weekly != null) {
            JsonArray weekJsonArr = weekly.getAsJsonObject("SiteRep").getAsJsonObject("DV")
                                          .getAsJsonObject("Location").getAsJsonArray("Period");
            
            for (JsonElement j : weekJsonArr) {
                JsonArray dayNight = j.getAsJsonObject().getAsJsonArray("Rep");
                int       max      = dayNight.get(0).getAsJsonObject().get("Dm").getAsInt();
                int       min      = dayNight.get(1).getAsJsonObject().get("Nm").getAsInt();
                toReturn.add(Math.max(((double) max + min) / 2 - base, 0));
            }
        }
        
        return toReturn;
    }
}