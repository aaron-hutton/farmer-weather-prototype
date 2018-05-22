package uk.ac.cam.cl.interaction_design.group19.app.util;

import java.time.LocalDateTime;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

public class WeatherData {
    private static final Map<Integer,WeatherType> weatherCodes = Map.ofEntries(
            new SimpleEntry<>(0, WeatherType.CLEAR_NIGHT),
            new SimpleEntry<>(1, WeatherType.SUNNY_DAY),
            new SimpleEntry<>(2, WeatherType.PARTLY_CLOUDY_NIGHT),
            new SimpleEntry<>(3, WeatherType.PARTLY_CLOUDY_DAY),
            new SimpleEntry<>(5, WeatherType.MIST),
            new SimpleEntry<>(6, WeatherType.FOG),
            new SimpleEntry<>(7, WeatherType.CLOUDY),
            new SimpleEntry<>(8, WeatherType.OVERCAST),
            new SimpleEntry<>(9, WeatherType.LIGHT_RAIN_SHOWER_NIGHT),
            new SimpleEntry<>(10, WeatherType.LIGHT_RAIN_SHOWER_DAY),
            new SimpleEntry<>(11, WeatherType.DRIZZLE),
            new SimpleEntry<>(12, WeatherType.LIGHT_RAIN),
            new SimpleEntry<>(13, WeatherType.HEAVY_RAIN_SHOWER_NIGHT),
            new SimpleEntry<>(14, WeatherType.HEAVY_RAIN_SHOWER_DAY),
            new SimpleEntry<>(15, WeatherType.HEAVY_RAIN),
            new SimpleEntry<>(16, WeatherType.SLEET_SHOWER_NIGHT),
            new SimpleEntry<>(17, WeatherType.SLEET_SHOWER_DAY),
            new SimpleEntry<>(18, WeatherType.SLEET),
            new SimpleEntry<>(19, WeatherType.HAIL_SHOWER_NIGHT),
            new SimpleEntry<>(20, WeatherType.HAIL_SHOWER_DAY),
            new SimpleEntry<>(21, WeatherType.HAIL),
            new SimpleEntry<>(22, WeatherType.LIGHT_SNOW_SHOWER_NIGHT),
            new SimpleEntry<>(23, WeatherType.LIGHT_SNOW_SHOWER_DAY),
            new SimpleEntry<>(24, WeatherType.LIGHT_SNOW),
            new SimpleEntry<>(25, WeatherType.HEAVY_SNOW_SHOWER_NIGHT),
            new SimpleEntry<>(26, WeatherType.HEAVY_SNOW_SHOWER_DAY),
            new SimpleEntry<>(27, WeatherType.HEAVY_SNOW),
            new SimpleEntry<>(28, WeatherType.THUNDER_SHOWER_NIGHT),
            new SimpleEntry<>(29, WeatherType.THUNDER_SHOWER_DAY),
            new SimpleEntry<>(30, WeatherType.THUNDER)
    );
    
    public final LocalDateTime   time;
    public final int         temperature;
    public final int         low_temperature;
    public final int         high_temperature;
    public final int         precipitation_prob;
    public final int         frost_prob;
    public final WeatherType weather;
    public final WindDir     wind_direction;
    public final int         wind_speed;
    public final int         soil_moisture;
    public final int         soil_temperature;
    public final int         cloud_cover;
    
    public WeatherData(LocalDateTime time,
                    WeatherType weather,
                    double temperature,
                    double low_temperature,
                    double high_temperature,
                    int precipitation_prob,
                    int frost_prob,
                    WindDir wind_direction,
                    int wind_speed) {
        this.time = time;
        this.temperature = (int) Math.round(temperature);
        this.low_temperature = (int) Math.round(low_temperature);
        this.high_temperature = (int) Math.round(high_temperature);
        this.precipitation_prob = precipitation_prob;
        this.frost_prob = frost_prob;
        this.weather = weather;
        this.cloud_cover = -1;
        this.wind_direction = wind_direction;
        this.wind_speed = wind_speed;
        this.soil_moisture = -1;
        this.soil_temperature = -1;
    }
    
    public static WeatherType getWeatherType(String to_parse) {
        if (to_parse.equals("NA")) {
            return null;
        } else {
            var n = Integer.parseInt(to_parse);
            return weatherCodes.getOrDefault(n, null);
        }
    }
    
    public static WindDir getWindDir(String to_parse) {
        switch (to_parse) {
            case "N":
                return WindDir.N;
            case "NE":
                return WindDir.NE;
            case "E":
                return WindDir.E;
            case "SE":
                return WindDir.SE;
            case "S":
                return WindDir.S;
            case "SW":
                return WindDir.SW;
            case "W":
                return WindDir.W;
            case "NW":
                return WindDir.NW;
        }
        return null;
    }
}
