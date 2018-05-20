package uk.ac.cam.cl.interaction_design.group19.app.api;

import uk.ac.cam.cl.interaction_design.group19.app.WindDir;

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
    
    /*
    public WeatherType getWeatherType() {
        if (this.weather_type.equals("NA")) {
            return WeatherType.NOT_AVAILABLE;
        } else {
            return WeatherType.values()[Integer.parseInt(this.weather_type) + 1];
        }
    }
    */
    
    public static WeatherType getWeatherType(String to_parse) {
        if (to_parse.equals("NA")) {
            return WeatherType.NOT_AVAILABLE;
        } else {
            return WeatherType.values()[Integer.parseInt(to_parse) + 1];
        }
    }
    
    public static WindDir getWindDir(String to_parse) {
        switch(to_parse) {
            case "N": return WindDir.N;
            case "NE": return WindDir.NE;
            case "E": return WindDir.E;
            case "SE": return WindDir.SE;
            case "S": return WindDir.S;
            case "SW": return WindDir.SW;
            case "W": return WindDir.W;
            case "NW": return WindDir.NW;
        }
        return null;
    }
}
