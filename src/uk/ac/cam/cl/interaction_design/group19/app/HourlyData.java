package uk.ac.cam.cl.interaction_design.group19.app;

public class HourlyData extends WeatherData {
    public HourlyData(double temperature, double wind_speed, String wind_direction, String weather_type, int precipitation_prob) {
        super(temperature, wind_speed, wind_direction, weather_type, precipitation_prob);
    }
}
