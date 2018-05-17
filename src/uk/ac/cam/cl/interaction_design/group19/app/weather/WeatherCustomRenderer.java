package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import uk.ac.cam.cl.interaction_design.group19.app.Icons;
import uk.ac.cam.cl.interaction_design.group19.app.MainWindow;
import uk.ac.cam.cl.interaction_design.group19.app.WeatherData;
import uk.ac.cam.cl.interaction_design.group19.app.WeatherType;

public abstract class WeatherCustomRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        JLabel label = new JLabel();
        label.setBackground(MainWindow.BACKGROUND_COLOR);
        label.setOpaque(true);


        if(table.getRowCount()-1 == row) {
            label.setBorder(BorderFactory.createEmptyBorder());
        } else {
            label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        }


        if(!(value instanceof WeatherData)) {
            System.err.println("The data is not weather data.");
            System.exit(1);
        }
        WeatherData data = (WeatherData) value;
        switch(column) {
            case 2:
                label.setText(Math.round(data.temperature)+"°C");
                break;
            case 4:
                label.setText("Precip here");
                label.setMaximumSize(new Dimension(50, 1));
                break;
            case 1:
                label.setIcon(new ImageIcon(Icons.getSizedIcon(data.getWeatherType(), 30)));
                label.setMaximumSize(new Dimension(50, 1));
                break;
            case 3:
                label.setIcon(new ImageIcon(Icons.getSizedIcon(WeatherType.RAINDROP, 30)));
                label.setMaximumSize(new Dimension(50, 1));
                break;
        }
        return label;
    }
}
