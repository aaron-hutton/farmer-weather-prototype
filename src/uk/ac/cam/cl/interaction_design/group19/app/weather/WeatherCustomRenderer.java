package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;
import uk.ac.cam.cl.interaction_design.group19.app.MainWindow;
import uk.ac.cam.cl.interaction_design.group19.app.util.WeatherData;
import uk.ac.cam.cl.interaction_design.group19.app.util.IconType;
import uk.ac.cam.cl.interaction_design.group19.app.util.Icons;

public abstract class WeatherCustomRenderer extends JLabel implements TableCellRenderer {
    
    private JComponent[][] cache = new JComponent[5][];
    
    public WeatherCustomRenderer(int size) {
        for (int i = 0; i < cache.length; i++) {
            cache[i] = new JComponent[size];
        }
    }
    
    public void updateSize(int size) {
        for (int i = 0; i < cache.length; i++) {
            cache[i] = new JComponent[size];
        }
    }
    
    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        
        if (cache[column][row] != null) {
            return cache[column][row];
        }
        
        JLabel label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBackground(MainWindow.BACKGROUND_COLOR);
        label.setOpaque(true);
        
        
        if (table.getRowCount() - 1 == row) {
            label.setBorder(BorderFactory.createEmptyBorder());
        } else {
            label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        }
    
        if (!(value instanceof WeatherData)) {
            System.err.println("The data is not weather data.");
            System.exit(1);
        }
        WeatherData data = (WeatherData) value;
        switch (column) {
            case 2:
                label.setText(Math.round(data.temperature) + "°C");
                break;
            case 4:
                label.setText("Precip here");
                break;
            case 1:
                label.setIcon(new ImageIcon(Icons.getSizedWidthIcon(data.weather, 30)));
                break;
            case 3:
                label.setIcon(new ImageIcon(Icons.getSizedWidthIcon(IconType.RAINDROP, 30)));
                break;
        }
        cache[column][row] = label;
        return label;
    }
}
