package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.awt.Component;
import java.time.format.DateTimeFormatter;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;
import uk.ac.cam.cl.interaction_design.group19.app.util.IconType;
import uk.ac.cam.cl.interaction_design.group19.app.util.Icons;
import uk.ac.cam.cl.interaction_design.group19.app.util.WeatherData;

public class HourlyWeatherRenderer extends WeatherCustomRenderer implements TableCellRenderer {

    private final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");
    
    public HourlyWeatherRenderer(int size)
    {
        super(size);
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        if(!(value instanceof WeatherData)) {
            System.err.println("Hourly: The input is not of type WeatherData.");
            System.exit(0);
        }
        WeatherData data = (WeatherData) value;
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        if (column == 0) {
            label.setText(data.time.format(TIME_FORMAT));
        } else if(column == 4) {
            label.setText(data.precipitation_prob+"%");
            label.setHorizontalAlignment(SwingConstants.LEFT);
        } else if(column == 3) {
            label.setIcon(new ImageIcon(Icons.getSizedWidthIcon(IconType.RAINDROP, 30)));
            label.setHorizontalAlignment(SwingConstants.RIGHT);
        }
        return label;
    }
}
