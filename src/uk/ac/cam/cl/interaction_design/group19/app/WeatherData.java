package uk.ac.cam.cl.interaction_design.group19.app;

public class WeatherData {
    public final double temperature;
    public final double wind_speed;    // mph
    public final String wind_direction;
    public final String weather_type;

    public enum WeatherType {
        NOT_AVAILABLE,
        CLEAR_NIGHT,
        SUNNY_DAY,
        PARTLY_CLOUDY_NIGHT,
        PARTLY_CLOUDY_DAY,
        NOT_USED,
        MIST,
        FOG,
        CLOUDY,
        OVERCAST,
        LIGHT_RAIN_SHOWER_NIGHT,
        LIGHT_RAIN_SHOWER_DAY,
        DRIZZLE,
        LIGHT_RAIN,
        HEAVY_RAIN_SHOWER_NIGHT,
        HEAVY_RAIN_SHOWER_DAY,
        HEAVY_RAIN,
        SLEET_SHOWER_NIGHT,
        SLEET_SHOWER_DAY,
        SLEET,
        HAIL_SHOWER_NIGHT,
        HAIL_SHOWER_DAY,
        HAIL,
        LIGHT_SNOW_SHOWER_NIGHT,
        LIGHT_SNOW_SHOWER_DAY,
        LIGHT_SNOW,
        HEAVY_SNOW_SHOWER_NIGHT,
        HEAVY_SNOW_SHOWER_DAY,
        HEAVY_SNOW,
        THUNDER_SHOWER_NIGHT,
        THUNDER_SHOWER_DAY,
        THUNDER
        }

    public WeatherData(double temperature, double wind_speed, String wind_direction, String weather_type) {
        this.temperature = temperature;
        this.wind_speed = wind_speed;
        this.wind_direction = wind_direction;
        this.weather_type = weather_type;
    }

    public WeatherType getWeatherType() {
        if (this.weather_type.equals("NA")) {
            return WeatherType.NOT_AVAILABLE;
        } else {
            return WeatherType.values()[Integer.parseInt(this.weather_type)+1];
        }
    }

}
