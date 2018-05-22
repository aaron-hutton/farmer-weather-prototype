package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import uk.ac.cam.cl.interaction_design.group19.app.MainWindow;
import uk.ac.cam.cl.interaction_design.group19.app.util.Updatable;
import uk.ac.cam.cl.interaction_design.group19.app.util.WeatherData;

/**
 * A panel containing the table instance for displaying the hourly data
 */
public class HourlyTable extends JPanel {
    
    private WeatherTableModel model;
    private JTable table;
    private WeatherCustomRenderer renderer;
    
    public HourlyTable(List<WeatherData> data) {
    
        model = new WeatherTableModel(data);
        table = new JTable(model);
    
        // Specify the column widths
        table.getColumnModel().getColumn(0).setMaxWidth(MainWindow.SCREEN_WIDTH/6);
        table.getColumnModel().getColumn(1).setMaxWidth(MainWindow.SCREEN_WIDTH/8);
        table.getColumnModel().getColumn(2).setMaxWidth(MainWindow.SCREEN_WIDTH/4+10);
        table.getColumnModel().getColumn(3).setMaxWidth(MainWindow.SCREEN_WIDTH/9);
        // Let final column resize
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    
        renderer = new HourlyWeatherRenderer(data.size());
        table.setDefaultRenderer(WeatherData.class, renderer);
    
        this.add(WeeklyTable.setupTableAndBundle(table, MainWindow.SCREEN_HEIGHT - 180), BorderLayout.CENTER);
    }
    
    public void updateTable(List<WeatherData> data) {
        model.updateData(data);
        renderer.updateSize(data.size());
        table.repaint();
    }
}
