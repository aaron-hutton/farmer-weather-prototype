package uk.ac.cam.cl.interaction_design.group19.app.api;

public class HourlyData extends WeatherData {
    public final String time;
    
    public HourlyData(double temperature, double wind_speed, String wind_direction, String weather_type, String time) {
        super(temperature, wind_speed, wind_direction, weather_type);
        this.time = time;
    }
}
