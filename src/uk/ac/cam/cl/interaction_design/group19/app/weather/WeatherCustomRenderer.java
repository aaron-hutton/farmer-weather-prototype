package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import uk.ac.cam.cl.interaction_design.group19.app.WeatherData;

public abstract class WeatherCustomRenderer extends JLabel implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        if(!(value instanceof WeatherData)) {
            System.err.println("The data is not weather data.");
            System.exit(1);
        }
        WeatherData data = (WeatherData) value;
        switch(column) {
            case 0:
                System.err.println("The 0th column of the renderer should be overridden.");
                System.exit(1);
            case 2:
                this.setText(Math.round(data.temperature)+"°C");
                break;
            case 4:
                this.setText("Precipitation here");
                break;
            case 1:
                this.setText(data.weather_type);
                break;
            case 3:
                this.setText("Raindrop");
                break;
        }
        return this;
    }
}
