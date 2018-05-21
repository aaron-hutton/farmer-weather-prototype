package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import uk.ac.cam.cl.interaction_design.group19.app.MainWindow;
import uk.ac.cam.cl.interaction_design.group19.app.api.WeatherData;
import uk.ac.cam.cl.interaction_design.group19.app.api.WeatherData;

public class HourlyTable extends JPanel {
    
    public HourlyTable(List<WeatherData> data) {
    
        JTable table = new JTable(new WeatherTableModel(data));
    
        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(1).setMaxWidth(40);
        table.getColumnModel().getColumn(2).setMaxWidth(50);
        table.getColumnModel().getColumn(3).setMaxWidth(30);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    
        table.setDefaultRenderer(WeatherData.class, new HourlyWeatherRenderer(data.size()));
    
        this.add(WeeklyTable.setupTableAndBundle(table, MainWindow.SCREEN_HEIGHT - 180), BorderLayout.CENTER);
    }
}
