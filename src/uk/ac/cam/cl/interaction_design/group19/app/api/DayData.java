package uk.ac.cam.cl.interaction_design.group19.app.api;

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import uk.ac.cam.cl.interaction_design.group19.app.weather.WeatherType;
import uk.ac.cam.cl.interaction_design.group19.app.weather.WindDir;

public class DayData {
    // TODO: inherit from WeatherData
    public final LocalDate   date;
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
    
    public DayData(LocalDate date,
                   WeatherType weather,
                   double temperature,
                   double low_temperature,
                   double high_temperature,
                   int precipitation_prob,
                   int frost_prob,
                   WindDir wind_direction,
                   int wind_speed) {
        this.date = date;
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
}
