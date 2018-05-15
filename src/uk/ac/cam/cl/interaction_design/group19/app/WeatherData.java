package uk.ac.cam.cl.interaction_design.group19.app;

public class WeatherData {
    public final double temperature;
    public final double wind_speed;    // mph
    public final String wind_direction;
    public final String weather_type;
    public final int precipitation_prob;

    public WeatherData(double temperature, double wind_speed, String wind_direction, String weather_type, int precipitation_prob) {
        this.temperature = temperature;
        this.wind_speed = wind_speed;
        this.wind_direction = wind_direction;
        this.weather_type = weather_type;
        this.precipitation_prob = precipitation_prob;
    }

}
