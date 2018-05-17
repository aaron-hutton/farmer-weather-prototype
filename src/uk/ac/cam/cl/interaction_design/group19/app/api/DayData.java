package uk.ac.cam.cl.interaction_design.group19.app.api;

public class DayData {
    public final double temperature;
    public final double low_temperature;
    public final double high_temperature;
    public final int precipitation_prob;
    public final int frost_prob;
    public DayData(double temperature, double low_temperature, double high_temperature, int precipitation_prob, int frost_prob) {
        this.temperature = temperature;
        this.low_temperature = low_temperature;
        this.high_temperature = high_temperature;
        this.precipitation_prob = precipitation_prob;
        this.frost_prob = frost_prob;
    }
}
