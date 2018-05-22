package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import uk.ac.cam.cl.interaction_design.group19.app.util.WeatherData;

public class HourlyWeatherRenderer extends WeatherCustomRenderer implements TableCellRenderer {

    public HourlyWeatherRenderer(int size)
    {
        super(size);
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        if(!(value instanceof WeatherData)) {
            System.err.println("An error occurred.");
            System.exit(0);
        }
        WeatherData data = (WeatherData) value;
        if (column == 0) {
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            label.setText(data.time.toString());
            return label;
        } else {
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }
}
