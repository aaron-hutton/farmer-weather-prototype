package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import uk.ac.cam.cl.interaction_design.group19.app.api.HourlyData;
import uk.ac.cam.cl.interaction_design.group19.app.api.WeatherData;

public class HourlyTable extends JPanel {
    
    public HourlyTable(List<HourlyData> data) {
        
        JTable table = new JTable(new WeatherTableModel(data));
        table.setDefaultRenderer(WeatherData.class, new HourlyWeatherRenderer());
        
        JScrollPane scroller = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        this.add(scroller);
    }
}
