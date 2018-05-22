package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import uk.ac.cam.cl.interaction_design.group19.app.MainWindow;
import uk.ac.cam.cl.interaction_design.group19.app.util.Updatable;
import uk.ac.cam.cl.interaction_design.group19.app.util.WeatherData;

public class HourlyTable extends JPanel {
    
    private WeatherTableModel model;
    private JTable table;
    private WeatherCustomRenderer renderer;
    
    public HourlyTable(List<WeatherData> data) {
    
        model = new WeatherTableModel(data);
        table = new JTable(model);
    
        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(1).setMaxWidth(40);
        table.getColumnModel().getColumn(2).setMaxWidth(90);
        table.getColumnModel().getColumn(3).setMaxWidth(30);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);
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
