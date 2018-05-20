package uk.ac.cam.cl.interaction_design.group19.app.api;

public class WeatherData {
    public final double temperature;
    public final double wind_speed;    // mph
    public final String wind_direction;
    public final String weather_type;
    
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
            return WeatherType.values()[Integer.parseInt(this.weather_type) + 1];
        }
    }
}
