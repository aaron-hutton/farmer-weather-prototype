package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.awt.Component;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;
import javax.swing.JTable;
import uk.ac.cam.cl.interaction_design.group19.app.util.WeatherData;

public class WeeklyWeatherRenderer extends WeatherCustomRenderer {
    
    private final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("EEE, dd");
    
    public WeeklyWeatherRenderer(int size)
    {
        super(size);
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        if(!(value instanceof WeatherData)) {
            System.err.println("Weekly: The input is not of type WeatherData.");
            System.exit(0);
        }
        WeatherData data = (WeatherData) value;
        
        if(column == 0) {

            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            label.setText(data.time.format(TIME_FORMAT)+getDayOfMonthSuffix(data.time.getDayOfMonth()));
            return label;
        } else {
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }
    
    private static String getDayOfMonthSuffix(int day) {
        if (day >= 11 && day <= 13) {
            return "th";
        }
        switch (day % 10) {
            case 1:  return "st";
            case 2:  return "nd";
            case 3:  return "rd";
            default: return "th";
        }
    }
}
