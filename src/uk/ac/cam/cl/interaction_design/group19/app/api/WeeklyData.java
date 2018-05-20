package uk.ac.cam.cl.interaction_design.group19.app.api;

public class WeeklyData extends WeatherData {
    public final int precipitation_prob;

    public WeeklyData(double temperature,
                      double wind_speed,
                      String wind_direction,
                      String weather_type,
                      int precipitation_prob) {
        super(temperature, wind_speed, wind_direction, weather_type);
        this.precipitation_prob = precipitation_prob;
    }
}
